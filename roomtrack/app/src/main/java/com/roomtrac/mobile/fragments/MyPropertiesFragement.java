package com.roomtrac.mobile.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.roomtrac.mobile.R;
import com.roomtrac.mobile.activites.PropertyDetailsActivity;
import com.roomtrac.mobile.activites.SearchActivity;
import com.roomtrac.mobile.adapters.SearchRecyclerViewAdapter;
import com.roomtrac.mobile.connectioncalls.connections.RT_RetrofitSevicecall;
import com.roomtrac.mobile.connectioncalls.datasets.Dataset;
import com.roomtrac.mobile.connectioncalls.datasets.DetailsDataset;
import com.roomtrac.mobile.connectioncalls.datasets.LandingPageDataset;
import com.roomtrac.mobile.connectioncalls.datasets.LoginDataset;
import com.roomtrac.mobile.interfaces.OnItemClickListener;
import com.roomtrac.mobile.interfaces.ResponceCallback;
import com.roomtrac.mobile.services.RequestParams;
import com.roomtrac.mobile.utils.CommonUtils;
import com.roomtrac.mobile.utils.Constants;
import com.roomtrac.mobile.utils.JSONUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyPropertiesFragement extends Fragment implements View.OnClickListener, ResponceCallback {

    public static View.OnClickListener myOnClickListener;
    private static RecyclerView searchview;
    private AutoCompleteTextView searcheditbox;
    private Context mContext;
    private AppCompatImageView filter;
    private AppCompatButton search_button;
    private AppCompatTextView mSearch_area;
    private RecyclerView.LayoutManager mLayoutManager;
    private RT_RetrofitSevicecall mRt_retrofitSevicecall;
    private Bundle bundle;
    private int search_type;
    private ArrayList<String> list, data;
    private String list_data;
    private SearchRecyclerViewAdapter adapter;
    private String[] tabtitle = {"Rooms", "Appartments", "Roommates", "Paying Guest", "Hostel"};
    LoginDataset loginDataset;
    public MyPropertiesFragement() {
        // Required empty public constructor


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = this.getArguments();
        if (bundle != null) {
            search_type = bundle.getInt("TYPE", 0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.property_layout, null);
        mContext = getActivity();
        SharedPreferences mPrefs = mContext.getSharedPreferences("Login_repsonse", mContext.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        loginDataset = gson.fromJson(json, LoginDataset.class);
        mContext = getActivity();
        mRt_retrofitSevicecall = new RT_RetrofitSevicecall(mContext,MyPropertiesFragement.this);
        searchview = view.findViewById(R.id.searchview);
        searchview.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(mContext);
        searchview.setLayoutManager(mLayoutManager);
        loadsearchlist(loginDataset.getMember_id());
        return view;
    }


    public void loadSearchList(Object responce) {

        List<LinkedTreeMap> data = (List<LinkedTreeMap>) responce;
        List<DetailsDataset> detailsDataset=new ArrayList<>();
        if(search_type==Constants.MY_PROPERTIES)
            detailsDataset= JSONUtils.getSearchdata(data);
        else
            detailsDataset=JSONUtils.getBookmarkData(data);
        adapter = new SearchRecyclerViewAdapter(detailsDataset, mContext,search_type);
        searchview.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, ArrayList<DetailsDataset> landingPageDataset,String typw) {

            }

            @Override
            public void onItemClick_(int position, ArrayList<DetailsDataset> landingPageDataset) {
                CommonUtils.mSelecteditemDetails = landingPageDataset.get(position);
                startActivity(new Intent(mContext, PropertyDetailsActivity.class));
            }

            @Override
            public void onItemClick_dataset(int position, ArrayList<Dataset> landingPageDataset) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        int view_id = v.getId();

        switch (view_id) {

            case R.id.btn_submit:

                break;

        }


    }

    private void loadsearchlist(String member_id) {

        RequestParams.MyProperties searchTypes= new RequestParams().new MyProperties().getproperties(member_id,search_type);
        mRt_retrofitSevicecall.searchPost(search_type, searchTypes);

    }

    /*
    * http://qaroomtrac.wizardtechnologiesprivatelimited.com/apidata/api/Api/getpropertydetails-post
    {
       "member_id":"226" //after login get using member_id
    }*/
    @Override
    public void callback(JSONObject responce) {

    }

    @Override
    public void callback(Object responce, int requesttype) {
        loadSearchList(responce);
    }

    @Override
    public void errorcallback(String errortitle, String errormessage) {

    }
}
