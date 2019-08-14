package com.sim.chongwukongjing.ui.fragment.machune;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.graphics.Path;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sim.chongwukongjing.R;
import com.sim.chongwukongjing.ui.Base.BaseFragment;
import com.sim.chongwukongjing.ui.bean.LoginParam;
import com.sim.chongwukongjing.ui.bean.LoginResult;
import com.sim.chongwukongjing.ui.http.HttpApi;
import com.sim.chongwukongjing.ui.http.RetrofitClient;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.goldze.mvvmhabit.utils.ToastUtils;
import okhttp3.FormBody;

/**
 * @author Administrator 定时界面
 */
public class MachineStateTimingFragment extends BaseFragment {

    @BindView(R.id.kaishi)
    TextView kaishi;

    @BindView(R.id.jieshu)
    TextView jieshu;


    @Override
    protected View getBaseView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.dingshi_fragment, container, false);
    }

    @Override
    protected void initView(View view) {
//绘制贝塞尔曲线
        Path path = new Path();
        path.moveTo(0,0);
        path.lineTo(300,300);
        path.quadTo(50,500,300,700);
        path.cubicTo(600,600,500,250,50,800);
        path.quadTo(500,0,0,0);
    }

    @Override
    protected void initDate(View view) {
        HttpApi mloginApi;
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
                });

    }

    @Override
    protected void initSet(View view) {

    }

    @Override
    protected void initDisplayData(View view) {

    }

    @Override
    @OnClick({R.id.kaishi,R.id.jieshu})
    public void onClick(View v) {
        switch (v.getId()) {
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
}
