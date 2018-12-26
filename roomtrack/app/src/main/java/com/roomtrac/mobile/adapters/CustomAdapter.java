package com.roomtrac.mobile.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.roomtrac.mobile.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    ArrayList<String> mValues;
    Context mContext;

    public CustomAdapter(Activity context,  ArrayList<String> osImages) {
        mValues = osImages;
        mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public ViewHolder(View v) {
            super(v);
            imageView = v.findViewById(R.id.os_images);
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.imageview_gridlayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int pos) {

        Picasso.get()
                .load(mValues.get(pos))
                .placeholder(R.drawable.defaultproperty)
                .error(R.drawable.defaultproperty)
                .into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }


}





/*extends BaseAdapter{

    String [] result;
    Context context;
    int [] imageId;
    private static LayoutInflater inflater=null;
    public CustomAdapter(Activity mainActivity, String[] osNameList, int[] osImages) {
        // TODO Auto-generated constructor stub
        result=osNameList;
        context=mainActivity;
        imageId=osImages;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return imageId.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        ImageView os_img;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.imageview_gridlayout, null);

        holder.os_img =(ImageView) rowView.findViewById(R.id.os_images);

        //holder.os_text.setText(result[position]);
        holder.os_img.setImageResource(imageId[position]);

        rowView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked "+imageId[position], Toast.LENGTH_SHORT).show();
            }
        });

        return rowView;
    }

}*/