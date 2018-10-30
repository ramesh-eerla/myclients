package com.roomtrac.mobile.services;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ramesh.eerla on 1/12/2017.
 */

public class RequestParams {
    /**
     * @Class Logindata
     * This class is used for Login
     * @dparam String userName,tversion, device;
     * Boolean AutoLogin;
     */
    public class LoginDetails {
        String email, password;
        /**
         * @Class Logindata
         * This class is used for Login with email and password
         * @neededparam String email,password;
         */

        public LoginDetails loginWithEmail(String email, String password) {
            this.email = email;
            this.password = password;

            return LoginDetails.this;
        }
    }

      /**
     * @Class Register
     * This class is used for Registering user with requirdData
     * @neededparam String name,email,password,mobilenumber;
     */
    public class Register {
        String name, email, password, mobilenumber;

        public Register register_user(String name, String email,
                                               String password, String mobilenumber) {

            this.name = name;
            this.email = email;
            this.password = password;
            this.mobilenumber = mobilenumber;
                       return Register.this;
        }
    }


    /**
     * @Class PersonalProfile
     * This class is used for Registering user with requirdData
     * @neededparam String member_id,name,date,year,month,marital_status,gender,home_town,address;
     */
    public class PersonalProfile {
        String member_id,name,date,year,month,marital_status,gender,home_town,address;


        public PersonalProfile personalProfileDetails(String member_id,String name, String date,
                                      String year, String month,String marital_status, String gender,
                                                      String home_town, String address) {

            this.member_id=member_id;
            this.name=name;
            this.date=date;
            this.year=year;
            this.month=month;
            this.marital_status=marital_status;
            this.gender=gender;
            this.home_town=home_town;
            this.address=address;

            return PersonalProfile.this;
        }
    }
}
