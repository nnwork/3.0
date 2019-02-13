package com.customerservice.login.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.customerservice.login.ClassFiles.HelperCategory;
import com.customerservice.login.R;

import java.util.List;

public class HelperCategoryAdapter extends BaseAdapter {
    Activity activity;
    List<HelperCategory>helperCategoryList;

    public HelperCategoryAdapter(Activity activity, List<HelperCategory> helperCategoryList) {
        this.activity = activity;
        this.helperCategoryList = helperCategoryList;
    }

    @Override
    public int getCount() {
        return helperCategoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return helperCategoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.singleownerhelperviewdesign,null);

        TextView txthelpername = (TextView)view.findViewById(R.id.txthelpername);

        HelperCategory item = helperCategoryList.get(position);

        txthelpername.setText(item.getHcat_name());
        return view;
    }
}
