package com.roomtrac.mobile.activites;

import android.animation.StateListAnimator;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;

import com.roomtrac.mobile.R;
import com.roomtrac.mobile.utils.CommonUtils;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

public class FilterActivity extends AppCompatActivity implements DiscreteSeekBar.OnProgressChangeListener, View.OnClickListener {

    DiscreteSeekBar seekbar_area, seekbar_rent, seekbar_age;
    AppCompatTextView area, rent, age;
    AppCompatButton btn_submit;
    CheckBox male, female, veg, non_veg, ac, non_ac, furnish, un_furnish, semi_furnish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        seekbar_area = findViewById(R.id.seekbar_area);
        seekbar_rent = findViewById(R.id.rentseekbar);
        seekbar_age = findViewById(R.id.ageseekbar);

        area = findViewById(R.id.seekbar_area_value);
        rent = findViewById(R.id.seekbar_rent);
        age = findViewById(R.id.seekbar_age);

        male = findViewById(R.id.male_chekbox);
        female = findViewById(R.id.female_chekbox);
        veg = findViewById(R.id.veg_chekbox);
        non_veg = findViewById(R.id.non_veg_chekbox);
        ac = findViewById(R.id.ac_checkbox);
        non_ac = findViewById(R.id.non_ac_checkbox);
        furnish = findViewById(R.id.furnsihed_checkbox);
        un_furnish = findViewById(R.id.un_furnished_checkbox);
        semi_furnish = findViewById(R.id.semi_furnished_checkbox);
        btn_submit = findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(this);
        seekbar_area.setOnProgressChangeListener(this);
        seekbar_rent.setOnProgressChangeListener(this);
        seekbar_age.setOnProgressChangeListener(this);
        if (CommonUtils.applyfilter) {
            seekbar_area.setProgress(CommonUtils.filter_area);
            seekbar_rent.setProgress(CommonUtils.filter_rent);
            seekbar_age.setProgress(CommonUtils.filter_age);

            ac.setChecked(CommonUtils.ac_status);
            non_ac.setChecked(CommonUtils.non_acstatus);
            male.setChecked(CommonUtils.male_status);
            female.setChecked(CommonUtils.female_status);
            veg.setChecked(CommonUtils.veg_status);
            non_veg.setChecked(CommonUtils.non_veg_status);
            furnish.setChecked(CommonUtils.furnished_status);
            semi_furnish.setChecked(CommonUtils.semi_furnished_status);
            un_furnish.setChecked(CommonUtils.un_furnished_status);
        }


        area.setText("Selected :" + seekbar_area.getProgress());
        rent.setText("Selected :" + seekbar_rent.getProgress());
        age.setText("Selected :" + seekbar_age.getProgress());


    }


    @Override
    public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.seekbar_area:
                area.setText("Selected :" + seekBar.getProgress());
                break;
            case R.id.rentseekbar:
                rent.setText("Selected :" + seekBar.getProgress());
                break;
            case R.id.ageseekbar:
                age.setText("Selected :" + seekBar.getProgress());
                break;

        }

    }

    @Override
    public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

    }

    @Override
    public void onClick(View v) {

        CommonUtils.filter_area = Integer.parseInt(area.getText().toString().replaceAll("Selected :", ""));
        CommonUtils.filter_age = Integer.parseInt(age.getText().toString().replaceAll("Selected :", ""));
        CommonUtils.filter_rent = Integer.parseInt(rent.getText().toString().replaceAll("Selected :", ""));
        if (CommonUtils.searchtype != 5) {
            CommonUtils.veg_status = veg.isChecked();
            CommonUtils.non_veg_status = non_veg.isChecked();
        } else {
            CommonUtils.veg_status = false;
            CommonUtils.non_veg_status = false;
        }

        CommonUtils.ac_status = ac.isChecked();
        CommonUtils.non_acstatus = non_ac.isChecked();
        CommonUtils.male_status = male.isChecked();
        CommonUtils.female_status = female.isChecked();
        CommonUtils.furnished_status = furnish.isChecked();
        CommonUtils.semi_furnished_status = semi_furnish.isChecked();
        CommonUtils.un_furnished_status = un_furnish.isChecked();
        CommonUtils.applyfilter = true;
        finish();
    }
}
