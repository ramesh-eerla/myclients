package com.roomtrac.mobile.connectioncalls.connections;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.JsonParser;
import com.roomtrac.mobile.interfaces.ResponceCallback;
import com.roomtrac.mobile.utils.Constants;
import com.roomtrac.mobile.utils.JSONParser;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetServicecall extends AsyncTask<String, String, String> {

    /**
     * Before starting background thread Show Progress Dialog
     * */
    Context context;
    ResponceCallback mResponceCallback;
    Object object;
    List<NameValuePair> params=new ArrayList<>();
    String method;
    JSONParser jsonParser;
    int type;
    public GetServicecall(Context context, Object object, List<NameValuePair> params, String method, int type){
        this.context=context;
        this.object=object;
        this.params=params;
        this.method=method;
        this.type=type;
        this.jsonParser=new JSONParser();
        this.mResponceCallback = (ResponceCallback) object;

    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }



    /**
     * Creating product
     * */
    protected String doInBackground(String... args) {

        // getting JSON Object
        // Note that create product url accepts POST method
        String url= method;//;
        JSONObject json = jsonParser.makeHttpRequest(url,
                type, params);


        return json.toString();
    }

    /**
     * After completing background task Dismiss the progress dialog
     * **/
    protected void onPostExecute(String result) {
        try {
            JSONObject json=new JSONObject(result);
            int success = json.getInt(Constants.TAG_SUCCESS);
            if (success == 1) {

            } else {
                mResponceCallback.errorcallback("Server error",json.getString("message"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}