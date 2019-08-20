package com.sim.chongwukongjing.ui.Activity;

import android.view.View;

import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.sim.chongwukongjing.R;
import com.sim.chongwukongjing.ui.Base.BaseActivity;
import com.sim.chongwukongjing.ui.utils.SharedPreferencesUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Administrator
 */
public class UserInfoActivity extends BaseActivity {

    @BindView(R.id.group_list_item_gegngaishebeimignc)
    QMUICommonListItemView group_list_item_gegngaishebeimignc;

    @BindView(R.id.group_list_item_shouhoufuwu)
    QMUICommonListItemView mShouhoufuwuListItemGender;

    @BindView(R.id.group_list_item_genggaimima)
    QMUICommonListItemView group_list_item_genggaimima;

    @BindView(R.id.group_list_item_changjianwenti)
    QMUICommonListItemView group_list_item_changjianwenti;

    @BindView(R.id.group_list_item_richangweihushouce)
    QMUICommonListItemView group_list_item_richangweihushouce;


    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_userinfo_set;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initSet() {
        group_list_item_gegngaishebeimignc.setText("更改设备名称");
        //group_list_item_gegngaishebeimignc.showNewTip(true);
        group_list_item_gegngaishebeimignc.setRedDotPosition(QMUICommonListItemView.REDDOT_POSITION_RIGHT);
        group_list_item_gegngaishebeimignc.showRedDot(true);
        group_list_item_gegngaishebeimignc.setImageDrawable(getResources().getDrawable(R.drawable.diqiu));

        mShouhoufuwuListItemGender.setText("售后维修");
        mShouhoufuwuListItemGender.showNewTip(true);
        mShouhoufuwuListItemGender.setImageDrawable(getResources().getDrawable(R.drawable.qmui_popup_arrow_up));
        mShouhoufuwuListItemGender.setImageDrawable(getResources().getDrawable(R.drawable.shoho));

        group_list_item_genggaimima.setText("更改密码");
        //group_list_item_genggaimima.showNewTip(true);
        group_list_item_genggaimima.setImageDrawable(getResources().getDrawable(R.drawable.qmui_popup_arrow_up));
        group_list_item_genggaimima.setImageDrawable(getResources().getDrawable(R.drawable.suo));

        group_list_item_changjianwenti.setText("常见问题");
        //group_list_item_changjianwenti.showNewTip(true);
        group_list_item_changjianwenti.setImageDrawable(getResources().getDrawable(R.drawable.qmui_popup_arrow_up));
        group_list_item_changjianwenti.setImageDrawable(getResources().getDrawable(R.drawable.changjianwent));

        group_list_item_richangweihushouce.setText("日常维护手册");
        //group_list_item_richangweihushouce.showNewTip(true);
        group_list_item_richangweihushouce.setImageDrawable(getResources().getDrawable(R.drawable.qmui_popup_arrow_up));
        group_list_item_richangweihushouce.setImageDrawable(getResources().getDrawable(R.drawable.richangweihu));
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.textView3,R.id.textView4})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView3:
                startActivity(MyEquipmentAcitivity.class);
                /* Intent intent=new Intent(this,MyEquipmentAcitivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);*/

                finish();
                break;
            case R.id.textView4:
                startActivity(LoginActivity.class);
                SharedPreferencesUtil.deleteUser(getApplicationContext());
                finish();
                break;
            default:
                break;
        }
    }
}
