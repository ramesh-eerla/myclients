package com.roomtrack.mobile.interfaces;

import java.util.Map;


public interface GetHttpResponse {
	
	/**
	* This is the callback method with arguments exception_message
	* @param exception_message The exception message
	* @return void
	* @author Launchship_Ravichandra
	*/
	public void callback(String exception_message);
	
	/**
	* This is the callback method with arguments response,type
	* @param response The response received from the server
	* @param type To validate which type of response
	* @return void
	* @author Launchship_Ravichandra
	*/
    public void callback(String response, String type);
    
	/**
	* This is the callback method with arguments response,type
	* @param response The response received from the server
	* @param isAuthorised To check is Authorised user
	* @param type To validate which type of response
	* @return void
	* @author Launchship_Ravichandra
	*/
    public void callback(String response, boolean isAuthorised, String type);
    
	/**
	*  This is the callback method with arguments response,isAuthorised,type
	*  @param result_map Map class with cw users wise grouped work list
	*  @param type To validate which type of response
	*  @param common_obj CommonHelper class reference 
	*  @return void
    *  @author Launchship_Ravichandra
	*/
    @SuppressWarnings("rawtypes")
	public void callback(Map result_map, String type, Object common_helper_obj);
    
	/**
	* This is the callback method with arguments response,isThisTimeSheetSupported,type,common_helper_obj
	* @param response The response received from the server
	* @param isThisTimeSheetSupported To check timesheet is supported or not
	* @param type To validate which type of response
	* @param common_obj CommonHelper class reference 
	* @return void 
    * @author Launchship_Ravichandra
	*/
    public void callback(String response, boolean isThisTimeSheetSupported, String type, Object common_helper_obj);
}
