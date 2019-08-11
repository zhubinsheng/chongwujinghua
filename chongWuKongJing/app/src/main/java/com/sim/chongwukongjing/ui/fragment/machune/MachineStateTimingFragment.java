package com.sim.chongwukongjing.ui.fragment.machune;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sim.chongwukongjing.ui.Base.BaseFragment;
import com.sim.chongwukongjing.R;
import com.sim.chongwukongjing.ui.bean.LoginParam;
import com.sim.chongwukongjing.ui.bean.LoginResult;
import com.sim.chongwukongjing.ui.http.HttpApi;
import com.sim.chongwukongjing.ui.http.RetrofitClient;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.goldze.mvvmhabit.utils.ToastUtils;
import okhttp3.FormBody;

/**
 * @author Administrator
 */
public class MachineStateTimingFragment extends BaseFragment {
    @Override
    protected View getBaseView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.machine_timing, container, false);
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initDate(View view) {
        HttpApi mloginApi;
        mloginApi = RetrofitClient.create(HttpApi.class);

        LoginParam loginParam = new LoginParam();
        loginParam.setAppid("1288");

        //而OkHttp库中有一个专门来构建参数上传的RequestBody的子类，FormBody
        FormBody body = new FormBody.Builder()
                .add("appid", "1288")
                .add("motime", "23123124")
                .add("sign", "2d639e66c233d27bc430bc8a834dc76d")
                .add("mac", "2231233")
                .add("phone", "13337213721")
                .add("passwd", "123456")
                .build();

        Observable<LoginResult> observable = mloginApi.phoneregister2(body);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LoginResult>() {
                    @Override
                    public void accept(LoginResult baseInfo) throws Exception {

                        ToastUtils.showShort(baseInfo.getMsg());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ToastUtils.showShort("失败");
                    }
                });

    }

    @Override
    protected void initSet(View view) {

    }

    @Override
    protected void initDisplayData(View view) {

    }
}
