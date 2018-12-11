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
import com.roomtrac.mobile.connectioncalls.datasets.LoginDataset;
import com.roomtrac.mobile.utils.Constants;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditetProfileFragement extends Fragment implements View.OnClickListener {

    AppCompatButton btn_submit;
    AppCompatTextView name,dob,marital,gender,hometown,address;
    AppCompatSpinner day,month,year,marital_spinner,gender_spinner;
    AppCompatEditText edit_name,edit_hometown,edit_address;
    private int catageri = 0,day_;
    private String[] days,yers;
    private Context mContext;
    LoginDataset loginDataset;
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



        return view;
    }

    private void intValues() {

        name.setText(ApprovalFontCommons.manditoryString(name.getText().toString()));
        marital.setText(ApprovalFontCommons.manditoryString(marital.getText().toString()));
        dob.setText(ApprovalFontCommons.manditoryString(dob.getText().toString()));
        gender.setText(ApprovalFontCommons.manditoryString(gender.getText().toString()));
        hometown.setText(ApprovalFontCommons.manditoryString(hometown.getText().toString()));
        address.setText(ApprovalFontCommons.nonmaditoryStringlable(address.getText().toString()));

        edit_name.setText(loginDataset.getEmail());
        final Calendar calendar=Calendar.getInstance(TimeZone.getDefault());
        yers=new String[]{"Year",""+calendar.get(Calendar.YEAR),""+(calendar.get(Calendar.YEAR)+1)};
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
                    loadday(day.getSelectedItemPosition(),Integer.parseInt(yers[position]),month.getSelectedItemPosition()) ;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        getadapter(R.array.gendertype,gender_spinner);
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

        switch (view_id) {

            case R.id.appartments:
                catageri = 2;
                break;
            case R.id.rooms:
                catageri = 1;
                break;
            case R.id.roommates:
                catageri = 3;
                break;
            case R.id.hostels:
                catageri = 5;
                break;
            case R.id.payingguest:
                catageri = 4;
                break;

        }
        if (v.getId() == R.id.postyourproperty) {
            startActivity(new Intent(mContext, PostPropertyActivity.class));

        } else {
            startActivity(new Intent(mContext, SearchActivity.class).putExtra(Constants.RT_SEACH_TYPE, catageri));
        }

    }
}
