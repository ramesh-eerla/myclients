package com.roomtrack.mobile.Uicomponents;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;

public class ViewHolder {

    public RelativeLayout custom_list_layout;
    public TextView delete_value;
    public Spinner skill_exp_sp,skill_level_sp;
    public SmartTrackEditText multiline_text,singleline_text,comment__ad_ed,skill_title_value,skill_comment_value,skill_level_edit,mJobtitle_edit,mRequester_edit;
    public TextInputLayout skill_comment_text,comment_ad;
    public SmartTrackTextView applicant_header_text,applicant_qation,skill_title,slider_item,resume_title,skill_exp_text,skill_level_text,mFromdate_lbl,mTodate_lbl,mJobTitle_lbl,mRecutur_lbl;
    public AppCompatRadioButton aprb1,aprb2,aprb3,aprb4;
    public AppCompatCheckBox apch1,apch2,apch3,apch4;
    public Button addmore,remove_,mFromdate_btn,mTodate_btn;
    public SmartTrackEditText skill_exp_edit,skill_title_edit;
    public TableRow addtional_comment;
    public LinearLayout skill_item,mTableRow,multi_layout,single_layout,radio_layout,check_box_layout,applicant_qt,applicant_header,add_morelayout;
    public AutoCompleteTextView mFromdate_auto,mTodate_auto;

}