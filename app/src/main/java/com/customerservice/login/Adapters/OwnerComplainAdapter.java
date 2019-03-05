package com.customerservice.login.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.customerservice.login.ClassFiles.ComplainClassFile;
import com.customerservice.login.R;

import java.util.List;

public class OwnerComplainAdapter extends BaseAdapter {
    Activity activity;
    List<ComplainClassFile>complainClassFileList;

    public OwnerComplainAdapter(Activity activity, List<ComplainClassFile> complainClassFileList) {
    this.activity=activity;
    this.complainClassFileList=complainClassFileList;
    }

    @Override
    public int getCount() {
        return complainClassFileList.size();
    }

    @Override
    public Object getItem(int position) {
        return complainClassFileList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=activity.getLayoutInflater();
        View view=inflater.inflate(R.layout.singleownercomplaindesign,null);

//        TextView owner_complain_problem=(TextView)view.findViewById(R.id.owner_complain_problem);
        TextView owner_complain_hcat_id=(TextView)view.findViewById(R.id.owner_complain_hcat_id);
        TextView owner_complain_status=(TextView)view.findViewById(R.id.owner_complain_status);
        TextView owner_complain_date_time=(TextView)view.findViewById(R.id.owner_complain_date_time);

        ComplainClassFile item= complainClassFileList.get(position);
        owner_complain_hcat_id.setText(item.getComplain_hcat_id());
        owner_complain_status.setText(item.getComplain_status());
        owner_complain_date_time.setText(item.getComplain_date_time());
        return view;
    }
}
