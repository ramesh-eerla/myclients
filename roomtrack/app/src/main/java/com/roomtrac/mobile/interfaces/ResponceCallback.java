package com.roomtrac.mobile.interfaces;

import org.json.JSONObject;

/**
 * Created by Ramesh.eerla on 24/10/2018.
 * call back interface after getting the responce from service
 */
public interface ResponceCallback {

    public void callback(JSONObject responce);
    public void callback(Object responce, int requesttype);
    public void errorcallback(String errortitle, String errormessage);

}
