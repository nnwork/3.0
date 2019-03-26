package com.customerservice.login.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.customerservice.login.ClassFiles.Visitor;
import com.customerservice.login.R;

import java.util.List;

public class AdminVisitorAdapter extends BaseAdapter {
    Activity activity;
    List<Visitor>visitorList;

    public AdminVisitorAdapter(Activity activity, List<Visitor> visitorList) {
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
        View view=inflater.inflate(R.layout.singleadminvisitordesign,null);

        TextView admin_visitors_id=(TextView)view.findViewById(R.id.admin_visitors_id);
        TextView admin_visitors_name=(TextView)view.findViewById(R.id.admin_visitors_name);
        TextView admin_visitors_contect=(TextView)view.findViewById(R.id.admin_visitors_contect);
        TextView admin_visitors_flat_number=(TextView)view.findViewById(R.id.admin_visitors_flat_number);

        TextView admin_visitors_photo=(TextView)view.findViewById(R.id.admin_visitors_photo);
        TextView admin_visitors_watchman_name=(TextView)view.findViewById(R.id.admin_visitors_watchman_name);
        TextView admin_visitors_v_date=(TextView)view.findViewById(R.id.admin_visitors_v_date);
        TextView admin_visitors_v_time=(TextView)view.findViewById(R.id.admin_visitors_v_time);

        TextView admin_visitors_flat_approve=(TextView)view.findViewById(R.id.admin_visitors_flat_approve);
        TextView admin_visitors_app_date_time=(TextView)view.findViewById(R.id.admin_visitors_app_date_time);
        TextView admin_visitors_exit_date_time=(TextView)view.findViewById(R.id.admin_visitors_exit_date_time);

        Visitor item=visitorList.get(position);
        admin_visitors_name.setText(item.getVisitors_name());
        admin_visitors_contect.setText(item.getVisitors_contect());
        admin_visitors_watchman_name.setText(item.getVisitors_watchman_id());
        admin_visitors_v_date.setText(item.getVisitors_v_date());
        admin_visitors_v_time.setText(item.getVisitors_v_time());
        admin_visitors_flat_approve.setText(item.getVisitors_flat_approve());
        admin_visitors_app_date_time.setText(item.getVisitors_app_date_time());
        admin_visitors_exit_date_time.setText(item.getVisitors_exit_date_time());

        admin_visitors_flat_number.setText(item.getFlatNumber());
        admin_visitors_watchman_name.setText(item.getUser_name());

        return view;
    }
}
