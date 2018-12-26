package com.roomtrac.mobile.connectioncalls.datasets;
/**
 * Created by Ramesh.eerla on 24/10/2018.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ErrorBooleanstatus {
    @Expose
    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private boolean status;

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}