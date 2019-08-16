package com.sim.chongwukongjing.ui.Base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.sim.chongwukongjing.ui.bean.MessageWrap;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;

/**
 * 基础的Fragment类
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    private View view;

    //数据
    protected QMUITipDialog tipD;//声明一个QMUITipDialog对象

    protected boolean isViewInitiated;
    protected boolean isVisibleToUser;
    protected boolean isDataInitiated;
    private boolean isLoadMore;

    //////////////////////////////////////////抽象方法///////////////////////////////////////////////
    /**
     * 返回当前引入的根布局
     * @return View
     * @param inflater
     * @param container
     */
    protected abstract View getBaseView(LayoutInflater inflater, ViewGroup container);

    /**
     * 初始化控件
     * @param view 根布局
     */
    protected abstract void initView(View view);

    /**
     * 初始化数据
     * @param view 根布局
     */
    protected abstract void initDate(View view);

    /**
     * 初始化设置
     * @param view 根布局
     */
    protected abstract void initSet(View view);

    /**
     * 初始化显示数据/操作
     * @param view 根布局
     */
    protected abstract void initDisplayData(View view);

    ////////////////////////////////////////初始化策略&&生命周期管理///////////////////////////////////
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = getBaseView(inflater,container);
        //初始化静态数据
        initStaticData();
        init(view);
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewInitiated = true;
        prepareFetchData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        prepareFetchData();
    }

    public void fetchData(){

    }

    public boolean prepareFetchData() {
        return prepareFetchData(false);
    }

    public boolean prepareFetchData(boolean forceUpdate) {
        if (isVisibleToUser && isViewInitiated && (!isDataInitiated || forceUpdate)) {
            fetchData();
            isDataInitiated = true;
            return true;
        }
        return false;
    }

    /**
     * 初始化操作
     * @param view
     */
    protected void init(View view){
        ButterKnife.bind(this,view);
        initView(view);
        initDate(view);
        initSet(view);
    }

    protected void requestGetData(boolean isCache){

    }

    /**
     * 初始化静态数据
     */
    protected void initStaticData(){

    }

    @Override
    public void onStart() {
        initDisplayData(view);
        super.onStart();
        if(isRegEvent()){
            EventBus.getDefault().register(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveMsg(MessageWrap message) {
        Log.e("zbs", "onReceiveMsg: " + message.toString());
    }

    @Override
    public void onStop() {
        super.onStop();
        if(isRegEvent()){
            EventBus.getDefault().unregister(this);
        }
    }

    protected boolean isRegEvent(){
        return false;
    }

    ///////////////////////////////////////////工具方法///////////////////////////////////////////////
    /**
     * 批量更改控件显示状态
     * @param view
     */
    protected void showOrConceal(View... view){
        for (View v:view) {
            v.setVisibility(v.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
        }
    }

    /**
     * 显示控件
     * @param view
     * @return
     */
    protected View showView(View view){
        view.setVisibility(View.VISIBLE);
        return view;
    }

    /**
     * 隐藏控件
     * @param view
     * @return
     */
    protected View concealView(View view){
        view.setVisibility(View.GONE);
        return view;
    }

    /**
     * Activity跳转方法
     * @param cls
     * @return 返回Activity对象
     */
    protected Activity goActivity(Context context, Class cls){
        Intent intent = new Intent(context,cls);
        startActivity(intent);
        return (Activity)context;
    }

    /**
     * Activity跳转方法
     * @param cls 目标位置
     * @param str 字符串参数
     * @return
     */
    protected Activity goActivity(Context context, Class cls, String str){
        Intent intent = new Intent(context,cls);
        intent.putExtra("str",str);
        startActivity(intent);
        return (Activity)context;
    }

        /**
         * Activity跳转方法
         * @param cls 目标位置
         * @param inter 整型参数
         * @return
         */
    protected Activity goActivity(Context context, Class cls, int inter){
        Intent intent = new Intent(context,cls);
        intent.putExtra("inter",inter);
        startActivity(intent);
        return (Activity)context;
    }

    /**
     * Activity跳转方法
     * @param cls 目标位置
     * @param obj 序列化之后的对象
     * @return 获取:Object obj = getIntent().getParcelableExtra("obj");
     */
    protected Activity goActivity(Context context, Class cls, Object obj){
        Intent intent = new Intent(context,cls);
        intent.putExtra("obj",(Parcelable)obj);
        startActivity(intent);
        return (Activity)context;
    }

    /**
     * log
     * @param msg
     */
    protected void log(String msg){
        Log.d("tag",msg);
    }

    /**
     * 显示一条toast
     * @param msg
     */
    protected  void showToastMsg(Context context, String msg){
        Toast.makeText(context,msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 给控件对象批量设置点击监听
     * @param view
     */
    protected void setOnclickListener(View... view){
        if (view != null && view.length != 0){
            for (View v:view) {
                v.setOnClickListener(this);
            }
        }
    }

    /**
     * 根据控件id批量设置点击监听(引用布局)
     * @param ids
     */
    protected void setOnclickListener(View view, int... ids){
        if (ids.length != 0 && view != null){
            for (int id:ids) {
                view.findViewById(id).setOnClickListener(this);
            }
        }
    }

    /**
     * 显示并延时关闭dialog
     * @param dialog 要关闭的dialog
     */
    protected void showThenDialog(final QMUITipDialog dialog){
        dialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        },1000);
    }

    //////////////////////////////////////////监听事件处理////////////////////////////////////////////
    @Override
    public void onClick(View v) {
        //点击监听
    }

    @Override
    public void onRefresh() {
        //上拉加载
    }

    @Override
    public void onResume() {
        super.onResume();

        //MobclickAgent.onResume(getContext());
    }

    @Override
    public void onPause() {
        super.onPause();

        //MobclickAgent.onPause(getContext());
    }


    protected void showLoadingDialog(String msg){

        if(tipD != null){
            tipD.dismiss();
        }
        tipD = new QMUITipDialog.Builder(getContext())
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord(msg)
                .create();
        tipD.show();
    }

    protected void hideLoadingDialog(){
        if(tipD != null){
            tipD.dismiss();
        }
    }
}
