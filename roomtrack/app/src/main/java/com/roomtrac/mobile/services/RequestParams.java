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
     * @Class SearchTypes
     * This class is used for searching rooms
     * @dparam String searchtype;
     * Boolean AutoLogin;
     */
    public class SearchValues {
        String searchvalue;

        public SearchValues setsearch(String search_type) {
             this.searchvalue = search_type;
            return SearchValues.this;
        }
    }
    /**
     * @Class SearchTypes
     * This class is used for searching rooms
     * @dparam String searchtype;
     * Boolean AutoLogin;
     */
    public class SearchTypes {
       /* String state_id,city_id,location_id,search_type;*/
        int search_type;
        /*int rent_id,hostel_id;*/

        public SearchTypes setsearch(String state_id,String city_id,String location_id,int rent_id) {

            if(rent_id==5)
                this.search_type=1;
            /*else
                this.search_type=rent_id;*/

            this.search_type = rent_id;
           /* this.city_id=city_id;
            this.location_id=location_id;
            if(rent_id==5)
                this.hostel_id=1;
            else
                this.rent_id=rent_id;*/
            return SearchTypes.this;
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

  public class  Hostel_POST{
      //qaroomtrac.wizardtechnologiesprivatelimited.com/apidata/api/Api/havehostel
        //date dd/mm/yyyy
      String member_id,hostel_id,hosteltype,hostelfood_type,hostelfurnish_type,hostelfacility,hostelareasqfeet,hostelrent_per_month,
      hostelmembers_per_room,hostelvacency_for,payingmin_age,payingmax_age,hostelphone_number,hostelavailibility_date,hostelnature,
      country,state,city,location,hosteldescaboutroom,hosteluploadphotos;

      public Hostel_POST set_submitdata(String member_id,String hostel_id,String hosteltype,String hostelfood_type,String hostelfurnish_type,String hostelfacility,String hostelareasqfeet,String hostelrent_per_month,
                                        String  hostelmembers_per_room,String hostelvacency_for,String payingmin_age,String payingmax_age,String hostelphone_number,String hostelavailibility_date,String hostelnature,
                                        String country,String state,String city,String location,String hosteldescaboutroom,String hosteluploadphotos){
          this.member_id=member_id;
          this.hostel_id=hostel_id;
          this.hosteltype=hosteltype;
          this.hostelfood_type=hostelfood_type;
          this.hostelfurnish_type=hostelfurnish_type;
          this.hostelfacility=hostelfacility;
          this.hostelareasqfeet=hostelareasqfeet;
          this.hostelrent_per_month=hostelrent_per_month;
          this.hostelmembers_per_room=hostelmembers_per_room;
          this.hostelvacency_for=hostelvacency_for;
          this.payingmin_age=payingmin_age;
          this.payingmax_age=payingmax_age;
          this.hostelphone_number=hostelphone_number;
          this.hostelavailibility_date=hostelavailibility_date;
          this.hostelnature=hostelnature;
          this.country=country;
          this.state=state;
          this.city=city;
          this.location=location;
          this.hosteldescaboutroom=hosteldescaboutroom;
          this.hosteluploadphotos=hosteluploadphotos;
          return Hostel_POST.this;
      }
  }

    public class  PayingGust_POST{
        //qaroomtrac.wizardtechnologiesprivatelimited.com/apidata/api/Api/payingguest
        //date dd/mm/yyyy
        String member_id,rent_id,payingtagline,roomtype,pauyingfood_type,payingfurnish_type,payingfacility,payingareasqfeet,payingrent_per_month,
                payingmembers_per_room,payingvacency_for,guest_gender,payingmin_age,payingmax_age,payingphone_number,roomavailibility_date,
                country,state,city,location,descpaying,payinguploadphotos;
        public PayingGust_POST set_submitdata(String member_id,String payingtagline, String hostel_id, String hosteltype, String hostelfood_type, String hostelfurnish_type, String hostelfacility, String hostelareasqfeet, String hostelrent_per_month,
                                          String  hostelmembers_per_room, String hostelvacency_for,String guest_gender, String payingmin_age, String payingmax_age, String hostelphone_number, String hostelavailibility_date,
                                          String country, String state, String city, String location, String hosteldescaboutroom, String hosteluploadphotos){
            this.member_id=member_id;
            this.rent_id=hostel_id;
            this.payingtagline=payingtagline;
            this.roomtype=hosteltype;
            this.pauyingfood_type=hostelfood_type;
            this.payingfurnish_type=hostelfurnish_type;
            this.payingfacility=hostelfacility;
            this.payingareasqfeet=hostelareasqfeet;
            this.payingrent_per_month=hostelrent_per_month;
            this.payingmembers_per_room=hostelmembers_per_room;
            this.payingvacency_for=hostelvacency_for;
            this.payingmin_age=payingmin_age;
            this.payingmax_age=payingmax_age;
            this.payingphone_number=hostelphone_number;
            this.roomavailibility_date=hostelavailibility_date;
            this.guest_gender=guest_gender;
            this.country=country;
            this.state=state;
            this.city=city;
            this.location=location;
            this.descpaying=hosteldescaboutroom;
            this.payinguploadphotos=hosteluploadphotos;
            return PayingGust_POST.this;
        }
    }
    public class  Roommate_POST{
        //qaroomtrac.wizardtechnologiesprivatelimited.com/apidata/api/Api/postroommate
        //date dd/mm/yyyy
        String member_id,rent_id,roomatestagline,roomtype,roomfood_type,roomfurnish_type,roomfacility,roomareasqfeet,
                roomrent_per_month,roommembers_per_room,roomvacency_for,roomsex,roommin_age,roommax_age,
                roomphone_number,roomavailibility_date,country,state,city,location,descaboutroomates,uploadsphotos;


        public Roommate_POST set_submitdata(String member_id, String payingtagline, String hostel_id, String hosteltype, String hostelfood_type, String hostelfurnish_type, String hostelfacility, String hostelareasqfeet, String hostelrent_per_month,
                                              String  hostelmembers_per_room, String hostelvacency_for, String guest_gender, String payingmin_age, String payingmax_age, String hostelphone_number, String hostelavailibility_date,
                                              String country, String state, String city, String location, String hosteldescaboutroom, String hosteluploadphotos){
            this.member_id=member_id;
            this.rent_id=hostel_id;
            this.roomatestagline=payingtagline;
            this.roomtype=hosteltype;
            this.roomfood_type=hostelfood_type;
            this.roomfurnish_type=hostelfurnish_type;
            this.roomfacility=hostelfacility;
            this.roomareasqfeet=hostelareasqfeet;
            this.roomrent_per_month=hostelrent_per_month;
            this.roommembers_per_room=hostelmembers_per_room;
            this.roomvacency_for=hostelvacency_for;
            this.roommin_age=payingmin_age;
            this.roommax_age=payingmax_age;
            this.roomphone_number=hostelphone_number;
            this.roomavailibility_date=hostelavailibility_date;
            this.roomsex=guest_gender;
            this.country=country;
            this.state=state;
            this.city=city;
            this.location=location;
            this.descaboutroomates=hosteldescaboutroom;
            this.uploadsphotos=hosteluploadphotos;
            return Roommate_POST.this;
        }
    }

    public class  Home_POST{
        //qaroomtrac.wizardtechnologiesprivatelimited.com/apidata/api/Api/postroomhomeorappt
        //date dd/mm/yyyy
        String member_id,rent_id,apptagline,Home_Apttype,appfurnish_type,appareasqfeet,
                apptrent_per_month,Preference,apptmin_age,apptmax_age,
                appphone_number,appavailibility_date,country,state,city,location,appdescaboutroom,appuploadsphotos;





        public Home_POST set_submitdata(String member_id, String payingtagline, String hostel_id, String hosteltype,  String hostelfurnish_type,  String hostelareasqfeet, String hostelrent_per_month,
                                             String guest_gender, String payingmin_age, String payingmax_age, String hostelphone_number, String hostelavailibility_date,
                                            String country, String state, String city, String location, String hosteldescaboutroom, String hosteluploadphotos){
            this.member_id=member_id;
            this.rent_id=hostel_id;
            this.apptagline=payingtagline;
            this.Home_Apttype=hosteltype;
            this.appfurnish_type=hostelfurnish_type;
            this.appareasqfeet=hostelareasqfeet;
            this.apptrent_per_month=hostelrent_per_month;
            this.apptmin_age=payingmin_age;
            this.apptmax_age=payingmax_age;
            this.appphone_number=hostelphone_number;
            this.appavailibility_date=hostelavailibility_date;
            this.Preference=guest_gender;
            this.country=country;
            this.state=state;
            this.city=city;
            this.location=location;
            this.appdescaboutroom=hosteldescaboutroom;
            this.appuploadsphotos=hosteluploadphotos;
            return Home_POST.this;
        }
    }
    public class  Room_POST{
        //qaroomtrac.wizardtechnologiesprivatelimited.com/apidata/api/Api/postroomproperty
        //date dd/mm/yyyy
        String member_id,rent_id,roomtag,roomtype,food_type,furnish_type,roomfacility,areasqfeet,
                rent_per_month,members_per_room,vacency_for,gender,min_age,max_age,
                phone_number,availibility_date,country,state,city,location,descaboutroom,uploadsphotos;

        public Room_POST set_submitdata(String member_id, String payingtagline, String hostel_id, String hosteltype, String hostelfood_type, String hostelfurnish_type, String hostelfacility, String hostelareasqfeet, String hostelrent_per_month,
                                            String  hostelmembers_per_room, String hostelvacency_for, String guest_gender, String payingmin_age, String payingmax_age, String hostelphone_number, String hostelavailibility_date,
                                            String country, String state, String city, String location, String hosteldescaboutroom, String hosteluploadphotos){
            this.member_id=member_id;
            this.rent_id=hostel_id;
            this.roomtag=payingtagline;
            this.roomtype=hosteltype;
            this.food_type=hostelfood_type;
            this.furnish_type=hostelfurnish_type;
            this.roomfacility=hostelfacility;
            this.areasqfeet=hostelareasqfeet;
            this.rent_per_month=hostelrent_per_month;
            this.members_per_room=hostelmembers_per_room;
            this.vacency_for=hostelvacency_for;
            this.min_age=payingmin_age;
            this.max_age=payingmax_age;
            this.phone_number=hostelphone_number;
            this.availibility_date=hostelavailibility_date;
            this.gender=guest_gender;
            this.country=country;
            this.state=state;
            this.city=city;
            this.location=location;
            this.descaboutroom=hosteldescaboutroom;
            this.uploadsphotos=hosteluploadphotos;
            return Room_POST.this;
        }
    }
}
