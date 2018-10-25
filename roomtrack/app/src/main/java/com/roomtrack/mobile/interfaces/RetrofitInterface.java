package com.roomtrack.mobile.interfaces;

import com.dcrworkforce.mobile.actions.OfferToHireAction;
import com.dcrworkforce.mobile.model.AcceptOfferModel;
import com.dcrworkforce.mobile.model.RetrofitResponse;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Ramesh.eerla on 24/10/2018.
 */

public abstract class RetrofitInterface {
    public void onResponse(Call<RetrofitResponse> call, Response<RetrofitResponse> response){};
    public void onResponseAcceptOffer(Call<AcceptOfferModel> call, Response<AcceptOfferModel> response){};
    public void onJsonObjectResponse(JSONObject response){};
    public void onFailure(Call<RetrofitResponse> call, Throwable t){}
}
