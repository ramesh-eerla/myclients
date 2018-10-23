package com.roomtrack.mobile.Uicomponents;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.dcrworkforce.mobile.R;
import com.dcrworkforce.mobile.activity.ToDolistPage;
import com.dcrworkforce.mobile.backgroundtasks.AlertDialogforLogout;
import com.dcrworkforce.mobile.dataset.Commanvariables;
import com.dcrworkforce.mobile.requestandresponce.Commons;
import com.dcrworkforce.mobile.screenhelpers.CommonHelper;


/**
 * Created by ramesh.eerla on 1/21/2016.
 */
public abstract class SuperActivity extends AppCompatActivity {
    RelativeLayout content_layout;
    DrawerLayout drawer;
    ListView listView;
    Toolbar toolbar;
    ImageButton mTo_DO_List;
    AlertDialogforLogout alertDialogforLogout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.super_mainlayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        alertDialogforLogout = new AlertDialogforLogout(this);
        Commons.superActivity_title = (SmartTrackTextView) toolbar.findViewById(R.id.smart_title);
        Commons.superActivity_details_title = (SmartTrackTextView) toolbar.findViewById(R.id.details_title);
        Commons.superActivity_details_sub_title = (SmartTrackTextView) toolbar.findViewById(R.id.details_subtitle);
        Commons.superActivity_toolbar = toolbar;
        setSupportActionBar(toolbar);
        Commanvariables.commonHelper = new CommonHelper();
        mTo_DO_List = (ImageButton) toolbar.findViewById(R.id.todo_click);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        Commanvariables.title_app = (SmartTrackTextView) headerView.findViewById(R.id.title_app);
        Commanvariables.User_fullname = (SmartTrackTextView) headerView.findViewById(R.id.user_id_slider);
        listView = (ListView) findViewById(R.id.client_list);
        Commanvariables.slide_listview = listView;
        Commons.superActivity_drawer = drawer;
        content_layout = (RelativeLayout) findViewById(R.id.content_layout);
        View v = getLayoutInflater().inflate(getLayoutResourceid(), null);
        content_layout.addView(v);
        getSupportActionBar().setDisplayShowHomeEnabled(setDisplayShowHomeEnabled());
        getSupportActionBar().setDisplayShowTitleEnabled(setDisplayShowTitleEnabled());
        getSupportActionBar().setDisplayHomeAsUpEnabled(setDisplayHomeAsUpEnabled());
        if (Commons.user_type_id == 5) {
            mTo_DO_List.setVisibility(View.VISIBLE);
        } else {
            mTo_DO_List.setVisibility(View.GONE);
        }
        mTo_DO_List.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Commons.client_id != 0) {
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ToDolistPage frag = new ToDolistPage();
                    frag.show(ft, "txn_tag");
                } else {
                    CommonHelper.showErrorAlertDiaolog(SuperActivity.this, "", "Please select client");
                }
            }
        });
    }

    public abstract int getLayoutResourceid();

    public abstract boolean setDisplayShowHomeEnabled();

    public abstract boolean setDisplayShowTitleEnabled();

    public abstract boolean setDisplayHomeAsUpEnabled();

    public DrawerLayout getdrawer() {
        return drawer;
    }

    public ListView getlistview() {
        return listView;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean isdown = false;
        invalidateOptionsMenu();
        MenuItem item1 = menu.findItem(R.id.timeSheet);
        MenuItem item2 = menu.findItem(R.id.approvals);
        MenuItem item3 = menu.findItem(R.id.requisitionfulfillment);
        MenuItem item4 = menu.findItem(R.id.createrequisition);
        MenuItem item5 = menu.findItem(R.id.logout);
        MenuItem item6 = menu.findItem(R.id.requirements);
        MenuItem item7 = menu.findItem(R.id.interview);
        MenuItem item8 = menu.findItem(R.id.offers);

        item1.setVisible(isdown);
        item2.setVisible(isdown);
        item3.setVisible(isdown);
        item4.setVisible(isdown);
        item5.setVisible(isdown);
        item6.setVisible(isdown);
        item7.setVisible(isdown);
        item8.setVisible(isdown);
        invalidateOptionsMenu();

        return super.onPrepareOptionsMenu(menu);
    }

   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.timeSheet:
                Commanvariables.TAB_SELECTEDPOSITION=0;
                break;
            case R.id.approvals:
                Commanvariables.TAB_SELECTEDPOSITION=1;
                break;
            case R.id.requisitionfulfillment:
                Commanvariables.TAB_SELECTEDPOSITION=2;
                break;
            case R.id.createrequisition:
                Commanvariables.TAB_SELECTEDPOSITION=3;
                break;
            case R.id.requirements:
                Commanvariables.TAB_SELECTEDPOSITION =0;
                break;
            case R.id.interview:
                Commanvariables.TAB_SELECTEDPOSITION=1;
                break;
            case R.id.offers:
                Commanvariables.TAB_SELECTEDPOSITION=2;
                break;


            case R.id.logout:
                FragmentManager fm = SuperActivity.this.getSupportFragmentManager();
                for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                    fm.popBackStack();
                }
                alertDialogforLogout.setAlertDiaolog(1).show();
                break;
        }
        if(id!=R.id.logout){
        Commanvariables.menuselection=true;
        startActivity(new Intent(this,LandingPagewithAutologin.class));}
        return super.onOptionsItemSelected(item);
    }
*/


}
