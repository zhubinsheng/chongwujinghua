package com.sim.chongwukongjing.ui.Activity;

import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.sim.chongwukongjing.R;
import com.sim.chongwukongjing.ui.Base.BaseActivity;

import butterknife.BindView;

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
        group_list_item_gegngaishebeimignc.showNewTip(true);
        group_list_item_gegngaishebeimignc.setRedDotPosition(QMUICommonListItemView.REDDOT_POSITION_RIGHT);
        group_list_item_gegngaishebeimignc.showRedDot(true);
        group_list_item_gegngaishebeimignc.setImageDrawable(getResources().getDrawable(R.drawable.qmui_icon_checkbox_checked));

        mShouhoufuwuListItemGender.setText("售后维修");
        mShouhoufuwuListItemGender.setImageDrawable(getResources().getDrawable(R.drawable.qmui_list_item_bg_with_border_bottom_inset_left));

        group_list_item_genggaimima.setText("更改密码");
        group_list_item_genggaimima.showNewTip(true);
        group_list_item_genggaimima.setImageDrawable(getResources().getDrawable(R.drawable.qmui_popup_arrow_up));

        group_list_item_changjianwenti.setText("常见问题");
        group_list_item_changjianwenti.showNewTip(true);
        group_list_item_changjianwenti.setImageDrawable(getResources().getDrawable(R.drawable.qmui_popup_arrow_up));

        group_list_item_richangweihushouce.setText("日常维护手册");
        group_list_item_richangweihushouce.showNewTip(true);
        group_list_item_richangweihushouce.setImageDrawable(getResources().getDrawable(R.drawable.qmui_popup_arrow_up));
    }

    @Override
    protected void initData() {

    }
}
