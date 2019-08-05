package com.sim.chongwukongjing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import me.jessyan.autosize.internal.CustomAdapt;

/**
 * @author binshengzhu
 */
public class MainActivity extends BaseActivity  {

    @Override
    protected int getLayoutRes() {
        return 0;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 沉浸式状态栏
        QMUIStatusBarHelper.translucent(this);
    }
}
