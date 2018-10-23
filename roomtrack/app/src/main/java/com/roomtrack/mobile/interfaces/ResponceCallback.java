package com.roomtrack.mobile.interfaces;

import org.json.JSONObject;

/**
 * Created by ramesh.eerla on 5/17/2016.
 * call back interface after getting the responce from service
 */
public interface ResponceCallback {
    /**
     * Created by ramesh.eerla on 5/17/2016.
     * call back interface after getting the responce from service
     * @param responce is the result of the reuest
     */
    public void callback(JSONObject responce);
    public void callback(String response, boolean isThisTimeSheetSupported, String type);
    public void callback(Object responce, int requesttype);
    public void errorcallback(String errortitle, String errormessage);

}
