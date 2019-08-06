package com.sim.chongwukongjing.ui.fragment.home;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.sim.chongwukongjing.ui.Base.BaseFragment;
import com.sim.chongwukongjing.R;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 我的
 */
public class HomePagement extends BaseFragment {
    private RelativeLayout user_page_my_user_page, user_page_money_btn;


    private Dialog radioDialog;//分成比例dialog

    //显示数据
    private CircleImageView userImg;//用户头像
    private TextView userName;//用户名
    private ImageView userIsVerify;//用户是否验证图标

    private TextView aboutNumber;//关注人数
    private TextView fansNumber;//粉丝数
    private TextView ratioNumber;//分成比例


    private TextView moneyNumber;//聊币数
    private TextView user_page_recharge_text;
    private RelativeLayout ll_video_auth;
    private RelativeLayout ll_short_video;
    private RelativeLayout ll_private_photo;
    private RelativeLayout ll_invite;
    private RelativeLayout ll_new_guide;
    private RelativeLayout ll_cooperation;
    private RelativeLayout ll_setting;
    private RelativeLayout ll_level;
    private RelativeLayout ll_buyVip;
    private RelativeLayout ll_switch_disturb;

    @BindView(R.id.group_list_item_kefu)
    QMUICommonListItemView mKefuListItemGender;

    @BindView(R.id.group_list_item_shouhoufuwu)
    QMUICommonListItemView mShouhoufuwuListItemGender;

    @BindView(R.id.group_list_item_wodewuliu)
    QMUICommonListItemView mwodewuliuListItemGender;

    @Override
    protected View getBaseView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_userinfo_set, container, false);
    }

    @Override
    protected void initView(View view) {
        //userImg = view.findViewById(R.id.userpage_img);
        //userName = view.findViewById(R.id.userpage_nickname);


    }

    @Override
    protected void initDate(View view) {

    }

    @Override
    protected void initSet(View view) {
        mKefuListItemGender.setText("客服");
        mKefuListItemGender.showNewTip(true);
        mKefuListItemGender.setRedDotPosition(QMUICommonListItemView.REDDOT_POSITION_RIGHT);
        mKefuListItemGender.showRedDot(true);
        mKefuListItemGender.setImageDrawable(getResources().getDrawable(R.drawable.qmui_icon_checkbox_checked));

        mShouhoufuwuListItemGender.setText("售后服务");
        mShouhoufuwuListItemGender.setImageDrawable(getResources().getDrawable(R.drawable.qmui_list_item_bg_with_border_bottom_inset_left));

        mwodewuliuListItemGender.setText("我的物流");
        mwodewuliuListItemGender.showNewTip(true);
        mwodewuliuListItemGender.setImageDrawable(getResources().getDrawable(R.drawable.qmui_popup_arrow_up));
    }

    @Override
    protected void initDisplayData(View view) {
    }


    //@OnClick({R.id.ll_wallet})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //case R.id.ll_wallet:
            //   break;
            default:
                break;
        }
    }



    /**
     * 刷新用户资料页面显示
     */
    private void refreshUserData() {
    }

    /**
     * 服务端请求用户数据
     */
    private void requestUserData() {

    }


    /**
     * 退出/注销方法
     */
    //private void doLogout() {
    //    LoginUtils.doLoginOut(getContext());
    //}

    @Override
    public void onResume() {
        super.onResume();
        requestUserData();//服务端请求用户数据并设置到页面
    }
}
