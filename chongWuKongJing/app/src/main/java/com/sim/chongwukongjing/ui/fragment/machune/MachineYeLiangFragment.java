package com.sim.chongwukongjing.ui.fragment.machune;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;
import com.sim.chongwukongjing.R;
import com.sim.chongwukongjing.ui.Base.BaseFragment;
import com.sim.chongwukongjing.ui.bean.DvcInfoResult;
import com.sim.chongwukongjing.ui.bean.MessageDecInfo;
import com.sim.chongwukongjing.ui.bean.MessageWrap;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

/**
 * @author binshengzhu
 */
public class MachineYeLiangFragment extends BaseFragment {

    @BindView(R.id.meitian)
    QMUIRoundButton meitian;

    @BindView(R.id.textView18)
    TextView textView18;


    @Override
    protected View getBaseView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.yeti_fragment, container, false);
    }

    @Override
    protected void initView(View view) {
        meitian.setBackgroundColor(Color.parseColor("#D4D4D4"));
    }

    @Override
    protected void initDate(View view) {

    }

    @Override
    protected void initSet(View view) {

    }

    @Override
    protected void initDisplayData(View view) {

    }

    @Override
    protected boolean isRegEvent() {
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveMsg(MessageWrap message) {
        Log.e("zbs", "onReceiveMsg: " + message.toString());
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onGetStickyEvent(MessageDecInfo message) {
        Log.e("zbs", "onReceiveMsg: " + message.toString());
        DvcInfoResult.DataBean dat = message.getResult().getData();

        float zyx =  dat.get_$6()/1000 ;
        textView18.setText((int)zyx+"%");

    }
}
