package com.roomtrack.mobile.controller;
/**
 * Created by Ramesh.eerla on 24/10/2018.
 */

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.ParseException;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.dcrworkforce.mobile.R;
import com.dcrworkforce.mobile.STinterface.ResponceCallback;
import com.dcrworkforce.mobile.Uicomponents.SmartTrackProgressDialog;
import com.dcrworkforce.mobile.activity.LandingPagewithAutologin;
import com.dcrworkforce.mobile.activity.NewLoginActivity;
import com.dcrworkforce.mobile.connectioncalls.datasets.AccessToakenSet;
import com.dcrworkforce.mobile.connectioncalls.interfaces.STRequestInterface;
import com.dcrworkforce.mobile.dataset.CreatrequirementObjects;
import com.dcrworkforce.mobile.fragments.SupplierLandingFragment;
import com.dcrworkforce.mobile.notifications.Config;
import com.dcrworkforce.mobile.requestandresponce.Commons;
import com.dcrworkforce.mobile.screenhelpers.CommonHelper;
import com.dcrworkforce.mobile.threads.Timer;
import com.dcrworkforce.mobile.utils.MapUtils;
import com.google.android.gcm.GCMRegistrar;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.velocity.runtime.resource.Resource;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AppController extends Application {

    public static final String TAG = AppController.class.getSimpleName();

    private RequestQueue mRequestQueue;


    private static AppController mInstance;
    private final int MAX_ATTEMPTS = 5;
    private final int BACKOFF_MILLI_SECONDS = 2000;
    private final Random random = new Random();
    private String result;

    public void clearApplicationData() {
        File cacheDirectory = getCacheDir();
        File applicationDirectory = new File(cacheDirectory.getParent());
        if (applicationDirectory.exists()) {
            String[] fileNames = applicationDirectory.list();
            for (String fileName : fileNames) {
                if (!fileName.equals("lib")) {
                    deleteFile(new File(applicationDirectory, fileName));
                }
            }
        }
    }

    public static boolean deleteFile(File file) {
        boolean deletedAll = true;
        if (file != null) {
            if (file.isDirectory()) {
                String[] children = file.list();
                for (int i = 0; i < children.length; i++) {
                    deletedAll = deleteFile(new File(file, children[i])) && deletedAll;
                }
            } else {
                deletedAll = file.delete();
            }
        }

        return deletedAll;
    }

    // Register this account with the server.
    public void register(final Context context, int user_id, String email, final String regId) {

        Log.i(Config.TAG, "registering device (regId = " + regId + ")");

        long backoff = BACKOFF_MILLI_SECONDS + random.nextInt(1000);

        // Once GCM returns a registration id, we need to register on our server
        // As the server might be down, we will retry it a couple
        // times.
        for (int i = 1; i <= MAX_ATTEMPTS; i++) {

            Log.d(Config.TAG, "Attempt #" + i + " to register");

            try {
                //Send Broadcast to Show message on screen
                displayMessageOnScreen(context, context.getString(R.string.server_registering, i, MAX_ATTEMPTS));
                String url_server = Commons.webapi_domain_url + getString(R.string.webapi_urn_2) + getString(R.string.savetoken);
                JSONObject tokenobjet = new JSONObject();
                tokenobjet.put("tokenId", regId);
                tokenobjet.put("userId", user_id);
                tokenobjet.put("deviceId", "Android");
                try {
                    tokenobjet.put("versionId", context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName);
                } catch (PackageManager.NameNotFoundException e) {

                    e.printStackTrace();
                }
                Log.e("url_server", "" + url_server);
                HttpClient client = new DefaultHttpClient();

                HttpPost httppost = new HttpPost(url_server);
                httppost.setHeader("Content-type", "application/json");
                httppost.setHeader("Authorization", Commons.token_type + " " + Commons.Authrization_token);
                //httppost.setHeader("SessionKey", Commons.session_key);
                StringEntity se = new StringEntity(tokenobjet.toString());
                se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                httppost.setEntity(se);

                HttpResponse response = client.execute(httppost);
                HttpEntity entity = response.getEntity();

                if (entity != null) {
                    result = EntityUtils.toString(entity);
                    Commons.versionMessage = new JSONObject(result).getString("versionMessage");
                    Commons.versionMessage_title = new JSONObject(result).getString("versionHeaderTitle");
                    //	result = result.substring(1, result.length()-1);
                    Log.e("result", "" + result);
                }

                if (result.equalsIgnoreCase("inserted") || result.equalsIgnoreCase("existed")) {
                    GCMRegistrar.setRegisteredOnServer(context, true);
                    Commons.registration_userids.add(Commons.user_id);
                }
                //Send Broadcast to Show message on screen
                String message = context.getString(R.string.server_registered);
                displayMessageOnScreen(context, message);

                return;

            } catch (ParseException e) {
                e.printStackTrace();
            } catch (IOException e) {

                // Here we are simplifying and retrying on any error; in a real
                // application, it should retry only on unrecoverable errors
                // (like HTTP error code 503).

                Log.e(Config.TAG, "Failed to register on attempt " + i + ":" + e);

                if (i == MAX_ATTEMPTS) {
                    break;
                }
                try {

                    Log.d(Config.TAG, "Sleeping for " + backoff + " ms before retry");
                    Thread.sleep(backoff);

                } catch (InterruptedException e1) {
                    // Activity finished before we complete - exit.
                    Log.d(Config.TAG, "Thread interrupted: abort remaining retries!");
                    Thread.currentThread().interrupt();
                    return;
                }

                // increase backoff exponentially
                backoff *= 2;
            } catch (JSONException e) {
                e.printStackTrace();
            } /*catch (NameNotFoundException e) {

				e.printStackTrace();
			}*/
        }

        String message = context.getString(R.string.server_register_error, MAX_ATTEMPTS);

        //Send Broadcast to Show message on screen
        displayMessageOnScreen(context, message);
    }

    // Unregister this account/device pair within the server.
    public void unregister(final Context context, final String regId) {

        Log.i(Config.TAG, "unregistering device (regId = " + regId + ")");

        String serverUrl = Config.YOUR_SERVER_URL + "/unregister";
        Map<String, String> params = new HashMap<String, String>();
        params.put("regId", regId);

        try {
            post(serverUrl, params);
            GCMRegistrar.setRegisteredOnServer(context, false);
            String message = context.getString(R.string.server_unregistered);
            displayMessageOnScreen(context, message);
        } catch (IOException e) {

            // At this point the device is unregistered from GCM, but still
            // registered in the our server.
            // We could try to unregister again, but it is not necessary:
            // if the server tries to send a message to the device, it will get
            // a "NotRegistered" error message and should unregister the device.

            String message = context.getString(R.string.server_unregister_error, e.getMessage());
            displayMessageOnScreen(context, message);
        }
    }

    // Issue a POST request to the server.
    private static void post(String endpoint, Map<String, String> params) throws IOException {

        URL url;
        try {

            url = new URL(endpoint);

        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("invalid url: " + endpoint);
        }

        StringBuilder bodyBuilder = new StringBuilder();
        Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();

        // constructs the POST body using the parameters
        while (iterator.hasNext()) {
            Map.Entry<String, String> param = iterator.next();
            bodyBuilder.append(param.getKey()).append('=').append(param.getValue());
            if (iterator.hasNext()) {
                bodyBuilder.append('&');
            }
        }

        String body = bodyBuilder.toString();

        Log.v(Config.TAG, "Posting '" + body + "' to " + url);

        byte[] bytes = body.getBytes();

        HttpURLConnection conn = null;
        try {

            Log.e("URL", "> " + url);

            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setFixedLengthStreamingMode(bytes.length);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            // post the request
            OutputStream out = conn.getOutputStream();
            out.write(bytes);
            out.close();

            // handle the response
            int status = conn.getResponseCode();

            // If response is not success
            if (status != 200) {

                throw new IOException("Post failed with error code " + status);
            }
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }


    // Checking for all possible internet providers
    public boolean isConnectingToInternet() {

        ConnectivityManager connectivity =
                (ConnectivityManager) getSystemService(
                        Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }

        }
        return false;
    }

    // Notifies UI to display a message.
    public void displayMessageOnScreen(Context context, String message) {

        Intent intent = new Intent(Config.DISPLAY_MESSAGE_ACTION);
        intent.putExtra(Config.EXTRA_MESSAGE, message);

        // Send Broadcast to Broadcast receiver with message
        context.sendBroadcast(intent);

    }


    //Function to display simple Alert Dialog
    public void showAlertDialog(Context context, String title, String message,
                                Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        // Set Dialog Title
        alertDialog.setTitle(title);

        // Set Dialog Message
        alertDialog.setMessage(message);

        if (status != null)
            // Set alert dialog ic_launcher

            // Set OK Button
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                }
            });

        // Show Alert Message
        alertDialog.show();
    }

    private PowerManager.WakeLock wakeLock;

    @SuppressLint("Wakelock")
    public void acquireWakeLock(Context context) {
        if (wakeLock != null) wakeLock.release();

        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);

        wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK |
                PowerManager.ACQUIRE_CAUSES_WAKEUP |
                PowerManager.ON_AFTER_RELEASE, "WakeLock");

        wakeLock.acquire();
    }

    public void releaseWakeLock() {
        if (wakeLock != null) wakeLock.release();
        wakeLock = null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public static STRequestInterface getAccessToken(String sessionStatus) {
        if (sessionStatus != null && sessionStatus.equalsIgnoreCase("Default"))
            Commons.oAuthurl = new MapUtils().access_tokenurls.get("prod");
        else
            Commons.oAuthurl = new MapUtils().access_tokenurls.get("other");
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = null;

        okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Commons.oAuthurl + "/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final STRequestInterface mInterfaceService = retrofit.create(STRequestInterface.class);
        return mInterfaceService;
    }

    public void startTimerForAuthrization() {

        Commons.oauth_flag = true;
        int time = (int) TimeUnit.SECONDS.toMillis(Commons.token_timer);

        final Timer tmrBlink = new Timer(time, new Runnable() {

            public void run() {
                Commons.oauth_flag = false;
                getAuthrization(null, "thred");
                System.out.print("falge status changed" + Commons.oauth_flag);
            }

        });

        tmrBlink.start();
    }

    public String getOuathClientid(Object object) {

        String clientId = "";
        if (object == null)
            object = new LandingPagewithAutologin();
        if (Commons.app_type != null && Commons.app_type.equalsIgnoreCase("Default"))  // Production
            clientId = getInstance().getResources().getString(R.string.client_id_prod);
        else // Other Environments
            clientId = getInstance().getResources().getString(R.string.client_id_local);
        return clientId;
    }

    public String getOuathSecretCode(Object object) {
        String secretCode = "";
        if (object == null)
            object = new LandingPagewithAutologin();
        try {
            if (Commons.app_type != null && Commons.app_type.equalsIgnoreCase("Default")) // Production
                secretCode = getInstance().getResources().getString(R.string.secret_code_prod);
            else // Other Environments
                secretCode = getInstance().getResources().getString(R.string.secret_code_local);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return secretCode;
    }

    public String getOuathScope(Object object) {
        String scope = "";
        if (object == null)
            object = new LandingPagewithAutologin();
        if (Commons.app_type != null && Commons.app_type.equalsIgnoreCase("Default")) // Production
            scope = getInstance().getResources().getString(R.string.scope_prod);
        else  // Other Environments
            scope = "";
        return scope;
    }

    public void getAuthrization(final Object object, final String type) {
        ProgressDialog dialog = null;
        if (type != null && type.equalsIgnoreCase("Forgatpasseord")) {
            dialog = new CommonHelper().showDialog((Context) object);
        }
        String secretCode = "", clientId = "", scope = "";
        secretCode = getOuathSecretCode(object);
        clientId = getOuathClientid(object);
        scope = getOuathScope(object);
        STRequestInterface myApiService = AppController.getAccessToken(Commons.app_type);
        Call<AccessToakenSet> myService = null;
        myService = myApiService.getAccessToaken("token", clientId, "client_credentials", "smarttrack://oauth", secretCode, scope);
        /*myService=myApiService.getAccessToaken("token","b44bcb7d3de44f3a81025c8e09ca5165","client_credentials","smarttrack://oauth","secret");*/
        final ProgressDialog finalDialog = dialog;
        myService.enqueue(new Callback<AccessToakenSet>() {

            @Override
            public void onResponse(Call<AccessToakenSet> call, retrofit2.Response<AccessToakenSet> response) {
                if (finalDialog != null && finalDialog.isShowing())
                    finalDialog.cancel();
                if (response.isSuccessful()) {
                    AccessToakenSet accessToakenSet = response.body();
                    Commons.Authrization_token = accessToakenSet.getAccess_token();
                    Commons.token_timer = accessToakenSet.expires_in;
                    Commons.token_type = accessToakenSet.token_type;
                    if (Commons.oauth_flag == false)
                        startTimerForAuthrization();

                    ResponceCallback mResponceCallback = null;
                    if (object != null && !type.equalsIgnoreCase("thred")) {
                        mResponceCallback = (ResponceCallback) object;
                        if (object instanceof NewLoginActivity)
                            mResponceCallback.callback(null, true, type);
                        if (object instanceof LandingPagewithAutologin)
                            mResponceCallback.callback(null, 1);
                    }
                }
            }

            @Override
            public void onFailure(Call<AccessToakenSet> call, Throwable t) {
                if (finalDialog != null && finalDialog.isShowing())
                    finalDialog.cancel();
            }
        });
    }

    public static STRequestInterface getInterfaceService(boolean sessionStatus) {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = null;
        final String localSessionKey;
        switch (Commons.user_type_id) {
            case Commons.Supplier:
                localSessionKey = getNewClientSeessionKey();
                break;

            default:
                localSessionKey = Commons.session_key;
                break;
        }
        if (sessionStatus) {
            okHttpClient = new OkHttpClient().newBuilder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor(new Interceptor() {
                        @Override
                        public okhttp3.Response intercept(Chain chain) throws IOException {
                            return chain.proceed(chain.request()
                                    .newBuilder()
                                    .addHeader("Authorization", Commons.token_type + " " + Commons.Authrization_token)
                                    .build());
                        }
                    })
                    .addInterceptor(logging)
                    .build();
        } else {
            okHttpClient = new OkHttpClient().newBuilder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor(new Interceptor() {
                        @Override
                        public okhttp3.Response intercept(Chain chain) throws IOException {
                            return chain.proceed(chain.request()
                                    .newBuilder()
                                    .addHeader("SessionKey", localSessionKey)
                                    .addHeader("Authorization", Commons.token_type + " " + Commons.Authrization_token)
                                    .build());
                        }
                    })
                    .addInterceptor(logging)
                    .build();

        }


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Commons.webapi_domain_url + "/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final STRequestInterface mInterfaceService = retrofit.create(STRequestInterface.class);
        return mInterfaceService;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }


    public JsonObjectRequest postRequest(Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener, final String url, final JSONObject requestData) {
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.POST,
                url, requestData,
                responseListener,
                errorListener)

        {
            /**
             * Code Written by
             * Satya
             * Parsing requested headers*
             **/

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", Commons.token_type + " " + Commons.Authrization_token);
                if (Commons.client_id != 0) {
                    switch (Commons.user_type_id) {
                        case Commons.Supplier:
                            if ((url.contains(getResources().getString(R.string.supplierOfferList))) ||
                                    (url.contains(getResources().getString(R.string.supplier_requiement_list))) ||
                                    (url.contains(getResources().getString(R.string.supplierInterviewList)))) {
                                try {

                                    if (requestData.has("isActive") && requestData.getBoolean("isActive")) {
                                        headers.put("SessionKey", Commons.session_key);

                                        Log.e("SESSION_KEY", Commons.session_key);
                                    } else {
                                        headers.put("SessionKey", getNewClientSeessionKey());
                                        Log.e("CHANGE_SESSION_KEY", getNewClientSeessionKey());
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                headers.put("SessionKey", getNewClientSeessionKey());
                                Log.e("CHANGE_SESSION_KEY", getNewClientSeessionKey());
                            }
                            break;
                        default: {
                            headers.put("SessionKey", Commons.session_key);//SessionKey
                            Log.e("SESSION_KEY", Commons.session_key);
                        }
                        break;
                    }

                } else {
                    headers.put("SessionKey", Commons.session_key);//SessionKey
                    Log.e("SESSION_KEY", Commons.session_key);
                }
                return headers;
            }
        };

        //Requested URL and Params
        Log.e("REQUEST_URL", url);
        Log.e("OBJECT", "" + requestData);
//        Log.e("SESSION_KEY", Commons.session_key);

        return jsonObjReq;
    }

    public static String getNewClientSeessionKey() {
        String changedSessionKey = "";
        if (SupplierLandingFragment.globalObj != null && SupplierLandingFragment.globalObj.getDropDownArrayList() != null) {
            for (int i = 0; i < SupplierLandingFragment.globalObj.getDropDownArrayList().size(); i++) {
                CreatrequirementObjects.DropDownsListModel clientArray = SupplierLandingFragment.globalObj.getDropDownArrayList().get(i);
                if (clientArray.getClientID() == Commons.client_id) {
                    changedSessionKey = clientArray.getSupplierSessionKey();
                    Log.e("ClientID", "" + Commons.client_id);
                }

            }

        }
        return changedSessionKey;
    }


}
