package com.roomtrac.mobile.connectioncalls.connections;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.roomtrac.mobile.R;
import com.roomtrac.mobile.controller.AppController;
import com.roomtrac.mobile.interfaces.ResponceCallback;
import com.roomtrac.mobile.utils.CommonHelper;
import com.roomtrac.mobile.utils.CommonUtils;
import com.roomtrac.mobile.utils.VolleyErrorHelper;

import org.json.JSONObject;

/**
 * Created by Ramesh.eerla on 24/10/2018.
 * ST_RequestService is for requesting server post ,get
 */

public class ST_RequestService {

    private Context mContext;
    private ResponceCallback mResponceCallback;
    private Object mObject;
    private Intent mIntent;
    private ProgressDialog mProgressDialog;
    private CommonHelper mCommonHelper;

    /**
     * Created by ramesh.eerla on 1/17/2017.
     * ST_RequestService() is constructor
     *
     * @param context is the activity context object
    */

    public ST_RequestService(Context context) {
        this.mContext = context;
        try {
            this.mResponceCallback = (ResponceCallback) context;
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.mCommonHelper = new CommonHelper();

    }


    public ST_RequestService(Context context, Object object) {
        this.mContext = context;
        this.mObject = object;
        mResponceCallback = (ResponceCallback) object;
        this.mCommonHelper = new CommonHelper();
    }


    public ST_RequestService(Context context, Intent intent) {
        this.mContext = context;
        this.mIntent = intent;
        this.mCommonHelper = new CommonHelper();
    }

    public void loadPostRequest(String request_url, JSONObject mRequestJsonObject) {

        JsonRequest request = AppController.getInstance().postRequest(
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        mResponceCallback.callback(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        handleVolleyError(mResponceCallback, error);

                    }
                }, request_url, mRequestJsonObject);


        request.setRetryPolicy(new DefaultRetryPolicy(
                CommonUtils.SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(request);

    }

    /**
     * Created by ramesh.eerla on 1/17/2017.
     * loadPostRequest() method is used to call the post services
     *
     * @param request_url        is the request url
     * @param mRequestJsonObject is requested parameter for the url*/

    public void loadIntentRequest(final String request_url, JSONObject mRequestJsonObject) {
        //if (mCommonHelper != null && mContext != null)
            //mProgressDialog = mCommonHelper.showDialog(mContext);
        JsonRequest request = AppController.getInstance().postRequest(
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        mCommonHelper.dismissDialog(mProgressDialog);
                        if (request_url.contains("SubmittedCandidateDetails")) {

                        } else
                            mIntent.putExtra("String_response", response.toString());
                        mContext.startActivity(mIntent);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mCommonHelper.dismissDialog(mProgressDialog);
                        handleVolleyError(mResponceCallback, error);

                    }
                }, request_url, mRequestJsonObject);
        request.setRetryPolicy(new DefaultRetryPolicy(
                CommonUtils.SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(request);

    }


    /* * @author :: Santosh Kumar Baratam
     * Use :: Handling Volley error cases at one place
     **/

    public void handleVolleyError(ResponceCallback mResponceCallback, VolleyError error) {
        NetworkResponse response = error.networkResponse;
        if (response != null && response.data != null) {
            String json = new String(response.data);
            if (json != null)
                if (mResponceCallback != null)
                    mResponceCallback.errorcallback(mContext.getResources().getString(R.string.app_name), json);
                else
                    CommonHelper.showErrorAlertDiaolog(mContext, mContext.getResources().getString(R.string.app_name), json);
            else if (mResponceCallback != null)
                mResponceCallback.errorcallback(VolleyErrorHelper.getErrorType(error, mContext), VolleyErrorHelper.getMessage(error, mContext));
            else
                CommonHelper.showErrorAlertDiaolog(mContext, VolleyErrorHelper.getErrorType(error, mContext), VolleyErrorHelper.getMessage(error, mContext));
        } else {
            if (mResponceCallback != null)
                mResponceCallback.errorcallback(VolleyErrorHelper.getErrorType(error, mContext), VolleyErrorHelper.getMessage(error, mContext));
            else
                CommonHelper.showErrorAlertDiaolog(mContext, VolleyErrorHelper.getErrorType(error, mContext), VolleyErrorHelper.getMessage(error, mContext));
        }
    }


}
