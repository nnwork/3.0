package com.customerservice.login.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.customerservice.login.Admin.ExpencesActivity;
import com.customerservice.login.ClassFiles.Expences;
import com.customerservice.login.R;

import java.util.List;

public class ExpencesAdapter extends BaseAdapter {
    List<Expences> expencesAdapterList;
    Activity activity;

    public ExpencesAdapter(Activity activity,List<Expences> expencesAdapterList) {
        this.activity = activity;
        this.expencesAdapterList = expencesAdapterList;
    }

    @Override
    public int getCount() {
        return expencesAdapterList.size();
    }

    @Override
    public Object getItem(int position) {
        return expencesAdapterList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater= activity.getLayoutInflater();
        View  view= layoutInflater.inflate(R.layout.singleexpencesdesign,null);
        TextView txtexpencetitle = (TextView)view.findViewById(R.id.txtexpencetitle);
        TextView txtexpenceamount = (TextView)view.findViewById(R.id.txtexpenceamount);
        TextView txtexpencesdatetime = (TextView)view.findViewById(R.id.txtexpencesdatetime);

        Expences expences = expencesAdapterList.get(position);

        txtexpencetitle.setText(expences.getExpense_title());
        txtexpenceamount.setText(expences.getExpense_amt());
        txtexpencesdatetime.setText(expences.getExpense_date_time());


        return view;
    }
}
