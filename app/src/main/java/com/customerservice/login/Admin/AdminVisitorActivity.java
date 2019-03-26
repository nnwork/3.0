package com.customerservice.login.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.customerservice.login.Adapters.AdminVisitorAdapter;
import com.customerservice.login.ClassFiles.Visitor;
import com.customerservice.login.R;
import com.customerservice.login.Utility.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminVisitorActivity extends AppCompatActivity {
    ListView listView;
    AdminVisitorAdapter adapter;
    String visitors_watchman_id="all";
    List<Visitor>visitorList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_visitor);
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

        if(getIntent().hasExtra("visitors_watchman_id"))
        {
            visitors_watchman_id=getIntent().getStringExtra("visitors_watchman_id");
        }
        else
        {
            visitors_watchman_id="all";
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listView=(ListView)findViewById(R.id.admin_visitor_list_view);
        adapter=new AdminVisitorAdapter(AdminVisitorActivity.this,visitorList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Visitor item=visitorList.get(position);
                Intent intent=new Intent(AdminVisitorActivity.this,AdminVisitorDetailsActvity.class);
                intent.putExtra("visitorobj",item);
                startActivity(intent);
            }
        });

        StringRequest request=new StringRequest(Request.Method.POST, Config.list_tbl_admin_visitor, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(AdminVisitorActivity.this, "abc"+response, Toast.LENGTH_LONG).show();
                try {
                    JSONArray array=new JSONArray(response);
                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject object=array.getJSONObject(i);

                        Visitor classfile=new Visitor();

                        classfile.setVisitors_id(object.getString("visitors_id"));
                        classfile.setVisitors_name(object.getString("visitors_name"));
                        classfile.setVisitors_contect(object.getString("visitors_contect"));
                        classfile.setVisitors_flat_id(object.getString("visitors_flat_id"));
                        classfile.setBuildingName(object.getString("BuildingName"));
                        classfile.setBuildingId(object.getString("BuildingId"));
                        classfile.setWatchmen_name(object.getString("watchmen_name"));
                        classfile.setVisitors_photo(object.getString("visitors_photo"));
                        classfile.setVisitors_watchman_id(object.getString("visitors_watchman_id"));
                        classfile.setVisitors_v_date(object.getString("visitors_v_date"));
                        classfile.setVisitors_v_time(object.getString("visitors_v_time"));
                        classfile.setVisitors_flat_approve(object.getString("visitors_flat_approve"));

                        classfile.setVisitors_app_date_time(object.getString("visitors_app_date_time"));
                        classfile.setVisitors_exit_date_time(object.getString("visitors_exit_date_time"));

                        classfile.setFlatNumber(object.getString("FlatNumber"));
                        classfile.setUser_name(object.getString("user_name"));

                        visitorList.add(classfile);
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
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("visitors_watchman_id",visitors_watchman_id);
                return params;
            }
        };
        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(request);
    }
}