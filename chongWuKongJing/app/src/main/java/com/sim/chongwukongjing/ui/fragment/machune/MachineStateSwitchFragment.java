package com.sim.chongwukongjing.ui.fragment.machune;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.sim.chongwukongjing.R;
import com.sim.chongwukongjing.ui.Base.BaseFragment;
import com.sim.chongwukongjing.ui.MqttService;

import butterknife.BindView;
import butterknife.OnClick;
import me.goldze.mvvmhabit.utils.ToastUtils;
import pl.droidsonroids.gif.GifImageView;

/**
 * @author Administrator
 */
public class MachineStateSwitchFragment extends BaseFragment {

    private static int MANSU = 1;
    private static int ZHONGSU = 2;
    private static int KUAISU = 3;


    @BindView(R.id.imageView2)
    ImageView imageView2;

    @BindView(R.id.GifImageView)
    GifImageView gifImageView;

    @BindView(R.id.seekBar2)
    SeekBar seekBar2;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
                case 1:gifImageView.setImageResource(R.drawable.mansu_machene_x_b); break;
                case 2:gifImageView.setImageResource(R.drawable.zhongsu_x_b);  break;
                case 3:gifImageView.setImageResource(R.drawable.kuaisu_x_b);  break;
                default:break;
            }
        }
    };

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    protected View getBaseView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.machine_state, container, false);
    }

    @Override
    protected void initView(View view) {
        MqttService.makePassword(getActivity());
    }

    @Override
    protected void initDate(View view) {

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
                        ToastUtils.showLong("已关闭机器,停止风扇");
                        break;
                    case 1:
                        gifImageView.setImageResource(R.drawable.mansu_machine_x_a);
                        handler.sendEmptyMessageDelayed(MANSU,3000);

                        ToastUtils.showLong(String.valueOf(seekBar.getProgress()));
                        break;
                    case 2:
                        gifImageView.setImageResource(R.drawable.zhongsu_x_a);
                        handler.sendEmptyMessageDelayed(ZHONGSU,1500);

                        ToastUtils.showLong(String.valueOf(seekBar.getProgress()));
                        break;
                    case 3:
                        gifImageView.setImageResource(R.drawable.kuaisu_x_a);
                        handler.sendEmptyMessageDelayed(KUAISU,1000);

                        ToastUtils.showLong(String.valueOf(seekBar.getProgress()));
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


    @OnClick({R.id.seekBar2})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.seekBar2:

                break;

            default:
                break;
        }
    }

}
