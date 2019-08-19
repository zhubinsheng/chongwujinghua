package com.sim.chongwukongjing.ui.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mingle.widget.LoadingView;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.sim.chongwukongjing.R;
import com.sim.chongwukongjing.ui.Base.BaseActivity;
import com.sim.chongwukongjing.ui.Main.MyApplication;
import com.sim.chongwukongjing.ui.bean.MessageWrap;
import com.sim.chongwukongjing.ui.bean.MyList;
import com.sim.chongwukongjing.ui.bean.UnbindResult;
import com.sim.chongwukongjing.ui.fragment.machune.MachineSetActivity;
import com.sim.chongwukongjing.ui.http.HttpApi;
import com.sim.chongwukongjing.ui.http.MyMqttService;
import com.sim.chongwukongjing.ui.http.RetrofitClient;
import com.sim.chongwukongjing.ui.wigdet.MyProductlistAdapter;
import com.sim.chongwukongjing.ui.wigdet.SpacesItemDecoration;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.goldze.mvvmhabit.utils.ToastUtils;
import okhttp3.FormBody;

import static com.sim.chongwukongjing.ui.utils.Md5Util.signMD5;

/**
 * @author binshengzhu
 */
public class MyEquipmentAcitivity extends BaseActivity {


    private MyProductlistAdapter productlistAdapter;
    private List<MyList.DataBean> data;

    @BindView(R.id.ProductRecy)
    RecyclerView productRecy;

    @BindView(R.id.topbar)
    QMUITopBar qmuiTopBar;

    @BindView(R.id.textView3)
    TextView textView3;

    @BindView(R.id.textView4)
    TextView textView4;

    @BindView(R.id.loadView)
    LoadingView loadingView;



    @Override
    protected void onStart() {
        super.onStart();
        getProductlist();
    }


    @Override
    protected void onResume() {
        super.onResume();
        getProductlist();
    }
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_my_equipment;
    }

    @Override
    protected void initView() {
        qmuiTopBar.setTitle("设备列表");
        qmuiTopBar.addLeftImageButton(R.drawable.caidan,R.id.caidan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(UserInfoActivity.class);
            }
        });




    }

    @SuppressLint("WrongConstant")
    @Override
    protected void initSet() {
        //创建布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        int space = 8;
        productRecy.addItemDecoration(new SpacesItemDecoration(space));
        productRecy.setLayoutManager(layoutManager);

        if (MyApplication.getInstance().getLoginResult() == null){
            ToastUtils.showShort("请先登录");
            startActivity(LoginActivity.class);
            finish();
        }

    }

    @OnClick({R.id.textView3,R.id.textView4})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView3:
                startActivity(MachineSetActivity.class);
                break;
            case R.id.textView4:
                startActivity(AddMachineActivity.class);
                break;
            default:
                break;
        }
    }




    @Override
    protected void initData() {
        getProductlist();
    }

    @SuppressLint("CheckResult")
    private void getProductlist() {
        HttpApi mloginApi;
        mloginApi = RetrofitClient.create(HttpApi.class);
        String motime = String.valueOf(System.currentTimeMillis());

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("motime",motime);
        //hashMap.put("phone",phone.getText().toString());

        String sign = signMD5("interlnx&aY4N!bAAds",hashMap);

        FormBody body = new FormBody.Builder()
                .add("appid", "1288")
                .add("motime",  motime)
                .add("sign", "1234567890")
                .add("token", MyApplication.getInstance().getLoginResult().getData().getToken())

                .build();

        Observable<MyList> observable = mloginApi.mylist(body);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MyList>() {
                    @Override
                    public void accept(MyList baseInfo) throws Exception {
                        if ("10000".equals(baseInfo.getCode())){
                            ToastUtils.showShort(baseInfo.getMsg());
                            //View不可见，且不占据空间
                            loadingView.setVisibility(View.GONE);
                            //创建适配器
                            data = baseInfo.getData();
                            productlistAdapter = new MyProductlistAdapter(R.layout.drag_item_shanchu, data,MyEquipmentAcitivity.this);

                            /*ItemDragAndSwipeCallback itemDragAndSwipeCallback = new ItemDragAndSwipeCallback(productlistAdapter);
                            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
                            itemTouchHelper.attachToRecyclerView(productRecy);

                            // 开启滑动删除功能
                            productlistAdapter.enableSwipeItem();
                            //若不做特殊处理,可以不设置滑动或拖拽监听,其自动能实现拖拽或删除功能了
                            productlistAdapter.setOnItemSwipeListener(onItemSwipeListener);*/


                            //条目点击事件 开启侧滑以后被屏蔽了　草
                           /* productlistAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    MyList.DataBean dataBean = data.get(position);
                                    startActivity(MachineSetActivity.class);

                                }
                            });*/


                            productlistAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                                @Override
                                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                                    switch (view.getId()) {
                                        case R.id.content:
                                            MyList.DataBean dataBean = data.get(position);

                                            Intent startIntent = new Intent(getApplicationContext(),
                                                    MachineSetActivity.class);
                                            startIntent.putExtra("did",dataBean.getDid());

                                            startActivity(startIntent);
                                            break;

                                        case R.id.shanchu:
                                        new QMUIDialog.MessageDialogBuilder(MyEquipmentAcitivity.this).setTitle("解绑此台机器").setMessage("确定要删除吗？")
                                                .addAction("取消", new QMUIDialogAction.ActionListener() {
                                                    @Override
                                                    public void onClick(QMUIDialog dialog, int index) {
                                                        dialog.dismiss();
                                                    }
                                                })
                                                .addAction(0, "删除", QMUIDialogAction.ACTION_PROP_NEGATIVE, new QMUIDialogAction.ActionListener() {

                                                    @Override
                                                    public void onClick(QMUIDialog dialog, int index) {
                                                        MyList.DataBean dataBean = data.get(position);
                                                        unbind(dataBean.getDid());
                                                        dialog.dismiss();
                                                    }
                                                })
                                                .show();

                                        default:break;
                                    }
                                }
                            });
                            //给RecyclerView设置适配器
                            productRecy.setAdapter(productlistAdapter);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ToastUtils.showShort("机器列表获取失败，请稍后重试");
                    }
                });
    }

    @SuppressLint("CheckResult")
    private void unbind( String did ) {
        HttpApi mloginApi;
        mloginApi = RetrofitClient.create(HttpApi.class);
        String motime = String.valueOf(System.currentTimeMillis());

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("motime",motime);
        //hashMap.put("phone",phone.getText().toString());

        String sign = signMD5("interlnx&aY4N!bAAds",hashMap);

        FormBody body = new FormBody.Builder()
                .add("appid", "1288")
                .add("motime",  motime)
                .add("sign", "1234567890")
                .add("token", MyApplication.getInstance().getLoginResult().getData().getToken())
                .add("did",did)
                .build();

        Observable<UnbindResult> observable = mloginApi.unbind(body);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UnbindResult>() {
                    @Override
                    public void accept(UnbindResult baseInfo) throws Exception {
                            ToastUtils.showShort(baseInfo.getMsg());

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ToastUtils.showShort("网络延迟过高，请稍后重试");
                    }
                });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveMsg(MessageWrap message) {
        getProductlist();
    }
}
