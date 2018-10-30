package com.roomtrac.mobile.interfaces;



import com.roomtrac.mobile.connectioncalls.datasets.Dataset;
import com.roomtrac.mobile.connectioncalls.datasets.DetailsDataset;
import com.roomtrac.mobile.connectioncalls.datasets.LandingPageDataset;

import java.util.ArrayList;


/**
 * Created by Ramesh.eerla on 24/10/2018.
 */
public interface OnItemClickListener {
    public void onItemClick(int position, ArrayList<LandingPageDataset> landingPageDataset);

    public void onItemClick_(int position, ArrayList<DetailsDataset> landingPageDataset);

    public void onItemClick_dataset(int position, ArrayList<Dataset> landingPageDataset);
}

