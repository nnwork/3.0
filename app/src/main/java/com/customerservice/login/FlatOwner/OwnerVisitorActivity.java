package com.customerservice.login.FlatOwner;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.customerservice.login.Adapters.OwnerVisitorAdapter;
import com.customerservice.login.ClassFiles.Visitor;
import com.customerservice.login.R;
import com.customerservice.login.Utility.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OwnerVisitorActivity extends AppCompatActivity {

    ListView owner_visitor_list_view;
    OwnerVisitorAdapter ownerVisitorAdapter;
    List<Visitor> visitorList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_visitor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intentwidget=new Intent(OwnerVisitorActivity.this,OwnerComplainDetailsActivity.class);
                startActivity(Intentwidget);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        owner_visitor_list_view=(ListView)findViewById(R.id.owner_visitor_list_view);
        ownerVisitorAdapter=new OwnerVisitorAdapter(OwnerVisitorActivity.this,visitorList);
        owner_visitor_list_view.setAdapter(ownerVisitorAdapter);

        owner_visitor_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Visitor item=visitorList.get(position);
                Intent intent=new Intent(OwnerVisitorActivity.this,OwnerVisitorDetailsActivity.class);
                intent.putExtra("visitoribject",item);
                startActivity(intent);
            }
        });


        StringRequest rq=new StringRequest(Request.Method.POST, Config.list_tbl_visitor, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array=new JSONArray(response);
                    for (int i=0;i<array.length();i++)
                    {
                        JSONObject object=array.getJSONObject(i);

                        Visitor Visitor =new Visitor();
                        Visitor.setVisitors_id(object.getString("visitors_id"));
                        Visitor.setVisitors_name(object.getString("visitors_name"));
                        Visitor.setVisitors_contect(object.getString("visitors_contect"));
                        visitorList.add(Visitor);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                finally {
                    ownerVisitorAdapter.notifyDataSetChanged();
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
