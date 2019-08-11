package com.sim.chongwukongjing.ui.http;


import com.sim.chongwukongjing.ui.bean.LoginParam;
import com.sim.chongwukongjing.ui.bean.LoginResult;
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
    Observable<SendcodeResult> reg(@Body RequestBody body);

}