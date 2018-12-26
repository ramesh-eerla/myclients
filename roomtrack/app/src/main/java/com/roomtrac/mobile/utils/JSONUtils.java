package com.roomtrac.mobile.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.google.gson.internal.LinkedTreeMap;
import com.roomtrac.mobile.connectioncalls.datasets.DetailsDataset;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SuppressWarnings("unused")
public class JSONUtils {




	/**  
	 *    This is the class which is use for all JSON parsing operations.
	 *    This class is used for parsing any JSON string in the whole project. 
	 * 
	 */

	/**
	 * To check whether the given JSON string is valid or not
	 * @param JSON_str is the JSON string which has to be validated
	 * @return returns true if given JSON string is valid else returns false
	 * @author Launchship_Ravichandra 
	 */
    public static boolean validateJSONFormat(String JSON_str)
	{
    	// System.out.println("validateJSONFormat() called");
         boolean isJSONFormat = true;
		
		try {
				JSONObject json=new JSONObject(JSON_str);
		
		} catch (JSONException e) {
				isJSONFormat = false;
				System.out.println("***JSONException ()" + e.getMessage());
		}
		catch (NullPointerException e) {
			    isJSONFormat = false;
				System.out.println("***NullPointerException ()" + e.getMessage());
	    }
		catch (Exception e) {
				isJSONFormat = false;
				System.out.println("***Exception ()" + e.getMessage());
		}
		//Log.e("isJSONFormat:",isJSONFormat+"");
		
		return isJSONFormat;
	}
    
    /**
     * To get JSON string object from the given JSON string using the given key
     * @param JSON_str is the JSON string which is used to get  JSON string object using the key
     * @param key is the value which is used to get JSON string object from a JSON string
     * @return returns JSON string object if no exception occurred else returns null
     * @author Launchship_Ravichandra 
     */
	@SuppressLint("LongLogTag")
	public static String getJSONObjectStringByKey(String JSON_str, String key)
	{
      
		String value=null;
		
		try {
				JSONObject json=new JSONObject(JSON_str);
				//Log.e("JSON:","<jsonobject>\n"+json.toString()+"\n</jsonobject>");
				if(json.has(key))
				value=(json.get(key)).toString();
				else
					value = json.toString();
			
		} catch (JSONException e) {
			Log.e("***Exception getJSONObjectStringByKey()", e.getMessage());
		 }
		catch (NullPointerException e) {
			Log.e("***NullPointerException " +
                    "getJSONObjectStringByKey()", "" + e.getMessage());
		}
		catch (ClassCastException e) {
			Log.e("***ClassCastException getJSONObjectStringByKey()", e.getMessage());
		}
		catch (Exception e) {
			Log.e("***Exception getJSONObjectStringByKey()", e.getMessage());
		}
		
		
		
		return value;
	}
	
	 /**
     * To get JSON string object from the given JSON string using the given key
     * @param JSON_str is the JSON string which is used to get  JSON string object using the key
     * @param key is the value which is used to get JSON string object from a JSON string
     * @return returns JSON string object if no exception occurred else returns null
     * @author Launchship_Ravichandra 
     */
	@SuppressLint("LongLogTag")
	public static String getJSONObjectStringByKey_fulfillment(String JSON_str, String key)
	{
      
		String value="";
		
		try {
				JSONObject json=new JSONObject(JSON_str);
				//Log.e("JSON:","<jsonobject>\n"+json.toString()+"\n</jsonobject>");
				value=((JSONObject)json.get(key)).toString();
			
		} catch (JSONException e) {
			Log.e("***Exception getJSONObjectStringByKey()", e.getMessage());
		}
		catch (NullPointerException e) {
			Log.e("***NullPointerException " +
					"getJSONObjectStringByKey()", ""+e.getMessage());
		}
		catch (ClassCastException e) {
			Log.e("***ClassCastException getJSONObjectStringByKey()", e.getMessage());
		}
		catch (Exception e) {
			Log.e("***Exception getJSONObjectStringByKey()", e.getMessage());
		}
		
		
		
		return value;
	}
	
	
	/**
	 * To get JSON  object from the given JSON string using the given key
	 * @param JSON_str is the JSON string which is used to get  JSON  object using the key
	 * @param key is the value which is used to get JSON  object from a JSON string
	 * @return returns JSON  object if no exception occurred else returns null
	 * @author Launchship_Ravichandra 
	 */
	@SuppressLint("LongLogTag")
	public static Object getJSONValueByKey(String JSON_str, String key)
	{
      // Log.e("--------getJSONValueByKey()---------", "--------getJSONValueByKey() starts--------");
		Object value=null;
		
		try {
				JSONObject json=new JSONObject(JSON_str);
				//Log.e("JSON:","<jsonobject>\n"+json.toString()+"\n</jsonobject>");
			if(json.has(key))
				value=(Object)json.get(key);
			else
				value = (Object)json;
			
		}
		 catch (ClassCastException e) {
				Log.e("***getJSONValueByKey()", e.getMessage());
			}
		catch (JSONException e) {
				Log.e("***JSONException", e.getMessage());
		}
		catch (NullPointerException e) {
			Log.e("***NullPointerException getJSONValueByKey()", ""+e.getMessage());
		}
		catch (Exception e) {
			Log.e("***Exception getJSONValueByKey()", e.getMessage());
		}
		
		return value;
	}
	/**
	 * To get JSON  object from the given JSON string using the given key
	 * @param JSON_str is the JSON string which is used to get  JSON  object using the key
	 * @param key is the value which is used to get JSON  object from a JSON string
	 * @return returns JSON  object if no exception occurred else returns null
	 * @author Launchship_Ramesh
	 */
	@SuppressLint("LongLogTag")
	public static JSONObject getJSONObject(String JSON_str, String key)
	{
		// Log.e("--------getJSONValueByKey()---------", "--------getJSONValueByKey() starts--------");
		JSONObject value=null;

		try {
			JSONObject json=new JSONObject(JSON_str);
			//Log.e("JSON:","<jsonobject>\n"+json.toString()+"\n</jsonobject>");
			value=json.getJSONObject(key);

		}
		catch (ClassCastException e) {
			Log.e("***ClassCastException getJSONObject()", e.getMessage());
		}
		catch (JSONException e) {
			Log.e("***JSONException getJSONObject()", e.getMessage());
		}
		catch (NullPointerException e) {
			Log.e("***NullPointerException getJSONObject()", ""+e.getMessage());
		}
		catch (Exception e) {
			Log.e("***Exception getJSONObject()", e.getMessage());
		}

		return value;
	}
	/**
	 * To get the boolean value based on the given JSON string and key
	 * @param JSON_str is the JSON string which is used to get  boolean value using the key 
	 * @param key is the value which is used to get boolean value from a JSON string
	 * @return returns true if the key existed in the given JSON string else returns false
	 * @author Launchship_Ravichandra 
	 */
	@SuppressLint("LongLogTag")
	public static boolean getBooleanByKey(String JSON_str, String key)
	{
      // Log.e("--------getJSONValueByKey()---------", "--------getJSONValueByKey() starts--------");
		boolean flag = false;
		
		try {
			Log.d("the json string",""+JSON_str+"key"+key);
				JSONObject json=new JSONObject(JSON_str);			
				flag = json.getBoolean(key);
				Log.d("the flag values", "flag"+flag);
			
		}
	    catch (ClassCastException e) {
			Log.e("***ClassCastException getBooleanByKey()", e.getMessage());
		}
		catch (NullPointerException e) {
			Log.e("***NullPointerException getBooleanByKey()", ""+e.getMessage());
		} 
		catch (JSONException e) {
				Log.e("***JSONException getBooleanByKey()", e.getMessage());
		}
		catch (Exception e) {
			Log.e("***Exception getBooleanByKey()", e.getMessage());
	    }
		
		return flag;
	}
	/**
	 * To get the integer value based on the given JSON string and key
	 * @param JSON_str is the JSON string which is used to get  integer value using the key 
	 * @param key is the value which is used to get integer value from a JSON string
	 * @return returns integer value if the key existed in the given JSON string else returns 0
	 * @author Launchship_Ravichandra 
	 */
	@SuppressLint("LongLogTag")
	public static int getIntegerByKey(String JSON_str, String key)
	{
     //  Log.e("--------getJSONValueByKey()---------", "--------getJSONValueByKey() starts--------");
     //  System.out.println("key:"+key);
		int value = 0;
		
		try {
				JSONObject json=new JSONObject(JSON_str);
				//Log.e("JSON:","<jsonobject>\n"+json.toString()+"\n</jsonobject>");
				value = (Integer)json.get(key);
			
		}
		 catch (ClassCastException e) {
				Log.e("***ClassCastException getIntegerByKey()", e.getMessage());
			}
		 catch (NullPointerException e) {
				Log.e("***NullPointerException getIntegerByKey()", "" + e.getMessage());
			}
		catch (JSONException e) {
				Log.e("***JSONException getIntegerByKey()", e.getMessage());
		}
		catch (Exception e) {
			Log.e("***Exception getIntegerByKey()", e.getMessage());
	    }
 
		return value;
	}
	/**
     * To get string value from the given JSON string using the given key
     * @param JSON_str is the JSON string which is used to get string value  using the key
     * @param key is the value which is used to get  string value from a JSON string
     * @return returns JSON string value if no exception occurred else returns empty string
     * @author Launchship_Ravichandra 
     */
	@SuppressLint("LongLogTag")
	public static String getStringByKey(String JSON_str, String key)
	{
      // Log.e("--------getJSONValueByKey()---------", "--------getJSONValueByKey() starts--------");
		String value = "";
		
		try {
				JSONObject json=new JSONObject(JSON_str);
				Log.e("JSON:",""+json);
				value = (String)json.get(key);
			
		}
		 catch (ClassCastException e) {
				Log.e("***ClassCastException getStringByKey()", e.getMessage());
				e.printStackTrace();
			}
		catch (JSONException e) {
				Log.e("***JSONException getStringByKey()", e.getMessage());
				e.printStackTrace();
		}
		 catch (NullPointerException e) {
				Log.e("***NullPointerException getStringByKey()", ""+e.getMessage());
				e.printStackTrace();
			}
		catch (Exception e) {
			Log.e("***Exception getStringByKey()", e.getMessage());
			e.printStackTrace();
	}
		return value;
	}
	/**
     * To get JSONArray from the given JSON string using the given key
     * @param JSON_str is the JSON string which is used to get JSONArray using the key
     * @return returns JSONArray if no exception occurred else returns null
     * @author Launchship_Ravichandra 
     */
	@SuppressLint("LongLogTag")
	public static List<DetailsDataset> getSearchdata(List<LinkedTreeMap> JSON_str)
	{
		ArrayList<DetailsDataset> datasets=new ArrayList<>();
		 
		JSONArray jsonArray=null;
		try {
			for(LinkedTreeMap map: JSON_str) {
				DetailsDataset dataset=new DetailsDataset();
				dataset.setMembers_detail_id(map.get("members_detail_id").toString());
				dataset.setMember_id(map.get("member_id").toString());
				dataset.setRent_id(map.get("rent_id").toString());
				dataset.setHostel_id(map.get("hostel_id").toString());
				dataset.setRoom_type_id(map.get("room_type_id").toString());
				dataset.setFacility(map.get("facility").toString());
				dataset.setFood_type(map.get("food_type").toString());
				dataset.setFurnish_type(map.get("furnish_type").toString());;
				dataset.setArea_sqft(map.get("area_sqft").toString());
				dataset.setHome_appt_nature_id(map.get("home_appt_nature_id").toString());
				dataset.setTag_line(map.get("tag_line").toString());
				dataset.setRent_per_month(map.get("rent_per_month").toString());
				dataset.setHome_appt_type_id(map.get("home_appt_type_id").toString());
				dataset.setHome_appt_preferences_id(map.get("home_appt_preferences_id").toString());
				dataset.setHostel_type_id(map.get("hostel_type_id").toString());
				dataset.setHostel_nature_id(map.get("hostel_nature_id").toString());
				dataset.setMembers_per_room(map.get("members_per_room").toString());
				dataset.setVacency_for(map.get("vacency_for").toString());;
				dataset.setSex(map.get("sex").toString());
				dataset.setMin_age(map.get("min_age").toString());
				dataset.setMax_age(map.get("max_age").toString());
				dataset.setAvailibility_date(map.get("availibility_date").toString());
				dataset.setAvailibility_month(map.get("availibility_month").toString());
				dataset.setAvailibility_year(map.get("availibility_year").toString());
				dataset.setCountry_id(map.get("country_id").toString());
				dataset.setState_id(map.get("state_id").toString());
				dataset.setCity_id(map.get("city_id").toString());
				dataset.setLocation_id(map.get("location_id").toString());
				dataset.setAbout_room(map.get("about_room").toString());;
				dataset.setAbout_yourself(map.get("about_yourself").toString());
				dataset.setHome_town(map.get("home_town").toString());

				dataset.setPhone_number(map.get("phone_number").toString());
				dataset.setUser_uploaded(map.get("user_uploaded").toString());
				dataset.setLast_activity(map.get("last_activity").toString());
				dataset.setStatus(map.get("status").toString());;
				dataset.setFlag(map.get("flag").toString());
				dataset.setPosted_on(map.get("posted_on").toString());
				if(map.keySet().contains("memberid"))
				dataset.setMemberid(map.get("memberid").toString());
				dataset.setLocation_name(map.get("location_name").toString());
				dataset.setCity_name(map.get("city_name").toString());
				dataset.setState_name(map.get("state_name").toString());
				if(map.keySet().contains("memberid"))
				dataset.setCountry_name(map.get("country_name").toString());
              	dataset.setFirst_name(map.get("first_name").toString());
				dataset.setLast_name(map.get("last_name").toString());
				dataset.setEmail(map.get("email").toString());

                if(CommonUtils.applyfilter) {
                    if (!validateFileterData(dataset))
                        datasets.add(dataset);

                }else{datasets.add(dataset);}
			}

			
		} 
		catch (ClassCastException e) {
			    Log.e("***ClassCastException getJSONArray()", e.getMessage());
			//e.printStackTrace();
		}
		catch (Exception e) {
			Log.e("***JSONException getJSONArray()", e.getMessage());
	    }

	
 
		finally
		{
			if(jsonArray==null)
			   jsonArray = new JSONArray();
		}
		
       return datasets;
	}

	@SuppressLint("LongLogTag")
	public static List<DetailsDataset> getBookmarkData(List<LinkedTreeMap> JSON_str)
	{
		ArrayList<DetailsDataset> datasets=new ArrayList<>();

		JSONArray jsonArray=null;
		try {
			for(LinkedTreeMap map: JSON_str) {
				DetailsDataset dataset=new DetailsDataset();


				dataset.setMember_id(map.get("member_id").toString());
				dataset.setRent_id(map.get("old_rent_id").toString());
				dataset.setFirst_name(map.get("first_name").toString());
				dataset.setLast_name(map.get("last_name").toString());
				dataset.setEmail(map.get("email").toString());
				dataset.setHome_town(map.get("home_town").toString());
				dataset.setPhone_number(map.get("phone_no").toString());
				dataset.setSex(map.get("sex").toString());
				dataset.setMin_age(map.get("age").toString());
				dataset.setAvailibility_date(map.get("dob_date").toString());
				dataset.setAvailibility_month(map.get("dob_month").toString());
				dataset.setAvailibility_year(map.get("dob_year").toString());
				dataset.setCountry_name(map.get("spoken_language").toString());
				dataset.setUser_uploaded(map.get("avatar").toString());
				dataset.setAbout_yourself(map.get("password").toString());
				dataset.setHostel_id(map.get("old_hostel_id").toString());
				dataset.setRoom_type_id(map.get("old_stay_id").toString());
				dataset.setFlag(map.get("flag").toString());
				dataset.setLast_activity(map.get("last_login").toString());
				dataset.setStatus(map.get("status").toString());
				dataset.setFacility(map.get("activate").toString());
				dataset.setFood_type(map.get("registration_expire").toString());
				dataset.setPosted_on(map.get("posted_on").toString());
				dataset.setArea_sqft(map.get("registered_on").toString());
				dataset.setLocation_name(map.get("address").toString());

						datasets.add(dataset);


			}


		}
		catch (ClassCastException e) {
			Log.e("***ClassCastException getJSONArray()", e.getMessage());
			//e.printStackTrace();
		}
		catch (Exception e) {
			Log.e("***JSONException getJSONArray()", e.getMessage());
		}



		finally
		{
			if(jsonArray==null)
				jsonArray = new JSONArray();
		}

		return datasets;
	}

    private static boolean validateFileterData(DetailsDataset dataset) {
	    boolean status=false;
	    if(CommonUtils.filter_rent!=2000)
	    status=CommonUtils.filter_rent<= Integer.parseInt(dataset.getRent_per_month())? true:false;
        if(CommonUtils.filter_area!=500)
	    status=CommonUtils.filter_area<= Integer.parseInt(dataset.getArea_sqft())? true:status;
        if(CommonUtils.filter_age!=18)
        status=CommonUtils.filter_age<= Integer.parseInt(dataset.getMax_age())? true:status;
		if(CommonUtils.ac_status&&CommonUtils.non_acstatus)
			status=status;
        else if(CommonUtils.ac_status&&!CommonUtils.non_acstatus)
        status=dataset.getFacility().equalsIgnoreCase("A/C")?status:true;
        else if(CommonUtils.non_acstatus)
        status=dataset.getFacility().equalsIgnoreCase("Non A/C")?status:true;

        if(CommonUtils.male_status&&CommonUtils.female_status)
			status=status;
        else if(CommonUtils.male_status&&!CommonUtils.female_status)
            status=dataset.getSex().equalsIgnoreCase("Male")?status:true;
        if(CommonUtils.female_status)
            status=dataset.getSex().equalsIgnoreCase("Female")?status:true;

		if(CommonUtils.veg_status&&CommonUtils.non_veg_status)
			status=status;
        if(CommonUtils.veg_status&&!CommonUtils.non_veg_status)
            status=dataset.getFood_type().equalsIgnoreCase("Veg")?status:true;
        if(CommonUtils.non_veg_status)
            status=dataset.getFood_type().equalsIgnoreCase("Non Veg")?status:true;
        if(CommonUtils.furnished_status&&CommonUtils.semi_furnished_status&&CommonUtils.un_furnished_status)
        	status=status;
        else if(CommonUtils.furnished_status&&!CommonUtils.semi_furnished_status&&!CommonUtils.un_furnished_status)
            status=dataset.getFurnish_type().equalsIgnoreCase("Furnished")?status:true;
        if(!CommonUtils.furnished_status&&CommonUtils.semi_furnished_status&&!CommonUtils.un_furnished_status)
            status=dataset.getFurnish_type().equalsIgnoreCase("Semi Furnished")?status:true;
        if(!CommonUtils.furnished_status&&!CommonUtils.semi_furnished_status&&CommonUtils.un_furnished_status)
            status=dataset.getFurnish_type().equalsIgnoreCase("Un Furnished")?status:true;
		else if(CommonUtils.furnished_status&&CommonUtils.semi_furnished_status&&!CommonUtils.un_furnished_status)
			status=(dataset.getFurnish_type().equalsIgnoreCase("Furnished")||dataset.getFurnish_type().equalsIgnoreCase("Semi Furnished")) ?status:true;
		if(!CommonUtils.furnished_status&&CommonUtils.semi_furnished_status&&CommonUtils.un_furnished_status)
			status=dataset.getFurnish_type().equalsIgnoreCase("Semi Furnished")||dataset.getFurnish_type().equalsIgnoreCase("Un Furnished")?status:true;
		if(CommonUtils.furnished_status&&!CommonUtils.semi_furnished_status&&CommonUtils.un_furnished_status)
			status=dataset.getFurnish_type().equalsIgnoreCase("Furnished")||dataset.getFurnish_type().equalsIgnoreCase("Un Furnished")?status:true;

        return status;

    }

    /**
	 * To get Root element from the given JSON string
	 * @param jsonString is the JSON string from which the root element has to be retrieved
	 * @return returns root_element_name if no exception occurred else returns null
	 * @author Launchship_Ravichandra
	 */	
	@SuppressLint("LongLogTag")
	public static String getRootElementName(String jsonString)
	{
		     String root_element_name = null;
		   
		        try {
							 JSONObject jsonObj = new JSONObject(jsonString);
							 JSONArray jsonArrayObj=jsonObj.names();
							 
							 if(jsonArrayObj.length()>0)
							 root_element_name = (String)jsonArrayObj.get(0);
							 
							 
					} 
			        catch (ClassCastException e) {
						    Log.e("***ClassCastException getRootElementName()", e.getMessage());
						//e.printStackTrace();
					}
		           catch (JSONException e) {
						 Log.e("***JSONException getRootElementName()", e.getMessage());
					}
					catch (NullPointerException e) {
						Log.e("***NullPointerException getRootElementName()", ""+e.getMessage());
				  	}
				    catch (Exception e) {
		                Log.e("***Exception getRootElementName()", e.getMessage());
					//e.printStackTrace();
				    }
		             
				return root_element_name;	
	}
	
	
	

}
