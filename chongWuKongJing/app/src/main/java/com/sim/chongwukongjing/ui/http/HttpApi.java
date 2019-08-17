package com.sim.chongwukongjing.ui.http;


import com.sim.chongwukongjing.ui.bean.ConfigResult;
import com.sim.chongwukongjing.ui.bean.ControlResult;
import com.sim.chongwukongjing.ui.bean.DvcInfoResult;
import com.sim.chongwukongjing.ui.bean.FindDeviceResult;
import com.sim.chongwukongjing.ui.bean.ForgotpassResult;
import com.sim.chongwukongjing.ui.bean.LoginResult;
import com.sim.chongwukongjing.ui.bean.MyList;
import com.sim.chongwukongjing.ui.bean.ProductlistResult;
import com.sim.chongwukongjing.ui.bean.RegResult;
import com.sim.chongwukongjing.ui.bean.SendcodeResult;
import com.sim.chongwukongjing.ui.bean.UnbindResult;
import com.sim.chongwukongjing.ui.bean.UuidResult;
import com.sim.chongwukongjing.ui.bean.WeatherResult;
import com.sim.chongwukongjing.ui.bean.tianqiResult;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

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

    @POST("api/sso/forgotpass")
    Observable<ForgotpassResult> forgotpass(@Body RequestBody body);

    @POST("api/sso/login")
    Observable<LoginResult> login(@Body RequestBody body);

    @POST("api/dvc/productlist")
    Observable<ProductlistResult> productlist(@Body RequestBody body);

    @POST("api/dvc/mylist")
    Observable<MyList> mylist(@Body RequestBody body);

    @POST("api/dvc/unbind")
    Observable<UnbindResult> unbind(@Body RequestBody body);

    @POST("api/dvc/findDevice")
    Observable<FindDeviceResult> findDevice(@Body RequestBody body);

    @POST("api/dvc/dvcinfo")
    Observable<DvcInfoResult> dvcinfo(@Body RequestBody body);

    @POST("api/common/weather")
    Observable<WeatherResult> getWeather(@Body RequestBody body);

    @POST("api/cmd/control")
    Observable<ControlResult> control(@Body RequestBody body);

    @POST("qq/linx/config")
    Observable<ConfigResult> config(@Body RequestBody body);


    @POST("api/sso/uuid")
    Observable<UuidResult> uuid(@Body RequestBody body);

    @GET("/reverse_geocoding/v3")
    Call<tianqiResult> findLocation(@Query("location") String location, @Query("ak") String ak);
}