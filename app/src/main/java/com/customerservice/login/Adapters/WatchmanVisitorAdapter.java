package com.customerservice.login.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.customerservice.login.ClassFiles.watchmanvisitor;
import com.customerservice.login.R;

import java.util.List;



public class WatchmanVisitorAdapter extends BaseAdapter {

    Activity activity;
    List<watchmanvisitor>watchmanvisitorList;

    public WatchmanVisitorAdapter(Activity activity, List<watchmanvisitor> watchmanvisitorList) {
        this.activity=activity;
        this.watchmanvisitorList=watchmanvisitorList;
    }

    @Override
    public int getCount() {
        return watchmanvisitorList.size();
    }

    @Override
    public Object getItem(int position) {
        return watchmanvisitorList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=activity.getLayoutInflater();
        View view=inflater.inflate(R.layout.watchmanvisitordesign,null);
        TextView textViewName=(TextView)view.findViewById(R.id.wachman_visiters_name);
        TextView textViewContact=(TextView)view.findViewById(R.id.wachman_visiters_contect);

        watchmanvisitor item=watchmanvisitorList.get(position);
        textViewName.setText(item.getVisitors_name());
        textViewContact.setText(item.getVisitors_contect());
        return view;
    }
}
