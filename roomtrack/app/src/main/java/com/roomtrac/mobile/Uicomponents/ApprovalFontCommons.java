package com.roomtrac.mobile.Uicomponents;
/**
 * Created by Ramesh.eerla on 24/10/2018.
 */
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.text.Html;
import android.text.Spanned;
import android.util.DisplayMetrics;

import com.roomtrac.mobile.R;
import com.roomtrac.mobile.utils.Constants;


public class ApprovalFontCommons {

	/**
	 *   This class contains bean objects which may be used whenever required in the project.
	 *   The scope of this class is any where in the project.
	 */

	public static Typeface Roboto;

	public ApprovalFontCommons(Context context) {
	}



	public static boolean requiredvalue(String data) {
		boolean status=false;
		if(data!=null&&data.equalsIgnoreCase("yes"))
			status=true;
		return status;
	}



	public  static Spanned manditoryString(String data){


		Spanned sp=Html.fromHtml("<font color=#8e8c8c>"+data+" "+"</font><font color='#ff0101'>*</font>");

		return sp;

	}


	public static Spanned maditoryStringlable(String data) {
		Spanned sp=Html.fromHtml("<font color=#8e8c8c>"+data+"</font><font color='#8e8c8c'>&nbsp;&nbsp;*</font>");

		return sp;
	}

	public static Spanned requiredlable(String data,String required) {
		Spanned sp=null;
		if(required!=null&&required.equalsIgnoreCase("yes"))
			sp=Html.fromHtml("<font color=#8e8c8c>"+data+"</font><font color='#8e8c8c'>&nbsp;&nbsp;*</font>");
		else
			sp=Html.fromHtml("<font color=#8e8c8c>"+data+"</font>");


		return sp;
	}

	public static Spanned former_requiredlable(String data,String required) {
		Spanned sp=null;
		if(required!=null&&(required.equalsIgnoreCase("yes")||required.equalsIgnoreCase("true")))
			sp=Html.fromHtml("<font color=#8e8c8c>"+data+"</font><font color='#8e8c8c'>&nbsp;&nbsp;*</font>");
		else
			sp=Html.fromHtml("<font color=#8e8c8c>"+data+"</font>");


		return sp;
	}

	public static Spanned requiredlable(String data,boolean required) {
		Spanned sp=null;
		if(required)
			sp=Html.fromHtml("<font color=#8e8c8c>"+data+"</font><font color='#8e8c8c'>&nbsp;&nbsp;*</font>");
		else
			sp=Html.fromHtml("<font color=#8e8c8c>"+data+"</font>");


		return sp;
	}
	public static Spanned nonmaditoryStringlable(String data) {
		Spanned sp=Html.fromHtml("<font color=#8e8c8c>"+data+"</font>");

		return sp;
	}
	//*this method is used for the color code #0e679a
	public static String blueColorText(String data,String data2,String type){
		String sp=null;
		switch (type){
			case Constants.SECOND_ITEM_UNDERLINE:
				sp="<font color =#9b9b9b>" + data + "&nbsp;&nbsp;</font><font color=#0e679a><u>" + data2 + "</u></font>";
				break;
			case Constants.SECOND_ITEM_JOBDESC:
				sp="<font color =#1e1e1e>" + data + "</font><font color=#0e679a><u>" + data2 + "</u></font>";
				break;
			case Constants.FIRST_ITEM:
				sp="<font color =#0288d1>" + data + "</font><br><font color =#9b9b9b>" + data2 + "</font>";
				break;
			case Constants.SINGLE_ITEM_UNDERLINE:
				sp="<font color =#0e679a><u>" + data + "</u></font>";
				break;
			default:
				sp="<font color=#0e679a>" + data + " </font>";
				break;
		}

		return sp;

	}
	//*this method is used for the color code #8E8C8C
	public static String grayColorText(String data,String data2,String data3,String type){
		String sp=null;
		switch (type){
			case Constants.FIRST_ITEM:
				sp="<font color=#8e8c8c>" + data + "  </font><font color =#1e1e1e>&nbsp;&nbsp;" +data2 + "</u></font>";
				break;
			case Constants.TWO_ITEMS:
				sp="<font color=#8e8c8c>" +data + "  </font><font color =#1e1e1e>&nbsp;&nbsp;" +data2 + "</u></font><br/><font color=#8e8c8c>" + data3 + " </font>";
				break;
			default:
				sp="<font color=#8e8c8c>" + data + " </font>";
				break;
		}

		return sp;

	}
	//*this method is used for the color code #1E1E1E
	public static String blackColorText(String data){
		String sp="<font color=#1e1e1e>" + data + " </font>";
		return sp;
	}

	//*this method is used for the color code #F24E4E
	public static String redColorText(String data,String data2,String type){
		String sp=null;
		switch (type){
			case Constants.SECOND_ITEM:
				sp="<font color =#9b9b9b>" + data + "&nbsp;&nbsp;</font><font color=#f24e4e>" + data2 + "</font>";
				break;
			case Constants.FIRST_ITEM:
				sp="<font color=#f24e4e>" + data + "</font><font color=#1e1e1e> (" + data2 + ")</font>";
				break;
			default:
				sp="<font color=#f24e4e>" + data + "</font>";
				break;
		}

		return sp;

	}
	//*this method is used for the color code #9b9b9b
	public static String lightGrayColorText(String data,String data2,String type){
		String sp=null;
		switch (type){
			case Constants.FIRST_ITEM:
				if(data.isEmpty())
					sp=	"<font color=#1e1e1e>" + data2 + "</font>";
				else
					sp="<font color =#9b9b9b>" + data + "&nbsp;&nbsp;</font><font color=#1e1e1e>" + data2 + "</font>";
				break;
			case Constants.EXPENSE_ITEM:
				sp="<font color =#9b9b9b>" + data + "&nbsp;&nbsp;</font><font color=#1e1e1e>" + data2 + "</font>";
				break;
			default:
				sp="<font color =#9b9b9b>" + data + "</font>";
				break;
		}

		return sp;

	}
}
