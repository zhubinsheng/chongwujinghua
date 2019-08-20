package com.sim.chongwukongjing.ui.fragment.machune;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;
import com.sim.chongwukongjing.R;
import com.sim.chongwukongjing.ui.Base.BaseFragment;
import com.sim.chongwukongjing.ui.Main.MyApplication;
import com.sim.chongwukongjing.ui.bean.DvcInfoResult;
import com.sim.chongwukongjing.ui.bean.MessageDecInfo;
import com.sim.chongwukongjing.ui.bean.MessageWrap;
import com.sim.chongwukongjing.ui.bean.TtinmingResult;
import com.sim.chongwukongjing.ui.http.HttpApi;
import com.sim.chongwukongjing.ui.http.RetrofitClient;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Calendar;
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

import static com.sim.chongwukongjing.ui.utils.Md5Util.signMD5;

/**
 * @author Administrator 定时界面
 */
public class MachineStateTimingFragment extends BaseFragment {

    @BindView(R.id.kaishi)
    TextView kaishi;

    @BindView(R.id.jieshu)
    TextView jieshu;

    @BindView(R.id.lianxu)
    TextView lianxu;

    @BindView(R.id.jianxie)
    TextView jianxie;

    @BindView(R.id.yici)
    TextView yici;

    @BindView(R.id.meitian)
    TextView meitian;

    @BindView(R.id.fabButton)
    Button fabButton;

    private boolean moshi = false;
    private boolean isloop = false;

    @Override
    protected View getBaseView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.dingshi_fragment, container, false);
    }

    @Override
    protected void initView(View view) {
        /*//绘制贝塞尔曲线
        Path path = new Path();
        path.moveTo(0,0);
        path.lineTo(300,300);
        path.quadTo(50,500,300,700);
        path.cubicTo(600,600,500,250,50,800);
        path.quadTo(500,0,0,0);*/
    }

    @Override
    protected void initDate(View view) {
       /* HttpApi mloginApi;
        mloginApi = RetrofitClient.create(HttpApi.class);

        LoginParam loginParam = new LoginParam();
        loginParam.setAppid("1288");

        //而OkHttp库中有一个专门来构建参数上传的RequestBody的子类，FormBody
        FormBody body = new FormBody.Builder()
                .add("appid", "1288")
                .add("motime", "23123124")
                .add("sign", "2d639e66c233d27bc430bc8a834dc76d")
                .add("mac", "2231233")
                .add("phone", "13337213721")
                .add("passwd", "123456")
                .build();

        Observable<LoginResult> observable = mloginApi.phoneregister2(body);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LoginResult>() {
                    @Override
                    public void accept(LoginResult baseInfo) throws Exception {

                        ToastUtils.showShort(baseInfo.getMsg());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ToastUtils.showShort("失败");
                    }
                });*/

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void initSet(View view) {
        jianxie.setBackground(getActivity().getDrawable(R.drawable.banbianyuanjiaohuise));
        lianxu.setBackground(getActivity().getDrawable(R.drawable.fanbianyuanjiaohuise));
        jianxie.setEnabled(false);
        lianxu.setEnabled(false);
    }

    @Override
    protected void initDisplayData(View view) {

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    @OnClick({R.id.kaishi,R.id.jieshu,R.id.fabButton,R.id.lianxu,R.id.jianxie,R.id.yici,R.id.meitian})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fabButton:
           /* if (moshi==false){
                Map map = new HashMap();
                map.put(1,0);
                Gson gson = new Gson();
                ControUtil.dvcinfo(gson.toJson(map));
            }else{
                Map map = new HashMap();
                map.put(1,1);
                Gson gson = new Gson();
                ControUtil.dvcinfo(gson.toJson(map));
            }*/
                setTiming();
            break;


            case R.id.lianxu:

                jianxie.setBackground(getActivity().getDrawable(R.drawable.banbianyuanjiaohuise));
                lianxu.setBackground(getActivity().getDrawable(R.drawable.fanbianyuanjiao));
                Map map = new HashMap();
                map.put(1,0);
                Gson gson = new Gson();
                ControUtil.dvcinfo(gson.toJson(map));
                break;
            case R.id.jianxie:jianxie.setBackground(getActivity().getDrawable(R.drawable.banbianyuanjiao));
                lianxu.setBackground(getActivity().getDrawable(R.drawable.fanbianyuanjiaohuise));
                moshi = true;
                Map map2 = new HashMap();
                map2.put(1,1);
                Gson gson2 = new Gson();
                ControUtil.dvcinfo(gson2.toJson(map2));
                break;
            case R.id.yici:meitian.setBackground(getActivity().getDrawable(R.drawable.banbianyuanjiaohuise));
                yici.setBackground(getActivity().getDrawable(R.drawable.fanbianyuanjiao));
                isloop = false;
                break;
            case R.id.meitian:yici.setBackground(getActivity().getDrawable(R.drawable.fanbianyuanjiaohuise));
                meitian.setBackground(getActivity().getDrawable(R.drawable.banbianyuanjiao));
                isloop = true;
                break;



            case R.id.kaishi:
                Calendar calendar = Calendar.getInstance();
                //小时
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                //分钟
                int minute = calendar.get(Calendar.MINUTE);
                TimePickerDialog dialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onTimeSet(android.widget.TimePicker timePicker, int hour, int minute) {
                        kaishi.setText(String.valueOf(hour)+String.valueOf(minute));
                    }
                },
                        hour,
                        minute,
                        true);
                dialog.show();


                break;
            case R.id.jieshu:
                Calendar calendar1 = Calendar.getInstance();
                //小时
                int hour1 = calendar1.get(Calendar.HOUR_OF_DAY);
                //分钟
                int minute1 = calendar1.get(Calendar.MINUTE);
                TimePickerDialog dialog1 = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onTimeSet(android.widget.TimePicker timePicker, int hour, int minute) {
                        jieshu.setText(String.valueOf(hour)+String.valueOf(minute));
                    }
                },
                        hour1,
                        minute1,
                        true);
                dialog1.show();

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
        Log.e("zbs", "onReceiveMsg: " + message.toString());
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onGetStickyEvent(MessageDecInfo message) {
        Log.e("zbs", "onReceiveMsg: " + message.toString());
        DvcInfoResult.DataBean dat = message.getResult().getData();

        int i  = dat.get_$1();
        if (i == 1){
            jianxie.setBackground(getActivity().getDrawable(R.drawable.banbianyuanjiao));
            lianxu.setBackground(getActivity().getDrawable(R.drawable.fanbianyuanjiaohuise));
            moshi = true;
        }else if (i == 0){
            jianxie.setBackground(getActivity().getDrawable(R.drawable.banbianyuanjiaohuise));
            lianxu.setBackground(getActivity().getDrawable(R.drawable.fanbianyuanjiao));
            moshi = false;
        }

        if (dat.get_$0() == 0){
            jianxie.setEnabled(false);
            lianxu.setEnabled(false);
        }else {
            jianxie.setEnabled(true);
            lianxu.setEnabled(true);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onGetStickyEvent2(MessageWrap message) {
        Log.e("zbs", "onReceiveMsg: " + message.toString());

        Map<String, Integer> map = new LinkedHashMap<String, Integer>();
        map = message.getMap();
        if (map.get("1") == 1){
            jianxie.setBackground(getActivity().getDrawable(R.drawable.banbianyuanjiao));
            lianxu.setBackground(getActivity().getDrawable(R.drawable.fanbianyuanjiaohuise));
            moshi = true;
            moshi = true;
        }else if (map.get("1") == 0){
            jianxie.setBackground(getActivity().getDrawable(R.drawable.banbianyuanjiaohuise));
            lianxu.setBackground(getActivity().getDrawable(R.drawable.fanbianyuanjiao));
            moshi = false;
        }

        if (map.get("0") == 1){
            jianxie.setEnabled(true);
            lianxu.setEnabled(true);
        }else {
            jianxie.setEnabled(false);
            lianxu.setEnabled(false);
        }

    }




    @SuppressLint("CheckResult")
    private void setTiming() {
        HttpApi mloginApi;
        mloginApi = RetrofitClient.create(HttpApi.class);
        String motime = String.valueOf(System.currentTimeMillis());

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("motime",motime);
        //hashMap.put("phone",phone.getText().toString());

        String sign = signMD5("interlnx&aY4N!bAAds",hashMap);

        Map map = new HashMap();
        map.put("start", kaishi.getText().toString());
        map.put("end",jieshu.getText().toString());
        Gson gson = new Gson();

        String mo_shi;
        if ( moshi ){
            mo_shi = "1";
        }else {
            mo_shi = "0";
        }

        String is_loop;
        if ( isloop ){
            is_loop = "1";
        }else {
            is_loop = "0";
        }


        FormBody body = new FormBody.Builder()
                .add("t1", gson.toJson(map))
                .add("isloop",is_loop)
                .add("mode",mo_shi)
                .add("appid", "1288")
                .add("motime",  motime)
                .add("sign", "1234567890")
                .add("token", MyApplication.getInstance().getLoginResult().getData().getToken())
                .add("did", MyApplication.getInstance().getDid())

                .build();

        Observable<TtinmingResult> observable = mloginApi.setTiming(body);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TtinmingResult>() {
                    @Override
                    public void accept(TtinmingResult baseInfo) throws Exception {
                        if ("10000".equals(baseInfo.getCode())){
                            ToastUtils.showShort(baseInfo.getMsg());
                            Map map2 = new HashMap();
                            map2.put(5,0);
                            Gson gson3 = new Gson();
                            ControUtil.dvcinfo(gson3.toJson(map2));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ToastUtils.showShort("定时失败，请稍后重试");
                    }
                });
    }


}
