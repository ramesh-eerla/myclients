package com.roomtrac.mobile.connectioncalls.datasets;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Countriesmodel {
    @SerializedName("country_name")
    @Expose
    private String country_name;
    @SerializedName("country_id")
    @Expose
    private String country_id;

    @SerializedName("state_id")
    @Expose
    private String state_id;
    @SerializedName("state_name")
    @Expose
    private String state_name;

    @SerializedName("city_id")
    @Expose
    private String city_id;
    @SerializedName("city_name")
    @Expose
    private String city_name;

    @SerializedName("location_id")
    @Expose
    private String location_id;
    @SerializedName("location_name")
    @Expose
    private String location_name;


    @SerializedName(value = "room_type_id",alternate ={"hostel_type_id","home_appt_type_id"} )
    @Expose
    private String room_type_id;
    @SerializedName(value = "room_type_name",alternate ={"hostel_type_name","home_appt_type_name" })
    @Expose
    private String room_type_name;

    @SerializedName(value = "home_appt_preferences_id",alternate = "hostel_nature_id")
    @Expose
    private String home_appt_preferences_id;
    @SerializedName(value = "home_appt_preferences_name",alternate = "hostel_nature_name")
    @Expose
    private String home_appt_preferences_name;



    public String getHome_appt_preferences_id() {
        return home_appt_preferences_id;
    }

    public void setHome_appt_preferences_id(String home_appt_preferences_id) {
        this.home_appt_preferences_id = home_appt_preferences_id;
    }

    public String getHome_appt_preferences_name() {
        return home_appt_preferences_name;
    }

    public void setHome_appt_preferences_name(String home_appt_preferences_name) {
        this.home_appt_preferences_name = home_appt_preferences_name;
    }

    public String getCountry_name() {
        return this.country_name;
    }

    public void setCountry_name(String country_id) {
        this.country_name = country_name;
    }

    public String getCountry_id() {
        return this.country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public String getState_id() { return state_id; }

    public void setState_id(String state_id) { this.state_id = state_id; }

    public String getState_name() { return state_name;}

    public void setState_name(String state_name) { this.state_name = state_name; }

    public String getCity_id() { return city_id;  }

    public void setCity_id(String city_id) { this.city_id = city_id; }

    public String getCity_name() { return city_name; }

    public void setCity_name(String city_name) { this.city_name = city_name;  }

    public String getLocation_id() { return location_id;  }

    public void setLocation_id(String location_id) { this.location_id = location_id; }

    public String getLocation_name() { return location_name; }

    public void setLocation_name(String location_name) { this.location_name = location_name; }

    public String getRoom_type_id() { return room_type_id; }

    public void setRoom_type_id(String room_type_id) { this.room_type_id = room_type_id; }

    public String getRoom_type_name() { return room_type_name;  }

    public void setRoom_type_name(String room_type_name) { this.room_type_name = room_type_name; }


}
