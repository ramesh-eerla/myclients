package com.roomtrac.mobile.activites;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.gson.internal.LinkedTreeMap;
import com.roomtrac.mobile.R;
import com.roomtrac.mobile.adapters.SearchRecyclerViewAdapter;
import com.roomtrac.mobile.connectioncalls.connections.RT_RetrofitSevicecall;
import com.roomtrac.mobile.connectioncalls.datasets.Dataset;
import com.roomtrac.mobile.connectioncalls.datasets.DetailsDataset;
import com.roomtrac.mobile.interfaces.OnItemClickListener;
import com.roomtrac.mobile.interfaces.ResponceCallback;
import com.roomtrac.mobile.services.RequestParams;
import com.roomtrac.mobile.utils.CommonUtils;
import com.roomtrac.mobile.utils.Constants;
import com.roomtrac.mobile.utils.JSONUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener,ResponceCallback {


    public  static View.OnClickListener myOnClickListener;
    private static RecyclerView searchview;
    private AutoCompleteTextView searcheditbox;
    private Context mContext;
    private AppCompatImageView filter;
    private AppCompatButton search_button;
    private AppCompatTextView mSearch_area;
    private RecyclerView.LayoutManager mLayoutManager;
    private RT_RetrofitSevicecall mRt_retrofitSevicecall;
    private Bundle bundle;
    private int search_type;
    private ArrayList<String> list,data;
    private String list_data;
    private SearchRecyclerViewAdapter adapter;
    private String[] tabtitle={"Rooms","Apartments","Roommates","Paying Guest","Hostel"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
        mRt_retrofitSevicecall=new RT_RetrofitSevicecall(mContext);
        myOnClickListener = new MyOnClickListener(mContext);
        setContentView(R.layout.searchpage);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        searcheditbox=findViewById(R.id.location);
        mSearch_area=findViewById(R.id.search_area);
        mSearch_area.setText(tabtitle[getIntent().getExtras().getInt(Constants.RT_SEACH_TYPE)-1]);
        searchview=findViewById(R.id.searchview);
        search_button=findViewById(R.id.search_button);
        search_button.setOnClickListener(this);
        searchview.setHasFixedSize(true);
        filter=findViewById(R.id.filter);
        filter.setOnClickListener(this);
        mLayoutManager=new LinearLayoutManager(mContext);
        searchview.setLayoutManager(mLayoutManager);
        searcheditbox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
              
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.length()!=0) {
                    loadLocation(searcheditbox.getText().toString());
                }else
                    search_button.setEnabled(false);


            }
        });

        searcheditbox.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                    long id) {
                search_button.setEnabled(true);
                list_data=list.get(pos);
                CommonUtils.location_data=list.get(pos);

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        if(CommonUtils.applyfilter){
            loadsearchlist(CommonUtils.location_data);
        }
    }

    public void loadSearchList(Object responce)  {

    List<LinkedTreeMap> data=(List<LinkedTreeMap>)responce;
    List<DetailsDataset> detailsDataset=JSONUtils.getSearchdata(data);
     adapter=new SearchRecyclerViewAdapter(detailsDataset,mContext, search_type);
    searchview.setAdapter(adapter);
    adapter.setOnItemClickListener(new OnItemClickListener() {
        @Override
        public void onItemClick(int position, ArrayList<DetailsDataset> landingPageDataset,String type) {
            CommonUtils.forget_anv=Constants.RT_SENDMAIL;
            CommonUtils.mSelecteditemDetails=landingPageDataset.get(position);
            mContext.startActivity(new Intent(mContext, Forgetpassword.class));
        }

        @Override
        public void onItemClick_(int position, ArrayList<DetailsDataset> landingPageDataset) {
            CommonUtils.mSelecteditemDetails=landingPageDataset.get(position);
startActivity(new Intent(mContext,PropertyDetailsActivity.class));
        }

        @Override
        public void onItemClick_dataset(int position, ArrayList<Dataset> landingPageDataset) {

        }
    });

}
    private void loadLocation(String s) {
        bundle=getIntent().getExtras();
        search_type=bundle.getInt(Constants.RT_SEACH_TYPE);
        RequestParams.SearchValues searchTypes= new RequestParams().new SearchValues().setsearch(""+s);
        mRt_retrofitSevicecall.searchPost(Constants.RT_SEARCH_LOCATION,searchTypes);

    }

    private void loadsearchlist(String listData) {
        bundle=getIntent().getExtras();
        search_type=bundle.getInt(Constants.RT_SEACH_TYPE);
        CommonUtils.searchtype=search_type;
        if(listData!=null&&!listData.equalsIgnoreCase("")){
            String[] data=listData.split(",");
        RequestParams.SearchTypes searchTypes= new RequestParams().new SearchTypes().setsearch(data[0],data[1],data[2],search_type);
        if(search_type==5)
            mRt_retrofitSevicecall.searchPost(Constants.RT_SEARCH_HOSTEL,searchTypes);
        else
        mRt_retrofitSevicecall.searchPost(Constants.RT_SEARCH,searchTypes);
        }


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.filter:
                    startActivity(new Intent(SearchActivity.this,FilterActivity.class));
                break;
            case R.id.search_button:
                loadsearchlist(list_data);
                break;
        }
    }

    @Override
    public void callback(JSONObject responce) {

    }

    @Override
    public void callback(Object responce, int requesttype) {
        switch (requesttype){
            case Constants.RT_SEARCH_LOCATION:
                List<Dataset> dataset=(List<Dataset>)responce;
                 list=new ArrayList<>();
                 data=new ArrayList<>();
                for(Dataset dat: dataset){
                    list.add(dat.getList());
                    data.add(dat.getData());
                }
                ArrayAdapter adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1,data);
                searcheditbox.setThreshold(1);
                searcheditbox.setAdapter(adapter);
                searcheditbox.showDropDown();
                break;
            default:
                    loadSearchList(responce);
            break;
        }

    }

    @Override
    public void errorcallback(String errortitle, String errormessage) {

    }

    private static class MyOnClickListener implements View.OnClickListener {

        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            removeItem(v);
        }

        private void removeItem(View v) {
            int selectedItemPosition = searchview.getChildPosition(v);
            RecyclerView.ViewHolder viewHolder
                    = searchview.findViewHolderForPosition(selectedItemPosition);
           /* TextView textViewName
                    = (TextView) viewHolder.itemView.findViewById(R.id.textViewName);*/
         //   String selectedName = (String) textViewName.getText();
            int selectedItemId = -1;
            /*for (int i = 0; i < MyData.nameArray.length; i++) {
                if (selectedName.equals(MyData.nameArray[i])) {
                    selectedItemId = MyData.id_[i];
                }
            }
            removedItems.add(selectedItemId);
            data.remove(selectedItemPosition);*/
//            adapter.notifyItemRemoved(selectedItemPosition);
        }
    }
}
