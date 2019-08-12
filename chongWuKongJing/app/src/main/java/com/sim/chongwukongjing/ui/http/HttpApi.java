package com.sim.chongwukongjing.ui.http;


import com.sim.chongwukongjing.ui.bean.LoginResult;
import com.sim.chongwukongjing.ui.bean.MyList;
import com.sim.chongwukongjing.ui.bean.ProductlistResult;
import com.sim.chongwukongjing.ui.bean.RegResult;
import com.sim.chongwukongjing.ui.bean.SendcodeResult;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author Administrator
 */
public interface HttpApi {

    @POST("api/sso/login")
    @FormUrlEncoded
    Observable<LoginResult> phoneregister(@Field("appid") String appid);

    @POST("api/sso/login")
    Observable<LoginResult> phoneregister2(@Body RequestBody body);


    @POST("api/sso/sendcode")
    Observable<SendcodeResult> sendcode(@Body RequestBody body);

    @POST("api/sso/reg")
    Observable<RegResult> reg(@Body RequestBody body);

    @POST("api/sso/login")
    Observable<LoginResult> login(@Body RequestBody body);

    @POST("api/dvc/productlist")
    Observable<ProductlistResult> productlist(@Body RequestBody body);

    @POST("api/dvc/mylist")
    Observable<MyList> mylist(@Body RequestBody body);

}