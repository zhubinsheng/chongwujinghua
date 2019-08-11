package com.sim.chongwukongjing.ui.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.sim.chongwukongjing.R;
import com.sim.chongwukongjing.ui.Base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author binshengzhu
 */
public class WelcomeActivity extends BaseActivity {

    @BindView(R.id.textView3)
    TextView textView3;

    @BindView(R.id.textView4)
    TextView textView4;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_select_login_or_regist;
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

    @OnClick({R.id.textView3,R.id.textView4})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView3:
                startActivity(RegisterActivity.class);
                break;
            case R.id.textView4:
                startActivity(RegisterActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 沉浸式状态栏
        //QMUIStatusBarHelper.translucent(this);
    }
}
