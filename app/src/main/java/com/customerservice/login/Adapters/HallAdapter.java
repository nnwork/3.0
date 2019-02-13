package com.customerservice.login.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.customerservice.login.ClassFiles.Expences;
import com.customerservice.login.ClassFiles.Hall;
import com.customerservice.login.R;

import java.util.List;

public class HallAdapter extends BaseAdapter {
    Activity activity;
    List<Hall> hallList;

    public HallAdapter(Activity activity, List<Hall> hallList) {
        this.activity = activity;
        this.hallList = hallList;
    }

    @Override
    public int getCount() {
        return hallList.size();
    }

    @Override
    public Object getItem(int position) {
        return hallList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.singlehallviewdesign,null);

        TextView txttitle = (TextView)view.findViewById(R.id.txttitle);
        TextView txtcapacity = (TextView)view.findViewById(R.id.txthallcapacity);


       Hall hall = hallList.get(position);

        txttitle.setText(hall.getHall_title());
        txtcapacity.setText(hall.getHall_capacity());


        return view;
    }
}
