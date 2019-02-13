package com.customerservice.login.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.customerservice.login.ClassFiles.MonthAmount;
import com.customerservice.login.R;

import org.w3c.dom.Text;

import java.util.List;

public class MonthAmountAdapter  extends BaseAdapter {
    Activity activity;
    List<MonthAmount> monthAmountList;

    public MonthAmountAdapter(Activity activity, List<MonthAmount> monthAmountList) {
        this.activity = activity;
        this.monthAmountList = monthAmountList;
    }

    @Override
    public int getCount() {
        return monthAmountList.size();
    }

    @Override
    public Object getItem(int position) {
        return monthAmountList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view =  inflater.inflate(R.layout.singlemonthamountdesign,null);

        TextView txtmonth = (TextView)view.findViewById(R.id.txtmonth);
        TextView txtmonthamount = (TextView)view.findViewById(R.id.txtmonthamount);

        MonthAmount item = monthAmountList.get(position);

        txtmonth.setText(item.getMonth());
        txtmonthamount.setText(item.getMonth_amount());

        return view;
    }
}
