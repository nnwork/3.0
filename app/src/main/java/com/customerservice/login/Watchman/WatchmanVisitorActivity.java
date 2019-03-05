package com.customerservice.login.Watchman;

import android.content.Intent;
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
import com.customerservice.login.Adapters.WatchmanVisitorAdapter;
import com.customerservice.login.ClassFiles.Visitor;
import com.customerservice.login.R;
import com.customerservice.login.Utility.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;




public class WatchmanVisitorActivity extends AppCompatActivity {

    ListView visitor_list_view;
    WatchmanVisitorAdapter watchmanVisitorAdapter;
    List<Visitor>watchmanvisitorList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchman_visitor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WatchmanVisitorActivity.this,AddVisitorActivity.class);
                startActivity(intent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        visitor_list_view=(ListView)findViewById(R.id.visitor_list_view);
        watchmanVisitorAdapter=new WatchmanVisitorAdapter(WatchmanVisitorActivity.this,watchmanvisitorList);
        visitor_list_view.setAdapter(watchmanVisitorAdapter);

        StringRequest rq=new StringRequest(Request.Method.POST, Config.list_tbl_visitor, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array=new JSONArray(response);
                    for (int i=0;i<array.length();i++)
                    {
                        JSONObject object=array.getJSONObject(i);

                        Visitor watchmanvisitor=new Visitor();
                        watchmanvisitor.setVisitors_name(object.getString("visitors_name"));
                        watchmanvisitor.setVisitors_contect(object.getString("visitors_contect"));
                        watchmanvisitorList.add(watchmanvisitor);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                finally {
                    watchmanVisitorAdapter.notifyDataSetChanged();
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
