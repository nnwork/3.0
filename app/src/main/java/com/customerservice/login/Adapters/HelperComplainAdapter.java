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
        TextView textView=(TextView)view.findViewById(R.id.helper_complain_problem);

        ComplainClassFile item= complainClassFileList.get(position);
        textView.setText(item.getComplain_problem());
        return view;
    }
}
