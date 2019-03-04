package com.customerservice.login.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.customerservice.login.ClassFiles.Hall;
import com.customerservice.login.R;

import java.util.List;



public class OwnerHallAdapter extends BaseAdapter {

    Activity activity;
    List<Hall>ownerHallList;

    public OwnerHallAdapter(Activity activity, List<Hall> ownerHallList) {

        this.activity=activity;
        this.ownerHallList= ownerHallList;
    }

    @Override
    public int getCount() {
        return ownerHallList.size();
    }

    @Override
    public Object getItem(int position) {
        return ownerHallList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=activity.getLayoutInflater();
        View view=inflater.inflate(R.layout.singleownerhalldesign,null);

      //  TextView owner_hall_id=(TextView)view.findViewById(R.id.owner_hall_id);
        TextView owner_hall_title=(TextView)view.findViewById((R.id.owner_hall_title));
        TextView owner_hall_capacity=(TextView)view.findViewById(R.id.owner_hall_capacity);

        Hall item=ownerHallList.get(position);

      //  owner_hall_id.setText(item.getHall_id());
        owner_hall_title.setText(item.getHall_title());
        owner_hall_capacity.setText(item.getHall_capacity());
        return view;
    }
}
