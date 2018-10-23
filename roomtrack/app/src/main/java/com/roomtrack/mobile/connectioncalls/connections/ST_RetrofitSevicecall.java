package com.roomtrack.mobile.connectioncalls.connections;

import android.app.ProgressDialog;
import android.content.Context;

import com.dcrworkforce.mobile.R;
import com.dcrworkforce.mobile.STinterface.ResponceCallback;
import com.dcrworkforce.mobile.connectioncalls.datasets.DeleteResumeDataset;
import com.dcrworkforce.mobile.connectioncalls.datasets.ResumeAndDocumentUploadDataSet;
import com.dcrworkforce.mobile.connectioncalls.interfaces.STRequestInterface;
import com.dcrworkforce.mobile.controller.AppController;
import com.dcrworkforce.mobile.requestandresponce.RequestParams;
import com.dcrworkforce.mobile.screenhelpers.CommonHelper;
import com.dcrworkforce.mobile.utils.Constants;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ramesh.eerla on 4/6/2017.
 */

public class ST_RetrofitSevicecall {
    public Context mContext;
    public Call<ResumeAndDocumentUploadDataSet> mService;
    public Call<DeleteResumeDataset> mService_delete;
    ProgressDialog mProgressDialog;
    CommonHelper mCommonHelper;
    ResponceCallback mResponceCallback;
    public ST_RetrofitSevicecall(Context mContext) {
        this.mContext=mContext;
        this.mResponceCallback=(ResponceCallback)mContext;
        this.mCommonHelper=new CommonHelper();
    }
    public ST_RetrofitSevicecall(Context mContext,Object mObject) {
        this.mContext=mContext;
        this.mResponceCallback=(ResponceCallback)mObject;
        this.mCommonHelper=new CommonHelper();
    }

    public void postUploadRequest(final int requestType, Object mUploadResumeFile) {
       mService=getRequestType(requestType,mUploadResumeFile);
        if (mCommonHelper!=null&& mContext != null)
            mProgressDialog = mCommonHelper.showDialog(mContext);

        mService.enqueue(new Callback<ResumeAndDocumentUploadDataSet>() {

            @Override
            public void onResponse(Call<ResumeAndDocumentUploadDataSet> call, Response<ResumeAndDocumentUploadDataSet> response) {
                mProgressDialog.dismiss();
                if (response.isSuccessful())
                if(response.body().getIsUploaded())
                mResponceCallback.callback(response.body(),requestType);
                else
                    CommonHelper.showErrorAlertDiaolog(mContext, "", response.body().getMessage());

            }

            @Override
            public void onFailure(Call<ResumeAndDocumentUploadDataSet> call, Throwable t) {
                mProgressDialog.dismiss();
                call.cancel();
                if(t instanceof SocketTimeoutException)
                    CommonHelper.showErrorAlertDiaolog(mContext, "Connection Timeout", Constants.ST_CONECTIONTIMEOUT_Error_Message);
                else if(t instanceof UnknownHostException)
                    CommonHelper.showErrorAlertDiaolog(mContext, "UnknownHost", t.getMessage());
                else
                    CommonHelper.showErrorAlertDiaolog(mContext, "No Network", Constants.ST_NONETWORK_Error_Message);
            }
        });
    }
    public void postDeleteRequest(final int requestType, Object mUploadResumeFile) {
        mService_delete=getRequestType(requestType,mUploadResumeFile);
        if (mCommonHelper!=null&& mContext != null)
            mProgressDialog = mCommonHelper.showDialog(mContext);

        mService_delete.enqueue(new Callback<DeleteResumeDataset>() {
            @Override
            public void onResponse(Call<DeleteResumeDataset> call, Response<DeleteResumeDataset> response) {
                mProgressDialog.dismiss();
                if (response.isSuccessful())
                    mResponceCallback.callback(response.body(), requestType);


            }

            @Override
            public void onFailure(Call<DeleteResumeDataset> call, Throwable t) {
                mProgressDialog.dismiss();
                call.cancel();
                if(t instanceof SocketTimeoutException)
                    CommonHelper.showErrorAlertDiaolog(mContext, "Connection Timeout", Constants.ST_CONECTIONTIMEOUT_Error_Message);
                else if(t instanceof UnknownHostException)
                    CommonHelper.showErrorAlertDiaolog(mContext, "UnknownHost", t.getMessage());
                else
                    CommonHelper.showErrorAlertDiaolog(mContext, "No Network", Constants.ST_NONETWORK_Error_Message);

            }
        });
    }

    private Call getRequestType(int requestType, Object fileObject) {

        STRequestInterface mApiService = AppController.getInterfaceService(false);

        switch (requestType){
            case Constants.ST_RESUMEUPLOAD:
                Call<ResumeAndDocumentUploadDataSet>  mService= mApiService.uploadResume(mContext.getString(R.string.webapi_urn_5),"UploadResumeFile",(RequestParams.UploadResumeFile)fileObject);
                return mService;
            case  Constants.ST_ATTACHMENTUPLOAD:
                Call<ResumeAndDocumentUploadDataSet>  mService1= mApiService.uploadAttachment(mContext.getString(R.string.webapi_urn_5),"UploadAdditionalDocFile",(RequestParams.UploadAdditionalDocuments)fileObject);
                return mService1;
            case Constants.ST_RESUMEDELETE:
                Call<DeleteResumeDataset>  mService2= mApiService.DeleteResume(mContext.getString(R.string.webapi_urn_5),"ResumeDocDelete",(RequestParams.DeleteResumeFile)fileObject);
                return mService2;
            case  Constants.ST_ATTACHMENTDELETE:
                Call<DeleteResumeDataset>  mService3= mApiService.DeleteAttachment(mContext.getString(R.string.webapi_urn_5),"DeleteAdditionalDocFile",(RequestParams.RemoveAdditionalDocuments)fileObject);
                return mService3;
        }
       return null;
    }
}

