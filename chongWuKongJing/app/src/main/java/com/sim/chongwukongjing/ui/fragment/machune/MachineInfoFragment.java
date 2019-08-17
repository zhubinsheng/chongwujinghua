package com.sim.chongwukongjing.ui.fragment.machune;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sim.chongwukongjing.R;
import com.sim.chongwukongjing.ui.Base.BaseFragment;
import com.sim.chongwukongjing.ui.Main.MyApplication;
import com.sim.chongwukongjing.ui.bean.ControlResult;
import com.sim.chongwukongjing.ui.bean.MessageWrap;
import com.sim.chongwukongjing.ui.bean.WeatherResult;
import com.sim.chongwukongjing.ui.http.HttpApi;
import com.sim.chongwukongjing.ui.http.RetrofitClient;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.goldze.mvvmhabit.utils.ToastUtils;
import okhttp3.FormBody;

import static com.sim.chongwukongjing.ui.utils.Md5Util.signMD5;

/**
 * @author Administrator
 */
public class MachineInfoFragment extends BaseFragment {

    @BindView(R.id.diqu)
    TextView diqu;
    @BindView(R.id.wendu)
    TextView wendu;
    @BindView(R.id.tianqi)
    TextView tianqi;
    @BindView(R.id.zuidigao)
    TextView zuidigao;
    @BindView(R.id.shidu)
    TextView shidu;
    @BindView(R.id.fengdu)
    TextView fengdu;
    @BindView(R.id.imageView9)
    ImageView imageView9;

    private boolean kaiguan = true;
    //@BindView(R.id.groupListView)
    //QMUICommonListItemView groupListView;

    @Override
    protected View getBaseView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.machine_info, container, false);
    }

    @Override
    protected void initView(View view) {

    }




    @Override
    protected void initDate(View view) {
        getWeather();
    }

    @Override
    protected void initSet(View view) {
        /*groupListView.setText("客服");
        groupListView.showNewTip(true);
        groupListView.setRedDotPosition(QMUICommonListItemView.REDDOT_POSITION_RIGHT);
        groupListView.showRedDot(true);
        groupListView.setImageDrawable(getResources().getDrawable(R.drawable.qmui_icon_checkbox_checked));*/
    }

    @Override
    protected void initDisplayData(View view) {

    }

    @Override
    @OnClick({R.id.diqu,R.id.imageView9})
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.imageView9:

                if (kaiguan){
                    contro_0(0);
                    kaiguan = false;
                }else {
                    contro_0(1);
                    kaiguan = true;
                }

                break;
            case R.id.diqu:  break;
            default:break;
        }
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
                            diqu.setText(baseInfo.getData().getAreanm());
                            wendu.setText(baseInfo.getData().getTemp());
                            tianqi.setText(baseInfo.getData().getWeath());
                            //zuidigao.setText();
                            shidu.setText(baseInfo.getData().getPm25());
                            fengdu.setText(baseInfo.getData().getWind());

                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ToastUtils.showShort("网络延迟过高，请稍后重试");
                    }
                });

    }


    public void contro_0(int i){
        Map map = new HashMap();
        map.put(0,i);
        Gson gson = new Gson();
        String jsonObject = gson.toJson(map);
        dvcinfo(MyApplication.getInstance().getDid(),jsonObject);
    }

    @SuppressLint("CheckResult")
    private void dvcinfo(String did , String jsonObject) {
        HttpApi mloginApi;
        mloginApi = RetrofitClient.create(HttpApi.class);
        String motime = String.valueOf(System.currentTimeMillis());

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("motime",motime);


        String sign = signMD5("interlnx&aY4N!bAAds",hashMap);

        FormBody body = new FormBody.Builder()
                .add("appid", "1288")
                .add("motime",  motime)
                .add("sign", "1234567890")
                .add("did", did)
                .add("cmd",jsonObject)
                .add("token", MyApplication.getInstance().getLoginResult().getData().getToken())
                .build();

        Observable<ControlResult> observable = mloginApi.control(body);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ControlResult>() {
                    @Override
                    public void accept(ControlResult baseInfo) throws Exception {
                        if ("10000".equals(baseInfo.getCode())){
                            ToastUtils.showShort(baseInfo.getMsg());

                        }else {
                            ToastUtils.showShort(baseInfo.getMsg());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ToastUtils.showShort("远程操纵失败，请稍后重试");
                    }
                });
    }

    @Override
    protected boolean isRegEvent() {
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveMsg(MessageWrap message) {
        Log.e("zbs", "onReceiveMsg: " + message.toString());
    }

}
