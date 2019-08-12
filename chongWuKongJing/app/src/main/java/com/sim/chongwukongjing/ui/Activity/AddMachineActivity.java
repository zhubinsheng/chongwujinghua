package com.sim.chongwukongjing.ui.Activity;

import android.view.View;
import android.widget.TextView;

import com.sim.chongwukongjing.R;
import com.sim.chongwukongjing.ui.Base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Administrator
 */
public class AddMachineActivity extends BaseActivity {

    @BindView(R.id.textView4)
    TextView textView4;



    @Override
    protected int getLayoutRes() {
        return R.layout.activity_adding_equipment;
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


    @OnClick({R.id.textView4})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView4:
                startActivity(FindEquipmentActivity.class);
                break;
            default:
                break;
        }
    }
}
