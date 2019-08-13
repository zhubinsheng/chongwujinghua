package com.sim.chongwukongjing.ui.Activity;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.sim.chongwukongjing.R;
import com.sim.chongwukongjing.ui.Base.BaseActivity;
import com.sim.chongwukongjing.ui.Main.MyApplication;
import com.sim.chongwukongjing.ui.bean.MyList;
import com.sim.chongwukongjing.ui.fragment.machune.MachineSetActivity;
import com.sim.chongwukongjing.ui.http.HttpApi;
import com.sim.chongwukongjing.ui.http.RetrofitClient;
import com.sim.chongwukongjing.ui.wigdet.MyProductlistAdapter;

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
    RecyclerView ProductRecy;

    @BindView(R.id.topbar)
    QMUITopBar qmuiTopBar;

    @BindView(R.id.textView3)
    TextView textView3;

    @BindView(R.id.textView4)
    TextView textView4;


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_my_equipment;
    }

    @Override
    protected void initView() {
        qmuiTopBar.setTitle("添加设备");


    }

    @SuppressLint("WrongConstant")
    @Override
    protected void initSet() {
        //创建布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        ProductRecy.setLayoutManager(layoutManager);

        if (MyApplication.getInstance().getLoginResult() == null){
            ToastUtils.showShort("请先登录");
            startActivity(LoginActivity.class);
            finish();
        }


        OnItemSwipeListener onItemSwipeListener = new OnItemSwipeListener() {
            @Override
            public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {}
            @Override
            public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {}
            @Override
            public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) {}

            @Override
            public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float v, float v1, boolean b) {

            }
        };



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
                            //创建适配器
                            data = baseInfo.getData();
                            productlistAdapter = new MyProductlistAdapter(R.layout.ny_productlist_itemview, data,MyEquipmentAcitivity.this);
                            //条目点击事件
                            productlistAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    MyList.DataBean dataBean = data.get(position);
                                    startActivity(InputPasswordActivity.class);
                                    finish();
                                }
                            });
                            //给RecyclerView设置适配器
                            ProductRecy.setAdapter(productlistAdapter);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ToastUtils.showShort("机器列表获取失败，请稍后重试");
                    }
                });
    }
}
