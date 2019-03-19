package com.customerservice.login.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.customerservice.login.ClassFiles.Building;
import com.customerservice.login.R;

import java.util.List;

public class BuildingAdapter extends BaseAdapter
{

    Activity activity;
    List<Building> buildingList;

    public BuildingAdapter(Activity activity,List<Building> buildingList)
    {
        this.activity=activity;
        this.buildingList=buildingList;
    }


    @Override
    public int getCount() {
        return buildingList.size();
    }

    @Override
    public Object getItem(int position) {
        return buildingList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {



        LayoutInflater inflater=activity.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.singlebuildingdesign,null);

        TextView txtbuildingname= rowView.findViewById(R.id.txtbuildingname);

        Building item=buildingList.get(position);

        txtbuildingname.setText(item.getBuildingName());


        return rowView;
    }
}
