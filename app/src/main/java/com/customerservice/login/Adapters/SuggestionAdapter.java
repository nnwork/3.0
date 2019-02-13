package com.customerservice.login.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.customerservice.login.ClassFiles.MonthAmount;
import com.customerservice.login.ClassFiles.Suggestion;
import com.customerservice.login.R;

import java.util.List;

public class SuggestionAdapter extends BaseAdapter {

    Activity activity;
    List<Suggestion>suggestionList;

    public SuggestionAdapter(Activity activity, List<Suggestion> suggestionList) {
        this.activity = activity;
        this.suggestionList = suggestionList;
    }


    @Override
    public int getCount() {
        return suggestionList.size();
    }

    @Override
    public Object getItem(int position) {
        return suggestionList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = activity.getLayoutInflater();
        View view =  inflater.inflate(R.layout.singlesuggestiondesign,null);

        TextView txtusername = (TextView)view.findViewById(R.id.txtusername);
        TextView txtsuggestion = (TextView)view.findViewById(R.id.txtsuggestion);

       Suggestion item = suggestionList.get(position);

        txtusername.setText(item.getUser_name());
        //txtsuggestion.setText(item.getSuggestion_text());
        return view;
    }
}
