package com.sim.chongwukongjing.ui.fragment.machune;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.sim.chongwukongjing.R;
import com.sim.chongwukongjing.ui.Base.BaseFragment;

import butterknife.BindView;

/**
 * @author Administrator
 */
public class MachineInfoFragment extends BaseFragment {

    @BindView(R.id.groupListView)
    QMUICommonListItemView groupListView;

    @Override
    protected View getBaseView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.machine_info, container, false);
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initDate(View view) {

    }

    @Override
    protected void initSet(View view) {
        groupListView.setText("客服");
        groupListView.showNewTip(true);
        groupListView.setRedDotPosition(QMUICommonListItemView.REDDOT_POSITION_RIGHT);
        groupListView.showRedDot(true);
        groupListView.setImageDrawable(getResources().getDrawable(R.drawable.qmui_icon_checkbox_checked));
    }

    @Override
    protected void initDisplayData(View view) {

    }
}
