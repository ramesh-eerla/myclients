package com.roomtrack.mobile.interfaces;

import com.dcrworkforce.mobile.dataset.DetailsDataset;
import com.dcrworkforce.mobile.dataset.LandingPageDataset;

import java.util.ArrayList;

import com.dcrworkforce.mobile.dataset.Dataset;

/**
 * Created by ramesh.eerla on 3/25/2016.
 */
public interface OnItemClickListener {
    public void onItemClick(int position, ArrayList<LandingPageDataset> landingPageDataset);

    public void onItemClick_(int position, ArrayList<DetailsDataset> landingPageDataset);

    public void onItemClick_dataset(int position, ArrayList<Dataset> landingPageDataset);
}

