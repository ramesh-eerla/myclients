package com.roomtrac.mobile.connectioncalls.interfaces;




import com.roomtrac.mobile.connectioncalls.datasets.BasicRequestParams;
import com.roomtrac.mobile.connectioncalls.datasets.Countriesmodel;
import com.roomtrac.mobile.connectioncalls.datasets.Dataset;
import com.roomtrac.mobile.connectioncalls.datasets.DetailsDataset;
import com.roomtrac.mobile.connectioncalls.datasets.LoginDataset;
import com.roomtrac.mobile.connectioncalls.datasets.RetrofitResponse;
import com.roomtrac.mobile.services.RequestParams;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by ramesh.eerla on 1/12/2017.
 */

public interface RTRequestInterface {
    /**
     * Created by ramesh.eerla on 1/12/2017.
     *
     * @Path("version") :version number like(v2/mobile)
     * @Path("methodname") : webapi method name
     * @Body : parameters for the request
     */

    @POST("{methodname}")
    Call<RetrofitResponse> user_Register(@Path("methodname") String methodname, @Body RequestParams.Register task );

    @POST("{methodname}")
    Call<List<DetailsDataset>> searchrooms(@Path("methodname") String methodname, @Body RequestParams.SearchTypes task);

    @POST("{methodname}")
    Call<List<Dataset>> searchtypes(@Path("methodname") String methodname, @Body RequestParams.SearchValues task);

    @POST("{methodname}")
    Call<LoginDataset> login(@Path("methodname") String version,  @Body RequestParams.LoginDetails task);

    @GET("{methodname}")
    Call<List<Countriesmodel>> getarea(@Path("methodname") String methodname);

    @GET("{methodname}")
    Call<List<Countriesmodel>> getareadata(@Path("methodname") String methodname, @QueryMap Map<String, String> options);


    @POST("{version}/{methodname}")
    Call<RetrofitResponse> retrofitCall(@Path("version") String version, @Path("methodname") String methodname, @Body BasicRequestParams task);


}
