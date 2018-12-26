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
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.roomtrac.mobile.R;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountSettingsFragement extends Fragment implements View.OnClickListener,ResponceCallback {

    private AppCompatButton btn_submit;
    private AppCompatTextView email,email_value,old_pwd,new_pwd,cnf_pwd;
    private AppCompatEditText old_pwd_value,new_pwd_value,cnf_pwd_value;
    private Context mContext;
    private LoginDataset loginDataset;
    private String old_pwd_Data,new_pwd_data;
    private RT_RetrofitSevicecall mRt_retrofitSevicecall;
    private LinearLayout change_pwd_layout,view_profile_layout;
     public AccountSettingsFragement() {
        // Required empty public constructor


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.account_settings,null);
        mContext=getActivity();
        SharedPreferences mPrefs = mContext.getSharedPreferences("Login_repsonse",mContext.MODE_PRIVATE);
        Gson gson = new Gson();
        mRt_retrofitSevicecall=new RT_RetrofitSevicecall(mContext,AccountSettingsFragement.this);
        String json = mPrefs.getString("MyObject", "");
        loginDataset= gson.fromJson(json, LoginDataset.class);
        btn_submit=view.findViewById(R.id.btn_submit);
        email=view.findViewById(R.id.email_lable);
        email_value=view.findViewById(R.id.email_value);
        old_pwd=view.findViewById(R.id.old_pwd_lable);
        new_pwd=view.findViewById(R.id.new_pwd_lable);
        cnf_pwd=view.findViewById(R.id.cnf_pwd_lable);

        email_value.setText(loginDataset.getEmail());
        old_pwd_value=view.findViewById(R.id.oldpwd_value);
        new_pwd_value=view.findViewById(R.id.newpwed_value);
        cnf_pwd_value=view.findViewById(R.id.confirmpwd_value);
        change_pwd_layout=view.findViewById(R.id.change_pwd_layout);
        view_profile_layout=view.findViewById(R.id.view_profile_layout);

        btn_submit.setOnClickListener(this);
        return view;
    }

    private void getallvalues() {
        old_pwd_Data=old_pwd_value.getText().toString();
        new_pwd_data=new_pwd_value.getText().toString();

    }
    private boolean validatevalues(){
        boolean status=false;

        status=old_pwd_Data.equalsIgnoreCase("")? true:false;
        status = new_pwd_data.equalsIgnoreCase("") ? true : status;


        return status;
    }
    @Override
    public void onClick(View v) {
        int view_id = v.getId();

        switch (view_id) {
//changepassword
            case R.id.btn_submit:

                getallvalues();
                boolean status=validatevalues();
                if(status)
                    CommonHelper.showSingleActionAlertDiaolog(mContext,"Alert","Please enter value for required fields",1);
                else {
                    RequestParams.ChangePassword personalProfile = new RequestParams().new ChangePassword();
                    personalProfile.updatepwd(CommonUtils.member_id,old_pwd_Data,new_pwd_data);
                    mRt_retrofitSevicecall.submitPost(6,"changepassword",personalProfile);
                }

                break;

        }


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
