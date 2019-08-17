package com.sim.chongwukongjing.ui.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.qmuiteam.qmui.widget.QMUITopBar;
import com.sim.chongwukongjing.R;
import com.sim.chongwukongjing.ui.Base.BaseActivity;
import com.sim.chongwukongjing.ui.Main.MyApplication;
import com.sim.chongwukongjing.ui.bean.FindDeviceResult;
import com.sim.chongwukongjing.ui.http.HttpApi;
import com.sim.chongwukongjing.ui.http.RetrofitClient;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;
import io.fogcloud.sdk.easylink.api.EasylinkP2P;
import io.fogcloud.sdk.easylink.helper.EasyLinkCallBack;
import io.fogcloud.sdk.easylink.helper.EasyLinkParams;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.goldze.mvvmhabit.utils.ToastUtils;
import okhttp3.FormBody;

import static com.sim.chongwukongjing.ui.utils.Md5Util.signMD5;

/**
 * @author binshengzhu
 */
public class InputPasswordActivity extends BaseActivity {

    private static final String TAG = "InputPasswordActivity";

    private boolean succuse = false;

    private boolean ssidsuccuse = false;

    @BindView(R.id.ssid_name)
    TextView ssidName;

    @BindView(R.id.textView8)
    TextView textView8;

    @BindView(R.id.editText3)
    TextView editText3;

    @BindView(R.id.topbar)
    QMUITopBar qmuiTopBar;

    private String ssid;
    private EasylinkP2P elp2p;

    @SuppressLint("HandlerLeak")
    private Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 0x123)
            {
                if (!succuse){
                    findDevice();
                }
            }

        }
    };

    @Override
    protected int getLayoutRes() {
        return R.layout.input_password;
    }

    @Override
    protected void initView() {
        elp2p = new EasylinkP2P(this);

/*
        ConnectivityManager ctm = (ConnectivityManager) this.
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = ctm.getActiveNetworkInfo();
        String ssid2 = networkInfo.getExtraInfo();*/
    }

    @Override
    protected void initSet() {
        qmuiTopBar.setTitle("添加设备");
    }

    @Override
    protected void initData() {
        requestPermissions();
        setSsidName();

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick({R.id.textView8,R.id.ssid_name})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ssid_name:
                //startActivity(MyEquipmentAcitivity.class);
                setSsidName();
            case R.id.textView8:
                //startActivity(MyEquipmentAcitivity.class);
                //finish();
                //发送数据包(包含ssid和password)给设备，连续发10s，再停止3s，再继续发，如此反复
                if (
                        "点击停止".contentEquals(textView8.getText()) && !succuse
                ){
                    elp2p.stopEasyLink(new EasyLinkCallBack() {
                        @Override
                        public void onSuccess(int code, String message) {
                            ToastUtils.showLong("配网已停止");
                        }

                        @Override
                        public void onFailure(int code, String message) {
                            ToastUtils.showLong("配网停止失败，建议重启应用");
                        }
                    });
                    succuse = false;
                    textView8.setText("配网");
                    textView8.setBackgroundColor(Color.parseColor("#87CEEB"));
                }else {

                    if (!ssidsuccuse){
                        ToastUtils.showLong("请先连接无线网");
                        return;
                    }
                    textView8.setBackgroundColor(getColor(R.color.colorAccent));
                    textView8.setText("点击停止");
                    //new SVProgressHUD(this).showWithStatus( "这是提示");


                    EasyLinkParams easylinkPara = new EasyLinkParams();
                    easylinkPara.ssid = ssid;
                    easylinkPara.password = editText3.getText().toString();
                    easylinkPara.runSecond = 60000;
                    easylinkPara.sleeptime = 20;
                    elp2p.startEasyLink(easylinkPara, new EasyLinkCallBack() {
                        @Override
                        public void onSuccess(int code, String message) {
                            //ToastUtils.showShort("机器配网成功!");
                            Log.d("zbs","机器配网成功!");
                            //findDevice();
                            //使用定时器,每隔200毫秒让handler发送一个空信息
                            new Timer().schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    myHandler.sendEmptyMessage(0x123);
                                }
                            }, 0,1000);
                            //startActivity(MyEquipmentAcitivity.class);
                            //finish();
                        }
                        @Override
                        public void onFailure(int code, String message) {
                            Log.d(TAG, code + message);
                        }
                    });
                }

                break;

            default:
                break;
        }
    }

    private void setSsidName() {
        ssid = getWIFISSID(this);
        if ("<unknown ssid>".equals(ssid)||"unknown id".equals(ssid)){
            ToastUtils.showLong("请先连接无线网");
            ssidName.setText("连接无线网后请点此刷新");
        }else {
            ssidsuccuse = true;
            ssidName.setText(ssid);
        }
    }


    /**
     * 获取SSID
     * @param activity 上下文
     * @return  WIFI 的SSID
     */
    public String getWIFISSID(Activity activity) {
        String ssid="unknown id";

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O||Build.VERSION.SDK_INT==Build.VERSION_CODES.P) {

            WifiManager mWifiManager = (WifiManager) activity.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

            assert mWifiManager != null;
            WifiInfo info = mWifiManager.getConnectionInfo();

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                return info.getSSID();
            } else {
                return info.getSSID().replace("\"", "");
            }
        } else if (Build.VERSION.SDK_INT==Build.VERSION_CODES.O_MR1){

            ConnectivityManager connManager = (ConnectivityManager) activity.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            assert connManager != null;
            NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
            if (networkInfo.isConnected()) {
                if (networkInfo.getExtraInfo()!=null){
                    return networkInfo.getExtraInfo().replace("\"","");
                }
            }
        }
        return ssid;
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
                        Log.d(TAG, permission.name + " is granted.");
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时。还会提示请求权限的对话框
                        Log.d(TAG, permission.name + " is denied. More info should be provided.");
                    } else {
                        // 用户拒绝了该权限，而且选中『不再询问』
                        Log.d(TAG, permission.name + " is denied.");
                    }
                });


    }

    @SuppressLint("CheckResult")
    private void findDevice() {
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
                .add("type", "5678")
                .add("token", MyApplication.getInstance().getLoginResult().getData().getToken())
                .build();

        Observable<FindDeviceResult> observable = mloginApi.findDevice(body);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FindDeviceResult>() {
                    @Override
                    public void accept(FindDeviceResult baseInfo) throws Exception {
                        if ("10000".equals(baseInfo.getCode())){
                            ToastUtils.showShort(baseInfo.getMsg());
                            if (baseInfo.getMsg().equals("配网成功！")){
                                Log.d("zbs","成功!");
                                succuse = true;
                            }
                            if (baseInfo.getCode().equals("10000")){
                                Log.d("zbs","成功2!");
                                succuse = true;
                            }
                            startActivity(MyEquipmentAcitivity.class);
                            finish();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ToastUtils.showShort("配对失败，请稍后重试");
                    }
                });
    }
}
