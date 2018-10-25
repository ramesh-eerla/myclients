package com.roomtrack.mobile.interfaces;



import java.util.ArrayList;


/**
 * Created by Ramesh.eerla on 24/10/2018.
 */
public interface OnItemClickListener {
    public void onItemClick(int position, ArrayList<LandingPageDataset> landingPageDataset);

    public void onItemClick_(int position, ArrayList<DetailsDataset> landingPageDataset);

    public void onItemClick_dataset(int position, ArrayList<Dataset> landingPageDataset);
}

