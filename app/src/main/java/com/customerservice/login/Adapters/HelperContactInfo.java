package com.customerservice.login.Adapters;

import android.app.Activity;
import android.graphics.Color;
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

import java.util.List;

public class HelperContactInfo extends BaseAdapter {
    Activity activity;
    List<UserContactinfo> userContactinfo;

    public HelperContactInfo(Activity activity, List<UserContactinfo> userContactinfo) {
        this.activity = activity;
        this.userContactinfo = userContactinfo;
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
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.singlehelperusercontactdesign,null);

       TextView txtusername = view.findViewById(R.id.txtusername);
        TextView txtcontact = view.findViewById(R.id.txtcontact);
        ImageView user_contact=view.findViewById(R.id.user_contact);


        UserContactinfo item = userContactinfo.get(position);
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
                .buildRound(firstLetter, color); // radius in px

        user_contact.setImageDrawable(drawable);
        return view;
    }
}
