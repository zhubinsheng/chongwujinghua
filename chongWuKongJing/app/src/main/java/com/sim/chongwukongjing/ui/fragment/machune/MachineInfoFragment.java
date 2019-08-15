package com.sim.chongwukongjing.ui.fragment.machune;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sim.chongwukongjing.R;
import com.sim.chongwukongjing.ui.Base.BaseFragment;
import com.sim.chongwukongjing.ui.Main.MyApplication;
import com.sim.chongwukongjing.ui.bean.WeatherResult;
import com.sim.chongwukongjing.ui.http.HttpApi;
import com.sim.chongwukongjing.ui.http.RetrofitClient;

import java.util.HashMap;

import butterknife.BindView;
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
}
