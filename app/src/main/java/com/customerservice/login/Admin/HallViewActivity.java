package com.customerservice.login.Admin;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.customerservice.login.Adapters.EventsAdapter;
import com.customerservice.login.Adapters.HallAdapter;
import com.customerservice.login.ClassFiles.Events;
import com.customerservice.login.ClassFiles.Hall;
import com.customerservice.login.R;
import com.customerservice.login.Utility.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HallViewActivity extends AppCompatActivity {

    ListView hallview;
    List<Hall>hallList = new ArrayList<>();
    HallAdapter hallAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HallViewActivity.this,Formhall.class);
                startActivity(intent);

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        hallview=(ListView)findViewById(R.id.hallview);
        hallAdapter= new HallAdapter(HallViewActivity.this,hallList);
        hallview.setAdapter(hallAdapter);


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.READ_Hall, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i=0;i<array.length();i++)
                    {
                        JSONObject object= array.getJSONObject(i);

                        Hall item = new Hall();
                        item.setHall_title(object.getString("hall_title"));
                        item.setHall_capacity(object.getString("hall_capacity"));
                        hallList.add(item);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                finally {
                    hallAdapter.notifyDataSetChanged();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue queue = Volley.newRequestQueue(HallViewActivity.this);
        queue.add(stringRequest);
    }

}
