package com.customerservice.login.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.customerservice.login.ClassFiles.Funds;
import com.customerservice.login.R;

import java.util.List;

public class FundsAdapter extends BaseAdapter {

    Activity activity;
    List<Funds>fundsList;

    public FundsAdapter(Activity activity, List<Funds> fundsList) {
        this.activity = activity;
        this.fundsList = fundsList;
    }

    @Override
    public int getCount() {
        return fundsList.size();
    }

    @Override
    public Object getItem(int position) {
        return fundsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater =activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.singlefundsdesign,null);

        TextView txtfunds = (TextView)view.findViewById(R.id.txtfunds);
        Funds item = fundsList.get(position);

        txtfunds.setText(item.getFund_title());
        return view;
    }
}
