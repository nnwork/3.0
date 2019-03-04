package com.customerservice.login.FlatOwner;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.customerservice.login.ClassFiles.Events;
import com.customerservice.login.R;
import com.customerservice.login.Utility.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class OwnerEventDetailsActivity extends AppCompatActivity {
    TextView owner_display_event_cat_name,owner_display_event_title,owner_display_event_desc,owner_display_event_venue;
    TextView owner_display_event_address,owner_display_event_landmark,owner_display_pincode,owner_display_event_date;
    TextView owner_display_event_time,owner_display_event_speciality,owner_display_added_datetime;
    Events ownerEventObject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_event_display);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ownerEventObject=(Events)getIntent().getSerializableExtra("EventObject");

        owner_display_event_cat_name=(TextView)findViewById(R.id.owner_display_event_cat_name);
        owner_display_event_title=(TextView)findViewById(R.id.owner_display_event_title);
        owner_display_event_desc=(TextView)findViewById(R.id.owner_display_event_desc);
        owner_display_event_venue=(TextView)findViewById(R.id.owner_display_event_venue);

        owner_display_event_address=(TextView)findViewById(R.id.owner_display_event_address);
        owner_display_event_title=(TextView)findViewById(R.id.owner_display_event_title);
        owner_display_event_landmark=(TextView)findViewById(R.id.owner_display_event_landmark);
        owner_display_pincode=(TextView)findViewById(R.id.owner_display_pincode);
        owner_display_event_date=(TextView)findViewById(R.id.owner_display_event_date);

        owner_display_event_time=(TextView)findViewById(R.id.owner_display_event_time);
        owner_display_event_speciality=(TextView)findViewById(R.id.owner_display_event_speciality);
        owner_display_added_datetime=(TextView)findViewById(R.id.owner_display_added_datetime);


        StringRequest request=new StringRequest(Request.Method.POST, Config.READ_UserEvents, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array=new JSONArray(response);
                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject object=array.getJSONObject(i);
                        String cat_name=object.getString("cat_name");
                        owner_display_event_cat_name.setText(cat_name);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("event_id",ownerEventObject.getEvent_id().toString());
                return params;
            }
        };
        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(request);

         toolbar.setTitle(ownerEventObject.getEvent_title());

        owner_display_event_title.setText(ownerEventObject.getEvent_title());
        owner_display_event_desc.setText(ownerEventObject.getEvent_desc());
        owner_display_event_venue.setText(ownerEventObject.getEvent_venue());
    }

}
