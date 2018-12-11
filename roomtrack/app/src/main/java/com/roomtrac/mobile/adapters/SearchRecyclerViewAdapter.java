package com.roomtrac.mobile.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.roomtrac.mobile.R;
import com.roomtrac.mobile.activites.SearchActivity;
import com.roomtrac.mobile.connectioncalls.datasets.DetailsDataset;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchRecyclerViewAdapter extends RecyclerView.Adapter<SearchRecyclerViewAdapter.MyViewHolder> {
    private List<DetailsDataset> detailsDataset;

    public SearchRecyclerViewAdapter(List<DetailsDataset> detailsDataset) {
        this.detailsDataset=detailsDataset;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_item, viewGroup, false);

        view.setOnClickListener(SearchActivity.myOnClickListener);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        DetailsDataset singleset=detailsDataset.get(i);
myViewHolder.details.setText(singleset.getLocationame()+","+singleset.getCityname()+","+singleset.getStatename()+"\nAvailability date:"+singleset.getAvailibilityDate()+"-"+singleset.getAvailibilityMonth()+"-"+singleset.getAvailibilityYear()/*"\n" +
        singleset.getFurnishType()+"\nMin to Max Age: "+singleset.getMinAge()+"-"+singleset.getMaxAge()+"\n" +
        "Listed by :"+singleset.getFirstName()+"\n"+
                "Last Activity : "+singleset.getLastActivity()*/);
myViewHolder.title.setText(singleset.getTagLine());
myViewHolder.price.setText("RS/- "+singleset.getRentPerMonth());
String url =singleset.getUserUploaded();
if(url.contains(","))
    url=url.split(",")[0];
        Picasso.get()
                .load("http://qaroomtrac.wizardtechnologiesprivatelimited.com/profile_uploads/uploads/"+url)
                .placeholder(R.drawable.defaultproperty)
                .error(R.drawable.defaultproperty)
                .into(myViewHolder.housepic);
    }

    @Override
    public int getItemCount() {
        return detailsDataset.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public AppCompatImageView housepic;
        public AppCompatTextView price,details,title;
        public AppCompatButton sendmail;

        public MyViewHolder(View v) {
            super(v);
            this.price = v.findViewById(R.id.price);
            this.details=v.findViewById(R.id.address);
            this.title=v.findViewById(R.id.title);
            this.sendmail=v.findViewById(R.id.sendmail);
            this.housepic=v.findViewById(R.id.housepic);
        }
    }
}
