package com.sim.chongwukongjing.ui.Main;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.sim.chongwukongjing.R;
import com.sim.chongwukongjing.ui.Base.BaseActivity;
import com.sim.chongwukongjing.ui.fragment.home.UserPageFragment;
import com.sim.chongwukongjing.ui.wigdet.FragAdapter;
import com.sim.chongwukongjing.ui.wigdet.NoScrollViewPager;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * 主页
 */

public class MainActivity extends BaseActivity {

    @BindView(R.id.rl_bottom)
    RelativeLayout rl_bottom;
    //功能
    @BindView(R.id.main_view_page)
    NoScrollViewPager mainViewPage;

    @BindView(R.id.main_tab_segment)
    QMUITabSegment mainTabSegment;

    //数据
    private List<Fragment> fragmentList;

    private static final int REQUEST_PERMISSION = 0;

    private TextView unReadMsg;
    private String pushData;



    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        //QMUIStatusBarHelper.translucent(this,getResources().getColor(R.color.transparent));
    }


    @Override
    protected void initData() {
    }



    @Override
    protected void initSet() {
        addTabAndViewPage();
        //设置viewPage的缓存页数
        mainViewPage.setOffscreenPageLimit(2);
        //设置adapter
        mainViewPage.setAdapter(new FragAdapter(getSupportFragmentManager(), fragmentList));
        //设置字体大小
       // mainTabSegment.setTabTextSize(ConvertUtils.dp2px(12));
        //设置 Tab 选中状态下的颜色
        mainTabSegment.setDefaultSelectedColor(getResources().getColor(R.color.qmui_config_color_50_blue));
        //关联viewPage
        mainTabSegment.setupWithViewPager(mainViewPage, false);
        String[] permission = {Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO};
        //PermissionUtils.requestPermissions(this, REQUEST_PERMISSION, permission, this);


        boolean isOpenNotification = NotificationManagerCompat.from(this).areNotificationsEnabled();
        if (!isOpenNotification) {
            new QMUIDialog.MessageDialogBuilder(this)
                    .setTitle("提示")
                    .setMessage("为了更好的软件体验请打开手机中的通知权限！")
                    .addAction(0, "确定", QMUIDialogAction.ACTION_PROP_NEGATIVE, new QMUIDialogAction.ActionListener() {
                        @Override
                        public void onClick(QMUIDialog dialog, int index) {
                            Intent localIntent = new Intent();
                            localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            if (Build.VERSION.SDK_INT >= 9) {
                                localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                                localIntent.setData(Uri.fromParts("package", MainActivity.this.getPackageName(), null));
                            } else if (Build.VERSION.SDK_INT <= 8) {
                                localIntent.setAction(Intent.ACTION_VIEW);

                                localIntent.setClassName("com.android.settings",
                                        "com.android.settings.InstalledAppDetails");

                                localIntent.putExtra("com.android.settings.ApplicationPkgName",
                                        MainActivity.this.getPackageName());
                            }
                            startActivity(localIntent);
                            dialog.dismiss();
                        }
                    })
                    .show();
        }
    }


    //初始化ViewPage和tab
    private void addTabAndViewPage() {

        //添加关联的fragment
        fragmentList = new ArrayList<>();
       /* fragmentList.add(new IndexPageFragment());

        if (StringUtils.toInt(ConfigModel.getInitData().getOpen_video_chat()) == 1) {
            if (SaveData.getInstance().getUserInfo().getSex() == 1) {
                fragmentList.add(new VideoPlayFragment());
            } else {
                fragmentList.add(new VideoPushFragment());
            }
        }

        fragmentList.add(new VideoSmallFragment());
        fragmentList.add(new DynamicFragment());
        fragmentList.add(new MsgFragment());
        */
        fragmentList.add(new UserPageFragment());
        fragmentList.add(new UserPageFragment());
        //添加tab
        mainTabSegment.addTab(
                new QMUITabSegment.Tab(
                        getResources().getDrawable(R.drawable.main_screen_drawable_mine_unselected),
                        getResources().getDrawable(R.drawable.main_screen_drawable_mine_selected),
                        getString(R.string.index),
                        false,
                        true));

        /*if (StringUtils.toInt(ConfigModel.getInitData().getOpen_video_chat()) == 1) {
            mainTabSegment.addTab(
                    new QMUITabSegment.Tab(
                            getResources().getDrawable(R.drawable.main_screen_drawable_ranking_unselected),
                            getResources().getDrawable(R.drawable.main_screen_drawable_ranking_selected),
                            null,
                            false,
                            true));
        }*/



        mainTabSegment.addTab(
                new QMUITabSegment.Tab(
                        getResources().getDrawable(R.drawable.main_screen_drawable_mine_unselected),
                        getResources().getDrawable(R.drawable.main_screen_drawable_mine_selected),
                        getString(R.string.me),
                        false,
                        false));
    }


    @Override
    protected void onResume() {
        super.onResume();


        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


    }

    @Override
    protected void onPause() {
        super.onPause();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
