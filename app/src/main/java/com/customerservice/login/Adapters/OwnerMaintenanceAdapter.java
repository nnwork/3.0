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



public class OwnerMaintenanceAdapter extends BaseAdapter {
    Activity activity;
    List<Maintenance>ownerMaintenanceList;

    public OwnerMaintenanceAdapter(Activity activity,List<Maintenance>ownerMaintenanceList)
    {
        this.activity=activity;
        this.ownerMaintenanceList=ownerMaintenanceList;
    }

    @Override
    public int getCount() {
        return ownerMaintenanceList.size();
    }

    @Override
    public Object getItem(int position) {
        return ownerMaintenanceList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=activity.getLayoutInflater();
        View view=inflater.inflate(R.layout.singleownermaintenancedesign,null);

        TextView owner_maintenance_status=(TextView)view.findViewById(R.id.owner_maintenance_status);

        Maintenance item=ownerMaintenanceList.get(position);
        owner_maintenance_status.setText(item.getStatus());

        return view;
    }
}
