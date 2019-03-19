package com.customerservice.login.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.customerservice.login.ClassFiles.Hall;
import com.customerservice.login.ClassFiles.HallBooking;
import com.customerservice.login.R;

import java.util.List;

public class AdminHallBookingViewAdapter extends BaseAdapter {
    Activity activity;
    List<HallBooking> hallList;

    public AdminHallBookingViewAdapter(Activity activity, List<HallBooking> hallList) {
        this.activity = activity;
        this.hallList = hallList;
    }

    @Override
    public int getCount() {
        return hallList.size();
    }

    @Override
    public Object getItem(int position) {
        return hallList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.singleadminhallbookingviewactivity,null);

        TextView txtsubject = view.findViewById(R.id.txtsubject);
        TextView txtbookingdatetime = view.findViewById(R.id.txtbookingdatetime);


        HallBooking hall = hallList.get(position);

        txtsubject.setText(hall.getHall_booking_subject());
        txtbookingdatetime.setText(hall.getHall_booking_date_time());


        return view;
    }
}
