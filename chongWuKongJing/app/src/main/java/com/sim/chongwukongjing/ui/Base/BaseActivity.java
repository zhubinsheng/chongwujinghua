package com.sim.chongwukongjing.ui.Base;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.sim.chongwukongjing.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author binshengzhu
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected Unbinder unbinder;
    protected QMUITipDialog tipD;//声明一个QMUITipDialog对象
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

    /**
     * 初始化设置
     */
    protected abstract void initSet();

    /**
     * 初始化数据
     */
    protected abstract void initData();

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
        initData();//初始化数据
        initSet();//设置控件属性
        //registerObserver();//注册登录观察者
        //isOnLine();//注册在线状态观察者
    }

    protected void showLoadingDialog(String msg) {

        if (tipD != null) {
            tipD.dismiss();
        }
        if (isFinishing()) {
            return;
        }
        tipD = new QMUITipDialog.Builder(this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord(msg)
                .create();
        tipD.show();
    }
}
