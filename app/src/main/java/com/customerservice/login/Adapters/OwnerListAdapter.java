package com.customerservice.login.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.customerservice.login.ClassFiles.Hall;
import com.customerservice.login.ClassFiles.User;
import com.customerservice.login.R;

import java.util.List;

public class OwnerListAdapter extends BaseAdapter {

    Activity activity;
    List<User> userList;

    public OwnerListAdapter(Activity activity, List<User> userList) {
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

        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.singleownerlistdesign,null);

        TextView owner_name = (TextView)view.findViewById(R.id.owner_name);
        TextView owner_contact = (TextView)view.findViewById(R.id.owner_contact);
        TextView owner_status = (TextView)view.findViewById(R.id.owner_status);

        User itemUser = userList.get(position);

        owner_name.setText(itemUser.getUser_name());
        owner_contact.setText(itemUser.getUser_contact());
        owner_status.setText(itemUser.getUser_is_available());
        return view;
    }
}
