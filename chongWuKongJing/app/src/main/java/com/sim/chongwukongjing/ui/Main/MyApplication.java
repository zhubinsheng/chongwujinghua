package com.sim.chongwukongjing.ui.Main;

import com.mob.MobSDK;
import com.sim.chongwukongjing.ui.Utils;

import me.goldze.mvvmhabit.base.BaseApplication;

/**
 * @author binshengzhu
 */
public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        //initOpenInstall();
        MobSDK.init(this);
    }
}