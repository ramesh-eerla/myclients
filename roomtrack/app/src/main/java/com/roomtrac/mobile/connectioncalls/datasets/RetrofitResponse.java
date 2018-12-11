package com.roomtrac.mobile.connectioncalls.datasets;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RetrofitResponse {
    @Expose
    @SerializedName(value = "message",alternate = {"Message"})
    private String Message;

    @SerializedName("status")
    private String status;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        this.Message = message;
    }

    public String getStatus() { return status;}

    public void setStatus(String status){this.status=status;}

}
