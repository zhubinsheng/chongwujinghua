package com.sim.chongwukongjing.ui.Activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.sim.chongwukongjing.R;
import com.sim.chongwukongjing.ui.Base.BaseActivity;

/**
 * @author Administrator
 */
public class InterfaceActivity extends BaseActivity {

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_interface;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initSet() {
    }

    @Override
    protected void initData() {
        handler.sendEmptyMessageDelayed(0,2000);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            getHome();
            super.handleMessage(msg);
        }
    };

    public void getHome(){
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
        finish();
    }
}
