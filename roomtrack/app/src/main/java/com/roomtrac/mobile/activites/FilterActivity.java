package com.roomtrac.mobile.activites;

import android.animation.StateListAnimator;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.roomtrac.mobile.R;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

public class FilterActivity extends AppCompatActivity implements DiscreteSeekBar.OnProgressChangeListener {

DiscreteSeekBar seekbar_area,seekbar_rent,seekbar_age;
AppCompatTextView area,rent,age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        seekbar_area=findViewById(R.id.seekbar_area);
        seekbar_rent=findViewById(R.id.rentseekbar);
        seekbar_age=findViewById(R.id.ageseekbar);

        area=findViewById(R.id.seekbar_area_value);
        rent=findViewById(R.id.seekbar_rent);
        age=findViewById(R.id.seekbar_age);

        seekbar_area.setOnProgressChangeListener(this);
        seekbar_rent.setOnProgressChangeListener(this);
        seekbar_age.setOnProgressChangeListener(this);
        area.setText("Selected :"+seekbar_area.getProgress());
        rent.setText("Selected :"+seekbar_rent.getProgress());
        age.setText("Selected :"+seekbar_age.getProgress());


    }



    @Override
    public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
       switch (seekBar.getId())
       { case R.id.seekbar_area:
           area.setText("Selected :"+seekBar.getProgress());
           break;
           case R.id.rentseekbar:
               rent.setText("Selected :"+seekBar.getProgress());
               break;
           case R.id.ageseekbar:
               age.setText("Selected :"+seekBar.getProgress());
               break;

       }

    }

    @Override
    public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

    }
}
