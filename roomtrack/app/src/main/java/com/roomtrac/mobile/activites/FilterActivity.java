package com.roomtrac.mobile.activites;

import android.animation.StateListAnimator;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.roomtrac.mobile.R;

public class FilterActivity extends AppCompatActivity {

private Button details,list,map,lastactive,newest,rent;
private RadioGroup viewtype,recent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        viewtype=findViewById(R.id.viewtype);
        recent=findViewById(R.id.recent);
        details=viewtype.findViewById(R.id.rd_bt_1);
        list=viewtype.findViewById(R.id.rd_bt_2);
        map=viewtype.findViewById(R.id.rd_bt_3);

        lastactive=recent.findViewById(R.id.rd_bt_1);
        newest=recent.findViewById(R.id.rd_bt_2);
        rent=recent.findViewById(R.id.rd_bt_3);

        details.setOnClickListener(topButtonsListener);
        list.setOnClickListener(topButtonsListener);
        map.setOnClickListener(topButtonsListener);

        lastactive.setOnClickListener(topButtonsListener);
        newest.setOnClickListener(topButtonsListener);
        rent.setOnClickListener(topButtonsListener);

    }

     View.OnClickListener topButtonsListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            int no_months = 0;
            if (view.getId() == R.id.rd_bt_1) {
                no_months = 3;
                details.setSelected(true);
                details.setTextColor(Color.WHITE);
               // list.setStateListAnimator(null);
                list.setSelected(false);
                list.setTextColor(Color.BLACK);
                map.setSelected(false);
               // map.setStateListAnimator(null);
                map.setTextColor(Color.BLACK);


            } else if (view.getId() == R.id.rd_bt_2) {
                no_months = 6;
                list.setSelected(true);
                list.setTextColor(Color.WHITE);
                //details.setStateListAnimator(null);
                details.setSelected(false);
                details.setTextColor(Color.BLACK);
                map.setSelected(false);
               // map.setStateListAnimator(null);
                map.setTextColor(Color.BLACK);

            } else if (view.getId() == R.id.rd_bt_3) {
                no_months = 9;
                map.setSelected(true);
                map.setTextColor(Color.WHITE);
                //details.setStateListAnimator(null);
                details.setSelected(false);
                details.setTextColor(Color.BLACK);
                list.setSelected(false);
               // list.setStateListAnimator(null);
                list.setTextColor(Color.BLACK);

            }





        }


    };

}
