package com.roomtrac.mobile.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.roomtrac.mobile.R;
import com.roomtrac.mobile.Uicomponents.ApprovalFontCommons;
import com.roomtrac.mobile.activites.PostPropertyActivity;
import com.roomtrac.mobile.activites.SearchActivity;
import com.roomtrac.mobile.connectioncalls.connections.RT_RetrofitSevicecall;
import com.roomtrac.mobile.connectioncalls.datasets.LoginDataset;
import com.roomtrac.mobile.connectioncalls.datasets.RetrofitResponse;
import com.roomtrac.mobile.controller.DateValidater;
import com.roomtrac.mobile.interfaces.ResponceCallback;
import com.roomtrac.mobile.services.RequestParams;
import com.roomtrac.mobile.utils.CommonHelper;
import com.roomtrac.mobile.utils.CommonUtils;
import com.roomtrac.mobile.utils.Constants;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditetProfileFragement extends Fragment implements View.OnClickListener,ResponceCallback {

    AppCompatButton btn_submit;
    AppCompatTextView name,dob,marital,gender,hometown,address;
    AppCompatSpinner day,month,year,marital_spinner,gender_spinner;
    AppCompatEditText edit_name,edit_hometown,edit_address;
    private int catageri = 0,day_;
    private String[] days;
    private ArrayList<String> yers;
    private Context mContext;
    LoginDataset loginDataset;
    private RT_RetrofitSevicecall mRt_retrofitSevicecall;
    String name_valu,day_value,month_value,year_value,marital_status,gendervalu,hometown_value,address_value;
    public EditetProfileFragement() {
        // Required empty public constructor


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.editeprofile,null);
        mContext=getActivity();
        SharedPreferences mPrefs = mContext.getSharedPreferences("Login_repsonse",mContext.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        loginDataset= gson.fromJson(json, LoginDataset.class);
        btn_submit = view.findViewById(R.id.btn_submit);
        mRt_retrofitSevicecall=new RT_RetrofitSevicecall(mContext,EditetProfileFragement.this);
        name = view.findViewById(R.id.name_lable);
        dob = view.findViewById(R.id.dob_lable);
        marital = view.findViewById(R.id.merital_lable);
        gender = view.findViewById(R.id.gender_lable);
        hometown = view.findViewById(R.id.home_town_lable);
        address= view.findViewById(R.id.address_lable);

        /*edittext init*/
        edit_name = view.findViewById(R.id.name_edit_value);
        edit_hometown = view.findViewById(R.id.home_twon_edit_value);
        edit_address = view.findViewById(R.id.address_edit_value);


        /*spinner init*/
        marital_spinner = view.findViewById(R.id.marital_spinner);
        gender_spinner = view.findViewById(R.id.gender_spinner);
        day = view.findViewById(R.id.day);
        month = view.findViewById(R.id.month);
        year = view.findViewById(R.id.year);
        intValues();

        btn_submit.setOnClickListener(this);

        return view;
    }

    private void intValues() {

        name.setText(ApprovalFontCommons.manditoryString(name.getText().toString()));
        marital.setText(ApprovalFontCommons.manditoryString(marital.getText().toString()));
        dob.setText(ApprovalFontCommons.manditoryString(dob.getText().toString()));
        gender.setText(ApprovalFontCommons.manditoryString(gender.getText().toString()));
        hometown.setText(ApprovalFontCommons.manditoryString(hometown.getText().toString()));
        address.setText(ApprovalFontCommons.nonmaditoryStringlable(address.getText().toString()));

        edit_name.setText(loginDataset.getFirst_name());
        final Calendar calendar=Calendar.getInstance(TimeZone.getDefault());
        yers=new ArrayList<>();
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 1900; i <= thisYear; i++) {
            yers.add(Integer.toString(i));
        }
        year.setAdapter(new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item,yers));
        day.setAdapter(new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item,Constants.month));
        day.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                loadday(position,calendar.get(Calendar.YEAR),month.getSelectedItemPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                if(position!=0)
                    loadday(day.getSelectedItemPosition(),Integer.parseInt(yers.get(position)),month.getSelectedItemPosition()) ;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        getadapter(R.array.gendertype,gender_spinner);

        month.setSelection(CommonHelper.convertInteger(loginDataset.getDob_date()));
        day.setSelection(CommonHelper.convertInteger(loginDataset.getDob_month()));
        year.setSelection(CommonHelper.convertInteger(loginDataset.getDob_year()));
        marital_spinner.setSelection(getposition(loginDataset.getMarital_status()));
        gender_spinner.setSelection(getposition(loginDataset.getSex()));
        edit_hometown.setText(loginDataset.getHome_town());
        edit_address.setText(loginDataset.getAddress());

    }

    private int getposition(String marital_status) {
        int positio =0;
        switch (marital_status){
            case "Married":
                return 1;
            case "Un-Married":
                return 2;
            case "Male":
                return 0;
            case "Female":
                return 1;
        }

    return positio;
    }

    private void loadday(int position, int year,int day_selecion) {
        day_ = day_selecion;
        if (position == 0) {
            days = new String[]{"Day"};

        } else if (position == 4 || position == 6 || position == 9 || position == 11)
            days = Constants.days_30;
        else if (position == 2)
            days = year/4==0 ? Constants.days_29 : Constants.days_28;
        else
            days = Constants.days_31;

        month.setAdapter(new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item,
                days));
        month.setSelection(day_);
    }

    private void getadapter(int arreyid, Spinner spinner) {
        List<String> spinnerArray = Arrays.asList(getResources().getStringArray(arreyid));
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,spinnerArray); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);
    }
    @Override
    public void onClick(View v) {
        int view_id = v.getId();
        getallvalues();
        boolean status=validatevalues();
        if(status)
            CommonHelper.showSingleActionAlertDiaolog(mContext,"Alert","Please enter value for required fields",1);
else {
            SharedPreferences mPrefs = mContext.getSharedPreferences("Login_repsonse",mContext.MODE_PRIVATE);
            SharedPreferences.Editor prefsEditor = mPrefs.edit();
           loginDataset.setMarital_status(marital_status);
           loginDataset.setActivate(address_value);
           loginDataset.setHome_town(hometown_value);
           loginDataset.setSex(gendervalu);
           loginDataset.setDob_month(""+day.getSelectedItemPosition());
           loginDataset.setDob_date(day_value);
           loginDataset.setDob_year(year_value);
           loginDataset.setFirst_name(name_valu);
            Gson gson = new Gson();
            String json = gson.toJson(loginDataset);
            prefsEditor.putString("MyObject", json);
            prefsEditor.commit();

            RequestParams.PersonalProfile personalProfile = new RequestParams().new PersonalProfile();
            personalProfile.personalProfileDetails(CommonUtils.member_id,name_valu,day_value,year_value,month_value,marital_status,gendervalu,hometown_value,address_value );
            mRt_retrofitSevicecall.submitPost(5,"personalprofile",personalProfile);
        }
       //
    }
    private boolean validatevalues(){
        boolean status=false;

        status=name_valu.equalsIgnoreCase("")? true:false;
        status = marital_status.equalsIgnoreCase("Select") ? true : status;
        status=day_value.equalsIgnoreCase("Day")? true:status;
        status=month_value.equalsIgnoreCase("Month")? true:status;
        status=year_value.equalsIgnoreCase("Year")? true:status;
        status=hometown_value.equalsIgnoreCase("")? true:status;

        return status;
    }

    private void getallvalues() {
        name_valu=edit_name.getText().toString();
        day_value=month.getSelectedItem().toString();
        month_value=new DateValidater().getCalenderMonth(day.getSelectedItem().toString());
        year_value=year.getSelectedItem().toString();
        marital_status=marital_spinner.getSelectedItem().toString();
        gendervalu=gender_spinner.getSelectedItem().toString();
        hometown_value=edit_hometown.getText().toString();
        address_value=edit_address.getText().toString();
    }

    @Override
    public void callback(JSONObject responce) {

    }

    @Override
    public void callback(Object responce, int requesttype) {
        RetrofitResponse rence=(RetrofitResponse)responce;
        CommonHelper.setalertdialog_(mContext,"Success",rence.getMessage());
    }

    @Override
    public void errorcallback(String errortitle, String errormessage) {

    }
}
