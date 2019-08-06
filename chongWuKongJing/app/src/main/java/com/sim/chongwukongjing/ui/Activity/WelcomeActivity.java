package com.sim.chongwukongjing.ui.Activity;

import android.os.Bundle;

import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.sim.chongwukongjing.R;
import com.sim.chongwukongjing.ui.Base.BaseActivity;

/**
 * @author binshengzhu
 */
public class WelcomeActivity extends BaseActivity {

    @Override
    protected int getLayoutRes() {
        return 0;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initSet() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_login_or_regist);
        // 沉浸式状态栏
        QMUIStatusBarHelper.translucent(this);
    }
}
