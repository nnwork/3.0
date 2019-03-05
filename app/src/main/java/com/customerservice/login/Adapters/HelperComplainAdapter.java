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



public class HelperComplainAdapter extends BaseAdapter {

    Activity activity;
    List<ComplainClassFile> complainClassFileList;

    public HelperComplainAdapter(Activity activity, List<ComplainClassFile> complainClassFileList) {

        this.activity=activity;
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

        LayoutInflater layoutInflater=activity.getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.singlehelperdesign,null);


        TextView helper_complain_hcat_id=(TextView)view.findViewById(R.id.helper_complain_hcat_id);
        TextView helper_complain_problem=(TextView)view.findViewById(R.id.helper_complain_problem);
        TextView helper_complain_img_uri=(TextView)view.findViewById(R.id.helper_complain_img_uri);

        TextView helper_complain_status=(TextView)view.findViewById(R.id.helper_complain_status);
        TextView helper_complain_v_date=(TextView)view.findViewById(R.id.helper_complain_v_date);
        TextView helper_complain_v_time=(TextView)view.findViewById(R.id.helper_complain_v_time);
        TextView helper_complain_date_time=(TextView)view.findViewById(R.id.helper_complain_date_time);



        ComplainClassFile item= complainClassFileList.get(position);

        helper_complain_problem.setText(item.getComplain_problem());
        helper_complain_img_uri.setText(item.getComplain_img_uri());
        helper_complain_status.setText(item.getComplain_status());
        helper_complain_v_date.setText(item.getComplain_v_date());
        helper_complain_v_time.setText(item.getComplain_v_time());
        helper_complain_date_time.setText(item.getComplain_date_time());
        return view;
    }
}
