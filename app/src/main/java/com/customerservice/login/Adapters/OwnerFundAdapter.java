package com.customerservice.login.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.customerservice.login.ClassFiles.OwnerFund;
import com.customerservice.login.R;

import java.util.List;



public class OwnerFundAdapter extends BaseAdapter {

    Activity activity;
    List<OwnerFund>list;

    public OwnerFundAdapter(Activity activity,List<OwnerFund>list)
    {
        this.activity=activity;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=activity.getLayoutInflater();
        View view=inflater.inflate(R.layout.singleownerfunddesign,null);

        TextView owner_fund_title=(TextView)view.findViewById(R.id.owner_fund_title);

        OwnerFund item=list.get(position);

        owner_fund_title.setText(item.getFund_title());

        return view;
    }
}
