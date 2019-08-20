package com.sim.chongwukongjing.ui.fragment.machune;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sim.chongwukongjing.R;
import com.sim.chongwukongjing.ui.Base.BaseFragment;
import com.sim.chongwukongjing.ui.Main.MyApplication;
import com.sim.chongwukongjing.ui.bean.ControlResult;
import com.sim.chongwukongjing.ui.bean.DvcInfoResult;
import com.sim.chongwukongjing.ui.bean.MessageDecInfo;
import com.sim.chongwukongjing.ui.bean.MessageEvent;
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

/**
 * @author Administrator  风力调整
 */
public class MachineStateSwitchFragment extends BaseFragment {

    private static int MANSU = 1;
    private static int ZHONGSU = 2;
    private static int KUAISU = 3;

    private boolean Chould = true;

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

    @BindView(R.id.imageView2)
    ImageView imageView2;

    @BindView(R.id.imageView7)
    ImageView imageView7;

    @BindView(R.id.GifImageView)
    GifImageView gifImageView;

    @BindView(R.id.seekBar2)
    SeekBar seekBar2;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
                case 1:gifImageView.setImageResource(R.drawable.mansu_machene_x_b);
                    Map map = new HashMap();
                    map.put(3,01);
                    Gson gson = new Gson();
                    ControUtil.dvcinfo(gson.toJson(map));
                break;
                case 2:gifImageView.setImageResource(R.drawable.zhongsu_x_b);
                    Map map1 = new HashMap();
                    map1.put(3,02);
                    Gson gson2 = new Gson();
                    ControUtil.dvcinfo(gson2.toJson(map1));
                break;
                case 3:gifImageView.setImageResource(R.drawable.kuaisu_x_b);
                    Map map2 = new HashMap();
                    map2.put(3,03);
                    Gson gson3 = new Gson();
                    ControUtil.dvcinfo(gson3.toJson(map2));
                break;
                default:break;
            }
        }
    };

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    protected View getBaseView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.machine_state, container, false);
    }

    @Override
    protected void initView(View view) {
        //MyMqttService.MqttService.makePassword(getActivity());
        seekBar2.setEnabled(false);
    }

    @Override
    protected void initDate(View view) {

    }

    @Override
    protected void initSet(View view) {

        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                /*if (!Chould){
                    return;
                }*/
                Log.d("zbsg", String.valueOf(seekBar.getProgress()));
                switch (seekBar.getProgress()) {
                    case 0:
                        //gifImageView.setVisibility(View.GONE);
                        //ToastUtils.showLong("已关闭机器,停止风扇");
                        //contro_0(0);
                        setProgress(1);
                        //Chould = false;
                        seekBar2.setEnabled(false);
                        handler.sendEmptyMessageDelayed(MANSU,0);
                        break;
                    case 1:
                        //gifImageView.setImageResource(R.drawable.mansu_machine_x_a);
                        //Chould = false;
                        seekBar2.setEnabled(false);
                        handler.sendEmptyMessageDelayed(MANSU,0);

                        //ToastUtils.showLong(String.valueOf(seekBar.getProgress()));
                        break;
                    case 2:
                        //gifImageView.setImageResource(R.drawable.zhongsu_x_a);
                        //Chould = false;
                        seekBar2.setEnabled(false);
                        handler.sendEmptyMessageDelayed(ZHONGSU,0);

                        //ToastUtils.showLong(String.valueOf(seekBar.getProgress()));
                        break;
                    case 3:
                        //gifImageView.setImageResource(R.drawable.kuaisu_x_a);
                        //Chould = false;
                        seekBar2.setEnabled(false);
                        handler.sendEmptyMessageDelayed(KUAISU,0);

                        //ToastUtils.showLong(String.valueOf(seekBar.getProgress()));
                        break;


                    default:

                        break;
                }

            }
        });
    }

    @Override
    protected void initDisplayData(View view) {

    }

    /**
     * 设置当前进度
     *
     * @param progress
     */
    public void setProgress(int progress) {
        progress = progress >= 0 ? progress : 0;
        progress = progress <= 3 ? progress : 3;
        seekBar2.setProgress(progress);
    }


    @OnClick({R.id.seekBar2})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.seekBar2:

                break;

            default:
                break;
        }
    }

    @Override
    protected boolean isRegEvent() {
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveMsg(MessageWrap message) {
        /*if (!message.message.equals("0")){
            return;
        }*/
        Log.e("zbs", "onReceiveMsg: " + message.toString());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveMsg2(MessageEvent message) {
        diqu.setText(message.getResult().getData().getAreanm());
        wendu.setText(message.getResult().getData().getTemp()+"°");
        tianqi.setText(message.getResult().getData().getWeath());
        shidu.setText("湿度："+message.getResult().getData().getTemp());
        fengdu.setText(message.getResult().getData().getWind());
        zuidigao.setText(message.getResult().getData().getTemp2());
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

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onGetStickyEvent2(MessageWrap message) {
        Log.e("zbs", "onReceiveMsg: " + message.toString());

        Map<String, Integer> map = new LinkedHashMap<String, Integer>();
        map = message.getMap();
        if (map.get("3") == 1){
            setProgress(1);
            gifImageView.setImageResource(R.drawable.mansu_machene_x_b);
            seekBar2.setEnabled(true);
            //Chould = true;
        }else if (map.get("3") == 2){
            setProgress(2);
            gifImageView.setImageResource(R.drawable.zhongsu_x_b);
            seekBar2.setEnabled(true);
            //Chould = true;
        }else if (map.get("3") == 3){
            setProgress(3);
            gifImageView.setImageResource(R.drawable.kuaisu_x_b);
            seekBar2.setEnabled(true);
           // Chould = true;
        }


        if (map.get("4") == 1){
            imageView7.setImageResource(R.drawable.ruoguang);
        }else if (map.get("4") == 2){
            imageView7.setImageResource(R.drawable.ciguang);
        }else if (map.get("4") == 3){
            imageView7.setImageResource(R.drawable.qiangguang);
        }else if (map.get("4") == 0){
            imageView7.setImageResource(R.drawable.wuguang);
        }

        //imageView7.setImageResource(R.drawable.wugaung)


        if (map.get("0") == 1){
            seekBar2.setEnabled(true);
        }else {
            seekBar2.setEnabled(false);
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onGetStickyEvent(MessageDecInfo message) {

        DvcInfoResult.DataBean dat = message.getResult().getData();

        if (dat.get_$0() == 0){
            seekBar2.setEnabled(false);
        }else {
            seekBar2.setEnabled(true);
        }

        int ii  = dat.get_$4();
        if (ii == 1){
            setProgress(1);
            imageView7.setImageResource(R.drawable.ruoguang);
        }else if (ii == 2){
            setProgress(2);
            imageView7.setImageResource(R.drawable.ciguang);
        }else if (ii == 3){
            setProgress(3);
            imageView7.setImageResource(R.drawable.qiangguang);
        }else if (ii ==0){
            setProgress(0);
            imageView7.setImageResource(R.drawable.wuguang);
        }


        int i  = dat.get_$3();
        if (i == 1){
            setProgress(1);
            gifImageView.setImageResource(R.drawable.mansu_machene_x_b);
        }else if (i == 2){
            setProgress(2);
            gifImageView.setImageResource(R.drawable.zhongsu_x_b);
        }else if (i == 3){
            setProgress(3);
            gifImageView.setImageResource(R.drawable.kuaisu_x_b);
        }

    }
}
