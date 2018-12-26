package com.roomtrac.mobile.activites;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.roomtrac.mobile.R;
import com.roomtrac.mobile.connectioncalls.connections.RT_RetrofitSevicecall;
import com.roomtrac.mobile.connectioncalls.datasets.LoginDataset;
import com.roomtrac.mobile.connectioncalls.datasets.RetrofitResponse;
import com.roomtrac.mobile.controller.DateValidater;
import com.roomtrac.mobile.interfaces.ResponceCallback;
import com.roomtrac.mobile.services.RequestParams;
import com.roomtrac.mobile.utils.CommonHelper;
import com.roomtrac.mobile.utils.CommonUtils;
import com.roomtrac.mobile.utils.Constants;

import org.json.JSONObject;

public class Forgetpassword extends Activity implements View.OnClickListener, ResponceCallback {
    Button cancel, submit;
    EditText email;
    String email_text;
    AppCompatTextView dailog_heading;
    private RT_RetrofitSevicecall mRt_retrofitSevicecall;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgetpwd);
        dailog_heading = findViewById(R.id.approval_footer_textheading);
        email = findViewById(R.id.emal_text);
        cancel = findViewById(R.id.cancel);
        submit = findViewById(R.id.submit);
        mRt_retrofitSevicecall = new RT_RetrofitSevicecall(this);
        init();
        cancel.setOnClickListener(this);
        submit.setOnClickListener(this);
        //sendmessageapproval_footer_textheading
    }

    private void init() {
        switch (CommonUtils.forget_anv) {
            case Constants.RT_FORGETPWD:
                dailog_heading.setText("Forgot password");
                email.setHint("Please enter your registered emailid");
                break;
            case Constants.RT_SENDMAIL:
                dailog_heading.setText("Send Mail");
                email.setHint("Please enter Comment");
                cancel.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel:
                finish();
                break;
            case R.id.submit:
                email_text = email.getText().toString();
                switch (CommonUtils.forget_anv) {
                    case Constants.RT_FORGETPWD:
                        if (email_text.trim().equalsIgnoreCase("")) {
                            email.setError("Please enter valid email");
                        } else if (new DateValidater().isValidEmail(email_text)) {
                            //forgotpassword
                            RequestParams.ForgetPassword forgetPassword = new RequestParams().new ForgetPassword().forgetpwd(email_text);
                            mRt_retrofitSevicecall.submitPost(7, "forgotpassword", forgetPassword);

                            //CommonHelper.showSingleActionAlertDiaolog(this, "Alert", "success", 1);
                        } else
                            email.setError("Please enter valid email");
                        break;
                    case Constants.RT_SENDMAIL:
                        SharedPreferences mPrefs = getSharedPreferences("Login_repsonse",MODE_PRIVATE);
                        Gson gson = new Gson();
                        String json = mPrefs.getString("MyObject", "");
                       LoginDataset loginDataset= gson.fromJson(json, LoginDataset.class);

                        RequestParams.SendMail forgetPassword = new RequestParams().new SendMail().getdatasendmail(loginDataset.getMember_id(),loginDataset.getFirst_name(),CommonUtils.mSelecteditemDetails.getMembers_detail_id(), CommonUtils.mSelecteditemDetails.getEmail(),CommonUtils.mSelecteditemDetails.getFirst_name(),CommonUtils.mSelecteditemDetails.getMember_id(),email_text);
                        mRt_retrofitSevicecall.submitPost(8, "sendmessage", forgetPassword);

                        break;



                }
        }
        }

        @Override
        public void callback (JSONObject responce){

        }

        @Override
        public void callback (Object responce,int requesttype){
            RetrofitResponse rence = (RetrofitResponse) responce;
            CommonHelper.setalertdialog_(this, rence.getStatus(), rence.getMessage());
        }

        @Override
        public void errorcallback (String errortitle, String errormessage){

        }
    }
