package com.sim.chongwukongjing.ui.fragment.machune;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sim.chongwukongjing.R;
import com.sim.chongwukongjing.ui.Base.BaseFragment;
import com.sim.chongwukongjing.ui.bean.MessageWrap;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

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

    @Override
    protected void initSet(View view) {

    }

    @Override
    protected void initDisplayData(View view) {

    }

    @Override
    @OnClick({R.id.kaishi,R.id.jieshu,R.id.fabButton,R.id.lianxu,R.id.jianxie,R.id.yici,R.id.meitian})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fabButton:
            if (moshi=false){
                Map map = new HashMap();
                map.put(1,0);
                Gson gson = new Gson();
                ControUtil.dvcinfo(gson.toJson(map));
            }else{
                Map map = new HashMap();
                map.put(1,1);
                Gson gson = new Gson();
                ControUtil.dvcinfo(gson.toJson(map));
            }
            break;


            case R.id.lianxu: lianxu.setBackgroundColor(Color.parseColor("#87CEEB"));
                jianxie.setBackgroundColor(Color.parseColor("#D4D4D4"));
                moshi = false;
                break;
            case R.id.jianxie:jianxie.setBackgroundColor(Color.parseColor("#87CEEB"));
                lianxu.setBackgroundColor(Color.parseColor("#D4D4D4"));
                moshi = true;
                break;
            case R.id.yici:yici.setBackgroundColor(Color.parseColor("#87CEEB"));
                meitian.setBackgroundColor(Color.parseColor("#D4D4D4"));
                break;
            case R.id.meitian:meitian.setBackgroundColor(Color.parseColor("#87CEEB"));
                yici.setBackgroundColor(Color.parseColor("#D4D4D4"));
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

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onGetStickyEvent2(MessageWrap message) {
        Log.e("zbs", "onReceiveMsg: " + message.toString());

        Map<String, Integer> map = new LinkedHashMap<String, Integer>();
        map = message.getMap();
        if (map.get("1") == 1){
            jianxie:jianxie.setBackgroundColor(Color.parseColor("#87CEEB"));
            lianxu.setBackgroundColor(Color.parseColor("#D4D4D4"));
            moshi = true;
        }else if (map.get("1") == 0){
            lianxu.setBackgroundColor(Color.parseColor("#87CEEB"));
            jianxie.setBackgroundColor(Color.parseColor("#D4D4D4"));
            moshi = false;
        }

    }
}
