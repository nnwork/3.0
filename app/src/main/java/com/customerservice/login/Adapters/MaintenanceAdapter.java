package com.customerservice.login.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.customerservice.login.ClassFiles.Maintenance;
import com.customerservice.login.R;

import java.util.List;

public class MaintenanceAdapter extends BaseAdapter {
    Activity activity;
    List<Maintenance> maintenanceList;

    public MaintenanceAdapter(Activity activity,List<Maintenance>maintenanceList)
    {
        this.activity=activity;
        this.maintenanceList=maintenanceList;
    }
    @Override
    public int getCount() {
        return maintenanceList.size();
    }

    @Override
    public Object getItem(int position) {
        return maintenanceList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater= activity.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.singlemaintenancedesign,null);

        TextView txtmaintenance=(TextView)view.findViewById(R.id.txtmaintenance);

        Maintenance item = maintenanceList.get(position);

        txtmaintenance.setText(item.getAmount());
        return view;
    }
}
