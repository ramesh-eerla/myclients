package com.roomtrac.mobile.activites;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

import android.app.Activity;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.roomtrac.mobile.R;
import com.roomtrac.mobile.connectioncalls.connections.RT_RetrofitSevicecall;
import com.roomtrac.mobile.connectioncalls.datasets.LoginDataset;
import com.roomtrac.mobile.connectioncalls.interfaces.RTRequestInterface;
import com.roomtrac.mobile.controller.AppController;
import com.roomtrac.mobile.interfaces.ResponceCallback;
import com.roomtrac.mobile.services.RequestParams;
import com.roomtrac.mobile.utils.CommonHelper;
import com.roomtrac.mobile.utils.CommonUtils;
import com.roomtrac.mobile.utils.Constants;


import org.json.JSONObject;

import retrofit2.Call;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_CONTACTS;
import static com.roomtrac.mobile.controller.AppController.TAG;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity implements ResponceCallback {

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
    CallbackManager callbackManager;
    // UI references.
    private EditText mEmailView,mPasswordView;
    private TextView register,forgotpwd;
    private ProgressDialog mProgressView;
    private View mLoginFormView;
    private CommonHelper mCommonHelper;
    private Context mContext;
    private GoogleSignInClient mGoogleSignInClient;
    private int RC_SIGN_IN=111;
    private RT_RetrofitSevicecall mRt_retrofitSevicecall;
    int PERMISSION_ALL = 1;
    String[] PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.GET_ACCOUNTS,Manifest.permission.READ_CONTACTS};

    public static boolean hasPermissions(Context mContext, String... permissions) {
        if (mContext != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(mContext, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        mContext=this;
        callbackManager = CallbackManager.Factory.create();
        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken(Constants.GmailOauthid)
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        mRt_retrofitSevicecall=new RT_RetrofitSevicecall(mContext);
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
if(isLoggedIn){
    afterloginnavigation();
}
        mCommonHelper=new CommonHelper();
        // Set up the login form.
        mEmailView =  findViewById(R.id.email_edit);


        mPasswordView = findViewById(R.id.pwd_edit);
        mEmailView.setText("saladisrinivas88@zoho.com");
        mPasswordView.setText("123456");
        register=(findViewById(R.id.register));
        forgotpwd=findViewById(R.id.forgotpwd);

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
        forgotpwd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonUtils.forget_anv=Constants.RT_FORGETPWD;
                startActivity(new Intent(LoginActivity.this, Forgetpassword.class));
            }
        });

       // mLoginFormView = findViewById(R.id.login_form);

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

            RequestParams.LoginDetails login= new RequestParams().new LoginDetails().loginWithEmail(email,password);

            mRt_retrofitSevicecall.loginpost(Constants.RT_LOGIN,login);

        }

    }

    public void gmaillogin(View view){
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }

public void afterloginnavigation(){
    /*Intent intent = new Intent(LoginActivity.this,
            LandigpageActivity.class);
    intent.putExtra("userProfile", json_object.toString());
    startActivity(intent);*/
    startActivity(new Intent(LoginActivity.this,LandigpageActivity.class));
}
    public void facbooklogin(View view){
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email","public_profile"));
        /*LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");*/
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                getUserDetails(loginResult);
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                CommonHelper.showErrorAlertDiaolog(mContext, "Facebook SignIn", "Facebook SignIn Failed");
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }else{
            callbackManager.onActivityResult(requestCode, resultCode, data); 
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            //updateUI(account);
            afterloginnavigation();
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.d(TAG, "signInResult:failed code=" + e.getStatusCode());
            CommonHelper.showErrorAlertDiaolog(mContext, "Google SignIn", "Google SignIn Failed");
            /*updateUI(null);*/

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

    protected void getUserDetails(LoginResult loginResult) {
        GraphRequest data_request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject json_object,
                            GraphResponse response) {
                        afterloginnavigation();

                    }

                });
        Bundle permission_param = new Bundle();
        permission_param.putString("fields", "id,name,email,picture.width(120).height(120)");
                data_request.setParameters(permission_param);
        data_request.executeAsync();

    }


    @Override
    public void callback(JSONObject responce) {

    }

    @Override
    public void callback(Object responce, int requesttype) {
        LoginDataset loginDataset= ((LoginDataset) responce);
        if(loginDataset.getStatus().equalsIgnoreCase("false"))
        CommonHelper.showErrorAlertDiaolog(mContext, "Login Failure", loginDataset.getMessage());
        else
        afterloginnavigation();
    }

    @Override
    public void errorcallback(String errortitle, String errormessage) {

    }
}

