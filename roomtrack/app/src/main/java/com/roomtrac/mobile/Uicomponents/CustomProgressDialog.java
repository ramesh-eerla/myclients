package com.roomtrac.mobile.Uicomponents;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.widget.CircularProgressDrawable;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.roomtrac.mobile.R;


/*
*
 * Created by Ramesh.eerla on 24/10/2018.*/


public class CustomProgressDialog {


        Context context;
        ProgressDialog progressDialog;
        ImageView iv;
        public CustomProgressDialog(Context context){
            this.context = context;
        }

        public ProgressDialog showProgressDialog(String titile,String message) {

            if (!((Activity) context).isFinishing()) {

                progressDialog = new ProgressDialog(context, R.style.STProgress);
                progressDialog.setTitle(titile);
                progressDialog.setMessage(message);

               // progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setProgressDrawable(new CircularProgressDrawable(context));
                progressDialog.setIndeterminate(true);
                progressDialog.setCancelable(true);
                if (!progressDialog.isShowing())
                    progressDialog.show();

                progressDialog.setCanceledOnTouchOutside(false);
            }
            return progressDialog;
        }
public void dismiss(){
    if(progressDialog!=null&&progressDialog.isShowing())
    progressDialog.dismiss();
}
}
