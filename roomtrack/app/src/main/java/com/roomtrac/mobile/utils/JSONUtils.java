package com.roomtrac.mobile.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

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
     * @param key is the value which is used to get JSONArray from a JSON string
     * @return returns JSONArray if no exception occurred else returns null
     * @author Launchship_Ravichandra 
     */
	@SuppressLint("LongLogTag")
	public static JSONArray getJSONArray(String JSON_str, String key)
	{
	//	Log.e("getJSONArray()","getJSONArray()");
		 
		JSONArray jsonArray=null;
		
		try {
				JSONObject json=new JSONObject(JSON_str);
				Log.d("jobj", ""+json);
				Log.d("key", ""+key);
				jsonArray=(JSONArray)json.get(key);
				Log.d("jarray", ""+jsonArray);
		       /* for(int i=0;i<jsonArray.length();i++)
                {
		        	JSONObject jsonObj=(JSONObject)jsonArray.get(i);
		        	//Log.e("Name", jsonObj.get("Name").toString());
                }*/
			
		} 
		catch (ClassCastException e) {
			    Log.e("***ClassCastException getJSONArray()", e.getMessage());
			//e.printStackTrace();
		}
		catch (JSONException e) {
			Log.e("***JSONException getJSONArray()", e.getMessage());
	    }
		catch (NullPointerException e) {
				Log.e("***NullPointerException getJSONArray()", "" + e.getMessage());
			}
		catch (Exception e) {
                Log.e("***Exception getJSONArray()", e.getMessage());
			//e.printStackTrace();
		}
	
 
		finally
		{
			if(jsonArray==null)
			   jsonArray = new JSONArray();
		}
		
       return jsonArray;
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
