package com.roomtrac.mobile.utils;

/**
 * Created by ramesh.eerla on 1/13/2017.
 */

public class Constants {
    public static final int ST_LAND =200 ;
    public static final int ST_OTHER =201 ;
    public static String ST_KEY="KEY";
    public static String ST_VALUE="VALUE";
    public static final int ST_NONETWORK=505;
    public static final int ST_CONECTIONTIMEOUT=506;
    public static final int ST_INVALIDRESPONCE=507;
    public static final int ST_TODOLIST=508;
    public static final int ST_USERNAME=500;
    public static final int ST_PWD=501;
    public static final int ST_USERNAME_PWD=502;
    public static final int ST_USERNAME_TOKEN=503;
    public static final int ST_TOKEN=504;
    public static final int ST_RELOADLOGIN=1;
    public static final int ST_RESUMEUPLOAD=400;
    public static final int ST_RESUMEDELETE=401;
    public static final int ST_ATTACHMENTUPLOAD=402;
    public static final int ST_ATTACHMENTDELETE=403;

    public static final String SECOND_ITEM_UNDERLINE="SECOND_ITEM_UNDERLINE";
    public static final String SECOND_ITEM_JOBDESC="SECOND_ITEM_JOBDESC";
    public static final String SINGLE_ITEM_UNDERLINE="SINGLE_ITEM_UNDERLINE";
    public static final String SINGLE_ITEM="SINGLE_ITEM";
    public static final String FIRST_ITEM="FIRST_ITEM";
    public static final String EXPENSE_ITEM="EXPENSE_ITEM";
    public static final String TWO_ITEMS="TWO_ITEMS";
    public static final String SECOND_ITEM="SECOND_ITEM";

    public static final String ST_USERMAIL="UserEmail";
    public static final String LOGOUT_KEY="Logout";
    public static final String EXIT_KEY="Exit";
    public static final String CLIENE_CHANGE_KEY="Client";
    public static final String ST_APTYPE="APP_ENVIRONMENT";


    public static String ST_NONETWORK_Error_Message="No network available";
    public static String ST_CONECTIONTIMEOUT_Error_Message="Connection Timeout";
    public static String ST_INVALIDRESPONCE_Error_Message="No result found";
    public static String ST_UN_AUTHARIZED_USER="Unauthorized user";
    public static String ST_INTERNAL_SERVER="Internal Server Error";

    public static String ST_USERNAME_Error_Message="Please enter your username.";
    public static String ST_PWD_Error_Message="Please enter your password.";
    public static String ST_USERNAME_PWD_Error_Message="Please enter your username and password.";
    public static String ST_USERNAME_TOKEN_Error_Message="Please enter username and token";
    public static String ST_TOKEN_Error_Message="Please enter token";
    public static String ST_ZERO_TIMESHEET="Zero Hour Timesheet can not be assigned for future dates.";
    public static String ST_NO_RESULT="No Result Found";
    public static String APPLICANT_QTS="There are no questions currently listed";
    /*
     * Author Sravan chatla
     * Added Constants for Cw expense
     */
    public static String ST_INVALID_EXPENSE_ERROR = "Some error occured while submitting expense.";
    public static String ST_INVALID_EXPENSE_SAVING_ERROR = "problem in saving!";
    public static String ST_INVALID_FAILED_RESPONSE = "failed to get response";
    public static String RI_ADD_BTN_CNFIRM = "The Interviewer Name selected is already present in the List";



    static final int SHOW_MAX_YEARS = 10;
    static final int SHOW_MIN_YEARS = -10;
    static final int SHOW_FROM_YEAR_END_DATE = 5001;
    static final int SHOW_TO_YEAR_END_DATE = 5002;

    static final int NO_HIDE_ALL_PAST_FUTURE_YEARS = 001;
    static final int HIDE_PAST_FUTURE_YEARS = 003;

    public static final int ADD_MODEL_TO_TAG = 111;
    public static int SUBMITTED_LIST = 0;
    public static int WITHDRAW_LIST = 1;
    public static final String CALL_TYPE= "callType";
    public static String ST_RIGHTS_VALIDATION_MESSAGE = "Unauthorized: Access is denied";


}
