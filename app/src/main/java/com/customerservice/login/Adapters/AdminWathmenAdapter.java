package com.customerservice.login.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.customerservice.login.ClassFiles.User;
import com.customerservice.login.R;

import java.util.List;

public class AdminWathmenAdapter extends BaseAdapter{
    Activity activity;
    List<User>userList;

    public AdminWathmenAdapter(Activity activity, List<User> userList) {
        this.activity = activity;
        this.userList = userList;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=activity.getLayoutInflater();
        View view=inflater.inflate(R.layout.singleadminwatchmendesign,null);

        User item = userList.get(position);

        TextView admin_watchmen_user_name = (TextView)view.findViewById(R.id.admin_watchmen_user_name);
        admin_watchmen_user_name.setText(item.getUser_name());
        return view;
    }
}
