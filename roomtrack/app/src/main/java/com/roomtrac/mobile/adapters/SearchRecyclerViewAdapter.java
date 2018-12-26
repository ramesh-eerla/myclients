package com.roomtrac.mobile.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.roomtrac.mobile.R;
import com.roomtrac.mobile.activites.LandigpageActivity;
import com.roomtrac.mobile.activites.SearchActivity;
import com.roomtrac.mobile.connectioncalls.datasets.DetailsDataset;
import com.roomtrac.mobile.interfaces.OnItemClickListener;
import com.roomtrac.mobile.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SearchRecyclerViewAdapter extends RecyclerView.Adapter<SearchRecyclerViewAdapter.MyViewHolder> {
    private ArrayList<DetailsDataset> detailsDataset;
    private Context mContext;
    public OnItemClickListener onItemClickListener;
    public int bookmark_type;

    public SearchRecyclerViewAdapter(List<DetailsDataset> detailsDataset, Context mContext, int search_type) {
        this.detailsDataset=new ArrayList<DetailsDataset>(detailsDataset);;
        this.mContext=mContext;
        this.bookmark_type=search_type;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_item, viewGroup, false);

        view.setOnClickListener(SearchActivity.myOnClickListener);
        MyViewHolder myViewHolder=new MyViewHolder(view,mContext);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        DetailsDataset singleset=detailsDataset.get(i);
        if(bookmark_type==Constants.MY_PROPERTIES) {
            myViewHolder.details.setText(singleset.getLocation_name() + "," + singleset.getCity_name() + "," + singleset.getState_name() + "\nAvailability date:" + singleset.getAvailibility_date() + "-" + singleset.getAvailibility_month() + "-" + singleset.getAvailibility_year());
            myViewHolder.title.setText(singleset.getTag_line());
            myViewHolder.price.setText("RS/- " + singleset.getRent_per_month());
        }else {
            myViewHolder.loc_image.setVisibility(View.INVISIBLE);
            myViewHolder.details.setText("Gender : " + singleset.getSex() + "\nEmail : " + singleset.getEmail() + "\nPhone Number : " + singleset.getPhone_number());
            myViewHolder.title.setText("Name : " + singleset.getFirst_name());
            myViewHolder.price.setText("Last Activity : " + singleset.getLast_activity().substring(0, 10));
        }
String url =singleset.getUser_uploaded();
if(url.contains(","))
    url=url.split(",")[0];
int defaultid=0;
        if(bookmark_type==Constants.BOOKMARKS){
            defaultid=R.drawable.default_user;
        }else{
            defaultid=R.drawable.defaultproperty;
        }

        Picasso.get()
                .load(Constants.MAIN_URL+"/profile_uploads/uploads/"+url)
                .placeholder(defaultid)
                .error(defaultid)
                .into(myViewHolder.housepic);
    }

    @Override
    public int getItemCount() {
        return detailsDataset.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public AppCompatImageView housepic;
        public AppCompatTextView price,details,title;
        public AppCompatButton sendmail;
        public ImageView loc_image;

        public MyViewHolder(View v, final Context mContext) {
            super(v);
            this.price = v.findViewById(R.id.price);
            this.details=v.findViewById(R.id.address);
            this.title=v.findViewById(R.id.title);
            this.sendmail=v.findViewById(R.id.sendmail);
            this.housepic=v.findViewById(R.id.housepic);
            this.loc_image=v.findViewById(R.id.loc_image);
            if(mContext instanceof LandigpageActivity)
                sendmail.setVisibility(View.GONE);

            this.sendmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    onItemClickListener.onItemClick(getPosition(),detailsDataset,"");
                  //
                }
            });
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick_(getPosition(),detailsDataset);

                }
            });
        }
    }
public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
}


}
