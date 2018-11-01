package com.roomtrac.mobile.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.roomtrac.mobile.R;
import com.roomtrac.mobile.Uicomponents.CustomProgressDialog;
import com.roomtrac.mobile.activites.LoginActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import okhttp3.internal.Util;

@SuppressLint("UseSparseArrays")
@SuppressWarnings({"unused", "rawtypes", "static-access", "unchecked"})
public class CommonHelper {

    /**
     * This CommonHelper class contains various methods which may be used frequently through out the project
     *
     * @author Ramesh Eerla
     */
    private int client_id = 0, msp_id = 0, msp_client_id = 0, user_id = 0, SupplierId = 0, CWID = 0, user_type_id = 0;
    private String session_key = null, worksheet_status_id = null, get_time_sheet_url = null, massaprovaltimesheet_url = null, time_sheets_result_str = null, json_time_sheets_result_str = null, time_sheet_array_key_name = null, saeNumber_str = null, msp_client_list_key_name = null, json_mass_approval_time_sheets_result_str = null, fullname = null, title = null;
    private JSONObject jsonWorklistObj = null;
    public static JSONArray jsonWorklistDisPlayArray = null, timesheets_json_Array_obj = null;
    private HashMap timesheets_map_hm = null;
    private ArrayList<JSONObject> worksheet_arraylist_obj = null;
    private ArrayList<String> lable_arraylist_obj = null;
    private JSONArray jsonWorklistGridLablesArray = null, jsonWorklistArray = null;
    public Map jsonWorklistGridLablesMap = null;
    public String approve_button_display = "False", mass_approve_status = "False", approve_button_label = null;
    private JSONObject get_time_request_JSON_obj = null;
    public static List<?> client_list_msp_list_array = null;
    public static String user_type = null;
    private boolean application_status = false;
    private ProgressDialog dialog = null;
    public static AlertDialog alertDialog = null;

    private String login_result_str = null, login_request_url = null, editTextUNameStr_beforesplit = null, editTextUNameStr = null, editTextUPasswordStr = null, splash_Screen_image_url,
            splash_image_name, SettingsEntity_json_str, alert_title_str = "", alert_message_str = "", CommonErrorMessages_json_str = "",
            common_error_message_str = "", common_error_message = "", errorMessage = "", usergetdata_urn, SearchEntityLabels, MobileModules, approvalsCount, requirementFullfillmentCount, approvalsEntityCollection, ScreenTitle;


    private String domain_url, gettimesheets_urn, getSearchList_urn, massaprovaltimesheet_urn;
    public static ArrayList<Object> filteredList = new ArrayList<>();
    private static ArrayList<AlertDialog> alertDialogArrayList = new ArrayList();

    /**
     * This method is used to get the JSON Array where the "Display" string is equals to "Yes" in the given jsonWorklistArray
     *
     * @param jsonWorklistArray is the JSONArray in which the "Display" string has to be checked for "Yes"
     * @return returns a JSONArray which contains values if the "Display" in the given array is "Yes"
     */
    public JSONArray getWorklistDisplayJsonArray(JSONArray jsonWorklistArray) {
        jsonWorklistDisPlayArray = new JSONArray();
        for (int i = 0; i < jsonWorklistArray.length(); i++) {
            try {
                JSONObject jsonWorklistObj = (JSONObject) jsonWorklistArray.get(i);
                String display = jsonWorklistObj.get("Display").toString();

                if (display != null && display.equals("Yes")) {
                    jsonWorklistDisPlayArray.put(jsonWorklistObj);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return jsonWorklistDisPlayArray;
    }



    /**
     * This method is used to set the given userid to the user id variable in Commons
     *
     * @param userid  is the value which has to set to the user id variable in Commons
     * @param context
     * @returns void
     */
    public static void setUserIdToCommons(String userid, Context context, boolean status) {
        CommonUtils.userid = userid;

    }

    /**
     * this method is used to exit from the corresponding context
     *
     * @param context is the corresponding context
     * @returns void
     */
    /*
     * Finishes(Exits) the Activity for corresponding context
     */
    public static void exitScreen(Context context) {

        try {
            ((Activity) context).finish();//Exit the activity
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * this is used to set application exit status for a variable in Commons
     *
     * @param status is value which contains true or false
     * @returns void
     */
    public static void setApplicationExitStatus(boolean status) {
        CommonUtils.application_exit_status = status;
    }


    /*
     * To show the loading dialog on the screen
     */
    public ProgressDialog showDialog(Context context) {
        dialog = new CustomProgressDialog(context).showProgressDialog("Please Wait ..", "Loading ..");
        if (dialog != null)
            dialog.setCanceledOnTouchOutside(false);


        return dialog;
    }

    public static AlertDialog setForceUpdateAlertDiaolog(final Context context, String title, String message) {
        /*AlertDialog alertDialog = new AlertDialog.Builder(context, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT).create();
        alertDialog.setCancelable(false);*/
        alertDialog = getAlertDialog(context, message);
        alertDialog.setCancelable(false);
        alertDialog.setMessage(message);
//        alertDialog.setCanceledOnTouchOutside(false);

        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                try {
                    Intent viewIntent =
                            new Intent("android.intent.action.VIEW",
                                    Uri.parse("https://play.google.com/store/apps/details?id=com.roomtrac.mobile"));
                    context.startActivity(viewIntent);
                } catch (Exception e) {
                    Toast.makeText(context, "Unable to Connect Try Again...",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });

        alertDialog.show();

        return alertDialog;
    }

    /**
     * This is to set logout alert dialog for corresponding context
     *
     * @param context is the corresponding context
     * @param title   is the title of the alert dialog
     * @param message is the message that has to be displayed on alert dialog
     * @return alert  dialog
     */
    public AlertDialog setLogoutAlertDiaolog(final Context context, String title, String message) {

        alertDialog = getAlertDialog(context, message);
        alertDialog.setCancelable(true);
        alertDialog.setMessage(message);
        alertDialog.setCanceledOnTouchOutside(false);

        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                ResolveInfo resolveInfo = context.getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
                String currentHomePackage = resolveInfo.activityInfo.packageName;
              //  AccountManagerST accountManager = new AccountManagerST(context);
                try {
                   /* if (accountManager.getSTAccountLength() > 0) {
                        accountManager.removeSTAccount(new AccountManagerST.OnAccountRemoveListener() {

                            @Override
                            public void onAccountRemove(boolean isDeleted) {
                                if (isDeleted == false) {
                                    ((Activity) context).finish();
                                    context.startActivity(((Activity) context).getIntent());

                                } else {
                                    CommonHelper.setApplicationStatus(false);
                                    ((Activity) context).finish();
                                    context.startActivity(new Intent(context, LoginActivity.class));
                                }
                            }
                        });*/
                  //  } else {

                        ((Activity) context).finish();
                        context.startActivity(((Activity) context).getIntent());
                    //}
                } catch (Exception e) {
                    Log.e(getClass().getSimpleName(), e.getMessage());
                }
            }
        });

        alertDialog.show();

        return alertDialog;
    }

    String url = null;

    /**
     * This is to set error alert dialog for corresponding context
     *
     * @param context is the corresponding context
     * @param title   is the title of the alert dialog
     * @param message is the message that has to be displayed on alert dialog
     * @return alert  dialog
     */
    public AlertDialog setErrorAlertDiaolog(final Context context, String title, String message) {

        String msg;
        String[] splitMsg = message.split(Pattern.quote("+"));
        if (splitMsg.length == 1) {
            msg = message;
        } else {
            title = "Notification";
            msg = splitMsg[0];
            url = splitMsg[1];
        }
        // alertDialog = new AlertDialog.Builder(context).create();
        String messageText = String.valueOf(Html.fromHtml(msg.replaceAll("(\r\n|\n)", "<br>")));
        alertDialog = getAlertDialog(context, messageText);
        alertDialog.setCancelable(true);
        /*if (title != null &&
                title.equalsIgnoreCase(context.getResources().getString(R.string.confirmation)))*/
            alertDialog.setTitle(title);
        alertDialog.setMessage(messageText);
        alertDialog.setCanceledOnTouchOutside(false);

        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (url != null) {
                    try {
                        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + context.getPackageName())));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url + context.getPackageName())));
                    }
                } else {
                    dialog.dismiss();
                }
            }
        });
        if (url != null)
            alertDialog.setButton2("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

        if (!((Activity) context).isFinishing())
            alertDialog.show();


        return alertDialog;
    }

    /**
     * This is to set error alert dialog with out ic_launcher for corresponding context
     *
     * @param context is the corresponding context
     * @param title   is the title of the alert dialog
     * @param message is the message that has to be displayed on alert dialog
     * @return alert  dialog
     */
    public AlertDialog setErrorAlertDiaologWithOutIcon(Context context, String title, String message) {

        // alertDialog = new AlertDialog.Builder(context).create();
        // String messageText = String.valueOf(Html.fromHtml(message.replaceAll("(\r\n|\n)", "<br>")));
        String messageText = "";//replaceTags(message);
        alertDialog = getAlertDialog(context, messageText);
        alertDialog.setCancelable(true);
        alertDialog.setMessage(messageText);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.cancel();
            }
        });
        if (!alertDialog.isShowing())
            alertDialog.show();

        return alertDialog;
    }

    /**
     * This is to show logout alert dialog for corresponding context
     *
     * @param context is the corresponding context
     * @param title   is the title of the alert dialog
     * @param message is the message that has to be displayed on alert dialog
     * @return void
     */
    public static void showLogoutAlertDiaolog(final Context context, final String title, final String message) {
        ((Activity) context).runOnUiThread(new Runnable() {

            @Override
            public void run() {
                CommonHelper common_helper_obj = new CommonHelper();
                common_helper_obj.setLogoutAlertDiaolog(context, title, message);
            }
        });

    }


    /**
     * This is to show error alert dialog for corresponding context
     *
     * @param context is the corresponding context
     * @param title   is the title of the alert dialog
     * @param message is the message that has to be displayed on alert dialog
     * @return void
     */
    public static void showErrorAlertDiaolog(final Context context, final String title, final String message) {
        final CommonHelper common_helper_obj = new CommonHelper();
        try {

            ((Activity) context).runOnUiThread(new Runnable() {

                @Override
                public void run() {

                                            common_helper_obj.setErrorAlertDiaolog(context, title, (message != null) ? message : title);

                }
            });
        } catch (Exception e) {
            common_helper_obj.setErrorAlertDiaolog(context, title, message);
        }
    }


    /**
     * This is to show error alert dialog for corresponding context and finish the activity while click on 'OK'
     *
     * @param context is the corresponding context
     * @param title2  is the title of the alert dialog
     * @param message is the message that has to be displayed on alert dialog
     * @return void
     */
    public void setErrorAlertDiaolog1(final Context context, String title2,
                                      String message) {
        // alertDialog = new AlertDialog.Builder(context).create();
        String messageText = String.valueOf(Html.fromHtml(message.replaceAll("(\r\n|\n)", "<br>")));
        alertDialog = getAlertDialog(context, messageText);
        alertDialog.setCancelable(true);
        alertDialog.setMessage(messageText);
        alertDialog.setCanceledOnTouchOutside(false);

        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                ((Activity) context).finish();
            }
        });

        alertDialog.show();
    }

    /**
     * This is to show Invalid Respone error alert dialog for corresponding context
     *
     * @param context is the corresponding context
     * @param message is the string that compares and finish the activity
     * @return void
     */
    public void setInvalidRespone_ErrorAlertDiaolog(final Context context, final String message, final String screenTitle) {
        String error_message = "";
        String val = "";

        Map<String, ?> error_title_message = CommonHelper.getPrefrence_(context, "" + Constants.ST_TODOLIST);
        for (Map.Entry<String, ?> entry : error_title_message.entrySet()) {
            if (entry.getKey().toString().equalsIgnoreCase(Constants.ST_VALUE))
                val = entry.getValue().toString();
        }

        if (!screenTitle.equalsIgnoreCase(""))
            error_message = val + " " + screenTitle;
        else
            error_message = val.replace("in your", "");

        // alertDialog = new AlertDialog.Builder(context).create();
        alertDialog = getAlertDialog(context, error_message);
        alertDialog.setCancelable(true);
        alertDialog.setMessage(error_message);
        alertDialog.setCanceledOnTouchOutside(false);

        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (message.contains("Finish"))
                    ((Activity) context).finish();
            }
        });

        alertDialog.show();
    }


    public static void showSingleActionAlertDiaolog(final Context context, final String title, final String message, final int backflag) {
        final CommonHelper common_helper_obj = new CommonHelper();
        try {
            ((Activity) context).runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    common_helper_obj.setSingleActionAlertDiaolog(context, title, message, backflag);
                }
            });
        } catch (Exception e) {
            common_helper_obj.setSingleActionAlertDiaolog(context, title, message, backflag);
        }
    }

    public void setSingleActionAlertDiaolog(final Context context, String title2,
                                            String message, final int backflag) {
        if (message.equalsIgnoreCase("")) {
            ((Activity) context).finish();
        } else {
            // alertDialog = new AlertDialog.Builder(context).create();
            alertDialog = getAlertDialog(context, message);
            alertDialog.setCancelable(true);
            alertDialog.setMessage(message);
            alertDialog.setCanceledOnTouchOutside(false);

            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    if (backflag != 0) {

                      //  context.startActivity(new Intent(context, Commanlistpage.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    } else
                        ((Activity) context).finish();
                }
            });
            alertDialog.show();
        }
    }

    /**
     * This is to show error alert dialog without ic_launcher for corresponding context
     *
     * @param context is the corresponding context
     * @param title   is the title of the alert dialog
     * @param message is the message that has to be displayed on alert dialog
     * @return void
     */
    public static void showErrorAlertDiaologWithOutDiaolog(final Context context, final String title, final String message) {
        CommonHelper common_helper_obj = new CommonHelper();
        common_helper_obj.setErrorAlertDiaologWithOutIcon(context, title, message);
        /*((Activity) context).runOnUiThread(new Runnable() {

            @Override
            public void run() {


            }
        });*/
    }

    /**
     * This is to check status of a given JSON string which has been set to status variable in commons
     *
     * @param JSON_str is the JSON string for which the status has to be checked
     * @return true or false
     */
    public static boolean checkStatus(String JSON_str) {
        boolean status = false;
        try {
            String root_element_name = JSONUtils.getRootElementName(JSON_str);
            JSONObject JSON_obj = (JSONObject) JSONUtils.getJSONValueByKey(JSON_str, root_element_name);
            String status_str = (String) JSONUtils.getJSONValueByKey(JSON_obj.toString(), "status");

            if (status_str != null && !status_str.equals(""))
                status = Boolean.parseBoolean(status_str);

        } catch (ClassCastException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }



        return status;
    }

    /**
     * This is to check status of a given JSON string which has been set to status variable in commons
     *
     * @param JSON_str is the JSON string for which the status has to be checked
     * @return true or false
     */
    public static boolean checkSessionStatus(String JSON_str) {
        boolean status = false;
        ArrayList<String> jarry;
        String status_str;
        try {
            jarry = new ArrayList<String>();
            JSONObject obj = new JSONObject(JSON_str);
            for (int i = 0; i < obj.names().length(); i++) {
                jarry.add(obj.names().getString(i));
            }
            if (jarry.contains("sessionDetails")) {
                obj = obj.getJSONObject("sessionDetails");
                status_str = obj.getString("sessionStatus");
            } else if (jarry.contains("SessionDetails")) {
                obj = obj.getJSONObject("SessionDetails");
                status_str = obj.getString("sessionStatus");
            } else {
                String root_element_name = JSONUtils.getRootElementName(JSON_str);
                JSONObject JSON_obj = (JSONObject) JSONUtils.getJSONValueByKey(JSON_str, root_element_name);
                status_str = (String) JSONUtils.getJSONValueByKey(JSON_obj.toString(), "status");
            }

            if (status_str != null && !status_str.equals(""))
                status = Boolean.parseBoolean(status_str);

        } catch (ClassCastException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }



        Log.d("status", "" + status);
        return status;
    }

    /**
     * This is to get the message string which is the given JSON string
     *
     * @param JSON_str is the JSON string for which the status has to be checked
     * @return a message string which is obtained from  given JSON string
     */
    public static String getCommonsMessage(String JSON_str) {
        String message_str = null;

        try {
            String root_element_name = JSONUtils.getRootElementName(JSON_str);
            JSONObject JSON_obj = (JSONObject) JSONUtils.getJSONValueByKey(JSON_str, root_element_name);
            message_str = (String) JSONUtils.getJSONValueByKey(JSON_obj.toString(), "message");

        } catch (ClassCastException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message_str;
    }

    /**
     * This is to get the message string which is the given JSON string
     *
     * @param JSON_str is the JSON string for which the status has to be checked
     * @return a message string which is obtained from  given JSON string
     */
    public static String getStatusCommonsMessage(String JSON_str) {
        String message_str = null;
        ArrayList<String> jarry;

        try {
            jarry = new ArrayList<String>();
            JSONObject obj = new JSONObject(JSON_str);
            for (int i = 0; i < obj.names().length(); i++) {
                jarry.add(obj.names().getString(i));
            }

            if (jarry.contains("sessionDetails")) {
                obj = obj.getJSONObject("sessionDetails");
                message_str = obj.getString("message");
            } else {

                String root_element_name = JSONUtils.getRootElementName(JSON_str);
                if (root_element_name.equals("Message")) {


                    JSONObject obj_error = new JSONObject(JSON_str);
                    message_str = obj_error.getString("Message");
                } else {

                    JSONObject JSON_obj = (JSONObject) JSONUtils.getJSONValueByKey(JSON_str, root_element_name);
                    message_str = (String) JSONUtils.getJSONValueByKey(JSON_obj.toString(), "message");
                }
            }

        } catch (ClassCastException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return message_str;
    }

    /**
     * This method is to get a string array which contains search drop down list where the display status is "yes"
     *
     * @param SearchDropdown_json_array is the JSON array from which we have to get the search drop down list array if the display string is equals to "yes"
     * @return returns String array which contains work list types which has display status as "yes"
     */
    public static String[] getWorkListTypeStringArray(JSONArray SearchDropdown_json_array) {
        int array_length = getWorkListTypeArrayCount(SearchDropdown_json_array);
        String[] worklistType_items = new String[array_length];
        int count = 0;
        for (int i = 0; i < array_length; i++) {
            try {
                JSONObject searchDropdown_json_obj = (JSONObject) SearchDropdown_json_array.get(i);
                boolean Display = JSONUtils.getBooleanByKey(searchDropdown_json_obj.toString(), "Display");
                String Name = JSONUtils.getStringByKey(searchDropdown_json_obj.toString(), "Name");

                if (Display) {
                    worklistType_items[count] = Name;
                    count++;
                }

            } catch (ClassCastException ex) {
                ex.printStackTrace();
            } catch (JSONException ex) {
                ex.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return worklistType_items;
    }

    /**
     * This method is to get the work list type count which has display status as "yes" in the given JSON Array.
     * The count will be incremented if the display status of particular work list type is "yes"
     *
     * @param worksheet_status_json_array is the JSON Array in which we have to check for work list type count
     * @return returns the count of work list types where the display status of work list type is equals to "yes"
     */
    public static int getWorkListTypeArrayCount(JSONArray worksheet_status_json_array) {
        int array_length = worksheet_status_json_array.length();
        int count = 0;
        try {
            for (int i = 0; i < array_length; i++) {
                JSONObject jsonObj = (JSONObject) worksheet_status_json_array.get(i);
                String Name = jsonObj.get("Name").toString();
                String Display = jsonObj.get("Display").toString();
                String worksheet_value = jsonObj.get("Value").toString();

                if (Display != null && Display.equalsIgnoreCase("true"))
                    count++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return count;
    }

    /**
     * This is to get the work list type name  from the given JSON array using the given position
     *
     * @param worksheet_status_json_array is the JSON array in which we have to check for the work list type name
     * @param possition                   is the value which is used to get the  work list type name from the given JSON Array
     * @return returns string which contains work list type name of the given position
     */
    public static String getWorkListTypeNameByPossition(JSONArray worksheet_status_json_array, int possition) {
        String worksheet_value = null;
        try {
            JSONObject jsonObj = (JSONObject) worksheet_status_json_array.get(possition);
            worksheet_value = jsonObj.get("Key").toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return worksheet_value;
    }

    /**
     * This is to get the work list type position  from the given JSON array using the selected work list type value
     *
     * @param worksheet_status_json_array is the JSON array in which we have to check for the work list type position
     * @param selected_value              is the value which is used to get the  work list type position from the given JSON Array
     * @return returns integer value which contains position value of the selected work list type value
     */
    public static int getWorkListTypeValuePossition(JSONArray worksheet_status_json_array, String selected_value) {
        int array_length = worksheet_status_json_array.length();
        int selected_possition = 0;
        try {
            for (int i = 0; i < array_length; i++) {
                JSONObject jsonObj = (JSONObject) worksheet_status_json_array.get(i);
                String worksheet_value = jsonObj.get("Value").toString();
                String Display = jsonObj.get("Display").toString();

                if (selected_value != null && worksheet_value != null && Display != null && Display.equalsIgnoreCase("yes") && worksheet_value.equalsIgnoreCase(selected_value))
                    selected_possition = i;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return selected_possition;
    }


    /**
     * This method id used to form a string array which contains the Names of the objects in the given JSON array
     * The names of the objects will be added to the string array if the display status of the particular object is "yes"
     * The other condition to satisfy is the value of the object must not be equals to "CW" or "SAE" or "Others"
     *
     * @param worksheet_status_json_array is the JSON array from where we have to get the objects
     * @return returns string array which contains names of the objects which satisfies the above conditions
     */
    public static String[] getWorkSheetStatusKeyStringArrayFromJSONArray(JSONArray worksheet_status_json_array) {
        int array_length = 0;
        if (worksheet_status_json_array != null)
            array_length = getWorkSheetStatusGetCount(worksheet_status_json_array);

        String[] string_array = new String[array_length];

        try {
            for (int i = 0; i < array_length; i++) {
                JSONObject jsonObj = (JSONObject) worksheet_status_json_array.get(i);
                String Name = jsonObj.get("Name").toString();
                String Display = jsonObj.get("Display").toString();
                String worksheet_value = jsonObj.get("Value").toString();

                if (Display != null && Display.equalsIgnoreCase("yes") && worksheet_value != null && !worksheet_value.equalsIgnoreCase("SAE") && !worksheet_value.equalsIgnoreCase("CW") && !worksheet_value.equalsIgnoreCase("Others"))
                    string_array[i] = Name;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return string_array;
    }

    /**
     * This is to form a string array which contains work sheet status names which are taken from the given JSON Array
     *
     * @param worksheet_status_json_array is the JSON array from which we have to get the work sheet status names
     * @return returns string array which contains work sheet status names.
     */
    public static String[] getWorkSheetStatusKeyStringArrayFromStatusJSONArray(JSONArray worksheet_status_json_array) {

        int array_length = 0;

        if (worksheet_status_json_array != null)
            array_length = worksheet_status_json_array.length();

        String[] string_array = new String[array_length];

        try {
            for (int i = 0; i < array_length; i++) {
                JSONObject jsonObj = (JSONObject) worksheet_status_json_array.get(i);
                int TimesheetStatusId = JSONUtils.getIntegerByKey(jsonObj.toString(), "TimesheetStatusId");
                String TimesheetStatusName = JSONUtils.getStringByKey(jsonObj.toString(), "TimesheetStatusName");
                string_array[i] = TimesheetStatusName;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return string_array;
    }

    /**
     * This method is used to get the work sheet status id which is matched to the given time sheet status name in the given JSON array
     *
     * @param worksheet_status_json_array  is the JSON array from which we have to get the work sheet status id
     * @param selected_TimesheetStatusName is the time sheet status name for which we have to get the time sheet status id
     * @return returns integer which contains status id of the given time sheet name
     */
    public static int getWorkSheetStatusID(JSONArray worksheet_status_json_array, String selected_TimesheetStatusName) {
        int array_length = worksheet_status_json_array.length();
        int selected_TimesheetStatusId = 0;

        try {
            for (int i = 0; i < array_length; i++) {
                JSONObject jsonObj = (JSONObject) worksheet_status_json_array.get(i);
                int TimesheetStatusId = JSONUtils.getIntegerByKey(jsonObj.toString(), "TimesheetStatusId");
                String TimesheetStatusName = JSONUtils.getStringByKey(jsonObj.toString(), "TimesheetStatusName");


                if (selected_TimesheetStatusName != null && TimesheetStatusName != null && TimesheetStatusName.equals(selected_TimesheetStatusName)) {
                    selected_TimesheetStatusId = TimesheetStatusId;
                    break;
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return selected_TimesheetStatusId;
    }

    /**
     * This method is to get the count of status of work sheets from the given JSON Array which satisfies the below conditions
     * The display status of the particular object in the given  JSON array must be equals to "yes" or "YES"
     * The work sheet value of the object must not be equals to "CW" or "SAE" or "Others"
     *
     * @param worksheet_status_json_array is the JSON array from where we have to get the work sheet status count
     * @return returns the work sheet status count
     */
    public static int getWorkSheetStatusGetCount(JSONArray worksheet_status_json_array) {
        int array_length = worksheet_status_json_array.length();
        int count = 0;
        try {
            for (int i = 0; i < array_length; i++) {
                JSONObject jsonObj = (JSONObject) worksheet_status_json_array.get(i);
                String Name = jsonObj.get("Name").toString();
                String Display = jsonObj.get("Display").toString();
                String worksheet_value = jsonObj.get("Value").toString();
                if (Display != null && Display.equalsIgnoreCase("yes") && worksheet_value != null && !worksheet_value.equalsIgnoreCase("SAE") && !worksheet_value.equalsIgnoreCase("CW") && !worksheet_value.equalsIgnoreCase("Others"))
                    count++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return count;
    }

    /**
     * Creates an HashMap for expandable group with index number as key and boolean status(default false) as value for given size
     * The status will be updated if the user checked on corresponding group checkbox of the same index
     *
     * @param size The size of the expandable group list view
     * @return expandable group list view HashMap with default unchecked status
     */
    public HashMap createExpandableCheckedGroupHM(int size) {
        HashMap expandable_group_hm = new HashMap<Integer, Boolean>();
        for (int i = 0; i < size; i++) {
            expandable_group_hm.put(i, false);
        }
        return expandable_group_hm;
    }

    /**
     * Updates the status of the user checked or unchecked on corresponding group checkbox of the same index
     *
     * @param expandable_group_hm expandable group list view HashMap
     * @param possition           position of the map that the status to be updated
     * @param isChecked           status of the checkbox('true' if it checked or 'false' if it unchecked)
     * @return expandable group list view HashMap with updated status
     */
    public HashMap updateExpandableCheckedGroupHM(HashMap expandable_group_hm, int possition, boolean isChecked) {
        expandable_group_hm.put(possition, isChecked);
        return expandable_group_hm;
    }


    /**
     * This is to get the label name of the given JSON label object
     *
     * @param label_json_obj is the JSON object from which we have to get the label name
     * @return returns label name if the display status of the given JSON object is equals to "yes" or "YES" else returns null
     */
    public static String getLabelFromJSON(String label_json_obj) {
        String label_str = null;

        String display = JSONUtils.getStringByKey(label_json_obj.toString(), "Display");

        if (display != null && display.equalsIgnoreCase("Yes"))
            label_str = JSONUtils.getStringByKey(label_json_obj.toString(), "Name");

        return label_str;
    }

    /**
     * This method is used to get the settings label name from the given settings JSON string using the given label key
     *
     * @param settings_json_str is the JSON string from which we have to get the settings label name
     * @param label_key         is the label key which is used to get corresponding label name from the given JSON string
     * @return returns string which contains settings label name
     */
    public static String getSettingsLabelFromJSON(String settings_json_str, String label_key) {
        String label_str = null;
        label_str = getSettingsLabelFromJSON(settings_json_str, label_key, null);
        return label_str;
    }

    /**
     * This method is used to get the settings label name from the given JSON string based on two conditions
     * (1) if the given key value is null, the settings label name will the Name attribute of the JSON  string object
     * (2) if the given key is not null then the  settings label name will the Key attribute of the JSON  string object
     *
     * @param settings_json_str is the JSON string which is used to get the JSON object
     * @param label_key         is the label key which is used to get the JSON object which was matched to the label key
     * @param key               is the key which is used to get the JSON object from the given JSON settings string
     * @return returns string which contains settings label name
     */
    public static String getSettingsLabelFromJSON(String settings_json_str, String label_key, String key) {
        String label_str = "";
        String setting_json_str = JSONUtils.getJSONObjectStringByKey(settings_json_str, label_key);
        if (setting_json_str != null && !setting_json_str.equals("") && key == null)
            label_str = JSONUtils.getStringByKey(setting_json_str, "Name");
        else if (setting_json_str != null && !setting_json_str.equals(""))
            label_str = JSONUtils.getStringByKey(setting_json_str, key);
        return label_str;
    }

    /**
     * This method is used to save the preference using the given parameters
     *
     * @param key_pref   is the key of the preference
     * @param value_pref is the value of the preference
     * @param context    is the activity context
     * @param type       is the operating mode. Use 0 or MODE_PRIVATE for the default operation, MODE_PRIVATE and MODE_WORLD_WRITEABLE to control permissions.
     * @param name_pref  is the name of the preference
     * @returns void
     */
    public static void savePrefrence(String key_pref, String value_pref, Context context, int type, String name_pref) {
        SharedPreferences myPrefs = ((Activity) context).getSharedPreferences(name_pref, type);
        SharedPreferences.Editor prefsEditor = myPrefs.edit();
        prefsEditor.putString(Constants.ST_KEY, key_pref);
        prefsEditor.putString(Constants.ST_VALUE, value_pref);
        prefsEditor.commit();
    }

    /**
     * This method is used to get the preference using the given parameters
     *
     * @param key_pref     is the key of the preference
     * @param default_pref is the default preference
     * @param context      is the activity context
     * @param type         is the operating mode. Use 0 or MODE_PRIVATE for the default operation, MODE_PRIVATE and MODE_WORLD_WRITEABLE to control permissions.
     * @param name_pref    is the name of the preference
     * @return string which contains preference value
     */
    public static String getPrefrence(String key_pref, String default_pref, Context context, int type, String name_pref) {

        SharedPreferences Prefs = ((Activity) context).getSharedPreferences(name_pref, type);
        String pref_value_str = Prefs.getString(key_pref, default_pref);

        return pref_value_str;
    }

    public static Map<String, ?> getPrefrence_(Context context, String name_pref) {

        SharedPreferences Prefs = ((Activity) context).getSharedPreferences(name_pref, context.MODE_PRIVATE);
        Map<String, ?> pref_value_str = Prefs.getAll();//String(key_pref, default_pref);

        return pref_value_str;
    }




    public String getTwoDecimalvalue(String value) {
        if (value == null && value.equalsIgnoreCase("null"))
            value = "0";

        Float litersOfPetrol = Float.parseFloat(value);
        DecimalFormat df = new DecimalFormat("0.00");
        df.setMaximumFractionDigits(2);
        value = df.format(litersOfPetrol);
        return value;

    }

    public void dismissDialog(ProgressDialog dialog) {
        try {
            if (dialog != null && dialog.isShowing())
                dialog.dismiss();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean fieldexist(JSONArray jsonArray, String usernameToFind) {
        return jsonArray.toString().contains("\"" + usernameToFind + "\"");
    }

      /**
     * author Ramesh Eerla
     *
     * @param doublevalue
     * @return String
     * To convert the double value to two decimal format string
     */
    public static String convertTwoDecimals(double doublevalue) {
        DecimalFormat df = new DecimalFormat("00.00##");
        String result = df.format(doublevalue);
        return result;
    }

    /**
     * author Ramesh Eerla
     *
     * @param val
     * @return int
     * To convert the String value to integer
     */
    public static int convertInteger(String val) {
        int value = 0;
        if (!val.equalsIgnoreCase("")) {
            try {
                value = Integer.parseInt(val);
            } catch (Exception e) {
                value = 0;
            }
        }
        return value;
    }

    /**
     * author Ramesh Eerla
     *
     * @param val
     * @return double
     * To convert the String value to double
     */
    public static double convertDouble(String val) {
        double value = 0;
        if (!val.equalsIgnoreCase("")) {
            try {
                value = Double.parseDouble(val);
            } catch (Exception e) {
                value = 0;
            }
        }
        return value;
    }

    /*
     * @Author :: Santosh Kumar Baratam
     * Use : Create the Alertdialog reference
     * */
    private static AlertDialog createNewAlertDialogInstance(Context context) {
        AlertDialog alertDialog = new AlertDialog.Builder(context, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT).create();
        alertDialog.setCancelable(true);
        alertDialogArrayList.add(alertDialog);
        return alertDialog;
    }

    /*
     * @Author :: Santosh Kumar Baratam
     * Use : Clear the arraylist , skipping dialog with same text , creating the new reference if different text or null dialog
     * */
    public static AlertDialog getAlertDialog(Context context, String message) {
        if (alertDialogArrayList.size() > 10) {
            alertDialogArrayList.clear();
        }
        if (alertDialog != null) {
            if (isAlertDialogShowingWithSameLabelText(context, message)) {
                return getAlertDialogShowingWithSameLabelText(context, message);
            } else {
                return createNewAlertDialogInstance(context);
            }
        } else {
            return createNewAlertDialogInstance(context);
        }
    }

    /*
     * @Author :: Santosh Kumar Baratam
     * Use : return the message added to the input alert dialog
     * */
    private static String getAlertDialogLabelText(Context context, AlertDialog alertDialog) {
        TextView tv = (TextView) alertDialog.findViewById(android.R.id.message);
        return (tv != null) ? tv.getText().toString() : "";
    }

    /*
     * @Author :: Santosh Kumar Baratam
     * Use : comparing similar text available in showing dialogs if yes skipping creating new dialog
     * */
    private static boolean isAlertDialogShowingWithSameLabelText(Context context, String message) {
        boolean status = false;
        for (AlertDialog alertDialog : alertDialogArrayList) {
            if (!status) {
                if (alertDialog != null &&
                        alertDialog.isShowing() &&
                        getAlertDialogLabelText(context, alertDialog).toLowerCase().equalsIgnoreCase(message.toLowerCase())) {
                    status = true;
                } else {
                    status = false;
                }
            }
        }
        return status;
    }

    private static AlertDialog getAlertDialogShowingWithSameLabelText(Context context, String message) {
        for (AlertDialog alertDialog : alertDialogArrayList) {
            if (alertDialog != null &&
                    alertDialog.isShowing() &&
                    getAlertDialogLabelText(context, alertDialog).toLowerCase().equalsIgnoreCase(message.toLowerCase())) {
                return alertDialog;
            }
        }
        return alertDialog;
    }

    }

