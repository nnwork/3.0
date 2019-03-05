package com.customerservice.login.Helper;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.customerservice.login.Adapters.HelperNotificationAdapter;
import com.customerservice.login.ClassFiles.Notification;
import com.customerservice.login.R;
import com.customerservice.login.Utility.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HelperNotificationActivity extends AppCompatActivity {
    ListView listView;
    HelperNotificationAdapter adapter;
    List<Notification> notificationList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper_notification);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView=(ListView)findViewById(R.id.notification_list_view);
        adapter=new HelperNotificationAdapter(HelperNotificationActivity.this,notificationList);
        listView.setAdapter(adapter);

        StringRequest request=new StringRequest(Request.Method.POST, Config.list_tbl_notification, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array=new JSONArray(response);
                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject object=array.getJSONObject(i);

                        Notification classfie=new Notification();
                        classfie.setNotification_id(object.getString("notification_id"));

                        classfie.setUser_id(object.getString("user_id"));
                        classfie.setNotification_title(object.getString("notification_title"));
                        classfie.setNotification_description(object.getString("notification_description"));
                        classfie.setDescription_date_time(object.getString("description_date_time"));

                        notificationList.add(classfie);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                finally {
                    adapter.notifyDataSetChanged();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(request);
    }

}
