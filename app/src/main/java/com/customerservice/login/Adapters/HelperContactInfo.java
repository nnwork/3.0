package com.customerservice.login.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.customerservice.login.ClassFiles.UserContactinfo;
import com.customerservice.login.R;

import java.security.AccessController;
import java.util.List;

public class HelperContactInfo extends BaseAdapter {
    Activity activity;
    List<UserContactinfo> userContactinfo;
    Context context;

    public HelperContactInfo(Activity activity, List<UserContactinfo> userContactinfo) {
        this.activity = activity;
        this.userContactinfo = userContactinfo;
    }


    customCallButton customcall;

    public interface customCallButton {
        public void onButtonClickListner(int position, String contact);
    }

    public void setCustomButtonCall(customCallButton listener) {
        this.customcall = listener;
    }

    @Override
    public int getCount() {
        return userContactinfo.size();
    }

    @Override
    public Object getItem(int position) {
        return userContactinfo.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = activity.getLayoutInflater();
        final View view = layoutInflater.inflate(R.layout.singlehelperusercontactdesign,null);

       TextView txtusername = view.findViewById(R.id.txtusername);
        final TextView txtcontact = view.findViewById(R.id.txtcontact);
        ImageView user_contact=view.findViewById(R.id.user_contact);
        ImageView call=view.findViewById(R.id.call);


        final UserContactinfo item = userContactinfo.get(position);
        txtusername.setText(item.getUser_name());
        txtcontact.setText(item.getUser_contact());


       String username = txtusername.getText().toString();

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
                .buildRound(firstLetter, color); // radius in px
        user_contact.setImageDrawable(drawable);


        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customcall != null) {
                    customcall.onButtonClickListner(position,item.getUser_contact());
                }
            }
        });
        return view;
    }
}
