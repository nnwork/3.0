package com.customerservice.login.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.customerservice.login.ClassFiles.Events;
import com.customerservice.login.R;

import java.util.ArrayList;
import java.util.List;

public class EventsAdapter extends BaseAdapter {
    Activity activity;
    List<Events>eventsList;

    public EventsAdapter(Activity activity,List<Events>eventsList)
    {
        this.activity=activity;
        this.eventsList=eventsList;
    }

    @Override
    public int getCount() {
        return eventsList.size();
    }

    @Override
    public Object getItem(int position) {
        return eventsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.singleeventdesign,null);

        TextView textView = (TextView)view.findViewById(R.id.txtevent);

        Events item = eventsList.get(position);
        textView.setText(item.getEvent_title());

        return view;
    }
}
