package com.sim.chongwukongjing.ui.fragment.machune;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.sim.chongwukongjing.R;
import com.sim.chongwukongjing.ui.Base.BaseFragment;
import com.sim.chongwukongjing.ui.Main.MyApplication;
import com.sim.chongwukongjing.ui.bean.ControlResult;
import com.sim.chongwukongjing.ui.bean.DvcInfoResult;
import com.sim.chongwukongjing.ui.bean.MessageDecInfo;
import com.sim.chongwukongjing.ui.bean.MessageWrap;
import com.sim.chongwukongjing.ui.http.HttpApi;
import com.sim.chongwukongjing.ui.http.RetrofitClient;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.goldze.mvvmhabit.utils.ToastUtils;
import okhttp3.FormBody;
import pl.droidsonroids.gif.GifImageView;

import static com.sim.chongwukongjing.ui.utils.Md5Util.signMD5;

public class MachinePic extends BaseFragment {

    private boolean kaiguan = true;

    @BindView(R.id.imageView7)
    ImageView imageView9;

    @BindView(R.id.GifImageView)
    GifImageView gifImageView;

    @Override
    protected View getBaseView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.machine_pic, container, false);
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initDate(View view) {

    }

    @Override
    protected void initSet(View view) {

    }

    @Override
    protected void initDisplayData(View view) {

    }

    @Override
    @OnClick({R.id.imageView7})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageView7:

                if (kaiguan){
                    imageView9.setImageResource(R.drawable.guanji);
                    gifImageView.setVisibility(View.GONE);
                    contro_0(0);
                    kaiguan = false;
                }else {
                    imageView9.setImageResource(R.drawable.ciguang);
                    gifImageView.setVisibility(View.VISIBLE);
                    contro_0(1);
                    kaiguan = true;
                }

                break;
            default:break;
        }
    }

    @Override
    protected boolean isRegEvent() {
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onGetStickyEvent(MessageDecInfo message) {
        Log.e("zbs", "onReceiveMsg: " + message.toString());
        DvcInfoResult.DataBean dat = message.getResult().getData();
        if (dat.get_$0() == 0){
            kaiguan = false;
        }else {
            kaiguan = true;
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onGetStickyEvent2(MessageWrap message) {
        Log.e("zbs", "onReceiveMsg: " + message.toString());

        Map<String, Integer> map = new LinkedHashMap<String, Integer>();
        map = message.getMap();
        if (map.get("0") == 0){
            imageView9.setImageResource(R.drawable.guanji);
            gifImageView.setVisibility(View.GONE);
            kaiguan = false;
        }else {
            imageView9.setImageResource(R.drawable.guanji);
            gifImageView.setVisibility(View.VISIBLE);
            kaiguan = true;
        }

        //４　氛围灯
        if (map.get("4") == 1){

            imageView9.setImageResource(R.drawable.ruoguang);
        }else if (map.get("4") == 2){

            imageView9.setImageResource(R.drawable.ciguang);
        }else if (map.get("4") == 3){

            imageView9.setImageResource(R.drawable.qiangguang);
        }if (map.get("4") == 0){

            imageView9.setImageResource(R.drawable.guanji);
        }

        //３　风力
        if (map.get("3") == 1){
            gifImageView.setImageResource(R.drawable.mansu_machene_x_b);
        }else if (map.get("3") == 2){
            gifImageView.setImageResource(R.drawable.zhongsu_x_b);
        }else if (map.get("3") == 3){
            gifImageView.setImageResource(R.drawable.kuaisu_x_b);
        }

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
}
