package com.sim.chongwukongjing.ui.Activity;

import android.annotation.SuppressLint;
import android.provider.Settings;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.qmuiteam.qmui.widget.QMUITopBar;
import com.sim.chongwukongjing.R;
import com.sim.chongwukongjing.ui.Base.BaseActivity;
import com.sim.chongwukongjing.ui.bean.SendcodeResult;
import com.sim.chongwukongjing.ui.http.HttpApi;
import com.sim.chongwukongjing.ui.http.RetrofitClient;
import com.sim.chongwukongjing.ui.utils.SharedPreferencesUtil;

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
import static com.sim.chongwukongjing.ui.utils.Md5.signMD5;

/**
 * @author Administrator
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.editText3)
    TextView editText3;
    @BindView(R.id.checkBox2)
    CheckBox checkBox2;
    @BindView(R.id.textView10)
    TextView textView10;
    @BindView(R.id.next)
    TextView next;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initSet() {
        topbar.setTitle("用户登录");
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.next,R.id.textView10})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next:
                if(!isMobile( phone.getText().toString())||phone.getText().toString().length()!=11){ ToastUtils.showShort("请输入正确的手机号码");
                    return;
                }
                if(editText3.getText()==null){
                    ToastUtils.showShort("请输入密码");
                    return;
                }
                login();
                break;
            default:
                break;
        }
    }

    @SuppressLint("CheckResult")
    private void login() {
        HttpApi mloginApi;
        mloginApi = RetrofitClient.create(HttpApi.class);
        String motime = String.valueOf(System.currentTimeMillis());

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("motime",motime);
        hashMap.put("smstype","REG");
        hashMap.put("phone",phone.getText().toString());

        String androidID = Settings.System.getString(this.getContentResolver(), Settings.System.ANDROID_ID);
        String sign = signMD5("interlnx&aY4N!bAAds",hashMap);

        FormBody body = new FormBody.Builder()
                    .add("appid", "1288")
                    .add("motime",  motime)
                    .add("sign", "1234567890")
                    .add("phone", phone.getText().toString())
                    .add("mac", androidID)
                    .add("passwd",editText3.getText().toString())
                    .build();

        Observable<SendcodeResult> observable = mloginApi.login(body);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SendcodeResult>() {
                    @Override
                    public void accept(SendcodeResult baseInfo) throws Exception {
                        if ("10000".equals(baseInfo.getCode())){
                            ToastUtils.showShort(baseInfo.getMsg());
                            if (checkBox2.isChecked()){
                                SharedPreferencesUtil.saveLoginInfo(getApplicationContext(),phone.getText().toString(),editText3.getText().toString());
                            }else {
                                SharedPreferencesUtil.deleteUser(getApplicationContext());
                            }
                            startActivity(AddMachineActivity.class);
                            finish();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ToastUtils.showShort("登录失败，请稍后重试");
                    }
                });
    }
}
