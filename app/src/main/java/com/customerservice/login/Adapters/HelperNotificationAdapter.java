package com.customerservice.login.Adapters;

import android.app.Activity;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
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

        Notification item=notificationList.get(position);
        TextView notification_user_name= view.findViewById(R.id.notification_user_name);
       // TextView notification_title= view.findViewById(R.id.notification_title);
        TextView notification_description= view.findViewById(R.id.notification_description);
        TextView description_date_time= view.findViewById(R.id.description_date_time);
       ImageView uname=view.findViewById(R.id.uname);


        notification_user_name.setText(item.getUser_name());

        //  notification_title.setText(item.getNotification_title());
        notification_description.setText(item.getNotification_description());
        description_date_time.setText(item.getDescription_date_time());

        String username = notification_user_name.getText().toString();

        //get first letter of each String itemString pos = String.valueOf(userContactinfo.get(position));
        String firstLetter = String.valueOf(username.charAt(0));

        ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT
        // generate random color
        int color = generator.getColor(getItem(position));
        //int color = generator.getRandomColor();

        TextDrawable drawable = TextDrawable.builder()
                .beginConfig()
                .width(60)  // width in px
                .height(60)
                .withBorder(4) // height in px
                .endConfig()
                .buildRoundRect(firstLetter, color,10); // radius in px
        uname.setImageDrawable(drawable);




        return view;
    }
}
