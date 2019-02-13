package com.customerservice.login.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.customerservice.login.ClassFiles.Flat;
import com.customerservice.login.R;

import java.util.List;

public class FlatAdapter extends BaseAdapter {

    Activity activity;
    List<Flat> flatList;

    public FlatAdapter(Activity activity,List<Flat>flatlist)
    {
            this.activity=activity;
            this.flatList=flatlist;
    }
    @Override
    public int getCount() {
        return flatList.size();
    }

    @Override
    public Object getItem(int position) {
        return flatList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.singleflatdesign,null);

        TextView textView= (TextView)view.findViewById(R.id.txtflatnumber);
        TextView txtbuildingname= (TextView)view.findViewById(R.id.txtbuildingname);

        Flat item=flatList.get(position);
        txtbuildingname.setText(item.getBuldingName());
        textView.setText(item.getFlatNumber());
        return view;
    }
}
