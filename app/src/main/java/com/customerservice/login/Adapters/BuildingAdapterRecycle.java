package com.customerservice.login.Adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.customerservice.login.ClassFiles.Building;
import com.customerservice.login.R;
import com.customerservice.login.Utility.Config;

import java.util.List;

public class BuildingAdapterRecycle extends RecyclerView.Adapter<BuildingAdapterRecycle.MyViewHolder> {

    List<Building> buildingList;
    Activity activity;

    public BuildingAdapterRecycle(List<Building> buildingList,Activity activity)
    {
        this.buildingList=buildingList;
        this.activity=activity;
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
        Glide.with(activity).load(Config.BASE_URL+"building/"+item.getBuildingImage()).into(myViewHolder.txtbuldingimage);
    }

    @Override
    public int getItemCount() {
        return buildingList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtbuildingname;
        public ImageView txtbuldingimage;


        public MyViewHolder(View view) {
            super(view);
            txtbuildingname = (TextView) view.findViewById(R.id.txtbuildingname);
            txtbuldingimage = (ImageView) view.findViewById(R.id.txtbuldingimage);
        }
    }





}
