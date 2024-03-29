package com.sim.chongwukongjing.ui.Activity;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;
import com.sim.chongwukongjing.R;
import com.sim.chongwukongjing.ui.Base.BaseActivity;
import com.sim.chongwukongjing.ui.bean.ForgotpassResult;
import com.sim.chongwukongjing.ui.bean.SendcodeResult;
import com.sim.chongwukongjing.ui.http.HttpApi;
import com.sim.chongwukongjing.ui.http.RetrofitClient;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.goldze.mvvmhabit.utils.ToastUtils;
import okhttp3.FormBody;

import static com.sim.chongwukongjing.ui.utils.AndroidUtils.isMobile;
import static com.sim.chongwukongjing.ui.utils.Md5Util.signMD5;

/**
 * @author Administrator
 */
public class RegisterMiMaActivity extends BaseActivity {

    @BindView(R.id.topbar)
    QMUITopBar topBar;

    @BindView(R.id.huoquyanzhengm)
    QMUIRoundButton huoquyanzhengm;

    @BindView(R.id.next)
    TextView next;

    @BindView(R.id.phone)
    EditText phone;

    @BindView(R.id.yanzhegnma)
    EditText yanzhegnma;


    @BindView(R.id.et_register_password4)
    EditText et_register_password4;


    @BindView(R.id.et_register_password5)
    EditText et_register_password5;

    private TimeCount mTime;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_regist_mima;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initSet() {
        topBar.setTitle("重置密码");
        mTime = new TimeCount(60000,1000);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.huoquyanzhengm,R.id.next})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.huoquyanzhengm:
                if(!isMobile( phone.getText().toString())||phone.getText().toString().length()!=11){ ToastUtils.showShort("请输入正确的手机号码");
                   return;
                }
                sendcode();
                break;
            case R.id.next:
                reg();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mTime.cancel();
    }

    @SuppressLint("CheckResult")
    private void reg() {
        HttpApi mloginApi;
        mloginApi = RetrofitClient.create(HttpApi.class);
        String motime = String.valueOf(System.currentTimeMillis());

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("motime",motime);
        hashMap.put("smstype","REG");
        hashMap.put("phone",phone.getText().toString());


        String androidID = Settings.System.getString(this.getContentResolver(), Settings.System.ANDROID_ID);

        String sign = signMD5("interlnx&aY4N!bAAds",hashMap);

        String  name ;
        FormBody body ;
        if(et_register_password4.getText().toString().equals(et_register_password5.getText().toString())){
             body = new FormBody.Builder()
                    .add("appid", "1288")
                    .add("motime",  motime)
                    .add("sign", "1234567890")
                     .add("verifycode", yanzhegnma.getText().toString())
                    .add("phone", phone.getText().toString())
                    .add("passwd",et_register_password4.getText().toString())
                    .build();
        }else {
            ToastUtils.showLong("两次密码输入不一致，请核查后提交");
            return;
        }



        Observable<ForgotpassResult> observable = mloginApi.forgotpass(body);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ForgotpassResult>() {
                    @Override
                    public void accept(ForgotpassResult baseInfo) throws Exception {
                        if ("10000".equals(baseInfo.getCode())){
                            ToastUtils.showShort(baseInfo.getMsg());
                            startActivity(LoginActivity.class);
                            finish();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ToastUtils.showShort("操作失败，请稍后重试");
                    }
                });

    }

    @SuppressLint("CheckResult")
    private void sendcode() {
        HttpApi mloginApi;
        mloginApi = RetrofitClient.create(HttpApi.class);
        String motime = String.valueOf(System.currentTimeMillis());

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("motime",motime);
        hashMap.put("smstype","REG");
        hashMap.put("phone",phone.getText().toString());



        String sign = signMD5("interlnx&aY4N!bAAds",hashMap);

        FormBody body = new FormBody.Builder()
                .add("appid", "1288")
                .add("motime",  motime)
                .add("sign", "1234567890")
                .add("phone", phone.getText().toString())
                .add("smstype", "REG")
                .build();

        Observable<SendcodeResult> observable = mloginApi.sendcode(body);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SendcodeResult>() {
                    @Override
                    public void accept(SendcodeResult baseInfo) throws Exception {
                        if ("10000".equals(baseInfo.getCode())){
                            ToastUtils.showShort(baseInfo.getMsg());
                            mTime.start();
                        }else {
                            huoquyanzhengm.setText(baseInfo.getMsg());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ToastUtils.showShort("验证码发送失败，请稍后重试");
                    }
                });
    }

    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            huoquyanzhengm.setClickable(false);
            //huoquyanzhengm.setBackgroundColor(ContextCompat.getColor(RegisterActivity.this,R.color.colorAccent));
            huoquyanzhengm.setText(l/1000+"s后可重新获取验证码");
        }

        @Override
        public void onFinish() {
            huoquyanzhengm.setClickable(true);
            //huoquyanzhengm.setBackgroundColor(ContextCompat.getColor(RegisterActivity.this,R.color.colorPrimary));
            huoquyanzhengm.setText("重新获取验证码");
        }
    }
}
