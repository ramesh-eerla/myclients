package com.roomtrac.mobile.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.roomtrac.mobile.R;
import com.roomtrac.mobile.activites.PostPropertyActivity;
import com.roomtrac.mobile.activites.SearchActivity;
import com.roomtrac.mobile.utils.CommonUtils;
import com.roomtrac.mobile.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragement extends Fragment implements View.OnClickListener {

    AppCompatButton appartments, rooms, roommates, hostels, payingguest, postyourproperty;
    int catageri = 0;
    private Context mContext;
    public HomeFragement() {
        // Required empty public constructor
        mContext=getActivity();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.buttonslayout,null);
        appartments = v.findViewById(R.id.appartments);
        mContext=getActivity();
        rooms = v.findViewById(R.id.rooms);
        roommates = v.findViewById(R.id.roommates);
        hostels = v.findViewById(R.id.hostels);
        payingguest = v.findViewById(R.id.payingguest);
        postyourproperty = v.findViewById(R.id.postyourproperty);
        appartments.setOnClickListener(this);
        rooms.setOnClickListener(this);
        roommates.setOnClickListener(this);
        hostels.setOnClickListener(this);
        payingguest.setOnClickListener(this);
        postyourproperty.setOnClickListener(this);
        return v;
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
            mContext.startActivity(new Intent(mContext, PostPropertyActivity.class));

        } else {
            CommonUtils.applyfilter=false;
            mContext.startActivity(new Intent(mContext, SearchActivity.class).putExtra(Constants.RT_SEACH_TYPE, catageri));
        }

    }
}
