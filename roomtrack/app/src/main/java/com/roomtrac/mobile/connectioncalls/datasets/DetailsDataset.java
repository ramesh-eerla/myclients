package com.roomtrac.mobile.connectioncalls.datasets;

import java.util.ArrayList;

/**
 * Created by ramesh.eerla on 3/9/2016.
 */
public class DetailsDataset {

    private String mKey, mDisplay, mFieldType, mIsRequired, mLabel, mFirst_Item, mSecond_Item, mThired_Item, mFourth_Item,
            mDate, mUser, mAction, mID, mRequired, mLabelVisible, mStatus, mEvent, mComment, mHeaderLabel;
    private ArrayList<DetailsDataset> mStatuslog;
    private ArrayList<NameIdDataset> nameIdDatasetArrayList;
    private boolean click, firstitemclick, seconditemclick, thirdItemclick, viewLine, mHasJobDesc;

    public boolean isThirdItemclick() {
        return thirdItemclick;
    }

    public void setThirdItemclick(boolean thirdItemclick) {
        this.thirdItemclick = thirdItemclick;
    }

    public String getmHeaderLabel() {
        return mHeaderLabel;
    }

    public void setmHeaderLabel(String mHeaderLabel) {
        this.mHeaderLabel = mHeaderLabel;
    }

    public boolean isViewLine() {
        return viewLine;
    }

    public void setmHasJobDesc(boolean mHasJobDesc) {
        this.mHasJobDesc = mHasJobDesc;
    }

    public boolean getmHasJobDesc() {
        return mHasJobDesc;
    }

    public void setViewLine(boolean viewLine) {
        this.viewLine = viewLine;
    }

    public void setOnClick(boolean click) {
        this.click = click;
    }

    public boolean getOnClick() {
        return click;
    }

    public void setOnFirstItmeClick(boolean firstitemclick) {
        this.firstitemclick = firstitemclick;
    }

    public boolean getOnFirstItmeClick() {
        return firstitemclick;
    }

    public void setOnSecondItmeClick(boolean seconditemclick) {
        this.seconditemclick = seconditemclick;
    }

    public boolean getOnSecondItmeClick() {
        return seconditemclick;
    }

    public void setStatuslog(ArrayList<DetailsDataset> mStatuslog) {
        this.mStatuslog = mStatuslog;
    }

    public ArrayList<DetailsDataset> getStatuslog() {
        return this.mStatuslog;
    }

    public void setKey(String mKey) {
        this.mKey = mKey;
    }

    public String getKey() {
        return this.mKey;
    }

    public void setFirstline(String mFirst_Item) {
        this.mFirst_Item = mFirst_Item;
    }

    public String getFirstline() {
        return this.mFirst_Item;
    }

    public void setSecondline(String mSecond_Item) {
        this.mSecond_Item = mSecond_Item;
    }

    public String getSecondline() {
        return this.mSecond_Item;
    }

    public void setThiredline(String mThired_Item) {
        this.mThired_Item = mThired_Item;
    }

    public String getThiredline() {
        return this.mThired_Item;
    }

    public void setFourthline(String mFourth_Item) {
        this.mFourth_Item = mFourth_Item;
    }

    public String getFourthline() {
        return this.mFourth_Item;
    }

    public void setDisplay(String mDisplay) {
        this.mDisplay = mDisplay;
    }

    public String getDisplay() {
        return this.mDisplay;
    }

    public void setRatevalues(String mFieldType) {
        this.mFieldType = mFieldType;
    }

    public String getRatevalues() {
        return this.mFieldType;
    }

    public void setIsRequired(String mIsRequired) {
        this.mIsRequired = mIsRequired;
    }

    public String getIsRequired() {
        return this.mIsRequired;
    }

    public void setLabel(String mLabel) {
        this.mLabel = mLabel;
    }

    public String getLabel() {
        return this.mLabel;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }

    public String getDate() {
        return this.mDate;
    }

    public void setUser(String mUser) {
        this.mUser = mUser;
    }

    public String getUser() {
        return this.mUser;
    }

    public void setComment(String mComment) {
        this.mComment = mComment;
    }

    public String getComment() {
        return this.mComment;
    }

    public void setAction(String mAction) {
        this.mAction = mAction;
    }

    public String getAction() {
        return this.mAction;
    }

    public void setID(String mID) {
        this.mID = mID;
    }

    public String getID() {
        return this.mID;
    }

    public void setRequired(String mRequired) {
        this.mRequired = mRequired;
    }

    public String getRequired() {
        return this.mRequired;
    }

    public void setLabelVisible(String mLabelVisible) {
        this.mLabelVisible = mLabelVisible;
    }

    public String getLabelVisible() {
        return this.mLabelVisible;
    }

    public void setStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public String getStatus() {
        return this.mStatus;
    }

    public void setEvent(String mEvent) {
        this.mEvent = mEvent;
    }

    public String getEvent() {
        return this.mEvent;
    }

    public ArrayList<NameIdDataset> getNameIdDatasetArrayList() {
        return nameIdDatasetArrayList;
    }

    public void setNameIdDatasetArrayList(ArrayList<NameIdDataset> nameIdDatasetArrayList) {
        this.nameIdDatasetArrayList = nameIdDatasetArrayList;
    }
}



