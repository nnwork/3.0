package com.customerservice.login.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.customerservice.login.ClassFiles.Events;
import com.customerservice.login.R;

import java.util.List;

public class OwnerEventAdapter extends BaseAdapter {

    Activity activity;
    List<Events>ownerEventList;

    public OwnerEventAdapter(Activity activity, List<Events> ownerEventList) {

        this.activity=activity;
        this.ownerEventList=ownerEventList;
    }

    @Override
    public int getCount() {
        return ownerEventList.size();
    }

    @Override
    public Object getItem(int position) {
        return ownerEventList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=activity.getLayoutInflater();
        View view=inflater.inflate(R.layout.singleownerevent,null);

        TextView owner_event_cat_id=(TextView)view.findViewById(R.id.owner_event_cat_id);
        TextView owner_event_title=(TextView)view.findViewById(R.id.owner_event_title);
        TextView owner_event_desc=(TextView)view.findViewById(R.id.owner_event_desc);
        TextView owner_event_venue=(TextView)view.findViewById(R.id.owner_event_venue);
        TextView owner_event_address=(TextView)view.findViewById(R.id.owner_event_address);
        TextView owner_event_landmark=(TextView)view.findViewById(R.id.owner_event_landmark);
        TextView owner_event_pincode=(TextView)view.findViewById(R.id.owner_event_pincode);
        TextView owner_event_speciality=(TextView)view.findViewById(R.id.owner_event_speciality);



        Events ltem=ownerEventList.get(position);
        owner_event_cat_id.setText(ltem.getCat_id());
        owner_event_title.setText(ltem.getEvent_title());
        owner_event_desc.setText(ltem.getEvent_desc());
        owner_event_venue.setText(ltem.getEvent_venue());
        owner_event_address.setText(ltem.getEvent_address());
        owner_event_landmark.setText(ltem.getEvent_landmark());
        owner_event_pincode.setText(ltem.getPincode());
        owner_event_speciality.setText(ltem.getEvent_speciality());
        return view;
    }
}
