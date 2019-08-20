package com.sim.chongwukongjing.ui.Activity;

import android.annotation.SuppressLint;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.sim.chongwukongjing.R;
import com.sim.chongwukongjing.ui.Base.BaseActivity;
import com.sim.chongwukongjing.ui.Main.MyApplication;
import com.sim.chongwukongjing.ui.bean.ProductlistResult;
import com.sim.chongwukongjing.ui.http.HttpApi;
import com.sim.chongwukongjing.ui.http.RetrofitClient;
import com.sim.chongwukongjing.ui.wigdet.FindProductlistAdapter;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
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
public class FindEquipmentActivity extends BaseActivity {

    private FindProductlistAdapter productlistAdapter;
    private List<ProductlistResult.DataBean> data;

    @BindView(R.id.ProductRecy)
    RecyclerView ProductRecy;

    @BindView(R.id.topbar)
    QMUITopBar qmuiTopBar;


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_finding_equipment;
    }

    @Override
    protected void initView() {
        qmuiTopBar.setTitle("添加设备");
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
        ProductRecy.setLayoutManager(layoutManager);

        if (MyApplication.getInstance().getLoginResult() == null){
            ToastUtils.showShort("请先登录");
            startActivity(LoginActivity.class);
            finish();
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

        Observable<ProductlistResult> observable = mloginApi.productlist(body);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ProductlistResult>() {
                    @Override
                    public void accept(ProductlistResult baseInfo) throws Exception {
                        if ("10000".equals(baseInfo.getCode())){
                            ToastUtils.showShort(baseInfo.getMsg());
                            //创建适配器
                            data = baseInfo.getData();
                            productlistAdapter = new FindProductlistAdapter(R.layout.productlist_itemview, data,FindEquipmentActivity.this);
                            //条目点击事件
                            productlistAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    ProductlistResult.DataBean dataBean = data.get(position);
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
