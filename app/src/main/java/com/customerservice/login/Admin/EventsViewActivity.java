package com.customerservice.login.Admin;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.EventLog;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.customerservice.login.Adapters.EventsAdapter;
import com.customerservice.login.ClassFiles.Events;
import com.customerservice.login.R;
import com.customerservice.login.Utility.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EventsViewActivity extends AppCompatActivity {

    ListView eventlistview;
    List<Events>eventsList=new ArrayList<>();
    EventsAdapter eventsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_view);
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
                Intent intent= new Intent(EventsViewActivity.this,AdminAddEventActivity.class);
                startActivity(intent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        eventlistview=(ListView)findViewById(R.id.eventlistview);
        eventsAdapter= new EventsAdapter(EventsViewActivity.this,eventsList);
        eventlistview.setAdapter(eventsAdapter);


        eventlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

              //  Toast.makeText(EventsViewActivity.this, "Click", Toast.LENGTH_SHORT).show();
                Events item=eventsList.get(position);
                Intent intent=new Intent(EventsViewActivity.this,AdminEventDetailsActivity.class);
                intent.putExtra("eventObject",item);
                startActivity(intent);

            }
        });


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.READ_Event, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i=0;i<array.length();i++)
                    {
                        JSONObject object= array.getJSONObject(i);

                        Events item = new Events();
                        item.setEvent_title(object.getString("event_title"));
                        eventsList.add(item);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                finally {
                    eventsAdapter.notifyDataSetChanged();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue queue = Volley.newRequestQueue(EventsViewActivity.this);
        queue.add(stringRequest);



    }

}
