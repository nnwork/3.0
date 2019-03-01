package com.customerservice.login.FlatOwner;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.customerservice.login.Adapters.OwnerEventAdapter;
import com.customerservice.login.ClassFiles.Events;
import com.customerservice.login.R;
import com.customerservice.login.Utility.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class OwnerEventActivity extends AppCompatActivity {

    ListView list_view_event;
    OwnerEventAdapter ownerEventAdapter;
    List<Events>ownerEventList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_event);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list_view_event=(ListView)findViewById(R.id.list_view_event);
        ownerEventAdapter=new OwnerEventAdapter(OwnerEventActivity.this,ownerEventList);
        list_view_event.setAdapter(ownerEventAdapter);

        list_view_event.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Events item=ownerEventList.get(position);
                Intent intent=new Intent(OwnerEventActivity.this, OwnerEventDetailsActivity.class);
                intent.putExtra("EventObject",item);
                startActivity(intent);
            }
        });

        StringRequest rq=new StringRequest(Request.Method.POST, Config.list_tbl_events, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array=new JSONArray(response);
                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject object=array.getJSONObject(i);

                        Events ownerEvent=new Events();
                        ownerEvent.setCat_id(object.getString("cat_id"));

                        ownerEvent.setEvent_id(object.getString("event_id"));
                        ownerEvent.setEvent_title(object.getString("event_title"));
                        ownerEvent.setEvent_desc(object.getString("event_desc"));
                        ownerEvent.setEvent_venue(object.getString("event_venue"));

                        ownerEvent.setEvent_address(object.getString("event_address"));
                        ownerEvent.setEvent_landmark(object.getString("event_landmark"));
                        ownerEvent.setPincode(object.getString("pincode"));
                        ownerEvent.setEvent_speciality(object.getString("event_speciality"));
                        ownerEventList.add(ownerEvent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                finally {
                    ownerEventAdapter.notifyDataSetChanged();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(rq);
    }

}
