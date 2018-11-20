package com.roomtrac.mobile.activites;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;

import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity  {

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

    private TextInputEditText mPasswordView,mEmailView;
    private TextView register;
    private ProgressDialog mProgressView;
    private View mLoginFormView;
    private CommonHelper mCommonHelper;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext=this;
        mCommonHelper=new CommonHelper();
        // Set up the login form.
        mEmailView =  findViewById(R.id.email_edit);


        mPasswordView = findViewById(R.id.pwd_edit);
        mEmailView.setText("saladisrinivas88@gmail.com");
        mPasswordView.setText("123456");
        register=(findViewById(R.id.register));
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
        register.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        mLoginFormView = findViewById(R.id.login_form);

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
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
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


            RequestParams.LoginDetails login= new RequestParams().new LoginDetails().loginWithEmail(email,password);
            STRequestInterface mApiService = AppController.getInterfaceService(mContext);
            Call<LoginDataset> mService = mApiService.login(getString(R.string.user_login), login);
            mService.enqueue(new Callback<LoginDataset>() {
                @Override
                public void onResponse(Call<LoginDataset> call, Response<LoginDataset> response) {
                    mProgressView.dismiss();
                    if(response.isSuccessful()){
                  startActivity(new Intent(LoginActivity.this,LandigpageActivity.class));
                    }else{
                        JsonParser parser = new JsonParser();
                        JsonElement mJson = null;
                        try {
                            mJson = parser.parse(response.errorBody().string());
                            Gson gson = new Gson();
                            RetrofitErrorResponse errorResponse = gson.fromJson(mJson, RetrofitErrorResponse.class);
                            String error_message = errorResponse.getMessage();
                            CommonHelper.showErrorAlertDiaolog(mContext, "Login Failure", error_message);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }

                    }
                }

                @Override
                public void onFailure(Call<LoginDataset> call, Throwable t) {
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




}

