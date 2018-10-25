package com.roomtrack.mobile.controller;
/**
 * Created by Ramesh.eerla on 24/10/2018.
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateValidater {


	 
	  private Pattern pattern;
	  private Matcher matcher;
	 
	  private static final String DATE_PATTERN = 
	          "(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/((19|20)\\d\\d)";
	  String EMAIL_REGEX = "^(['_a-zA-Z0-9-]+)(\\.['_a-zA-Z0-9-]+)*@([a-zA-Z0-9-]+)(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{2,5})$";
	 
	  public DateValidater(){
		  pattern = Pattern.compile(DATE_PATTERN);
	  }
	 
	  /**
	   * Validate date format with regular expression
	   * @param date date address for validation
	   * @return true valid date fromat, false invalid date format
	   */
	   public boolean validate(final String date){
	 
	     matcher = pattern.matcher(date);
	 
	     if(matcher.matches()){
	 
		 matcher.reset();
	 
		 if(matcher.find()){
	 
	             String day = matcher.group(1);
		     String month = matcher.group(2);
		     int year = Integer.parseInt(matcher.group(3));
	 
		     if (day.equals("31") && 
			  (month.equals("4") || month .equals("6") || month.equals("9") ||
	                  month.equals("11") || month.equals("04") || month .equals("06") ||
	                  month.equals("09"))) {
				return false; // only 1,3,5,7,8,10,12 has 31 days
		     } else if (month.equals("2") || month.equals("02")) {
	                  //leap year
			  if(year % 4==0){
				  if(day.equals("30") || day.equals("31")){
					  return false;
				  }else{
					  return true;
				  }
			  }else{
			         if(day.equals("29")||day.equals("30")||day.equals("31")){
					  return false;
			         }else{
					  return true;
				  }
			  }
		      }else{				 
			return true;				 
		      }
		   }else{
	    	      return false;
		   }		  
	     }else{
		  return false;
	     }			    
	   }
	   
	   
	   public boolean isValidEmail(String email){
		      return email.matches(EMAIL_REGEX);
	   }

	public boolean checkWithCurrentDate(String date){
		boolean featuredate=false;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			String currentdate=sdf.format(new Date());
			Date date1 =sdf.parse(currentdate);
			Date date2 = sdf.parse(date);



			if (date1.compareTo(date2) > 0) {
				featuredate=false;
			} else if (date1.compareTo(date2) < 0) {
				featuredate=true;
			} else if (date1.compareTo(date2) == 0) {
				featuredate=false;
			} else {
				featuredate=false;
			}
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return featuredate;
	}

	

}
