package com.roomtrack.mobile.Uicomponents;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.text.Html;
import android.text.Spanned;
import android.util.DisplayMetrics;

import com.dcrworkforce.mobile.R;
import com.dcrworkforce.mobile.utils.Constants;

public class ApprovalFontCommons {

	/**
	 *   This class contains bean objects which may be used whenever required in the project.
	 *   The scope of this class is any where in the project.
	 */

	public static Typeface Roboto;

	public ApprovalFontCommons(Context context) {



	}
public static Typeface robotoFont(Context context){
	Roboto=Typeface.createFromAsset(context.getAssets(), "fonts/Roboto.ttf");
	return Roboto;
}
	public int textSizes(DisplayMetrics displayMetrics, boolean mPhone_Type, int screensize){
		int textsize=0;

		if (mPhone_Type) {
			switch (screensize) {
				case Configuration.SCREENLAYOUT_SIZE_LARGE:

					if (displayMetrics.widthPixels >= 720
							&& displayMetrics.heightPixels > 1200) {

						textsize=15;
					} else {

						textsize=15;
					}

					break;
				case Configuration.SCREENLAYOUT_SIZE_NORMAL:

					if ((displayMetrics.widthPixels == 1080)
							|| (displayMetrics.heightPixels > 1300)) {

						textsize=12;

					} else if ((displayMetrics.widthPixels == 720)) {

						textsize=12;
					} else if (displayMetrics.heightPixels >= 1000
							&& displayMetrics.heightPixels < 1300) {

						textsize=10;

					} else if (displayMetrics.heightPixels >= 900 && displayMetrics.heightPixels < 1000) {

						textsize=10;

					} else if (displayMetrics.heightPixels >= 800 && displayMetrics.heightPixels < 900) {

						textsize=10;

					} else if (displayMetrics.heightPixels > 700 && displayMetrics.heightPixels < 800) {

						textsize=10;
					} else {
						textsize=10;
					}

					break;
				case Configuration.SCREENLAYOUT_SIZE_SMALL:

					break;
				default:

			}
		} else {

			if (displayMetrics.widthPixels == 600) {
				textsize=15;
			} else if (displayMetrics.widthPixels >= 720
					&& displayMetrics.heightPixels > 1200) {

				textsize=18;
			} else {
				textsize=18;
			}
		}

		return textsize;
	}


	public String tableviewheightswidth(DisplayMetrics displayMetrics, boolean mPhone_Type, int screensize) {


		int width=0,height=0;
		if (mPhone_Type) {
			switch (screensize) {
				case Configuration.SCREENLAYOUT_SIZE_LARGE:

					if (displayMetrics.widthPixels == 720) {

						width = displayMetrics.widthPixels - 60;
						height = 50;

					} else {

						width = displayMetrics.widthPixels - 40;
						height = 50;
					}

					break;
				case Configuration.SCREENLAYOUT_SIZE_NORMAL:

					if ((displayMetrics.widthPixels == 720)
							|| (displayMetrics.heightPixels >= 1000 && displayMetrics.heightPixels < 1300)) {

						width = displayMetrics.widthPixels - 50;
						height = 60;

					} else if ((displayMetrics.widthPixels > 1000 && displayMetrics.widthPixels < 1200)) {
						width = displayMetrics.widthPixels - 70;
						height = 120;

					} else if (displayMetrics.heightPixels >= 800
							&& displayMetrics.heightPixels < 900) {

						width = displayMetrics.widthPixels - 40;
						height = 50;

					} else if (displayMetrics.heightPixels > 900
							&& displayMetrics.heightPixels < 1000) {

						width = displayMetrics.widthPixels - 40;
						height = 60;
					} else if (displayMetrics.heightPixels > 700
							&& displayMetrics.heightPixels < 800) {

						width = displayMetrics.widthPixels - 40;
						height = 35;

					} else {

						width = displayMetrics.widthPixels - 20;
						height = 30;
					}

					break;
				case Configuration.SCREENLAYOUT_SIZE_SMALL:

					break;
				default:

			}
		} else {

			if ((displayMetrics.widthPixels == 800)
					&& (displayMetrics.heightPixels == 1216)) {

				width = displayMetrics.widthPixels - 62;
				height = 50;

			} else if ((displayMetrics.widthPixels == 800)
					&& (displayMetrics.heightPixels == 1232)) {

				width = displayMetrics.widthPixels - 42;
				height = 70;

			} else if (displayMetrics.widthPixels == 1200) {

			} else if (displayMetrics.widthPixels == 1600) {

			} else {

				width = displayMetrics.widthPixels - 42;
				height = 50;
			}
		}


		return width+"~"+height;
	}


	public int listitem_width(DisplayMetrics displayMetrics, boolean mPhone_Type, int screensize){
		int width=0;
		switch (screensize) {
			case Configuration.SCREENLAYOUT_SIZE_LARGE:


				width = 380;


				break;
			case Configuration.SCREENLAYOUT_SIZE_NORMAL:

				if((displayMetrics.widthPixels == 1080) || (displayMetrics.heightPixels > 1300)){

					width = 900;


				}else if((displayMetrics.widthPixels == 720)){

					width = 650;

				}else if (displayMetrics.heightPixels >= 1000 && displayMetrics.heightPixels < 1300){
					width = 480;

				} else if(displayMetrics.heightPixels >= 900 && displayMetrics.heightPixels < 1000){

					width = 450;

				}else if(displayMetrics.heightPixels >= 800 && displayMetrics.heightPixels < 900){

					width = 380;

				}else if(displayMetrics.heightPixels > 700 && displayMetrics.heightPixels < 800){
					width = 330;
				}else{
					width = 260;
				}

				break;
			case Configuration.SCREENLAYOUT_SIZE_SMALL:

				break;
			default:

		}
		return width;
	}


	public static boolean requiredvalue(String data) {
		boolean status=false;
		if(data!=null&&data.equalsIgnoreCase("yes"))
			status=true;
		return status;
	}



	public  Spanned manditoryString(String data,Context context){


		Spanned sp=Html.fromHtml("<font color=#8e8c8c>"+data+" "+"</font><font color='#ff0101'>"+context.getString(R.string.astrick)+"</font>");

		return sp;

	}

	public  Spanned applicantquestions_manditoryString(String data,Context context){


		Spanned sp=Html.fromHtml("<font color=#8e8c8c>"+data+" "+"</font><font color='#8e8c8c'>"+ context.getString(R.string.astrick)+"</font>");

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
