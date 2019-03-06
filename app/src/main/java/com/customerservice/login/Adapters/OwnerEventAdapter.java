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

        TextView owner_event_cat_id= view.findViewById(R.id.owner_event_cat_id);
        TextView owner_event_title= view.findViewById(R.id.owner_event_title);
        TextView owner_event_venue= view.findViewById(R.id.owner_event_venue);

        Events ltem=ownerEventList.get(position);
        owner_event_cat_id.setText(ltem.getCat_id());
        owner_event_title.setText(ltem.getEvent_title());
        owner_event_venue.setText(ltem.getEvent_venue());
        return view;
    }
}
