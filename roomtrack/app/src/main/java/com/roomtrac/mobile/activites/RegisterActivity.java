package com.roomtrac.mobile.activites;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.roomtrac.mobile.R;
import com.roomtrac.mobile.Uicomponents.CustomProgressDialog;
import com.roomtrac.mobile.connectioncalls.datasets.LoginDataset;
import com.roomtrac.mobile.connectioncalls.datasets.RetrofitErrorResponse;
import com.roomtrac.mobile.connectioncalls.datasets.RetrofitResponse;
import com.roomtrac.mobile.connectioncalls.interfaces.STRequestInterface;
import com.roomtrac.mobile.controller.AppController;
import com.roomtrac.mobile.services.RequestParams;
import com.roomtrac.mobile.utils.CommonHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.READ_CONTACTS;

public class RegisterActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor> {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
        // UI references.

    private TextInputEditText mName,mPasswordView,mConfirmPassword,mEmailView,mMobile_number;
   private String name_value,emial_value,pwd_value,mobile_value;
   private TextView link_login;
   private View mRegisterFormView;
   private ProgressDialog mProgressView;
   private CommonHelper mCommonHelper;
   private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_register);
        mCommonHelper=new CommonHelper();
        mContext=this;
        // Set up the login form.
        mName=findViewById(R.id.input_name);
        mEmailView =  findViewById(R.id.input_email);
        mPasswordView = findViewById(R.id.input_password);
        mConfirmPassword=findViewById(R.id.input_cnfpassword);
        mRegisterFormView=findViewById(R.id.parent);
        link_login=findViewById(R.id.link_login);
        link_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mConfirmPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                pwd_value=mPasswordView.getText().toString();
                if(!b&&!TextUtils.isEmpty(pwd_value)){
                    String cnfpwd=mConfirmPassword.getText().toString();
                    if(!pwd_value.equalsIgnoreCase(cnfpwd)){
                        mConfirmPassword.setError("Password Missmatch");
                    }else{
                        mConfirmPassword.setError(null);
                    }

                }
            }
        });
        mMobile_number=findViewById(R.id.input_phonenumber);

        AppCompatButton mEmailSignInButton = findViewById(R.id.btn_signup);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        /*mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress)*/;
    }



    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        emial_value = mEmailView.getText().toString();
        pwd_value = mPasswordView.getText().toString();
        name_value=mName.getText().toString();
        mobile_value=mMobile_number.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(pwd_value) && !isPasswordValid(pwd_value)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(emial_value)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(emial_value)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            mProgressView = mCommonHelper.showDialog(mContext);


            RequestParams.Register register= new RequestParams().new Register().register_user(name_value, emial_value,
                    pwd_value, mobile_value);
            STRequestInterface mApiService = AppController.getInterfaceService(RegisterActivity.this);
            Call<RetrofitResponse>   mService = mApiService.user_Register(getString(R.string.user_registration), register);
          mService.enqueue(new Callback<RetrofitResponse>() {
              @Override
              public void onResponse(Call<RetrofitResponse> call, Response<RetrofitResponse> response) {
                  mProgressView.dismiss();
                  if(response.isSuccessful()){

                  }else{
                      JsonParser parser = new JsonParser();
                      JsonElement mJson = null;
                      try {
                          mJson = parser.parse(response.errorBody().string());
                          Gson gson = new Gson();
                          RetrofitErrorResponse errorResponse = gson.fromJson(mJson, RetrofitErrorResponse.class);
                          String error_message = errorResponse.getMessage();
                          CommonHelper.showErrorAlertDiaolog(RegisterActivity.this, "Register Failure", error_message);
                      } catch (IOException ex) {
                          ex.printStackTrace();
                      }

                  }
              }

              @Override
              public void onFailure(Call<RetrofitResponse> call, Throwable t) {
                  mProgressView.dismiss();
                  if (t instanceof IOException) {
                      CommonHelper.showErrorAlertDiaolog(mContext, "NetWork Error", "No Interrnet Connection");

                  }
                  else {
                      CommonHelper.showErrorAlertDiaolog(mContext, "NetWork Unkown", "Unkown Connection");
                  }
              }
          });
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }


    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), RegisterActivity.ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(RegisterActivity.ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(RegisterActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        /* mEmailView.setAdapter(adapter);*/
    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }


}


