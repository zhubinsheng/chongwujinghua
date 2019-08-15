package com.sim.chongwukongjing.ui.Main;

import com.mob.MobSDK;
import com.sim.chongwukongjing.ui.bean.LoginResult;

import me.goldze.mvvmhabit.base.BaseApplication;

/**
 * @author binshengzhu
 */
public class MyApplication extends BaseApplication {

    private String did;
    private LoginResult loginResult;
    private static MyApplication instance;

    public static MyApplication getInstance() {
        return instance;
    }

    public static void setInstance(MyApplication instance) {
        MyApplication.instance = instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //initOpenInstall();
        MobSDK.init(this);
        instance = this;
    }

    public LoginResult getLoginResult() {
        return loginResult;
    }

    public void setLoginResult(LoginResult loginResult) {
        this.loginResult = loginResult;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }
}
