package com.customerservice.login.FlatOwner;

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
import com.customerservice.login.Adapters.OwnerHallAdapter;
import com.customerservice.login.ClassFiles.Hall;
import com.customerservice.login.R;
import com.customerservice.login.Utility.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;




public class OwnerHallActivity extends AppCompatActivity {

    ListView list_view_hall;
    OwnerHallAdapter adapter;
    List<Hall> ownerHallList =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_hall);
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

        list_view_hall=(ListView)findViewById(R.id.list_view_hall);
        adapter=new OwnerHallAdapter(OwnerHallActivity.this, ownerHallList);
        list_view_hall.setAdapter(adapter);

        StringRequest rq=new StringRequest(Request.Method.POST, Config.list_tbl_hall, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array=new JSONArray(response);
                            for (int i=0;i<array.length();i++)
                            {
                                JSONObject object=array.getJSONObject(i);
                                Hall ownerHall =new Hall();

                                ownerHall.setHall_id(object.getString("hall_id"));
                                ownerHall.setHall_title(object.getString("hall_title"));
                                ownerHall.setHall_capacity(object.getString("hall_capacity"));

                                ownerHallList.add(ownerHall);
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
        queue.add(rq);
    }
}