package com.roomtrac.mobile.utils;


import java.util.HashMap;

@SuppressWarnings({"unchecked", "rawtypes"})
public class MapUtils {

    public HashMap<String, String> gethome_Type;
    public HashMap<String, String> gethostel_Type;
    public HashMap<String, String> getroom_Type;
    public HashMap<String, String> getfood_Type;
    public HashMap<String, String> gethome_Preference_tyep;
    public HashMap<String, String> gethostel_nature;

    public MapUtils() {

        setHome_Type();
        setHostel_Type();
        setRoom_Type();
        setFood_Type();
        setHome_Preference();
        setHostel_Nature();

    }
    private void setRoom_Type() {
        getroom_Type = new HashMap<String, String>();
        getroom_Type.put("Timesheet1", "Create Timesheet");
        getroom_Type.put("Timesheet2", "Timesheet Details");
        getroom_Type.put("13", "Zero Hour Timesheet");
        getroom_Type.put("14", "Create New Timesheet");
        getroom_Type.put("15", "Create New ");
        getroom_Type.put("16", "Zero Hour ");
        getroom_Type.put("Non Employee Invoice1", "Create Non Employee Invoice");
        getroom_Type.put("Non Employee Invoice2", "Non Employee Invoice Details");
        getroom_Type.put("Non Employee Invoice3", "Zero Hours Non Employee Invoice");
        getroom_Type.put("Non Employee Invoice4", "Create New Non Employee Invoice");

    }


    private void setHome_Type() {
        gethome_Type = new HashMap<String, String>();
        gethome_Type.put("Timesheet1", "Create Timesheet");
        gethome_Type.put("Timesheet2", "Timesheet Details");
        gethome_Type.put("13", "Zero Hour Timesheet");
        gethome_Type.put("14", "Create New Timesheet");
        gethome_Type.put("15", "Create New ");
        gethome_Type.put("16", "Zero Hour ");
        gethome_Type.put("Non Employee Invoice1", "Create Non Employee Invoice");
        gethome_Type.put("Non Employee Invoice2", "Non Employee Invoice Details");
        gethome_Type.put("Non Employee Invoice3", "Zero Hours Non Employee Invoice");
        gethome_Type.put("Non Employee Invoice4", "Create New Non Employee Invoice");

    }
    private void setFood_Type() {
        getfood_Type = new HashMap<String, String>();
        getfood_Type.put("Timesheet1", "Create Timesheet");
        getfood_Type.put("Timesheet2", "Timesheet Details");
        getfood_Type.put("13", "Zero Hour Timesheet");
        getfood_Type.put("14", "Create New Timesheet");
        getfood_Type.put("15", "Create New ");
        getfood_Type.put("16", "Zero Hour ");
        getfood_Type.put("Non Employee Invoice1", "Create Non Employee Invoice");
        getfood_Type.put("Non Employee Invoice2", "Non Employee Invoice Details");
        getfood_Type.put("Non Employee Invoice3", "Zero Hours Non Employee Invoice");
        getfood_Type.put("Non Employee Invoice4", "Create New Non Employee Invoice");

    }
    private void setHome_Preference() {
        gethome_Preference_tyep = new HashMap<String, String>();
        gethome_Preference_tyep.put("Timesheet1", "Create Timesheet");
        gethome_Preference_tyep.put("Timesheet2", "Timesheet Details");
        gethome_Preference_tyep.put("13", "Zero Hour Timesheet");
        gethome_Preference_tyep.put("14", "Create New Timesheet");
        gethome_Preference_tyep.put("15", "Create New ");
        gethome_Preference_tyep.put("16", "Zero Hour ");
        gethome_Preference_tyep.put("Non Employee Invoice1", "Create Non Employee Invoice");
        gethome_Preference_tyep.put("Non Employee Invoice2", "Non Employee Invoice Details");
        gethome_Preference_tyep.put("Non Employee Invoice3", "Zero Hours Non Employee Invoice");
        gethome_Preference_tyep.put("Non Employee Invoice4", "Create New Non Employee Invoice");

    }
    private void setHostel_Nature() {
        gethostel_nature = new HashMap<String, String>();
        gethostel_nature.put("Timesheet1", "Create Timesheet");
        gethostel_nature.put("Timesheet2", "Timesheet Details");
        gethostel_nature.put("13", "Zero Hour Timesheet");
        gethostel_nature.put("14", "Create New Timesheet");
        gethostel_nature.put("15", "Create New ");
        gethostel_nature.put("16", "Zero Hour ");
        gethostel_nature.put("Non Employee Invoice1", "Create Non Employee Invoice");
        gethostel_nature.put("Non Employee Invoice2", "Non Employee Invoice Details");
        gethostel_nature.put("Non Employee Invoice3", "Zero Hours Non Employee Invoice");
        gethostel_nature.put("Non Employee Invoice4", "Create New Non Employee Invoice");

    }
    private void setHostel_Type() {
        gethostel_Type = new HashMap<String, String>();
        gethostel_Type.put("Timesheet1", "Create Timesheet");
        gethostel_Type.put("Timesheet2", "Timesheet Details");
        gethostel_Type.put("13", "Zero Hour Timesheet");
        gethostel_Type.put("14", "Create New Timesheet");
        gethostel_Type.put("15", "Create New ");
        gethostel_Type.put("16", "Zero Hour ");
        gethostel_Type.put("Non Employee Invoice1", "Create Non Employee Invoice");
        gethostel_Type.put("Non Employee Invoice2", "Non Employee Invoice Details");
        gethostel_Type.put("Non Employee Invoice3", "Zero Hours Non Employee Invoice");
        gethostel_Type.put("Non Employee Invoice4", "Create New Non Employee Invoice");

    }




}
