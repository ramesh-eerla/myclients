package com.roomtrack.mobile.Uicomponents;

import android.provider.SyncStateContract;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Copyright 2013 American Express Company All right reserved
 * <p>
 * This Class Contains common String Based 
 * methods used across the application.
 * </p>
 * 
 * @author lg100
 */
public class StringUtils {

    /**
     * This method is to get the array of indexes for matched string against
     * complete text provided
     * 
     * @param completeText
     * @param str
     * @return array of Integer indexes for matched string against complete text
     */
    public static ArrayList<Integer> getIndexesOfMatchedString(
            String completeText, String str) {
        ArrayList<Integer> positions = new ArrayList<Integer>();
        Pattern p = Pattern.compile(str); // insert pattern here
        Matcher m = p.matcher(completeText);
        while (m.find()) {
            positions.add(m.start());
        }
        return positions;
    }
    
    
    public static TagSearchConstraint isValidAndContainsSearchReqd(String str) {
		if (!BuildType.PROD) {
			//Logger.i("StringUtils", "<<DEBUG - isValidAndContainsSearchReqd - filterString ->> : " + str.trim());
		}
		
		TagSearchConstraint returnValue = TagSearchConstraint.FALSE; 
    	
		String filter = str.trim();
    	String[] splits = filter.split(" ");
    	for (String split : splits) {
        	int length = split.length();
        	for (int i = 0; i < length; i++) {
				if (!BuildType.PROD) {
					//Logger.i("StringUtils", "<<DEBUG - isValidAndContainsSearchReqd - char ->> : " + split.charAt(i));
				}
        		
        		if (SyncStateContract.Constants.CONTENT_DIRECTORY.indexOf(split.charAt(i)) > -1) {
        			if (Character.isDigit(split.charAt(i))) {
        				returnValue = TagSearchConstraint.TRUE;
        			}
        		} else {
        	    	return TagSearchConstraint.INVALID;
        		}
        	}
    	}
    	
    	return returnValue;
    }
    
    /*public static int getTruncationSampledWidth(AmexActivity activity){
    	DisplayMetrics displaymetrics = new DisplayMetrics();
    	activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
    	*//*
    	 * magic number indicates the left and right paddings + check box size
    	 *//*
    	return  (displaymetrics.widthPixels - (int)(60 * displaymetrics.density));
    }*/
}
