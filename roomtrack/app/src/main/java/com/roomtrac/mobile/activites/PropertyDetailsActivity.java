package com.roomtrac.mobile.activites;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;


import com.google.gson.Gson;
import com.roomtrac.mobile.R;
import com.roomtrac.mobile.adapters.PropertyViewPagerAdapter;
import com.roomtrac.mobile.adapters.SlidingImage_Adapter;
import com.roomtrac.mobile.connectioncalls.connections.RT_RetrofitSevicecall;
import com.roomtrac.mobile.connectioncalls.datasets.DetailsDataset;
import com.roomtrac.mobile.connectioncalls.datasets.ImageModel;
import com.roomtrac.mobile.connectioncalls.datasets.LoginDataset;
import com.roomtrac.mobile.connectioncalls.datasets.RetrofitResponse;
import com.roomtrac.mobile.fragments.Property_Details;
import com.roomtrac.mobile.fragments.Property_Gallery;
import com.roomtrac.mobile.fragments.Property_Review;
import com.roomtrac.mobile.interfaces.ResponceCallback;
import com.roomtrac.mobile.services.RequestParams;
import com.roomtrac.mobile.utils.CommonHelper;
import com.roomtrac.mobile.utils.CommonUtils;
import com.squareup.picasso.Picasso;
import com.viewpagerindicator.CirclePageIndicator;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class PropertyDetailsActivity extends AppCompatActivity implements ResponceCallback {

    private static ViewPager mPager;
    private Button book_mark_button;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private ArrayList<String> imageModelArrayList;
    private Context mContext;
    private DetailsDataset dataset;
    private RT_RetrofitSevicecall mRt_retrofitSevicecall;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_details_activity);
        mContext=this;
        imageModelArrayList = new ArrayList<>();
        mRt_retrofitSevicecall=new RT_RetrofitSevicecall(this);

        imageModelArrayList = CommonHelper.populateList(CommonUtils.mSelecteditemDetails);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        init();

        ViewPager viewPager = findViewById(R.id.pager);
        book_mark_button=findViewById(R.id.book_mark_button);
        book_mark_button.setVisibility(View.GONE);
        setbookmarkRequest(9,"checkbookmark_is_set");
        PropertyViewPagerAdapter adapter = new PropertyViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Property_Details(), "Description");
        adapter.addFragment(new Property_Gallery(), "Photos");
       /* adapter.addFragment(new Property_Review(), "Reviews");*/
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

public void updatebookmark(View view){
    setbookmarkRequest(10,"setbookmark");
}

public void setbookmarkRequest(int requesttype,String methodname){
    SharedPreferences mPrefs = getSharedPreferences("Login_repsonse",MODE_PRIVATE);
    Gson gson = new Gson();
    String json = mPrefs.getString("MyObject", "");
    LoginDataset loginDataset= gson.fromJson(json, LoginDataset.class);

    RequestParams.BookMarkset forgetPassword = new RequestParams().new BookMarkset().bookMarkset(loginDataset.getMember_id(),CommonUtils.mSelecteditemDetails.getMember_id());
    mRt_retrofitSevicecall.submitPost(requesttype, methodname, forgetPassword);

}


    private void init() {

        mPager = findViewById(R.id.pagerView);
        mPager.setAdapter(new SlidingImage_Adapter(mContext, imageModelArrayList));

        CirclePageIndicator indicator = (CirclePageIndicator)
                findViewById(R.id.indicator);

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

        //Set circle indicator radius
        indicator.setRadius(5 * density);

        NUM_PAGES = imageModelArrayList.size();

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

    }

    @Override
    public void callback(JSONObject responce) {

    }

    @Override
    public void callback(Object responce, int requesttype) {
        RetrofitResponse rence = (RetrofitResponse) responce;
        if (requesttype == 9) {
if(rence.getStatus().equalsIgnoreCase("false"))
    book_mark_button.setVisibility(View.VISIBLE);
        } else {
            if(rence.getStatus().equalsIgnoreCase("true"))
                book_mark_button.setVisibility(View.GONE);

            CommonHelper.setalertdialog_(this, "Bookmark Success", rence.getMessage());
        }
    }

    @Override
    public void errorcallback(String errortitle, String errormessage) {

    }
}