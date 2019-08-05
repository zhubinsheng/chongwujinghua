package com.sim.chongwukongjing;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * @author binshengzhu
 */
public class FindEquipmentActivity extends BaseActivity {

    protected Unbinder unbinder;

    @BindView(R.id.groupListView)
    QMUIGroupListView qmuiGroupListView;
    @BindView(R.id.topbar)
    QMUITopBar qmuiTopBar;


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_finding_equipment;
    }

    @Override
    protected void initView() {
        qmuiTopBar.setTitle("添加设备");

        QMUICommonListItemView normalItem = qmuiGroupListView.createItemView("Item 1");
        normalItem.setOrientation(QMUICommonListItemView.VERTICAL); //默认文字在左边

        QMUICommonListItemView itemWithDetail = qmuiGroupListView.createItemView("Item 2");
        itemWithDetail.setDetailText("在右方的详细信息");//默认文字在左边   描述文字在右边

        QMUICommonListItemView itemWithDetailBelow = qmuiGroupListView.createItemView("Item 3");
        itemWithDetailBelow.setOrientation(QMUICommonListItemView.VERTICAL);
        itemWithDetailBelow.setDetailText("在标题下方的详细信息");//默认文字在左边   描述文字在标题下边

        QMUICommonListItemView itemWithChevron = qmuiGroupListView.createItemView("Item 4");
        itemWithChevron.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);//默认文字在左边   右侧更多按钮
    }




}
