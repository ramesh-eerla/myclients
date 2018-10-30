package com.roomtrac.mobile.connectioncalls.datasets;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ramesh.eerla on 2/25/2016.
 */
public class LandingPageDataset {
    public String mAcordin_name;
    public String mstatus;
    public String mKey_url;
    public String mTS_value;
    public String module_name;
    public String module_url;
    public String mFirstlineitem;
    public String mSecondlineitem;
    public String mThiredlineitem;
    public String mFourthlineitem;
    public String assesment;
    public String interview;
    public String offer;
    public String neededStartDate;
    public boolean click;
    public String getOfferslbl() {
        return Offerslbl;
    }

    public void setOfferslbl(String offerslbl) {
        Offerslbl = offerslbl;
    }

    public String Offerslbl;
    public int moudlecount,mMspID,mClientID,module_id,requirementid,mModuel_key,mCandidateid,mCWRatechangeDetailID,mAcordin_key,mInterviewID;
    public ArrayList<LandingPageDataset> Approvalsmoduledata,timesheetmoduledata,fullfilmentdata;
    public JSONObject requestobject;
    public boolean mHasoffertohire,mHasAssignment;

    public boolean getHasoffertohire() {
        return mHasoffertohire;
    }

    public void setHasoffertohire(boolean mHasoffertohire) {
        this.mHasoffertohire = mHasoffertohire;
    }
    public void setOnClick(boolean click){this.click=click;}
    public boolean getOnClick(){return click;}


    public boolean getHasAssignment() {
        return mHasAssignment;
    }

    public void setHasAssignment(boolean mHasAssignment) {
        this.mHasAssignment = mHasAssignment;
    }

    public int getMspID() {
        return mMspID;
    }

    public void setMspID(int mMspID) {
        this.mMspID = mMspID;
    }

    public void setClientID(int mClientID){this.mClientID = mClientID;}
    public int getClientID() {
        return mClientID;
    }

    public void setModule_name(String module_name){this.module_name=module_name; }
    public String getModule_name(){return this.module_name; }

    public void setKey_url(String mKey_url){this.mKey_url=mKey_url; }
    public String getKey_url(){return this.mKey_url; }

    public void setStatus(String mstatus){this.mstatus=mstatus; }
    public String getStatus(){return this.mstatus; }

    public void setAccordians_key(int mAcordin_key){this.mAcordin_key=mAcordin_key; }
    public int getAccordians_key(){return this.mAcordin_key; }

    public void setAccordians_name(String mAcordin_name){this.mAcordin_name=mAcordin_name; }
    public String getAccordians_name(){return this.mAcordin_name; }

    public void setModule_key(int mModuel_key){this.mModuel_key=mModuel_key; }
    public int getModule_key(){return this.mModuel_key; }

    public void setModule_ID(int module_id){this.module_id=module_id; }
    public int getModule_ID(){return this.module_id; }

    public void setMoudlecount(int moudlecount){this.moudlecount=moudlecount; }
    public int getMoudlecount(){return this.moudlecount; }

    public void setTS_value(String mTS_value){this.mTS_value=mTS_value; }
    public String getTS_value(){return this.mTS_value; }

    public void setModule_url(String module_url){this.module_url=module_url; }
    public String getModule_url(){return this.module_url; }

    public void setAssesment(String assesment){this.assesment=assesment; }
    public String getAssesment(){return this.assesment; }

    public void setInterview(String interview){this.interview=interview; }
    public String getInterview(){return this.interview; }

    public void setOffer(String offer){this.offer=offer; }
    public String getOffer(){return this.offer; }

    public void setFirstLineitem(String mFirstlineitem){this.mFirstlineitem=mFirstlineitem; }
    public String getFirstLineitem(){

        return this.mFirstlineitem; }

    public void setSecondLineitem(String mSecondlineitem){this.mSecondlineitem=mSecondlineitem; }
    public String getSecondLineitem(){

        return this.mSecondlineitem; }

    public void setThiredLineitem(String mThiredlineitem){this.mThiredlineitem=mThiredlineitem; }
    public String getThiredLineitem(){

        return this.mThiredlineitem; }

    public void setFourthLineitem(String mFourthlineitem){this.mFourthlineitem=mFourthlineitem; }
    public String getFourthLineitem(){

        return this.mFourthlineitem; }



    public void setRequirementid(int requirementid){this.requirementid=requirementid; }
    public int getRequirementid(){return this.requirementid; }

    public void setCandidateid(int mCandidateid){this.mCandidateid=mCandidateid; }
    public int getCandidateid(){return this.mCandidateid; }


    public void setCWRatechangeDetailID(int mCWRatechangeDetailID){this.mCWRatechangeDetailID=mCWRatechangeDetailID; }
    public int getCWRatechangeDetailID(){return this.mCWRatechangeDetailID; }

    public void setApprovalsModuledata(ArrayList<LandingPageDataset> Approvalsmoduledata){this.Approvalsmoduledata=Approvalsmoduledata; }
    public ArrayList<LandingPageDataset> getApprovalsModuledata(){return this.Approvalsmoduledata; }


    public void setRequestobject(JSONObject requestobject){this.requestobject=requestobject; }
    public JSONObject getRequestobject(){return this.requestobject; }

    public void setTimesheetmoduledata(ArrayList<LandingPageDataset> timesheetmoduledata){this.timesheetmoduledata=timesheetmoduledata; }
    public ArrayList<LandingPageDataset> getTimesheetmoduledata(){return this.timesheetmoduledata; }

public void setFullfilmentdata(ArrayList<LandingPageDataset> fullfilmentdata){this.fullfilmentdata=fullfilmentdata;}
    public ArrayList<LandingPageDataset> getFullfilmentdata(){return fullfilmentdata;};

    public void setInterviewID(int mInterviewID) {
        this.mInterviewID=mInterviewID;
    }
public int getInterviewID(){
    return mInterviewID;
}






    /*statuslog,confirmmessages,outlinedata,jobdescription,approvallog,accordionbuttons
    public void setApprovebuttons(ArrayList<LandingPageDataset> approvebuttons){this.approvebuttons=approvebuttons; }
    public ArrayList<LandingPageDataset>getApprovebuttons(){return this.approvebuttons; }

    public void setStatuslog(ArrayList<LandingPageDataset> statuslog){this.statuslog=statuslog;}
    public ArrayList<LandingPageDataset>getFullfilmentdata(){return fullfilmentdata;};
    public void setTimesheetmoduledata(ArrayList<LandingPageDataset> timesheetmoduledata){this.timesheetmoduledata=timesheetmoduledata; }
    public ArrayList<LandingPageDataset>getTimesheetmoduledata(){return this.timesheetmoduledata; }

    public void setFullfilmentdata(ArrayList<LandingPageDataset> fullfilmentdata){this.fullfilmentdata=fullfilmentdata;}
    public ArrayList<LandingPageDataset>getFullfilmentdata(){return fullfilmentdata;};
    public void setTimesheetmoduledata(ArrayList<LandingPageDataset> timesheetmoduledata){this.timesheetmoduledata=timesheetmoduledata; }
    public ArrayList<LandingPageDataset>getTimesheetmoduledata(){return this.timesheetmoduledata; }

    public void setFullfilmentdata(ArrayList<LandingPageDataset> fullfilmentdata){this.fullfilmentdata=fullfilmentdata;}
    public ArrayList<LandingPageDataset>getFullfilmentdata(){return fullfilmentdata;};
    public void setTimesheetmoduledata(ArrayList<LandingPageDataset> timesheetmoduledata){this.timesheetmoduledata=timesheetmoduledata; }
    public ArrayList<LandingPageDataset>getTimesheetmoduledata(){return this.timesheetmoduledata; }

    public void setFullfilmentdata(ArrayList<LandingPageDataset> fullfilmentdata){this.fullfilmentdata=fullfilmentdata;}
    public ArrayList<LandingPageDataset>getFullfilmentdata(){return fullfilmentdata;};*/
}
