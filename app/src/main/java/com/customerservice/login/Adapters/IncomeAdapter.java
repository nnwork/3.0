package com.customerservice.login.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.customerservice.login.ClassFiles.Building;
import com.customerservice.login.ClassFiles.Maintenance;
import com.customerservice.login.R;

import java.util.List;

public class IncomeAdapter extends RecyclerView.Adapter<IncomeAdapter.IncomeViewHolder>{

    List<Maintenance> maintenanceList;

    public IncomeAdapter(List<Maintenance> maintenanceList) {
        this.maintenanceList = maintenanceList;
    }

    @Override
    public IncomeViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.singleincomedesign, viewGroup, false);
        return new IncomeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder( IncomeViewHolder incomeViewHolder, int i) {
                Maintenance maintenance = maintenanceList.get(i);
                incomeViewHolder.owner_name.setText(maintenance.getUser_name());
                incomeViewHolder.owner_maintenance_amount.setText(maintenance.getAmount());
                incomeViewHolder.owner_maintenance_status.setText(maintenance.getStatus());
                incomeViewHolder.owner_maintenance_pay_date.setText(maintenance.getPay_date());
    }

    @Override
    public int getItemCount() {
        return maintenanceList.size();
    }

    public class IncomeViewHolder extends RecyclerView.ViewHolder {

        TextView owner_name,owner_maintenance_amount,owner_maintenance_status,owner_maintenance_pay_date;

         public IncomeViewHolder(@NonNull View itemView) {
            super(itemView);
             owner_name = (TextView)itemView.findViewById(R.id.owner_name);
             owner_maintenance_amount = (TextView)itemView.findViewById(R.id.owner_maintenance_amount);
             owner_maintenance_status = (TextView)itemView.findViewById(R.id.owner_maintenance_status);
             owner_maintenance_pay_date = (TextView)itemView.findViewById(R.id.owner_maintenance_pay_date);
        }
    }



}
