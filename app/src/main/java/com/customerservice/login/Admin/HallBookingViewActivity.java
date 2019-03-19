package com.customerservice.login.Admin;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.customerservice.login.Adapters.AdminHallBookingViewAdapter;
import com.customerservice.login.ClassFiles.Hall;
import com.customerservice.login.ClassFiles.HallBooking;
import com.customerservice.login.R;
import com.customerservice.login.Utility.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HallBookingViewActivity extends AppCompatActivity {
    ListView hallview;
    List<HallBooking> hallList = new ArrayList<HallBooking>();
    AdminHallBookingViewAdapter hallAdapter;


    String hall_id="all";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_booking_view);
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
                Intent intent = new Intent(HallBookingViewActivity.this, Formhall.class);
                startActivity(intent);

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        hallview = (ListView) findViewById(R.id.hallview);
        hallAdapter= new AdminHallBookingViewAdapter(HallBookingViewActivity.this,hallList);
        hallview.setAdapter(hallAdapter);

        if( getIntent().hasExtra("hall_id"))
        {
            hall_id = getIntent().getStringExtra("hall_id");
        }
        else
        {
            hall_id="all";
        }

        hallview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HallBooking hallBooking = hallList.get(position);
                Intent intent = new Intent(HallBookingViewActivity.this,HallBookingDetailActivity.class);
                intent.putExtra("hallbookingid",hallBooking.getHall_booking_id());
                startActivity(intent);
            }
        });

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.READ_Hall_Booking, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i=0;i<array.length();i++)
                    {
                        JSONObject object= array.getJSONObject(i);

                        HallBooking item = new HallBooking();

                        item.setHall_booking_id(object.getString("hall_booking_id"));
                        item.setHall_booking_b_date(object.getString("hall_booking_b_date"));
                        item.setHall_booking_b_time(object.getString("hall_booking_b_time"));
                        item.setHall_booking_date_time(object.getString("hall_booking_date_time"));
                        item.setHall_booking_amt(object.getString("hall_booking_amt"));
                        item.setHall_id(object.getString("hall_id"));
                        item.setHall_booking_subject(object.getString("hall_booking_subject"));
                        item.setHall_booking_user_id(object.getString("hall_booking_user_id"));
                        item.setHall_booking_status(object.getString("hall_booking_status"));
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
                Toast.makeText(HallBookingViewActivity.this, "Server Message", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params = new HashMap<>();
                params.put("hall_id",hall_id);
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(HallBookingViewActivity.this);
        queue.add(stringRequest);
    }

}


