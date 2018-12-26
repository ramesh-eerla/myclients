package com.roomtrac.mobile.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.roomtrac.mobile.R;
import com.roomtrac.mobile.Uicomponents.ApprovalFontCommons;
import com.roomtrac.mobile.utils.CommonHelper;
import com.roomtrac.mobile.utils.CommonUtils;
import com.roomtrac.mobile.utils.Constants;


public class Property_Details extends Fragment {

    public Property_Details() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.property_details_fragment, container, false);
        AppCompatTextView details=view.findViewById(R.id.full_details);
        details.setText(Html.fromHtml(getdetails_Data()));




        return view;
    }

   public String getdetails_Data(){
        String tag_line="",date="";
       if(CommonUtils.mSelecteditemDetails.getTag_line()!=null&&!CommonUtils.mSelecteditemDetails.getTag_line().trim().equalsIgnoreCase(""))
           tag_line= ApprovalFontCommons.grayColorText("Tagline Info :",CommonUtils.mSelecteditemDetails.getAbout_room(),"",Constants.FIRST_ITEM)+"<br/><br/>";

       if(!CommonUtils.mSelecteditemDetails.getPosted_on().trim().equalsIgnoreCase(""))
           date= ApprovalFontCommons.grayColorText("Posted On :",CommonUtils.mSelecteditemDetails.getPosted_on().substring(0,10),"",Constants.FIRST_ITEM)+"<br/><br/>";

       String data=tag_line+ApprovalFontCommons.grayColorText("About Info :",CommonUtils.mSelecteditemDetails.getAbout_room(),"",Constants.FIRST_ITEM)+"<br/><br/>"+
              ApprovalFontCommons.grayColorText("Location :",CommonUtils.mSelecteditemDetails.getLocation_name()+","+CommonUtils.mSelecteditemDetails.getCity_name()+","+CommonUtils.mSelecteditemDetails.getState_name(),"",Constants.FIRST_ITEM)+"<br/><br/>"+
       ApprovalFontCommons.grayColorText("Area sqft Info :",CommonUtils.mSelecteditemDetails.getArea_sqft(),"",Constants.FIRST_ITEM)+"<br/><br/>"+
       ApprovalFontCommons.grayColorText("Rent per month :",CommonUtils.mSelecteditemDetails.getRent_per_month(),"",Constants.FIRST_ITEM)+"<br/><br/>"+
       ApprovalFontCommons.grayColorText("Facility Info :",CommonUtils.mSelecteditemDetails.getFacility(),"",Constants.FIRST_ITEM)+"<br/><br/>"+
       ApprovalFontCommons.grayColorText("Food Info :",CommonUtils.mSelecteditemDetails.getFood_type(),"",Constants.FIRST_ITEM)+"<br/><br/>"+
       ApprovalFontCommons.grayColorText("Furnish Info :",CommonUtils.mSelecteditemDetails.getFurnish_type(),"",Constants.FIRST_ITEM)+"<br/><br/>"+
       ApprovalFontCommons.grayColorText("Members Per Room :",CommonUtils.mSelecteditemDetails.getMembers_per_room(),"",Constants.FIRST_ITEM)+"<br/><br/>"+
       ApprovalFontCommons.grayColorText("Vacency for :",CommonUtils.mSelecteditemDetails.getVacency_for(),"",Constants.FIRST_ITEM)+"<br/><br/>"+
       ApprovalFontCommons.grayColorText("Phone Number :",CommonUtils.mSelecteditemDetails.getPhone_number(),"",Constants.FIRST_ITEM)+"<br/><br/>"+date;
return data;
    }


}