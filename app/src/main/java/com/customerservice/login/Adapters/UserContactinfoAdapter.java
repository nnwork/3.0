package com.customerservice.login.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;


import com.customerservice.login.ClassFiles.UserContactinfo;
import com.customerservice.login.R;
import com.customerservice.login.Watchman.WatchmanContactActivity;

import java.util.List;

public class UserContactinfoAdapter extends BaseAdapter {
    Activity activity;
    List<UserContactinfo> userContactinfo;

    public UserContactinfoAdapter(Activity activity, List<UserContactinfo> userContactinfo) {
        this.activity = activity;
        this.userContactinfo = userContactinfo;
    }

    @Override
    public int getCount() {
        return userContactinfo.size();
    }

    @Override
    public Object getItem(int position) {
        return userContactinfo.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.singleusercontactinfodesign,null);

        TextView txtusername = (TextView)view.findViewById(R.id.txtusername);
        TextView txtcontact = (TextView)view.findViewById(R.id.txtcontact);


        UserContactinfo item = userContactinfo.get(position);
        txtusername.setText(item.getUser_name());
        txtcontact.setText(item.getUser_contact());


        return view;
    }
}
