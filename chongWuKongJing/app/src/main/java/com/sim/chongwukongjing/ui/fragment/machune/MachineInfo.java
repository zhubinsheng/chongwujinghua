package com.sim.chongwukongjing.ui.fragment.machune;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sim.chongwukongjing.R;
import com.sim.chongwukongjing.ui.Base.BaseFragment;
import com.sim.chongwukongjing.ui.bean.DvcInfoResult;
import com.sim.chongwukongjing.ui.bean.MessageDecInfo;
import com.sim.chongwukongjing.ui.bean.MessageWrap;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Map;

import butterknife.BindView;

public class MachineInfo extends BaseFragment {

    @BindView(R.id.feng1)
    TextView feng1;

    @BindView(R.id.shijian1)
    TextView shijian1;

    @BindView(R.id.liangdu1)
    TextView liangdu1;

    @BindView(R.id.moshi1)
    TextView moshi1;

    @BindView(R.id.yetiyuliang1)
    TextView yetiyuliang1;






    @Override
    protected View getBaseView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.machine_info_rel, container, false);
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

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onGetStickyEvent(MessageDecInfo message) {
        Log.e("zbs", "onReceiveMsg: " + message.toString());
       DvcInfoResult.DataBean dat  = message.getResult().getData();



        // 01最弱,02中,03最强
        if (dat.get_$3() == 1 ){
            feng1.setText("低风挡");
        }else if (dat.get_$3() == 2 ){
            feng1.setText("中风挡");
        }else if (dat.get_$3() == 3 ){
            feng1.setText("高风挡");
        }

        // 0关闭，01最弱,02中,03最强
        if (dat.get_$4() == 1){
            liangdu1.setText("低亮度");
        }else if (dat.get_$4() == 2 ){
            liangdu1.setText("中亮度");
        }else if (dat.get_$4() == 3 ){
            liangdu1.setText("高亮度");
        }else if (dat.get_$4() == 0 ){
            liangdu1.setText("关机");
        }


        if (dat.get_$1() ==  0){
            moshi1.setText("连续运转模式");
        }else if (dat.get_$1() ==  1){
            moshi1.setText("智能模式");
        }

       float zyx =  dat.get_$6()/1000 ;
        yetiyuliang1.setText("液体剩余量"+(int)zyx+"%");
    }








    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveMsg(MessageWrap message) {
        Log.e("zbs", "onReceiveMsg: " + message.toString());
        if ("all".equals(message.message)){
            Map<String, Integer> map = message.getMap();
            // 01最弱,02中,03最强
            if (map.get("3").equals(1)){
                feng1.setText("低风挡");
            }else if (map.get("3").equals(2)){
                feng1.setText("中风挡");
            }else if (map.get("3").equals(3)){
                feng1.setText("高风挡");
            }

            // 0关闭，01最弱,02中,03最强
            if (map.get("4").equals(1)){
                liangdu1.setText("低亮度");
            }else if (map.get("4").equals(2)){
                liangdu1.setText("中亮度");
            }else if (map.get("4").equals(3)){
                liangdu1.setText("高亮度");
            }else if (map.get("4").equals(0)){
                liangdu1.setText("关机");
            }


            if (map.get("1").equals(0)){
                moshi1.setText("连续运转模式");
            }else if (map.get("1").equals(1)){
                moshi1.setText("智能模式");
            }

            float zyx = map.get("6")/1000 ;
            yetiyuliang1.setText("液体剩余量"+(int)zyx+"%");

        }
    }
}
