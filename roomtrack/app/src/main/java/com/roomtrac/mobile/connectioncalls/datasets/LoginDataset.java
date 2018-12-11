package com.roomtrac.mobile.connectioncalls.datasets;

/**
 * Created by Ramesh.eerla on 24/10/2018.
 */
import com.google.gson.annotations.SerializedName;




public class LoginDataset {
    @SerializedName("member_id")
    public String member_id;
    @SerializedName("first_name")
    public String first_name;
    @SerializedName("last_name")
    public String last_name;
    @SerializedName("email")
    public String email;
    @SerializedName("password")
    public String password;
    @SerializedName("phone_no")
    public String phone_no;
    @SerializedName("dob_date")
    public String dob_date;
    @SerializedName("dob_month")
    public String dob_month;
    @SerializedName("dob_year")
    public String dob_year;
    @SerializedName("age")
    public String age;
    @SerializedName("marital_status")
    public String marital_status;
    @SerializedName("sex")
    public String sex;
    @SerializedName("home_town")
    public String home_town;
    @SerializedName("spoken_language")
    public String spoken_language;
    @SerializedName("address")
    public String address;
    @SerializedName("avatar")
    public String avatar;
    @SerializedName("old_stay_id")
    public String old_stay_id;
    @SerializedName("old_rent_id")
    public String old_rent_id;
    @SerializedName("old_hostel_id")
    public String old_hostel_id;
    @SerializedName("activate")
    public String activate;
    @SerializedName("last_login")
    public String last_login;
    @SerializedName("status")
    public String status;
    @SerializedName("flag")
    public String flag;
    @SerializedName("registration_expire")
    public String registration_expire;
    @SerializedName("profile_status")
    public String profile_status;
    @SerializedName("posted_on")
    public String posted_on;
    @SerializedName("message")
    public String message;
    public String getSex ()
    {
        return sex;
    }

    public void setSex (String sex)
    {
        this.sex = sex;
    }

    public String getRegistration_expire ()
    {
        return registration_expire;
    }

    public void setRegistration_expire (String registration_expire)
    {
        this.registration_expire = registration_expire;
    }

    public String getPosted_on ()
    {
        return posted_on;
    }

    public void setPosted_on (String posted_on)
    {
        this.posted_on = posted_on;
    }

    public String getDob_month ()
    {
        return dob_month;
    }

    public void setDob_month (String dob_month)
    {
        this.dob_month = dob_month;
    }

    public String getPassword ()
    {
        return password;
    }

    public void setPassword (String password)
    {
        this.password = password;
    }

    public String getActivate ()
    {
        return activate;
    }

    public void setActivate (String activate)
    {
        this.activate = activate;
    }

    public String getFirst_name ()
    {
        return first_name;
    }

    public void setFirst_name (String first_name)
    {
        this.first_name = first_name;
    }

    public String getDob_year ()
    {
        return dob_year;
    }

    public void setDob_year (String dob_year)
    {
        this.dob_year = dob_year;
    }

    public String getAge ()
    {
        return age;
    }

    public void setAge (String age)
    {
        this.age = age;
    }

    public String getPhone_no ()
    {
        return phone_no;
    }

    public void setPhone_no (String phone_no)
    {
        this.phone_no = phone_no;
    }

    public String getOld_rent_id ()
    {
        return old_rent_id;
    }

    public void setOld_rent_id (String old_rent_id)
    {
        this.old_rent_id = old_rent_id;
    }

    public String getMarital_status ()
    {
        return marital_status;
    }

    public void setMarital_status (String marital_status)
    {
        this.marital_status = marital_status;
    }

    public String getHome_town ()
    {
        return home_town;
    }

    public void setHome_town (String home_town)
    {
        this.home_town = home_town;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public String getAvatar ()
    {
        return avatar;
    }

    public void setAvatar (String avatar)
    {
        this.avatar = avatar;
    }

    public String getMember_id ()
    {
        return member_id;
    }

    public void setMember_id (String member_id)
    {
        this.member_id = member_id;
    }

    public String getLast_login ()
    {
        return last_login;
    }

    public void setLast_login (String last_login)
    {
        this.last_login = last_login;
    }

    public String getFlag ()
    {
        return flag;
    }

    public void setFlag (String flag)
    {
        this.flag = flag;
    }

    public String getOld_hostel_id ()
    {
        return old_hostel_id;
    }

    public void setOld_hostel_id (String old_hostel_id)
    {
        this.old_hostel_id = old_hostel_id;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getAddress ()
    {
        return address;
    }

    public void setAddress (String address)
    {
        this.address = address;
    }

    public String getSpoken_language ()
    {
        return spoken_language;
    }

    public void setSpoken_language (String spoken_language)
    {
        this.spoken_language = spoken_language;
    }

    public String getProfile_status ()
    {
        return profile_status;
    }

    public void setProfile_status (String profile_status)
    {
        this.profile_status = profile_status;
    }

    public String getLast_name ()
    {
        return last_name;
    }

    public void setLast_name (String last_name)
    {
        this.last_name = last_name;
    }

    public String getDob_date ()
    {
        return dob_date;
    }

    public void setDob_date (String dob_date)
    {
        this.dob_date = dob_date;
    }

    public String getOld_stay_id ()
    {
        return old_stay_id;
    }

    public void setOld_stay_id (String old_stay_id)
    {
        this.old_stay_id = old_stay_id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
