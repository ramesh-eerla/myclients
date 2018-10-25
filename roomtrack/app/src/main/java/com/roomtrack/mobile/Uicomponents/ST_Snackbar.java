package com.roomtrack.mobile.Uicomponents;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;


import java.util.Map;

/**
 * Created by Ramesh.eerla on 24/10/2018.
 */
public class ST_Snackbar {

    public Snackbar snackbar;
    public static String error_message = "";
    public static Map<String, ?> error_title_message;

    /*
    * login_erros is for checking the off line and online login validation messages
    * context object of activity
    * error_case is for errorcase id
    * */
    public static String login_errors(Context context, int error_case) {
        error_message = "";
        error_title_message = CommonHelper.getPrefrence_(context, "" + error_case);
        for (Map.Entry<String, ?> entry : error_title_message.entrySet()) {
            if (entry.getValue().toString().equalsIgnoreCase(Constants.ST_VALUE))
                error_message = entry.getValue().toString();
        }
        if (error_message.equalsIgnoreCase(""))
            switch (error_case) {
                case Constants.ST_USERNAME:
                    error_message = Constants.ST_USERNAME_Error_Message;
                    break;
                case Constants.ST_PWD:
                    error_message = Constants.ST_PWD_Error_Message;
                    break;
                case Constants.ST_USERNAME_PWD:
                    error_message = Constants.ST_USERNAME_PWD_Error_Message;
                    break;
                case Constants.ST_USERNAME_TOKEN:
                    error_message = Constants.ST_USERNAME_TOKEN_Error_Message;
                    break;
                case Constants.ST_TOKEN:
                    error_message = Constants.ST_TOKEN_Error_Message;
                    break;
                case Constants.ST_NONETWORK:
                    error_message = Constants.ST_NONETWORK_Error_Message;
                    break;

            }
        return error_message;
    }

    /*
    * messageOnly is for showing a snackbar without any action
    * context object of activity
    * view  object of parentlayout of activity
    * error_case is for errorcase id
    * */
    public static Snackbar messageOnly(Context context, View view, int error_case) {

        Snackbar snackbar = Snackbar
                .make(view, login_errors(context, error_case), Snackbar.LENGTH_LONG);
        snackbar.getView().setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        TextView textView = (TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        snackbar.show();
        return snackbar;
    }

    public static Snackbar messageWithAction(View view, String message, String acttion) {
        Snackbar snackbar = Snackbar
                .make(view, message, Snackbar.LENGTH_LONG)
                .setAction(acttion, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

        snackbar.show();
        return snackbar;
    }

    public static void hideKeybord(View view) {
        InputMethodManager inputMethodManager = null;
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
