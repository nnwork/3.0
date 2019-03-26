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

public class AdminComplainAdapter extends BaseAdapter {
    Activity activity;
    List<ComplainClassFile>complainClassFileList;

    public AdminComplainAdapter(Activity activity, List<ComplainClassFile> complainClassFileList) {
        this.activity = activity;
        this.complainClassFileList = complainClassFileList;
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
        View view=inflater.inflate(R.layout.singleadmincomplainfile,null);


        TextView admin_complain_hcat_id=(TextView)view.findViewById(R.id.admin_complain_hcat_id);
        TextView admin_complain_problem=(TextView)view.findViewById(R.id.admin_complain_problem);

//        TextView admin_complain_img_uri=(TextView)view.findViewById(R.id.admin_complain_img_uri);
//        TextView admin_complain_status=(TextView)view.findViewById(R.id.admin_complain_status);
//        TextView admin_complain_date_time=(TextView)view.findViewById(R.id.admin_complain_date_time);

        ComplainClassFile item= complainClassFileList.get(position);
        admin_complain_hcat_id.setText(item.getComplain_hcat_id());
        admin_complain_problem.setText(item.getComplain_problem());

//        admin_complain_img_uri.setText(item.getComplain_hcat_id());
//        admin_complain_status.setText(item.getComplain_status());
//        admin_complain_date_time.setText(item.getComplain_date_time());
        return view;
    }
}