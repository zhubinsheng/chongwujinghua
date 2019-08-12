package com.sim.chongwukongjing.ui.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;

import com.sim.chongwukongjing.R;
import com.sim.chongwukongjing.ui.Base.BaseActivity;
import com.sim.chongwukongjing.ui.Main.MyApplication;
import com.sim.chongwukongjing.ui.bean.LoginResult;
import com.sim.chongwukongjing.ui.http.HttpApi;
import com.sim.chongwukongjing.ui.http.RetrofitClient;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.goldze.mvvmhabit.utils.ToastUtils;
import okhttp3.FormBody;

import static com.sim.chongwukongjing.ui.utils.Md5.signMD5;

/**
 * @author Administrator
 */
public class InterfaceActivity extends BaseActivity {

    private String username;
    private String password;
    private boolean aBoolean;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_interface;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initSet() {
        SharedPreferences sharedPreferences= this.getSharedPreferences("config", Context.MODE_PRIVATE);
        if (sharedPreferences != null){
             aBoolean = true;
             username =sharedPreferences.getString("username", "");
             password =sharedPreferences.getString("password", "");
             login();
        }
    }

    @Override
    protected void initData() {
        handler.sendEmptyMessageDelayed(0,2000);
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (!aBoolean){
                getHome();
            }
            super.handleMessage(msg);
        }
    };

    public void getHome(){
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
        finish();
    }

    @SuppressLint("CheckResult")
    private void login() {
        HttpApi mloginApi;
        mloginApi = RetrofitClient.create(HttpApi.class);
        String motime = String.valueOf(System.currentTimeMillis());

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("motime",motime);
        hashMap.put("smstype","REG");
        //hashMap.put("phone",phone.getText().toString());

        String androidID = Settings.System.getString(this.getContentResolver(), Settings.System.ANDROID_ID);
        String sign = signMD5("interlnx&aY4N!bAAds",hashMap);

        FormBody body = new FormBody.Builder()
                .add("appid", "1288")
                .add("motime",  motime)
                .add("sign", "1234567890")
                .add("phone", username)
                .add("mac", androidID)
                .add("passwd",password)
                .build();

        Observable<LoginResult> observable = mloginApi.login(body);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LoginResult>() {
                    @Override
                    public void accept(LoginResult baseInfo) throws Exception {
                        if ("10000".equals(baseInfo.getCode())){
                            ToastUtils.showShort(baseInfo.getMsg());
                            MyApplication.getInstance().setLoginResult(baseInfo);
                            startActivity(AddMachineActivity.class);
                            finish();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ToastUtils.showShort("自动登录失败，请稍后重试");
                        startActivity(LoginActivity.class);
                        finish();
                    }
                });
    }
}
