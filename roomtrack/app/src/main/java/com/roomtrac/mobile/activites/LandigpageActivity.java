package com.roomtrac.mobile.activites;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentTransitionImpl;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;

import com.google.gson.Gson;
import com.roomtrac.mobile.R;
import com.roomtrac.mobile.adapters.ExpandableListAdapter;
import com.roomtrac.mobile.connectioncalls.datasets.LoginDataset;
import com.roomtrac.mobile.connectioncalls.datasets.MenuModel;
import com.roomtrac.mobile.fragments.AccountSettingsFragement;
import com.roomtrac.mobile.fragments.EditetProfileFragement;
import com.roomtrac.mobile.fragments.HomeFragement;
import com.roomtrac.mobile.fragments.MessageFragment;
import com.roomtrac.mobile.utils.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Ramesh.eerla on 24/10/2018.
 */
public class LandigpageActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Context mContext;
    FrameLayout mContainer;
    ExpandableListAdapter expandableListAdapter;
    ExpandableListView expandableListView;
    List<MenuModel> headerList = new ArrayList<>();
    HashMap<MenuModel, List<MenuModel>> childList = new HashMap<>();
    FragmentManager mFragmentManager;
    Fragment mFragment;
    FragmentTransaction mFragmentTransaction;
    LoginDataset loginDataset;
    AppCompatTextView user_name,user_mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        SharedPreferences mPrefs = getSharedPreferences("Login_repsonse",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        loginDataset= gson.fromJson(json, LoginDataset.class);
        mContext = this;
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        mContainer = findViewById(R.id.container);

        mFragment= new HomeFragement();
        attachFragment(mFragment);

        expandableListView = findViewById(R.id.expandableListView);
        prepareMenuData();
        populateExpandableList();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        user_name =header.findViewById(R.id.username);
        user_mail=header.findViewById(R.id.user_email);
        user_name.setText(loginDataset.getFirst_name());
        user_mail.setText(loginDataset.getEmail());
    }

    private void attachFragment(Fragment mFragment) {
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.container, mFragment);
        mFragmentTransaction.addToBackStack(null);
        mFragmentTransaction.commit();
    }

    private void populateExpandableList() {
        expandableListAdapter = new ExpandableListAdapter(this, headerList, childList);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                if (headerList.get(groupPosition).isGroup) {
                    if (!headerList.get(groupPosition).hasChildren) {
                        MenuModel model = headerList.get(groupPosition);
                        switch (model.menuid) {
                            case Constants.HOME:
                                attachFragment(new HomeFragement());
                                break;
                            case Constants.BOOKMARKS:
                                attachFragment(new HomeFragement());
                                break;
                            case Constants.MY_PROPERTIES:
                                attachFragment(new HomeFragement());
                                break;
                        }
                        onBackPressed();
                    }
                }

                return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                onBackPressed();
                if (childList.get(headerList.get(groupPosition)) != null) {
                    MenuModel model = childList.get(headerList.get(groupPosition)).get(childPosition);
                    switch (model.menuid) {
                        case Constants.VIEWMESSAGE:
                            attachFragment(new HomeFragement());
                            break;
                        case Constants.SENTMESSAGES:
                            attachFragment(new HomeFragement());
                            break;
                        case Constants.FAVOURITES:
                            attachFragment(new HomeFragement());
                            break;
                        case Constants.ACCOUNTSETTINGS:
                            attachFragment(new AccountSettingsFragement());
                            break;
                        case Constants.EDIT_PROFILE:
                            attachFragment(new EditetProfileFragement());
                            break;
                        case Constants.VIEW_PROFFILE:
                            attachFragment(new HomeFragement());
                            break;
                    }

                }

                return false;
            }
        });
    }

    private void prepareMenuData() {
        MenuModel menuModel = new MenuModel("Home", true, false, Constants.HOME); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);
        if (menuModel.hasChildren) {
            childList.put(menuModel, null);
        }
        menuModel = new MenuModel("Messages", true, true, Constants.MESSAGE); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);
        List<MenuModel> childModelsList = new ArrayList<>();
        MenuModel childModel = new MenuModel("View Messages", false, false, Constants.VIEWMESSAGE);
        childModelsList.add(childModel);

        childModel = new MenuModel("Sent Messages", false, false, Constants.SENTMESSAGES);
        childModelsList.add(childModel);

        childModel = new MenuModel("Favourites", false, false, Constants.FAVOURITES);
        childModelsList.add(childModel);


        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }

        menuModel = new MenuModel("Bookmarks", false, false, Constants.BOOKMARKS); //Menu of Java Tutorials
        headerList.add(menuModel);
        if (menuModel.hasChildren) {
            //Log.d("API123","here");
            childList.put(menuModel, null);
        }

        childModelsList = new ArrayList<>();
        menuModel = new MenuModel("MyAccount", true, true, Constants.MYACCOUNT); //Menu of Python Tutorials
        headerList.add(menuModel);
        childModel = new MenuModel("Account Setting", false, false, Constants.ACCOUNTSETTINGS);
        childModelsList.add(childModel);

        childModel = new MenuModel("Edit Profile", false, false, Constants.EDIT_PROFILE);
        childModelsList.add(childModel);

        childModel = new MenuModel("View Profile", false, false, Constants.VIEW_PROFFILE);
        childModelsList.add(childModel);
        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }

        menuModel = new MenuModel("My Properties", true, false, Constants.MY_PROPERTIES); //Menu of Python Tutorials
        headerList.add(menuModel);

        if (menuModel.hasChildren) {
            childList.put(menuModel, null);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /* @Override
     public boolean onCreateOptionsMenu(Menu menu) {
         // Inflate the menu; this adds items to the action bar if it is present.
         getMenuInflater().inflate(R.menu.main, menu);
         return true;
     }

     @Override
     public boolean onOptionsItemSelected(MenuItem item) {
         // Handle action bar item clicks here. The action bar will
         // automatically handle clicks on the Home/Up button, so long
         // as you specify a parent activity in AndroidManifest.xml.
         int id = item.getItemId();

         //noinspection SimplifiableIfStatement
         if (id == R.id.action_settings) {
             return true;
         }

         return super.onOptionsItemSelected(item);
     }
 */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
