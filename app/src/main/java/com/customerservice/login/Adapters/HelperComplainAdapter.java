package com.customerservice.login.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.customerservice.login.ClassFiles.ComplainClassFile;
import com.customerservice.login.R;
import com.customerservice.login.Utility.Config;

import java.util.List;



public class HelperComplainAdapter extends BaseAdapter {

    Activity activity;
    List<ComplainClassFile> complainClassFileList;

    public HelperComplainAdapter(Activity activity, List<ComplainClassFile> complainClassFileList) {

        this.activity=activity;
        this.complainClassFileList = complainClassFileList;
    }

    @Override
    public int getCount() {
        return complainClassFileList.size();
    }

    @Override
    public Object getItem(int position) {
        return complainClassFileList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater=activity.getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.singlehelperdesign,null);

        TextView helper_complain_user_name=view.findViewById(R.id.helper_complain_user_name);
        TextView helper_complain_contact=view.findViewById(R.id.helper_complain_contact);
        TextView helper_complain_status= view.findViewById(R.id.helper_complain_status);
        TextView helper_complain_date_time= view.findViewById(R.id.helper_complain_date_time);
        ImageView helper_complain_image= view.findViewById(R.id.helper_complain_display_img_uri);


        ComplainClassFile item= complainClassFileList.get(position);

helper_complain_user_name.setText(item.getUser_name());
helper_complain_contact.setText(item.getUser_contact());
        helper_complain_status.setText(item.getComplain_status());
        helper_complain_date_time.setText(item.getComplain_date_time());
        Glide.with(activity).load(Config.BASE_URL+"complain/"+item.getComplain_img_uri()).into(helper_complain_image);
        return view;
    }
}
