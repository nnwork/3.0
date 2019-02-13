package com.customerservice.login.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.customerservice.login.ClassFiles.OwnerSuggestions;
import com.customerservice.login.R;

import java.util.List;



public class OwnerSuggestionsAdapter extends BaseAdapter {
    Activity activity;
    List<OwnerSuggestions>ownerSuggestionsList;

    public OwnerSuggestionsAdapter(Activity activity, List<OwnerSuggestions> ownerSuggestionsList) {
        this.activity = activity;
        this.ownerSuggestionsList = ownerSuggestionsList;
    }
    @Override
    public int getCount() {
        return ownerSuggestionsList.size();
    }

    @Override
    public Object getItem(int position) {
        return ownerSuggestionsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=activity.getLayoutInflater();
        View view=inflater.inflate(R.layout.singleownersuggestionsdesign,null);

        TextView owner_suggestion_field=(TextView)view.findViewById(R.id.owner_suggestion_field);

        OwnerSuggestions item=ownerSuggestionsList.get(position);

        owner_suggestion_field.setText(item.getSuggestion_field());
        return view;
    }
}
