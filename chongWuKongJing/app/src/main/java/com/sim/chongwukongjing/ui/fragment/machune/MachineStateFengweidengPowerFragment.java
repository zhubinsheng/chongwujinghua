package com.sim.chongwukongjing.ui.fragment.machune;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sim.chongwukongjing.R;
import com.sim.chongwukongjing.ui.Base.BaseFragment;
import com.sim.chongwukongjing.ui.bean.MessageEvent;
import com.sim.chongwukongjing.ui.bean.MessageWrap;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

/**
 * @author binshengzhu
 */
public class MachineStateFengweidengPowerFragment extends BaseFragment {

    @BindView(R.id.diqu)
    TextView diqu;
    @BindView(R.id.wendu)
    TextView wendu;
    @BindView(R.id.tianqi)
    TextView tianqi;
    @BindView(R.id.zuidigao)
    TextView zuidigao;
    @BindView(R.id.shidu)
    TextView shidu;
    @BindView(R.id.fengdu)
    TextView fengdu;

    @Override
    protected View getBaseView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.machine_fengweideng, container, false);
    }

    @Override
    protected void initView(View view) {

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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveMsg2(MessageEvent message) {
        Log.e("zbs", "onReceiveMsg: " + message.toString());
        diqu.setText(message.getResult().getData().getAreanm());
        wendu.setText(message.getResult().getData().getTemp());
        tianqi.setText(message.getResult().getData().getWeath());
        shidu.setText(message.getResult().getData().getPm25());
        fengdu.setText(message.getResult().getData().getWind());
    }
}
