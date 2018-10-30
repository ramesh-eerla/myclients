package com.roomtrac.mobile.connectioncalls.interfaces;




import com.roomtrac.mobile.connectioncalls.datasets.BasicRequestParams;
import com.roomtrac.mobile.connectioncalls.datasets.LoginDataset;
import com.roomtrac.mobile.connectioncalls.datasets.RetrofitResponse;
import com.roomtrac.mobile.services.RequestParams;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by ramesh.eerla on 1/12/2017.
 */

public interface STRequestInterface {
    /**
     * Created by ramesh.eerla on 1/12/2017.
     *
     * @Path("version") :version number like(v2/mobile)
     * @Path("methodname") : webapi method name
     * @Body : parameters for the request
     */
    @FormUrlEncoded
    @POST("{methodname}")
    Call<RetrofitResponse> user_Register(@Path("methodname") String methodname, @Body RequestParams.Register task );

    @POST("{version}/{methodname}")
    Call<LoginDataset> sso_login(@Path("version") String version, @Path("methodname") String methodname, @Body RequestParams.PersonalProfile task);

    @POST("{version}/{methodname}")
    Call<LoginDataset> login(@Path("version") String version, @Path("methodname") String methodname, @Body RequestParams.LoginDetails task);

    @POST("{version}/{methodname}")
    Call<LoginDataset> reloaddata(@Path("version") String version, @Path("methodname") String methodname);


    @POST("{version}/{methodname}")
    Call<RetrofitResponse> retrofitCall(@Path("version") String version, @Path("methodname") String methodname, @Body BasicRequestParams task);


}
