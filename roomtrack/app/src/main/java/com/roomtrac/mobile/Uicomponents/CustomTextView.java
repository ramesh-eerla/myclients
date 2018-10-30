package com.roomtrac.mobile.Uicomponents;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;




/**
  * <p>
 * This View is the base to inherit custom CustomTextView. Contains common
 * methods across the application.
 * </p>
 * 
 * @author krachama
 */
/**
 * Created by Ramesh.eerla on 24/10/2018.
 */
public class CustomTextView extends android.support.v7.widget.AppCompatTextView implements Justify.Justified{
	
    private final String TAG = "CustomTextView";
    private final String ellipsizeTxt = "...";
    
	private Context mCntxt;
	private FontUtil mFontUtil;

	private SearchLiteralStatus	searchLiteralStatus = null;
	
	private int computedWidth = 0;
	
    public CustomTextView(Context ctxt) {
		super(ctxt);
		init(ctxt);
	}

	public CustomTextView(Context ctxt, AttributeSet attrs) {
		super(ctxt, attrs);
		init(ctxt);
		Typeface typeFace = Typeface.createFromAsset(ctxt.getAssets(), "");
		this.setTypeface(typeFace);
		//mFontUtil.setFont(this, mFontUtil.getFontFileName(attrs));
	}

	/**
	 * Initializing the Context and Font
	 * 
	 * @param context
	 *            Activity Context
	 */
	private void init(Context context) {
		mCntxt = context;
		Typeface typeFace = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto.ttf");
		this.setTypeface(typeFace);
		//mFontUtil = new FontUtil(mCntxt);
	}

	/**
	 * Method to set the custom font
	 * 
	 * @param fontFileName
	 *            name of the font file
	 */
	public void setFont(String fontFileName) {

		mFontUtil.setFont(this, fontFileName);
	}

	public void setFont(AttributeSet attrs) {
		mFontUtil.setFont(this, attrs);
	}

	private void setSampledWidth( int width){
		computedWidth = width;
	}

	private int getSampledWidth(){
		return computedWidth;
	}	
	/**
	 * Method to display the text using the set of rules to truncate
	 * 
	 * @param text
	 *            text to be displayed
	 */
	public void setBreadCrumbText(CharSequence text , int width) {
		setBreadCrumbText(text, null , width);
	}
	
	/**
	 * Method to display the text using the set of rules to truncate
	 * 
	 * @param text
	 *            text to be displayed
	 * @param searchLiteral
	 *            search literal to give priority while truncating
	 */
	public void setBreadCrumbText(CharSequence text, String searchLiteralAsis , int width) {
		String searchLiteral = null;
		if (searchLiteralAsis != null) {
			searchLiteral = searchLiteralAsis.trim();
		}
		setSampledWidth(width);
		resetSearchLiteralStatus(text , searchLiteral);

		//When there is no search literal (only truncation with out selection)
		if (searchLiteral == null) {
			String breadCrumbText = truncateTo4OnAllLevels(text.toString(),
					null);
			// To remove dots when more than 3 (........ => ...)
			breadCrumbText = breadCrumbText.replaceAll("\\.{4,}", ellipsizeTxt);
            setText(addSpacesAroundBC(breadCrumbText));
        } else {
        	// When there is search literal (with selection)
			searchLiteral = searchLiteral.toLowerCase(Locale.US);
			String breadCrumbText = truncateTo4OnAllLevels(text.toString(),
					searchLiteral);

			//To remove dots when more than 3 (........ => ...)
			breadCrumbText = breadCrumbText.replaceAll("\\.{4,}", ellipsizeTxt);
            setBackgroundForMatchedWords(breadCrumbText, searchLiteral);
        }

	}

	private void resetSearchLiteralStatus(CharSequence text, String searchLiteralAsis){
		searchLiteralStatus = new SearchLiteralStatus(text , searchLiteralAsis);
	}
	/**
	 * Start with leftmost and truncate to 4 chars with ellipsis. Skip the
	 * search string in each level
	 * 
	 * @param textToTruncate
	 *            text to be truncated
	 * @param searchString
	 *            search literal for priority while truncating
	 * @return truncated string
	 */
	private String truncateTo4OnAllLevels(String textToTruncate,
			String searchString) {
		
		String[] levels = textToTruncate.split(":");
		int levelsLength = levels.length;
		
		//Check if the given text is sufficient in the screen without any truncation
		if (willBCFitWidth(textToTruncate)) {
			setTextTruncated(false);
			return textToTruncate;
		}		
		setTextTruncated(true);
		
		//Loop for hierarchy levels to truncate if length is more than 4 characters from Left to Right
		for (int levelCnt = 0; levelCnt < levelsLength; levelCnt++) {
			levels[levelCnt] = levels[levelCnt].trim();
			//Truncate if doesn't contain search literal and if length is more than 4
			if (!(searchString != null && searchLiteralStatus.isSearchLiteralPresent(levelCnt))) {
				
				if (levels[levelCnt].length() > 4) {
					//truncate to 4 chars and add ellipsize
					levels[levelCnt] = trimIfSpaceInBetween(levels[levelCnt].substring(0, 4))
							+ ellipsizeTxt;
					textToTruncate = appendLevelsToBC(levels);
					if (!BuildType.PROD) {
						//Logger.d(TAG, "4 chars truncatedBC: " + textToTruncate);
					}
					//check if trucated text is sufficient
					if (willBCFitWidth(textToTruncate)) {
						return textToTruncate;
					}
				}
			}
		}
		return truncateTo3OnAllLevels(textToTruncate, searchString);
	}

	/**
	 * Start with leftmost and truncate to 3 chars with ellipsis. Skip the
	 * search string in each level
	 * 
	 * @param textToTruncate
	 *            text to be truncated
	 * @param searchString
	 *            search literal for priority while truncating
	 * @return truncated string
	 */
	private String truncateTo3OnAllLevels(String textToTruncate,
			String searchString) {
		String[] levels = textToTruncate.split(":");
		int levelsLength = levels.length;
		
		//Truncate if doesn't contain search literal and if length is more than 3
		for (int levelCnt = 0; levelCnt < levelsLength; levelCnt++) {
			
			//if doesn't contain search literal and level length is more than 3
			if ((!(searchString != null && searchLiteralStatus.isSearchLiteralPresent(levelCnt)))
					&& trimEllipse(levels[levelCnt]).length() > 3) {
				
				//truncate to 3 chars and add ellipsize
				levels[levelCnt] = trimIfSpaceInBetween(levels[levelCnt].substring(0, 3)) + ellipsizeTxt;
				textToTruncate = appendLevelsToBC(levels);

				if (!BuildType.PROD) {
					//Logger.d(TAG, "3 chars truncatedBC: " + textToTruncate);
				}
				if (willBCFitWidth(textToTruncate)) {
					return textToTruncate;
				}
			}
		}
		return truncateTo2OnAllLevels(textToTruncate, searchString);
	}

	/**
	 * Start with leftmost and truncate to 2 chars with ellipsis. Skip the
	 * search string in each level
	 * 
	 * @param textToTruncate
	 *            text to be truncated
	 * @param searchString
	 *            search literal for priority while truncating
	 * @return truncated string
	 */
	private String truncateTo2OnAllLevels(String truncatedBC,
			String searchString) {
		String[] levels = truncatedBC.split(":");
		int levelsLength = levels.length;
		
		//Truncate if doesn't contain search literal and if length is more than 2
		for (int levelCnt = 0; levelCnt < levelsLength; levelCnt++) {
			
			//if doesn't contain search literal and level length is more than 2
			if ((!(searchString != null && searchLiteralStatus.isSearchLiteralPresent(levelCnt)))
					&& trimEllipse(levels[levelCnt]).length() > 2) {
				
				//truncate to 2 chars and add ellipsize
				levels[levelCnt] = trimIfSpaceInBetween(levels[levelCnt].substring(0, 2)) + ellipsizeTxt;
				truncatedBC = appendLevelsToBC(levels);
				if (!BuildType.PROD) {
					//Logger.d(TAG, "2 chars truncatedBC: " + truncatedBC);
				}
				if (willBCFitWidth(truncatedBC)) {
					return truncatedBC;
				}
			}
		}
		return truncateTo1OnAllLevels(truncatedBC, searchString);
	}

	/**
	 * Start with leftmost and truncate to 1 chars with ellipsis. Skip the
	 * search string in each level
	 * 
	 * @param textToTruncate
	 *            text to be truncated
	 * @param searchString
	 *            search literal for priority while truncating
	 * @return truncated string
	 */
	private String truncateTo1OnAllLevels(String truncatedBC,
			String searchString) {
		String[] levels = truncatedBC.split(":");
		int levelsLength = levels.length;
		
		//Truncate if doesn't contain search literal and if length is more than 1
		for (int levelCnt = 0; levelCnt < levelsLength; levelCnt++) {
			
			//if doesn't contain search literal and level length is more than 1
			if ((!(searchString != null && searchLiteralStatus.isSearchLiteralPresent(levelCnt)))
					&& trimEllipse(levels[levelCnt]).length() > 1) {
				
				// Truncate to 1 chars and add ellipsize
				levels[levelCnt] = levels[levelCnt].substring(0, 1) + ellipsizeTxt;
				truncatedBC = appendLevelsToBC(levels);
				if (!BuildType.PROD) {
					//Logger.d(TAG, "1 chars truncatedBC: " + truncatedBC);
				}
				if (willBCFitWidth(truncatedBC)) {
					return truncatedBC;
				}
			}
		}
		return truncateTo0OnAllLevels(truncatedBC, searchString);
	}

	/**
	 * Start with leftmost and truncate to only ellipsis. Skip the search string
	 * in each level
	 * 
	 * All literals with out search literal will be truncated to ellipsize
	 * 
	 * @param textToTruncate
	 *            text to be truncated
	 * @param searchString
	 *            search literal for priority while truncating
	 * @return truncated string
	 */
	private String truncateTo0OnAllLevels(String truncatedBC,
			String searchString) {
		String[] levels = truncatedBC.split(":");
		int levelsLength = levels.length;
		
		// Truncate if doesn't contain search literal and if length is more than 0 (complete truncated case (...) )
		for (int levelCnt = 0; levelCnt < levelsLength; levelCnt++) {
			
			// if doesn't contain search literal and level length is more than 0
			if ((!(searchString != null && searchLiteralStatus.isSearchLiteralPresent(levelCnt)))
					&& (trimEllipse(levels[levelCnt]).length() > 0)) {
				
				// Truncate to ellipsize
				levels[levelCnt] = ellipsizeTxt;
				truncatedBC = appendLevelsToBC(levels);
				if (!BuildType.PROD) {
					//Logger.d(TAG, "0 chars truncatedBC: " + truncatedBC);
				}
				if (willBCFitWidth(truncatedBC)) {
					return truncatedBC;
				}
			}
		}
		return truncateAroundSearchStringAllLevels(truncatedBC,
				searchString);
	}
	
	private int findEndIndex(boolean isNumeric , int index, String levelText , String searchText){
		int lastIndex = index;
		
		if(!isNumeric){
			if(searchText.length() < 4){
				lastIndex = ( (index + 4) <= levelText.length() ) 
										? (index + 4) : levelText.length();
			}else{
				lastIndex = ( (index + searchText.length()) <= levelText.length() ) 
						? (index + searchText.length()) : levelText.length();
			}
		}else{
			lastIndex = ( (index + searchText.length()) <= levelText.length() ) 
					? (index + searchText.length()) : levelText.length();
		}		
		return lastIndex;
	}
	
	/**
	 * In a given level if a search string is present truncate all the text around it
	 * before we start truncating the search string it self
	 * 
	 * @param levelText
	 *            text / tag of a single level in hierarchy
	 * @param searchString
	 *            search literal for priority while truncating
	 * @return truncated string
	 */
	private String truncateAroundSearchStringInALevel(String levelText ,
							String searchLiteral , int level){
		StringBuilder tc = new StringBuilder();
		boolean isNumeric = (StringUtils.isValidAndContainsSearchReqd(searchLiteral) == TagSearchConstraint.TRUE);
		int searchLiteralCount = 0;
		int lastIndex = 0;
		if (!BuildType.PROD) {
			Logger.d(TAG, "truncateAroundSearchStringInALevel: "
					+ levelText);
		}
		
		do{
			int index = levelText.toLowerCase(Locale.US).indexOf(
										searchLiteral , lastIndex);
			if(index >= 1){
				if(!isNumeric){
					if(levelText.substring(index - 1, index).equals(" ") ){

						lastIndex = findEndIndex(isNumeric , index , levelText , searchLiteral);
						tc.append(ellipsizeTxt + 
								trimIfSpaceInBetweenForLessThan4(levelText.substring(index, lastIndex)));
						if( (lastIndex < levelText.length()) || 
										(isSpaceInBetweenForLessThan4(levelText.substring(index, lastIndex)))){
							tc.append(ellipsizeTxt);
						}
						searchLiteralCount++;
					}else{
						lastIndex = index + searchLiteral.length();
					}
				}else{

					lastIndex = findEndIndex(isNumeric , index , levelText , searchLiteral);
					tc.append(ellipsizeTxt + 
							trimIfSpaceInBetweenForLessThan4(levelText.substring(index, lastIndex)));
					if( (lastIndex < levelText.length()) || 
							(isSpaceInBetweenForLessThan4(levelText.substring(index, lastIndex)))){
						tc.append(ellipsizeTxt);
					}
					searchLiteralCount++;
				}
			}else if(index == 0){

				lastIndex = findEndIndex(isNumeric , index , levelText , searchLiteral);							
				tc.append(trimIfSpaceInBetweenForLessThan4(levelText.substring(index, lastIndex)));
				if( (lastIndex < levelText.length()) || 
						(isSpaceInBetweenForLessThan4(levelText.substring(index, lastIndex)))){
					tc.append(ellipsizeTxt);
				}
				searchLiteralCount++;
			}else{
				lastIndex = -1;
			}
		}while(lastIndex != -1);
		
	/*	 if(levelText.endsWith(searchLiteral) && tc.length() > 4
					&& tc.toString().endsWith(searchLiteral + ellipsizeTxt)){
			 tc.delete(tc.length() - ellipsizeTxt.length(), tc.length());
		 }	*/
		 searchLiteralStatus.setLevelSearchLiteralCount(level, searchLiteralCount);
		 searchLiteralStatus.setSearchLiteralTruncated(true);
		return tc.toString().replaceAll(
				"\\.{4,}", ellipsizeTxt);
	}
	private String truncateAroundSearchStringAllLevels(String truncatedBC,
			String searchString) {

		String[] levels = truncatedBC.split(":");
		int levelsLength = levels.length;
		
		// Truncate if contain search literal around the literal
		for (int levelCnt = 0; levelCnt < levelsLength; levelCnt++) {
			if ( (searchString != null) && (searchLiteralStatus.isSearchLiteralPresent(levelCnt)) ) {
				//Truncate around the search literal in all Levels
				levels[levelCnt] = truncateAroundSearchStringInALevel(levels[levelCnt] , searchString , levelCnt);
				
				truncatedBC = appendLevelsToBC(levels);
				if (!BuildType.PROD) {
					Logger.d(TAG, "truncateAroundSearchStringAllLevels: "
							+ truncatedBC);
				}
				if (willBCFitWidth(truncatedBC)) {
					return truncatedBC;
				}
			}
		}

		if (willBCFitWidth(truncatedBC)) {
			return truncatedBC;
		} else if(null != searchString) {
			return truncateToNOnAllLevelsSearchString(truncatedBC, searchString , 4);
		}	
		return truncatedBC;
	}

	private String  truncatetoNInAGivenLevel(String levelText ,
			String searchLiteralOriginal , int truncLength){
		StringBuffer  levelBuffer = new StringBuffer();
		String searchLiteral =  searchLiteralOriginal;
		if(searchLiteralOriginal.length() > truncLength){
			searchLiteral = searchLiteralOriginal.substring(0, truncLength);
		}
		
		if(truncLength == 0){
			return ellipsizeTxt;
		}
		int lastIndex = 0;

		do{
			int index = levelText.toLowerCase(Locale.US).indexOf(
										searchLiteral , lastIndex);
			int end = ((index + truncLength) > levelText.length()) 
									? levelText.length() : (index + truncLength);
			if(index >= 1){
				if(levelText.substring(index - 1, index).equals(".") ){
					levelBuffer.append(ellipsizeTxt);
					levelBuffer.append(trimIfSpaceInBetweenForLessThan4(levelText.substring(index, end)));
					if(end != levelText.length()){
						levelBuffer.append(ellipsizeTxt);
					}
				}
				lastIndex = end;
			}else if(index == 0){
				levelBuffer.append(trimIfSpaceInBetweenForLessThan4(levelText.substring(index, end)));
				if(end != levelText.length()){
					levelBuffer.append(ellipsizeTxt);
				}
				lastIndex = end;
			}else{
				lastIndex = -1;
			}			
		}while(lastIndex != -1);
		
		return levelBuffer.toString().replaceAll(
				"\\.{4,}", ellipsizeTxt);
	}
	private String truncateToNOnAllLevelsSearchString(String truncatedBC,
			String searchString , int truncLength) {

		if(truncLength < 0)
			return truncatedBC;
		
		String[] levels = truncatedBC.split(":");
		int levelsLength = levels.length;
		if (!BuildType.PROD) {
			Logger.d(TAG, "truncateToNOnAllLevelsSearchString: " + " ** " + truncLength + " ** "
					+ truncatedBC);
		}
		
		// Truncate if contain search literal and if length is more than truncLength
		for (int levelCnt = 0; levelCnt < levelsLength; levelCnt++) {
			if ((searchString != null) && searchLiteralStatus.isSearchLiteralPresent(levelCnt)){
				
					//In each level make the search string to truncWidth
					levels[levelCnt] = truncatetoNInAGivenLevel(levels[levelCnt], 
											searchString , truncLength);
					
					truncatedBC = appendLevelsToBC(levels);
					
					if (willBCFitWidth(truncatedBC)) {
						return truncatedBC;
					}
			}
		}

		if (!willBCFitWidth(truncatedBC) && searchString != null) {
			return truncateToNOnAllLevelsSearchString(truncatedBC, searchString , truncLength - 1);
		} else {
			return truncatedBC;
		}
	}

	/**
	 * Method to check if the truncated string is sufficient in the available
	 * width
	 * 
	 * @param textToTruncate
	 *            text to be truncated
	 * @param text
	 *            view to calculate width
	 * @return boolean to decide if sufficient
	 */
	private boolean willBCFitWidth(String textToTruncate) {
		 if (!BuildType.PROD) {
			 searchLiteralStatus.rulesList.add(textToTruncate);
		 }
		float textWidth = getPaint().measureText(addSpacesAroundBC(textToTruncate));
		
		int measuredWidth = getSampledWidth();
		/*		if(measuredWidth == 0){
		measuredWidth =  getSampledWidth();
		if (!BuildType.PROD) {
			Logger.d(TAG, "***  Sampled Width  ***" + measuredWidth);
		}
		}*/
		if (!BuildType.PROD) {
			Logger.d(TAG, "textToTruncate*** " + textToTruncate + "** textWidth ** "
					+ textWidth+" ** Available Width ** "+ measuredWidth);
		}
		return !(textWidth >= measuredWidth);
	}

	/**
	 * Method to join the array of strings with a : literal
	 * 
	 * @param levels
	 *            array of strings
	 * @return string joined strings
	 */
	private String appendLevelsToBC(String[] levels) {
		return TextUtils.join(":", levels);
	}
	
	private String addSpacesAroundBC(String bc) {
		return bc.replaceAll(":", " : ");
	}	
	/**
	 * Method to select the search key work in the truncated text
	 * @param completeText truncated text
	 * @param searchLiteral search literal to be highlighted
	 */
	protected void setBackgroundForMatchedWords(String completeText,
			String searchLiteral) {
		
		if (!BuildType.PROD) {
			Logger.d(TAG, "setBackgroundForMatchedWords*** " + completeText + " *** searchLiteral "
					+ searchLiteral);
		}		
		completeText = addSpacesAroundBC(completeText);
		final Spannable wordToSpan = new SpannableString(completeText);
		if (searchLiteralStatus.isSearchLiteralTruncated()) {
			seachLiteralTruncated(completeText , searchLiteral , wordToSpan);
		} else {
			
			if(StringUtils.isValidAndContainsSearchReqd(searchLiteral) == TagSearchConstraint.FALSE){
				highlightStartsWithInBC(completeText, searchLiteral, wordToSpan);
			} else if(StringUtils.isValidAndContainsSearchReqd(searchLiteral) == TagSearchConstraint.TRUE){
				highLightContains(completeText, searchLiteral, wordToSpan);
			}else{
				if (!BuildType.PROD) {
					Logger.d(TAG, "setBackgroundForMatchedWords: Not truncatd, not TRUE, not False");
				}
			}
		}
		setText(wordToSpan);
	}
	private void highLightContains(String completeText,
			String searchLiteral , Spannable wordToSpan){
		int length = searchLiteral.length();

		int index = completeText.toLowerCase(Locale.US).indexOf(
				searchLiteral);
		while (index != -1) {
			if (index + length <= completeText.length()) {
				int spanStart = index;
				int spanEnd = index + length;
				if (spanStart < spanEnd
						&& spanEnd <= completeText.length()) {
					setBackgroundSpan(wordToSpan, spanStart , spanEnd);
				} else {
					if (!BuildType.PROD) {
						Logger.d(TAG,
								"highLightContains Error Occured while setSpan startSpan:::"
										+ spanStart + ":endSpan::"
										+ spanEnd + ":Total Length::"
										+ completeText.length());
					}
				}
				if (!BuildType.PROD) {
					Logger.d(TAG, completeText + ":non alpha start:"
							+ index + ":end:" + (index + length));
				}
			}
			index = completeText.toLowerCase(Locale.US).indexOf(
					searchLiteral, index + 1);
		}	
	}

	private void highlightStartsWithInBC(String completeText,
			String searchLiteral , Spannable wordToSpan){
		int lastIndex = 0;
		do{
			int index = completeText.toLowerCase(Locale.US).indexOf(
										searchLiteral , lastIndex);
			if(index >= 1){
				lastIndex = index + searchLiteral.length();
				if( (completeText.substring(index - 1, index).equals(":")) || 
							(completeText.substring(index - 1, index).equals(" ")) ){
					setBackgroundSpan(wordToSpan , index , index + searchLiteral.length());
				}
			}else if(index == 0){
				lastIndex = index + searchLiteral.length();
				setBackgroundSpan(wordToSpan , index , index + searchLiteral.length()); 
			}else{
				lastIndex = -1;
			}
		}while(lastIndex != -1);
	}
	
	private void seachLiteralTruncated(String completeText,
			String searchLiteral , Spannable wordToSpan){

		int startIndex = 0;
		String levels[] = completeText.split(" : ");
		for (int i = 0; i < levels.length; i++) {
							
			// Complete search literal truncated case 
			if((searchLiteralStatus.isSearchLiteralPresent(i)) && (levels[i].equals(ellipsizeTxt))) {
				setBackgroundSpan(wordToSpan , startIndex , startIndex
						+ ellipsizeTxt.length());
			}
			startIndex += levels[i].length() + 3; // adding 3 for space colon space
		}
		searchLiteralTruncHighLight(completeText, searchLiteral, wordToSpan);
	}

	private void searchLiteralTruncHighLight(String completeText,
			String searchLiteral , Spannable wordToSpan){
		boolean isNumeric = (StringUtils.isValidAndContainsSearchReqd(searchLiteral) == TagSearchConstraint.TRUE);

		String searchLiteralSubString = searchLiteral;
		int lastIndex = 0;
		boolean isSearchLtrltruncted = false;
		do{
			int index;
			if(isSearchLtrltruncted){
				index = completeText.toLowerCase(Locale.US).indexOf(
						searchLiteralSubString + ellipsizeTxt , lastIndex);
			} else {
				index = completeText.toLowerCase(Locale.US).indexOf(
						searchLiteralSubString , lastIndex);
			}
			
			if(index > 1){
				lastIndex = index + searchLiteralSubString.length();
				if(isNumeric){
					setBackgroundSpan(wordToSpan , index , index + searchLiteralSubString.length());
				}else{
					if( ((completeText.substring(index - 1, index).equals(":")) || 
							(completeText.substring(index - 1, index).equals(" ")) || 
								(completeText.substring(index - 1, index).equals("."))) ){
						setBackgroundSpan(wordToSpan , index , index + searchLiteralSubString.length());
					}
				}
			}else if(index == 0){
				lastIndex = index + searchLiteralSubString.length();
				setBackgroundSpan(wordToSpan , index , index + searchLiteralSubString.length()); 
			}else{
				if(searchLiteralSubString.length() > 4){
					searchLiteralSubString = trimIfSpaceInBetweenForLessThan4(searchLiteralSubString.substring(0, 4));
				}else{
					searchLiteralSubString = trimIfSpaceInBetweenForLessThan4(searchLiteralSubString.substring(0, searchLiteralSubString.length() - 1));
				}
				isSearchLtrltruncted = true;
				lastIndex = 0;
			}			
		}while(searchLiteralSubString.length() > 0);
	}
	private void setBackgroundSpan(Spannable wordToSpan , int start , int end){
		/*BackgroundColorSpan span = new BackgroundColorSpan(
				getResources().getColor(R.color.blue11));

		wordToSpan.setSpan(span, start, end,
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);*/
	}
	
	/**
	 * Method remove all ... in the complete string
	 * @param word
	 * @return
	 */
	private String trimEllipse(String word) {
		return word.replaceAll("\\.{0,}", "");
	}

	private String trimIfSpaceInBetween(String word){
		String[] strTrimmed = word.split(" ");
		return strTrimmed[0];
	}
	
	private String trimIfSpaceInBetweenForLessThan4(String word){
		if(word.length() <= 4){
			String[] strTrimmed = word.split(" ");
			return strTrimmed[0];
		}
		return word;
	}
	private boolean isSpaceInBetweenForLessThan4(String word){
		if((word.length() <= 4) && (word.contains(" "))){
			return true;
		}
		return false;
	}	
	private boolean hasSearchLiteral(String completeWord, String searchLiteral) {
		completeWord = completeWord.toLowerCase(Locale.US);
		boolean hasSearchLiteral = completeWord.contains(searchLiteral);
		
		if (StringUtils.isValidAndContainsSearchReqd(searchLiteral) == TagSearchConstraint.FALSE) {
			int lastIndex = 0;
			do{
				int index = completeWord.indexOf(searchLiteral , lastIndex);
				if(index >= 1){
					lastIndex = index + searchLiteral.length();
					if(completeWord.substring(index - 1, index).equals(" ") ){
						return hasSearchLiteral && true;
					}
				}else if(index == 0){	
					return hasSearchLiteral && true;
				}else if(index == -1){
					lastIndex = -1;
				}
			}while(lastIndex != -1);
		} else {
			if (hasSearchLiteral) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Method to check if the text is Ellipsized
	 * @return boolean
	 */
    public boolean isTextEllipsized() {
        return getLayout().getEllipsisCount(0) > 0;
    }
    
    /**
     * Method to check if the text is truncated
     * @return boolean
     */
    public boolean isTextTruncated() {
        return (null != searchLiteralStatus && searchLiteralStatus
                .isTextTruncated());
    }
    
    /**
     * Method to update the necessity of truncation
     * @param isTextTruncated
     */
    public void setTextTruncated(boolean isTextTruncated) {
        searchLiteralStatus.setTextTruncated( isTextTruncated);
    }

	public List<String> getRulesList() {
		if(null != searchLiteralStatus){
			return searchLiteralStatus.rulesList;
		}
		return null;
	}

	private class SearchLiteralStatus {

		private String BC;
		private String searchText;
		private boolean isSearchLiteralTruncated;

		private Map<Integer, SearchLevelInfo> levelsInfo = new HashMap<Integer, SearchLevelInfo>();
		private boolean isTextTruncated;

		private List<String> rulesList = new ArrayList<String>();

		private class SearchLevelInfo {
			boolean hasSearchStringInLevel;
			int searchStringInLevelCount;
		}

		public SearchLiteralStatus(CharSequence text, String searchLiteralAsis) {
			this.BC = text.toString();
			this.searchText = searchLiteralAsis;
			this.isSearchLiteralTruncated = false;
			this.isTextTruncated = false;
			this.setTextTruncated(false);
			rulesList.clear();
			setLevelSearchStatus(searchLiteralAsis);
		}

		private void setLevelSearchStatus(String searchLiteralAsis) {
			String[] levels = BC.split(":");

			// Method to check if the level contains the search string or not which
			// will be used in the case of full truncated selection case
			for (int levelCnt = 0; levelCnt < levels.length; levelCnt++) {
				levels[levelCnt] = levels[levelCnt].trim();
				SearchLevelInfo levelInfo = new SearchLevelInfo();
				if (searchText != null
						&& hasSearchLiteral(levels[levelCnt], searchText)) {
					levelInfo.hasSearchStringInLevel = true;
				} else {
					levelInfo.hasSearchStringInLevel = false;
				}
				levelsInfo.put(levelCnt, levelInfo);
			}
		}

		private void setLevelSearchLiteralCount(int level, int count) {
			if ((level < levelsInfo.size()) && (levelsInfo.get(level) != null)) {
				levelsInfo.get(level).searchStringInLevelCount = count;
			}
		}

		private int getLevelSearchLiteralCount(int level) {
			if ((level < levelsInfo.size()) && (levelsInfo.get(level) != null)) {
				return levelsInfo.get(level).searchStringInLevelCount;
			}
			return 0;
		}

		public boolean isSearchLiteralPresent(int level) {
			if (level < levelsInfo.size()) {
				return levelsInfo.get(level).hasSearchStringInLevel;
			}
			return false;
		}

		public boolean isSearchLiteralTruncated() {
			return isSearchLiteralTruncated;
		}

		public void setSearchLiteralTruncated(boolean isSearchLiteralTruncated) {
			this.isSearchLiteralTruncated = isSearchLiteralTruncated;
		}

		public boolean isTextTruncated() {
			return isTextTruncated;
		}

		public void setTextTruncated(boolean isTextTruncated) {
			this.isTextTruncated = isTextTruncated;
		}

	}

		@Override
		protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
			// Make sure we don't call setupScaleSpans again if the measure was triggered
			// by setupScaleSpans itself.
			if (!mMeasuring) {
				final Typeface typeface = getTypeface();
				final float textSize = getTextSize();
				final float textScaleX = getTextScaleX();
				final boolean fakeBold = getPaint().isFakeBoldText();
				if (mTypeface != typeface ||
						mTextSize != textSize ||
						mTextScaleX != textScaleX ||
						mFakeBold != fakeBold) {
					final int width = MeasureSpec.getSize(widthMeasureSpec);
					if (width > 0 && width != mWidth) {
						mTypeface = typeface;
						mTextSize = textSize;
						mTextScaleX = textScaleX;
						mFakeBold = fakeBold;
						mWidth = width;
						mMeasuring = true;
						try {
							// Setup ScaleXSpans on whitespaces to justify the text.
							Justify.setupScaleSpans(this, mSpanStarts, mSpanEnds, mSpans);
						}
						finally {
							mMeasuring = false;
						}
					}
				}
			}
		}

		@Override
		protected void onTextChanged(final CharSequence text,
									 final int start, final int lengthBefore, final int lengthAfter) {
			super.onTextChanged(text, start, lengthBefore, lengthAfter);
			final Layout layout = getLayout();
			if (layout != null) {
				Justify.setupScaleSpans(this, mSpanStarts, mSpanEnds, mSpans);
			}
		}

		@Override
		@NotNull
		public TextView getTextView() {
			return this;
		}

		@Override
		public float getMaxProportion() {
			return Justify.DEFAULT_MAX_PROPORTION;
		}

		private static final int MAX_SPANS = 512;

		private boolean mMeasuring = false;

		private Typeface mTypeface = null;
		private float mTextSize = 0f;
		private float mTextScaleX = 0f;
		private boolean mFakeBold = false;
		private int mWidth = 0;

		private int[] mSpanStarts = new int[MAX_SPANS];
		private int[] mSpanEnds = new int[MAX_SPANS];
		private Justify.ScaleSpan[] mSpans = new Justify.ScaleSpan[MAX_SPANS];
	}
