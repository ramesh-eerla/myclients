package com.roomtrac.mobile.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.roomtrac.mobile.R;
import com.roomtrac.mobile.adapters.CustomAdapter;
import com.roomtrac.mobile.utils.CommonHelper;
import com.roomtrac.mobile.utils.CommonUtils;

import java.util.ArrayList;


public class Property_Gallery extends Fragment {

    RecyclerView gridview;

    public ArrayList<String> osImages ;

    public Property_Gallery() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.property_gallery_fragment, container, false);
        gridview =  root.findViewById(R.id.customgrid);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false);
        gridview.setLayoutManager(manager);
        osImages= CommonHelper.populateList(CommonUtils.mSelecteditemDetails);
        gridview.setAdapter(new CustomAdapter(getActivity(), osImages));
        // Inflate the layout for this fragment
        return root;
    }

}