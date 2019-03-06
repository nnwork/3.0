package com.customerservice.login.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.customerservice.login.ClassFiles.Visitor;
import com.customerservice.login.R;

import java.util.List;

public class OwnerVisitorAdapter extends BaseAdapter {

    Activity activity;
    List<Visitor>visitorList;

    public OwnerVisitorAdapter(Activity activity, List<Visitor> visitorList) {
        this.activity = activity;
        this.visitorList = visitorList;
    }

    @Override
    public int getCount() {
        return visitorList.size();
    }

    @Override
    public Object getItem(int position) {
        return visitorList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=activity.getLayoutInflater();
        View view=inflater.inflate(R.layout.singleownervisitordesign,null);
        TextView textViewName= view.findViewById(R.id.owner_visiters_name);
        TextView textViewContact= view.findViewById(R.id.owner_visiters_contect);


        Visitor item = visitorList.get(position);
        textViewName.setText(item.getVisitors_name());
        textViewContact.setText(item.getVisitors_contect());

        return view;

    }
}
