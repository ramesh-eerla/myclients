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

import com.google.gson.Gson;
import com.roomtrac.mobile.R;
import com.roomtrac.mobile.activites.PostPropertyActivity;
import com.roomtrac.mobile.activites.SearchActivity;
import com.roomtrac.mobile.connectioncalls.datasets.LoginDataset;
import com.roomtrac.mobile.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountSettingsFragement extends Fragment implements View.OnClickListener {

    private AppCompatButton btn_submit;
    private AppCompatTextView email,email_value,old_pwd,new_pwd,cnf_pwd;
    private AppCompatEditText old_pwd_value,new_pwd_value,cnf_pwd_value;
    private Context mContext;
    private LoginDataset loginDataset;
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

        btn_submit.setOnClickListener(this);
        return view;
    }
    @Override
    public void onClick(View v) {
        int view_id = v.getId();

        switch (view_id) {

            case R.id.btn_submit:

                break;

        }


    }
}
