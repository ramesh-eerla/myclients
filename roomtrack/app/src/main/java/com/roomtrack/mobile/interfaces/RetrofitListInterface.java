package com.roomtrack.mobile.interfaces;

import com.dcrworkforce.mobile.model.DropdownReasons;
import com.dcrworkforce.mobile.model.KeyValueModel;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
/**
 * Created by Ramesh.eerla on 24/10/2018.
 */

public abstract class RetrofitListInterface {
    public void onResponseList(Call<List<KeyValueModel>> call, Response<List<KeyValueModel>> response, Call<List<KeyValueModel>> mService){};
    public void onResponseDropDownReasonList(Response<List<DropdownReasons>> response, Call<List<DropdownReasons>> mService){}
    public void onFailure(Call<List<KeyValueModel>> call, Throwable t){}
    public void onFailureDropDownReasons(Call<List<DropdownReasons>> call, Throwable t){}
}
