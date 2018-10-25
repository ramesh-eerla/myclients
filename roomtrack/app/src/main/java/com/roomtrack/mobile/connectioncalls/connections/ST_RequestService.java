package com.roomtrack.mobile.connectioncalls.connections;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.dcrworkforce.mobile.R;
import com.dcrworkforce.mobile.STinterface.ResponceCallback;
import com.dcrworkforce.mobile.STinterface.RetrofitInterface;
import com.dcrworkforce.mobile.controller.AppController;
import com.dcrworkforce.mobile.dataset.Commanvariables;
import com.dcrworkforce.mobile.requestandresponce.Commons;
import com.dcrworkforce.mobile.screenhelpers.CommonHelper;
import com.dcrworkforce.mobile.screenhelpers.CommonRequestJSONObjects;
import com.dcrworkforce.mobile.screenhelpers.TimeSheetScreenHelper;
import com.dcrworkforce.mobile.utils.ExtendedDataHolder;
import com.dcrworkforce.mobile.utils.VolleyErrorHelper;

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
    private TimeSheetScreenHelper timesheet_helper_obj;
    private CommonRequestJSONObjects mCommonRequestJSONObjects;
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
        mCommonRequestJSONObjects = new CommonRequestJSONObjects();
    }

    /**
     * Created by ramesh.eerla on 1/17/2017.
     * ST_RequestService() is constructor
     *
     * @param context is the activity context object
     */
    public ST_RequestService(Context context, Object object) {
        this.mContext = context;
        this.mObject = object;
        mResponceCallback = (ResponceCallback) object;
        this.mCommonHelper = new CommonHelper();
    }

    /**
     * Created by ramesh.eerla on 1/17/2017.
     * ST_RequestService() is constructor
     *
     * @param context is the activity context object
     */
    public ST_RequestService(Context context, Intent intent) {
        this.mContext = context;
        this.mIntent = intent;
        this.mCommonHelper = new CommonHelper();
    }

    public ST_RequestService(Context mContext, TimeSheetScreenHelper timesheet_helper_obj) {
        this.mContext = mContext;
        this.timesheet_helper_obj = timesheet_helper_obj;

        mResponceCallback = (ResponceCallback) mContext;
        mCommonRequestJSONObjects = new CommonRequestJSONObjects();
    }

    /*Method
     *Created by Satya Gowri .v
     * Used to perform Copy time Sheet
     * @params saeId(int), cwTimeSheetId(string), PreviousDate(Boolean), NextDate(Boolean), SelectedDate(Boolean), date(string)
     * */
    public void copyTimeSheetData(int sae_id, String cwTimeSheetId, String date) {
        String domain_url = Commons.webapi_domain_url + mContext.getString(R.string.webapi_urn_5) + mContext.getString(R.string.copytimesheet_urn);
        JsonRequest request = AppController.getInstance().postRequest(new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (response != null || response.length() > 0) {
                    String json_send_status_result_str = response.toString();
                    try {
                        JSONObject jsonObject = new JSONObject(json_send_status_result_str);
//                    timesheet_helper_obj.setTimeSheetRequiredData(json_send_status_result_str);
                        boolean is_this_timesheet_supported = jsonObject.getBoolean("IsSTTimesheet");
                        mResponceCallback.callback(json_send_status_result_str, is_this_timesheet_supported, "TimesheetResult");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                handleVolleyError(mResponceCallback, error);
            }
        }, domain_url, mCommonRequestJSONObjects.getCopyTimeSheetRequest(sae_id, cwTimeSheetId, date));
        request.setRetryPolicy(new DefaultRetryPolicy(
                Commanvariables.SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(request);
    }


    public void createTimesheetData(int sae_id, int sae_timesheet_id, boolean editTs, boolean previousTs, boolean nextTs, boolean selectedTS, String dateTs) {

        String domain_url = "";

        if (sae_timesheet_id != 0) {
            editTs = true;
        }

        domain_url = Commons.webapi_domain_url + mContext.getString(R.string.webapi_urn_5) + mContext.getString(R.string.createtimesheet_urn);


        JsonRequest request = AppController.getInstance().postRequest(
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (response != null || response.length() > 0) {
                            String json_send_status_result_str = response.toString();
                            //JSONUtils.getJSONObjectStringByKey(response.toString(), finalType);
                            timesheet_helper_obj.setTimeSheetRequiredData(json_send_status_result_str);
                            boolean is_this_timesheet_supported = timesheet_helper_obj.is_this_timesheet_supported;
                            mResponceCallback.callback(json_send_status_result_str, is_this_timesheet_supported, "TimesheetResult");
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        handleVolleyError(mResponceCallback, error);

                    }
                }, domain_url, mCommonRequestJSONObjects.getTimeSheetRequestJSONString(sae_id, sae_timesheet_id, editTs, previousTs, nextTs, selectedTS, dateTs));
        request.setRetryPolicy(new DefaultRetryPolicy(
                Commanvariables.SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(request);


    }

    public void sendRejectRequest(int sae_id, int timesheetID, String type) {

        JsonRequest request = AppController.getInstance().postRequest(
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String json_send_status_result_str = response.toString();
                        mResponceCallback.callback(json_send_status_result_str, true, "SendStatus");
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        handleVolleyError(mResponceCallback, error);
                    }
                }, Commons.webapi_domain_url + mContext.getString(R.string.webapi_urn_5) + mContext.getString(R.string.rejecttimesheet_urn), mCommonRequestJSONObjects.getSendConcurrencyStatusJSONString(sae_id, timesheetID, "", type));
        request.setRetryPolicy(new DefaultRetryPolicy(
                Commanvariables.SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(request);


    }

    public void saveTimeSheetRequest(String url, JSONObject save_Objects, final String type) {

        JsonRequest request = AppController.getInstance().postRequest(
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String json_send_status_result_str = response.toString();
                        mResponceCallback.callback(json_send_status_result_str, true, type);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        handleVolleyError(mResponceCallback, error);

                    }
                }, url, save_Objects);
        request.setRetryPolicy(new DefaultRetryPolicy(
                Commanvariables.SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(request);
    }


    public void submitaction(String requestUrl, JSONObject jsonObject) {
        mProgressDialog = mCommonHelper.showDialog(mContext);
        JsonRequest request = AppController.getInstance().postRequest(
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        mCommonHelper.dismissDialog(mProgressDialog);
                        mResponceCallback.callback(response.toString(), false, "");

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mCommonHelper.dismissDialog(mProgressDialog);
                        handleVolleyError(mResponceCallback, error);

                    }
                }, requestUrl, jsonObject);
        request.setRetryPolicy(new DefaultRetryPolicy(
                Commanvariables.SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(request);
    }


    public void actionDecline(String url, JSONObject request_date) {
        if (mCommonHelper != null && mContext != null)
            mProgressDialog = mCommonHelper.showDialog(mContext);
        JsonRequest request = AppController.getInstance().postRequest(
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        mCommonHelper.dismissDialog(mProgressDialog);
                        mResponceCallback.callback(response.toString(), true, "decline");
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mCommonHelper.dismissDialog(mProgressDialog);

                        handleVolleyError(mResponceCallback, error);

                    }
                }, url, request_date);
        request.setRetryPolicy(new DefaultRetryPolicy(
                Commanvariables.SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(request);
    }

    public void setZerohoursTimesheet(int sae_id, int sae_timesheet_id, String timesheet_start_date_str, String timesheet_end_date_str, String workScheduleID, String cw_endDate) {

        JsonRequest request = AppController.getInstance().postRequest(
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String json_send_status_result_str = response.toString();
                        mResponceCallback.callback(json_send_status_result_str, true, "setzerohourstimesheet");
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                        handleVolleyError(mResponceCallback, error);

                    }
                }, Commons.webapi_domain_url + mContext.getString(R.string.webapi_urn_5) + mContext.getString(R.string.setzerohourstimesheet_urn), mCommonRequestJSONObjects.getZerohoursTimesheetRequestJSONString(sae_id, sae_timesheet_id, timesheet_start_date_str, timesheet_end_date_str, workScheduleID, cw_endDate));
        request.setRetryPolicy(new DefaultRetryPolicy(
                Commanvariables.SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(request);
    }

    public void sendApproveRequest(int sae_id, int timesheetID, String statusID, String type) {
        JsonRequest request = AppController.getInstance().postRequest(
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
//                        String json_send_status_result_str = JSONUtils.getJSONObjectStringByKey(response.toString(), "ApproveTimesheetResult");
                        String json_send_status_result_str = response.toString();
                        mResponceCallback.callback(json_send_status_result_str, true, "SendStatus");
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        handleVolleyError(mResponceCallback, error);

                    }
                }, Commons.webapi_domain_url + mContext.getString(R.string.webapi_urn_5) + mContext.getString(R.string.approvetimesheet_urn), mCommonRequestJSONObjects.getSendConcurrencyStatusJSONString(sae_id, timesheetID, statusID, type));
        request.setRetryPolicy(new DefaultRetryPolicy(
                Commanvariables.SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(request);


    }

    public void sendSubmitRequest(int sae_id, int timesheetID, String submitComment, String startDate, String endDate) {
        JsonRequest request = AppController.getInstance().postRequest(
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String json_send_status_result_str = response.toString();
                        mResponceCallback.callback(json_send_status_result_str, true, "SendStatus");
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        handleVolleyError(mResponceCallback, error);

                    }
                }, Commons.webapi_domain_url + mContext.getString(R.string.webapi_urn_5) + mContext.getString(R.string.submittimesheet_urn), mCommonRequestJSONObjects.getSendStatusJSONString(sae_id, timesheetID, submitComment, startDate, endDate));
        request.setRetryPolicy(new DefaultRetryPolicy(
                Commanvariables.SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(request);


    }

    /**
     * Created by ramesh.eerla on 1/17/2017.
     * loadPostRequest() method is used to call the post services
     *
     * @param request_url        is the request url
     * @param mRequestJsonObject is requested parameter for the url
     */
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
                Commanvariables.SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(request);

    }

    /**
     * Created by ramesh.eerla on 1/17/2017.
     * loadPostRequest() method is used to call the post services
     *
     * @param request_url        is the request url
     * @param mRequestJsonObject is requested parameter for the url
     */
    public void loadIntentRequest(final String request_url, JSONObject mRequestJsonObject) {
        if (mCommonHelper != null && mContext != null)
            mProgressDialog = mCommonHelper.showDialog(mContext);
        JsonRequest request = AppController.getInstance().postRequest(
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        mCommonHelper.dismissDialog(mProgressDialog);
                        if (request_url.contains("SubmittedCandidateDetails")) {
                            ExtendedDataHolder extras = ExtendedDataHolder.getInstance();
                            extras.putExtra("String_response", response.toString());
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
                Commanvariables.SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(request);

    }

    /*
     * @author :: Santosh Kumar Baratam
     * Use :: Handling Volley error cases at one place
     * */

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

    public static void JsonObjectVolleyRequest(final Context mContext, String url, JSONObject requestParamKeys, final RetrofitInterface retrofitInterface) {
        CommonHelper mCommonHelper = new CommonHelper();
        ProgressDialog dialog = null;
        if (mCommonHelper != null && mContext != null)
            dialog = mCommonHelper.showDialog(mContext);

        final ProgressDialog finalDialog = dialog;
        JsonRequest request = AppController.getInstance().postRequest(
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        finalDialog.dismiss();
                        retrofitInterface.onJsonObjectResponse(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        finalDialog.dismiss();
                        new ST_RequestService(mContext).handleVolleyError(null, error);
                    }
                }, Commons.webapi_domain_url + "/" + url, requestParamKeys);
        request.setRetryPolicy(new DefaultRetryPolicy(
                Commanvariables.SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(request);
    }
}
