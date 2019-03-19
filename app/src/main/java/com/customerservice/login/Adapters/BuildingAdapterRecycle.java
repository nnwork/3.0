package com.customerservice.login.Adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.customerservice.login.ClassFiles.Building;
import com.customerservice.login.R;

import java.util.List;

public class BuildingAdapterRecycle extends RecyclerView.Adapter<BuildingAdapterRecycle.MyViewHolder> {

    List<Building> buildingList;

    public BuildingAdapterRecycle(List<Building> buildingList)
    {
        this.buildingList=buildingList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.singlebuildingdesign, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i)
    {
        Building item=buildingList.get(i);
        myViewHolder.txtbuildingname.setText(item.getBuildingName());
    }

    @Override
    public int getItemCount() {
        return buildingList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtbuildingname;


        public MyViewHolder(View view) {
            super(view);
            txtbuildingname = (TextView) view.findViewById(R.id.txtbuildingname);
        }
    }





}
