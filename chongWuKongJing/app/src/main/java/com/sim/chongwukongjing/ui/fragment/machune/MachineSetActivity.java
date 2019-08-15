package com.sim.chongwukongjing.ui.fragment.machune;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.sim.chongwukongjing.R;
import com.sim.chongwukongjing.ui.Base.BaseActivity;
import com.sim.chongwukongjing.ui.Main.MyApplication;
import com.sim.chongwukongjing.ui.bean.DvcInfoResult;
import com.sim.chongwukongjing.ui.bean.WeatherResult;
import com.sim.chongwukongjing.ui.bean.tianqiResult;
import com.sim.chongwukongjing.ui.http.HttpApi;
import com.sim.chongwukongjing.ui.http.RetrofitClient;
import com.sim.chongwukongjing.ui.utils.LocationUtils;
import com.sim.chongwukongjing.ui.wigdet.FragAdapter;
import com.sim.chongwukongjing.ui.wigdet.NoScrollViewPager;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.goldze.mvvmhabit.utils.StringUtils;
import me.goldze.mvvmhabit.utils.ToastUtils;
import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.sim.chongwukongjing.ui.utils.Md5Util.signMD5;


/**
 * 主页
 * @author Administrator
 */

public class MachineSetActivity extends BaseActivity {

    @BindView(R.id.rl_bottom)
    RelativeLayout rl_bottom;
    //功能
    @BindView(R.id.main_view_page)
    NoScrollViewPager mainViewPage;

    @BindView(R.id.main_tab_segment)
    QMUITabSegment mainTabSegment;

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    //数据
    private List<Fragment> fragmentList;

    private static final int REQUEST_PERMISSION = 0;

    private TextView unReadMsg;
    private String pushData;

    private Context mcontext;

    private String address;


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        requestPermissions();
        //QMUIStatusBarHelper.translucent(this,getResources().getColor(R.color.transparent));
        //关闭其他所有ａｃｔｉｖｉｔｙ
        //ActivityUtils.finishAllActivitiesExceptNewest();
        topbar.setTitle("My Air Machiene");
    }


    @Override
    protected void initData() {

        Location location = LocationUtils.getInstance( this ).showLocation();

        if (location != null) {
            address = "纬度：" + location.getLatitude() + "经度：" + location.getLongitude();
            Log.d("zbs",address);
        }
        //getWeather();
        //findLocation(address);
        /*Map<String,String> param =new HashMap<String,String>();
        param.put("mac", "123456");
        param.put("phone", "123");
        param.put("passwd","123124");
        String  sign =
        Md5.signMD5(API.appkey,param);
        System.out.println(sign);*/


        String infoString = getIntent().getStringExtra("did");

        if (!StringUtils.isEmpty(infoString)){
            MyApplication.getInstance().setDid(infoString);
            dvcinfo(infoString);
        }else {
            ToastUtils.showLong("机器编号不准为空");
            finish();
        }

    }

    @SuppressLint("CheckResult")
    private void requestPermissions() {
        RxPermissions rxPermission = new RxPermissions(this);
        rxPermission
                .requestEach(Manifest.permission.INTERNET,
                        Manifest.permission.ACCESS_WIFI_STATE,
                        //Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.ACCESS_NETWORK_STATE,
                        //Manifest.permission.READ_EXTERNAL_STORAGE,
                        //Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CHANGE_WIFI_MULTICAST_STATE,
                        Manifest.permission.CHANGE_WIFI_STATE,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                .subscribe(permission -> {
                    if (permission.granted) {
                        // 用户已经同意该权限
                        //Log.d(TAG, permission.name + " is granted.");
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时。还会提示请求权限的对话框
                        //Log.d(TAG, permission.name + " is denied. More info should be provided.");
                    } else {
                        // 用户拒绝了该权限，而且选中『不再询问』
                        //Log.d(TAG, permission.name + " is denied.");
                    }
                });


    }

    @Override
    protected void initSet() {
        addTabAndViewPage();
        //设置viewPage的缓存页数
        mainViewPage.setOffscreenPageLimit(5);
        //设置adapter
        mainViewPage.setAdapter(new FragAdapter(getSupportFragmentManager(), fragmentList));
        //设置字体大小
       // mainTabSegment.setTabTextSize(ConvertUtils.dp2px(12));
        //设置 Tab 选中状态下的颜色
        mainTabSegment.setDefaultSelectedColor(getResources().getColor(R.color.qmui_config_color_50_blue));
        //关联viewPage
        mainTabSegment.setupWithViewPager(mainViewPage, false);
        String[] permission = {Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO};
        //PermissionUtils.requestPermissions(this, REQUEST_PERMISSION, permission, this);


        boolean isOpenNotification = NotificationManagerCompat.from(this).areNotificationsEnabled();
        if (!isOpenNotification) {
            new QMUIDialog.MessageDialogBuilder(this)
                    .setTitle("提示")
                    .setMessage("为了更好的软件体验请打开手机中的通知权限！")
                    .addAction(0, "确定", QMUIDialogAction.ACTION_PROP_NEGATIVE, new QMUIDialogAction.ActionListener() {
                        @Override
                        public void onClick(QMUIDialog dialog, int index) {
                            Intent localIntent = new Intent();
                            localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            if (Build.VERSION.SDK_INT >= 9) {
                                localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                                localIntent.setData(Uri.fromParts("package", MachineSetActivity.this.getPackageName(), null));
                            } else if (Build.VERSION.SDK_INT <= 8) {
                                localIntent.setAction(Intent.ACTION_VIEW);

                                localIntent.setClassName("com.android.settings",
                                        "com.android.settings.InstalledAppDetails");

                                localIntent.putExtra("com.android.settings.ApplicationPkgName",
                                        MachineSetActivity.this.getPackageName());
                            }
                            startActivity(localIntent);
                            dialog.dismiss();
                        }
                    })
                    .show();
        }
    }


    //初始化ViewPage和tab
    private void addTabAndViewPage() {

        //添加关联的fragment
        fragmentList = new ArrayList<>();
       /* fragmentList.add(new IndexPageFragment());

        if (StringUtils.toInt(ConfigModel.getInitData().getOpen_video_chat()) == 1) {
            if (SaveData.getInstance().getUserInfo().getSex() == 1) {
                fragmentList.add(new VideoPlayFragment());
            } else {
                fragmentList.add(new VideoPushFragment());
            }
        }

        fragmentList.add(new VideoSmallFragment());
        fragmentList.add(new DynamicFragment());
        fragmentList.add(new MsgFragment());
        */
        fragmentList.add(new MachineYeLiangFragment());
        fragmentList.add(new MachineStateSwitchFragment());
        fragmentList.add(new MachineInfoFragment());
        fragmentList.add(new MachineStateTimingFragment());
        fragmentList.add(new MachineStateWindPowerFragment());
        //添加tab
        mainTabSegment.addTab(
                new QMUITabSegment.Tab(
                        getResources().getDrawable(R.drawable.main_screen_drawable_mine_unselected),
                        getResources().getDrawable(R.drawable.main_screen_drawable_mine_selected),
                        getString(R.string.yeliang),
                        false,
                        false));

        mainTabSegment.addTab(
                new QMUITabSegment.Tab(
                        getResources().getDrawable(R.drawable.main_screen_drawable_mine_unselected),
                        getResources().getDrawable(R.drawable.main_screen_drawable_mine_selected),
                        getString(R.string.fengli),
                        false,
                        false));

        mainTabSegment.addTab(
                new QMUITabSegment.Tab(
                        getResources().getDrawable(R.drawable.main_screen_drawable_mine_unselected),
                        getResources().getDrawable(R.drawable.main_screen_drawable_mine_selected),
                        getString(R.string.kaiguan),
                        false,
                        false));

        mainTabSegment.addTab(
                new QMUITabSegment.Tab(
                        getResources().getDrawable(R.drawable.main_screen_drawable_mine_unselected),
                        getResources().getDrawable(R.drawable.main_screen_drawable_mine_selected),
                        getString(R.string.dingshi),
                        false,
                        false));

        mainTabSegment.addTab(
                new QMUITabSegment.Tab(
                        getResources().getDrawable(R.drawable.main_screen_drawable_mine_unselected),
                        getResources().getDrawable(R.drawable.main_screen_drawable_mine_selected),
                        getString(R.string.fengweidneg),
                        false,
                        false));
    }


    @Override
    protected void onResume() {
        super.onResume();


        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


    }

    @Override
    protected void onPause() {
        super.onPause();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    public void findLocation(String address) {
        String baseUrl = "http://api.map.baidu.com";
        String ak = "2RWAxllFj7sSXPGXt6xoy0PBwfcMqZdY";
        float loc = (float) 118.29357;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .build();
        HttpApi apiService = retrofit.create(HttpApi.class);


        Call<tianqiResult> call = apiService.findLocation(address,ak);

        call.enqueue(new Callback<com.sim.chongwukongjing.ui.bean.tianqiResult>() {
            @Override
            public void onResponse(Call<tianqiResult> call, Response<tianqiResult> response) {

            }

            @Override
            public void onFailure(Call<tianqiResult> call, Throwable t) {

            }

        });
    }

    @SuppressLint("CheckResult")
    private void getWeather() {
        HttpApi mloginApi;
        mloginApi = RetrofitClient.create(HttpApi.class);
        String motime = String.valueOf(System.currentTimeMillis());

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("motime",motime);
        hashMap.put("smstype","REG");

        String sign = signMD5("interlnx&aY4N!bAAds",hashMap);

        FormBody body ;
        body = new FormBody.Builder()
                    .add("appid", "1288")
                    .add("motime",  motime)
                    .add("sign", "1234567890")
                    .add("city","南京市建邺区")
                    .add("token", MyApplication.getInstance().getLoginResult().getData().getToken())
                    .build();


        Observable<WeatherResult> observable = mloginApi.getWeather(body);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WeatherResult>() {
                    @Override
                    public void accept(WeatherResult baseInfo) throws Exception {
                        if ("10000".equals(baseInfo.getCode())){
                            ToastUtils.showShort(baseInfo.getMsg());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ToastUtils.showShort("网络延迟过高，请稍后重试");
                    }
                });

    }


    @SuppressLint("CheckResult")
    private void dvcinfo(String did) {
        HttpApi mloginApi;
        mloginApi = RetrofitClient.create(HttpApi.class);
        String motime = String.valueOf(System.currentTimeMillis());

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("motime",motime);


        String androidID = Settings.System.getString(this.getContentResolver(), Settings.System.ANDROID_ID);
        String sign = signMD5("interlnx&aY4N!bAAds",hashMap);

        FormBody body = new FormBody.Builder()
                .add("appid", "1288")
                .add("motime",  motime)
                .add("sign", "1234567890")
                .add("did", did)
                .add("token", MyApplication.getInstance().getLoginResult().getData().getToken())
                .build();

        Observable<DvcInfoResult> observable = mloginApi.dvcinfo(body);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DvcInfoResult>() {
                    @Override
                    public void accept(DvcInfoResult baseInfo) throws Exception {
                        if ("10000".equals(baseInfo.getCode())){
                            ToastUtils.showShort(baseInfo.getMsg());

                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ToastUtils.showShort("获取机器信息失败，请稍后重试");
                    }
                });
    }

}
