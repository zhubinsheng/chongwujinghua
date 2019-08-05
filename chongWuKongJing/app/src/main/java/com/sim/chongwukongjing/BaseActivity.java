package com.sim.chongwukongjing;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author binshengzhu
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected Unbinder unbinder;

    /**
     * 获取当前布局文件资源id
     *
     * @return layoutResId
     */
    protected abstract int getLayoutRes();

    /**
     * 初始化控件
     */
    protected abstract void initView();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_base_layout);
        if (getLayoutRes() != 0) {
            setContentView(View.inflate(this, getLayoutRes(), null));
        }
        //初始化方法
        init();
        ButterKnife.bind(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();

    }

    /**
     * 初始化操作
     */
    private void init() {
        ButterKnife.bind(this);
        //initBar();
        initView();//实例化控件
        //initData();//初始化数据
        //initSet();//设置控件属性
        //registerObserver();//注册登录观察者
        //isOnLine();//注册在线状态观察者
    }
}
