package com.roomtrack.mobile.connectioncalls.datasets;
/**
 * Created by Ramesh.eerla on 24/10/2018.
 */
import com.google.gson.annotations.SerializedName;

public class AccessToakenSet {
    @SerializedName("access_token")
    public String access_token;
    @SerializedName("expires_in")
    public int expires_in;
    @SerializedName("token_type")
    public String token_type;

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getToken_type() {
        return token_type;
    }
}
