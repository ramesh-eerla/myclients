package com.roomtrac.mobile.activites;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import com.roomtrac.mobile.R;
import com.roomtrac.mobile.adapters.ViewPagerAdapter;
import com.roomtrac.mobile.fragments.PostprapertyFragment;
import com.roomtrac.mobile.interfaces.TabLoaderInterface;
import com.roomtrac.mobile.utils.CommonUtils;


public class PostPropertyActivity extends AppCompatActivity
        implements  View.OnClickListener {


    Context mContext;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private AppCompatTextView mTitle;
    private ViewPagerAdapter adapter;
    private String[] tabtitle={"Room","Home/Apartment","Roommate","Paying Guest","Hostel"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_property_activity);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mContext=this;
        mTitle=findViewById(R.id.titletext);
        mTitle.setText("Post Your Property");
        viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        loadTabs();
        loadtabcounts();


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                TabLoaderInterface fragment = (TabLoaderInterface) adapter.instantiateItem(viewPager, i);
                if (fragment != null) {
                    if(CommonUtils.TAB_SELECTEDPOSITION!=i){
                        viewPager.setCurrentItem(i);
                        CommonUtils.TAB_SELECTEDPOSITION=i;
                        loadtabcounts();
                        fragment.fragmentBecameVisible(i);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                loadtabcounts();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager(),mContext);
        for (String title:tabtitle)
        adapter.addFragment(new PostprapertyFragment(), title);

        viewPager.setAdapter(adapter);
    }



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }



    @Override
    public void onBackPressed() {

            super.onBackPressed();

    }

    public void loadTabs(){

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(adapter.getTabView(i));
            if(CommonUtils.TAB_SELECTEDPOSITION==i){
                tab.select();
                CommonUtils.TAB_SELECTEDPOSITION=i;

            }
        }

    }
    public void loadtabcounts() {
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab1 = tabLayout.getTabAt(i);
            View v=tab1.getCustomView();
            if(v!=null){
                AppCompatTextView tv = v.findViewById(R.id.tabtitle);
                tv.setText(tabtitle[i]);
                if(CommonUtils.TAB_SELECTEDPOSITION==i){
                    tv.setTextColor(getResources().getColor(R.color.colorAccent));
                }else {
                    tv.setTextColor(getResources().getColor(R.color.colorDark));
                }


            }

        }
    }

    @Override
    public void onClick(View v) {
        int view_id=v.getId();
    }
}
