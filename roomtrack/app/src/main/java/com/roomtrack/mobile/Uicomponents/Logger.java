package com.roomtrack.mobile.Uicomponents;

import android.util.Log;


/**
 * This class is to enable and disable log's in the application based on the
 * PROD type.
 * 
 * @author krachama
 * 
 */
public class Logger {

    public static final String TAG = "OpenNativeApp";

    /**
     * logger with debug
     * 
     * @param tag
     * @param msg
     */
    public static void d(String tag, String msg) {
        if (!BuildType.PROD) {
            Log.d(getTag(tag), msg);
        }
    }

    /**
     * logger with warn
     * 
     * @param tag
     * @param msg
     */
    public static void w(String tag, String msg) {
        if (!BuildType.PROD) {
            Log.w(getTag(tag), msg);
        }
    }

    /**
     * logger with info
     * 
     * @param tag
     * @param msg
     */
    public static void i(String tag, String msg) {
        if (!BuildType.PROD) {
            Log.i(getTag(tag), msg);
        }
    }

    /**
     * logger with error
     * 
     * @param tag
     * @param msg
     * @param t
     */
    public static void e(String tag, String msg, Throwable t) {
        if (!BuildType.PROD) {
            if (t != null) {
                Log.e(getTag(tag), msg, t);
            } else {
                Log.e(getTag(tag), msg);
            }
        }
    }

    /**
     * logger with verbose
     * 
     * @param tag
     * @param msg
     * @param t
     */
    public static void v(String tag, String msg, Throwable t) {
        if (!BuildType.PROD) {
            if (t != null) {
                Log.v(getTag(tag), msg, t);
            } else {
                Log.v(getTag(tag), msg);
            }
        }
    }

    /**
     * logger with verbose
     * 
     * @param tag
     * @param msg
     */
    public static void v(String tag, String msg) {
        if (!BuildType.PROD) {
            Log.v(getTag(tag), msg);
        }
    }

    /**
     * logger to getTag value
     * 
     * @param inp
     * @return
     */
    private static String getTag(String inp) {
        if (inp != null && inp.trim().length() > 0) {
            return inp;
        } else {
            return TAG;
        }
    }
}
