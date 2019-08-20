package com.sim.chongwukongjing.ui.fragment.machune;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sim.chongwukongjing.R;
import com.sim.chongwukongjing.ui.Base.BaseFragment;
import com.sim.chongwukongjing.ui.bean.DvcInfoResult;
import com.sim.chongwukongjing.ui.bean.MessageDecInfo;
import com.sim.chongwukongjing.ui.bean.MessageEvent;
import com.sim.chongwukongjing.ui.bean.MessageWrap;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import pl.droidsonroids.gif.GifImageView;

/**
 * @author binshengzhu
 */
public class MachineStateFengweidengPowerFragment extends BaseFragment {

    private static int BULAING = 0;
    private static int CICILIANG = 1;
    private static int CILAING = 2;
    private static int ZUILIANG = 3;


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

    @BindView(R.id.seekBar2)
    SeekBar seekBar2;

    @BindView(R.id.imageView7)
    ImageView imageView7;

    @BindView(R.id.GifImageView)
    GifImageView gifImageView;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
                case 0:imageView7.setImageResource(R.drawable.wuguang);
                    Map map0 = new HashMap();
                    map0.put(4,0);
                    Gson gson0 = new Gson();
                    ControUtil.dvcinfo(gson0.toJson(map0));
                    break;
                case 1:imageView7.setImageResource(R.drawable.ruoguang);
                    Map map = new HashMap();
                    map.put(4,1);
                    Gson gson = new Gson();
                    ControUtil.dvcinfo(gson.toJson(map));
                    break;
                case 2:imageView7.setImageResource(R.drawable.ciguang);
                    Map map1 = new HashMap();
                    map1.put(4,2);
                    Gson gson2 = new Gson();
                    ControUtil.dvcinfo(gson2.toJson(map1));
                    break;
                case 3:imageView7.setImageResource(R.drawable.qiangguang);
                    Map map2 = new HashMap();
                    map2.put(4,3);
                    Gson gson3 = new Gson();
                    ControUtil.dvcinfo(gson3.toJson(map2));
                    break;
                default:break;
            }
        }
    };

    @Override
    protected View getBaseView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.machine_fengweideng, container, false);
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initDate(View view) {
        //seekBar2.setEnabled(false);
    }

    @Override
    protected void initSet(View view) {
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                switch (seekBar.getProgress()) {
                    case 0:
                        //gifImageView.setVisibility(View.GONE);
                        //ToastUtils.showLong("已关闭机器,停止风扇");
                        //contro_0(0);
                        handler.sendEmptyMessageDelayed(BULAING,0);
                        break;
                    case 1:
                        //gifImageView.setImageResource(R.drawable.mansu_machine_x_a);
                        handler.sendEmptyMessageDelayed(CICILIANG,0);

                        //ToastUtils.showLong(String.valueOf(seekBar.getProgress()));
                        break;
                    case 2:
                        //gifImageView.setImageResource(R.drawable.zhongsu_x_a);
                        handler.sendEmptyMessageDelayed(CILAING,0);

                        //ToastUtils.showLong(String.valueOf(seekBar.getProgress()));
                        break;
                    case 3:
                        //gifImageView.setImageResource(R.drawable.kuaisu_x_a);
                        handler.sendEmptyMessageDelayed(ZUILIANG,0);

                        //ToastUtils.showLong(String.valueOf(seekBar.getProgress()));
                        break;


                    default:

                        break;
                }

            }
        });
    }

    @Override
    protected void initDisplayData(View view) {

    }

    /**
     * 设置当前进度
     *
     * @param progress
     */
    public void setProgress(int progress) {
        progress = progress >= 0 ? progress : 0;
        progress = progress <= 3 ? progress : 3;
        seekBar2.setProgress(progress);
    }

    @Override
    protected boolean isRegEvent() {
        return true;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveMsg2(MessageEvent message) {
        diqu.setText(message.getResult().getData().getAreanm());
        wendu.setText(message.getResult().getData().getTemp()+"°");
        tianqi.setText(message.getResult().getData().getWeath());
        shidu.setText("湿度："+message.getResult().getData().getTemp());
        fengdu.setText(message.getResult().getData().getWind());
        zuidigao.setText(message.getResult().getData().getTemp2());
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onGetStickyEvent(MessageDecInfo message) {
        DvcInfoResult.DataBean dat = message.getResult().getData();

        int i  = dat.get_$4();
        if (i == 1){
            setProgress(1);
            imageView7.setImageResource(R.drawable.ruoguang);
        }else if (i == 2){
            setProgress(2);
            imageView7.setImageResource(R.drawable.ciguang);
        }else if (i == 3){
            setProgress(3);
            imageView7.setImageResource(R.drawable.qiangguang);
        }else if (i ==0){
            setProgress(0);
            imageView7.setImageResource(R.drawable.wuguang);
        }

        //３　风力
        if (dat.get_$3() == 1){
            gifImageView.setImageResource(R.drawable.mansu_machene_x_b);
        }else if (dat.get_$3() == 2){
            gifImageView.setImageResource(R.drawable.zhongsu_x_b);
        }else if (dat.get_$3() == 3){
            gifImageView.setImageResource(R.drawable.kuaisu_x_b);
        }

        if (dat.get_$0() == 0){
            seekBar2.setEnabled(false);
        }else {
            seekBar2.setEnabled(true);
        }


    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onGetStickyEvent2(MessageWrap message) {

        Map<String, Integer> map = new LinkedHashMap<String, Integer>();
        map = message.getMap();

        //４　氛围灯
        if (map.get("4") == 1){
            setProgress(1);
            imageView7.setImageResource(R.drawable.ruoguang);
        }else if (map.get("4") == 2){
            setProgress(2);
            imageView7.setImageResource(R.drawable.ciguang);
        }else if (map.get("4") == 3){
            setProgress(3);
            imageView7.setImageResource(R.drawable.qiangguang);
        }if (map.get("4") == 0){
            setProgress(0);
            imageView7.setImageResource(R.drawable.wuguang);
        }

        //３　风力
        if (map.get("3") == 1){
            gifImageView.setImageResource(R.drawable.mansu_machene_x_b);
        }else if (map.get("3") == 2){
            gifImageView.setImageResource(R.drawable.zhongsu_x_b);
        }else if (map.get("3") == 3){
            gifImageView.setImageResource(R.drawable.kuaisu_x_b);
        }



        if (map.get("0") == 1){
            seekBar2.setEnabled(true);
        }else {
            seekBar2.setEnabled(false);
        }

    }
}
