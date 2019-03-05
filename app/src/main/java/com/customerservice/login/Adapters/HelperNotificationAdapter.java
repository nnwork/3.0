package com.customerservice.login.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.customerservice.login.ClassFiles.Notification;
import com.customerservice.login.R;

import java.util.List;

public class HelperNotificationAdapter extends BaseAdapter {

    Activity activity;
    List<Notification>notificationList;

    public HelperNotificationAdapter(Activity activity, List<Notification> notificationList) {
        this.activity = activity;
        this.notificationList = notificationList;
    }

    @Override
    public int getCount() {
        return notificationList.size();
    }

    @Override
    public Object getItem(int position) {
        return notificationList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=activity.getLayoutInflater();
        View view=inflater.inflate(R.layout.singlenotificationdesign,null);

        TextView notification_id=(TextView)view.findViewById(R.id.notification_id);
        TextView notification_user_id=(TextView)view.findViewById(R.id.notification_user_id);
        TextView notification_title=(TextView)view.findViewById(R.id.notification_title);
        TextView notification_description=(TextView)view.findViewById(R.id.notification_description);
        TextView description_date_time=(TextView)view.findViewById(R.id.description_date_time);

        Notification item=notificationList.get(position);

        notification_id.setText(item.getNotification_id());
        notification_user_id.setText(item.getUser_id());
        notification_title.setText(item.getNotification_title());
        notification_description.setText(item.getNotification_description());
        description_date_time.setText(item.getDescription_date_time());
        return view;
    }
}
