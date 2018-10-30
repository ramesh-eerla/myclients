package com.roomtrac.mobile.Uicomponents;
/**
 * Created by Ramesh.eerla on 24/10/2018.
 */
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.widget.TextView;


public class FontUtil {

	private static final String TAG = "FontUtil";
	private static final String NAMESPACE_BASE = "http://schemas.android.com/apk/res/";
	private static final String ATTR_FONT = "font";
	private static final String ASSET_PATH_FONT = "fonts/";
	private Context context;

	public FontUtil(Context ctx) {
		this.context = ctx;
	}

/*
	 * Sets type-face as specified by the font file name, to the text view
	 * provided.
	 * 
	 * @param TextView
	 * @param String
	 *            fontFileName
	 */

	public void setFont(TextView tv, String  fontFileName) {
		Typeface typeFace = createTypeFace(fontFileName);
		if (typeFace != null) {
			tv.setTypeface(typeFace);
		}
	}
	
	public void setFont(TextView tv, AttributeSet attrs) {
		Typeface typeFace = createTypeFace(getFontFileName(attrs));
		if (typeFace != null) {
			tv.setTypeface(typeFace);
		}
	}

/*
	 * Create type-face object for the font file specified.
	 * 
	 * @param String
	 *            fontFileName
	 * @return
	 */

	private Typeface createTypeFace(String fontFileName) {
		Typeface typeFace = null;
		if (fontFileName != null && fontFileName.trim().length() > 0) {
			try {
				typeFace = Typeface.createFromAsset(context.getAssets(),"fonts/Roboto.ttf");
			} catch (Exception e) {
				Logger.w(TAG, "Unable to create TypeFace for '" + fontFileName
						+ "' - " + e.getMessage());
			}
		} else {
			Logger.w(TAG, "Unable to get font value");
		}
		return typeFace;
	}

/*
	 * Reads the font file name from the AttributeSet. Firstly, checks if the
	 * value is a resource reference and try reading the resource value. If it
	 * didn't work, assume the attribute value to be the font-file-name. If
	 * nothing works returns null.
	 * 
	 * @param AttributeSet
	 * @return String font-file-name, null if not available
	 */

	public String getFontFileName(AttributeSet attrs) {
		String fontFileName;
		String namespace = NAMESPACE_BASE + context.getPackageName();
		// Assuming font value as a reference to a string resource
		fontFileName = getFontFileName(attrs.getAttributeResourceValue(
				namespace, ATTR_FONT, 0));
		if (fontFileName == null) {
			// Value may be file name itself.
			fontFileName = attrs.getAttributeValue(namespace, ATTR_FONT);
			Logger.i(TAG,
					"Font value is not reference, reading value as string: " + fontFileName);
		}
		return fontFileName;
	}

/*
	 * Read font file name for the resource ID refered.
	 * 
	 * @param resId
	 * @return String or null
	 */

	private String getFontFileName(int resId) {
		String fontFileName = null;
		if (resId > 0) {
			try {
				// Read reference value
				fontFileName = context.getString(resId);
			} catch (Exception e) {
				Logger.w(
						TAG,
						"Invalid resource ID for font - "
								+ e.getMessage());
			}
		}
		return fontFileName;
	}

	public void getBackgroundColor(TextView _view, String color_code){

			String color_=null;
		    color_=color_code;
			GradientDrawable bgShape = (GradientDrawable)_view.getBackground();
			bgShape.setColor(Color.parseColor(color_));


	}


}
