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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.customerservice.login.Adapters.AdminComplainAdapter;
import com.customerservice.login.Adapters.AdminComplainStatusAdapter;
import com.customerservice.login.ClassFiles.ComplainClassFile;
import com.customerservice.login.R;
import com.customerservice.login.Utility.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminComplainStatusActivity extends AppCompatActivity {
    ListView listView;
    AdminComplainStatusAdapter adapter;
    List<ComplainClassFile> classFileList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_complain_status);
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
        listView=(ListView)findViewById(R.id.admin_complain_status_list_view);
        adapter=new AdminComplainStatusAdapter(AdminComplainStatusActivity.this,classFileList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ComplainClassFile item=classFileList.get(position);
                Intent intent=new Intent(AdminComplainStatusActivity.this,AdminComplainStatusDetailsActivity.class);
                intent.putExtra("complainobj",item);
                startActivity(intent);
            }
        });

        StringRequest request=new StringRequest(Request.Method.POST, Config.list_tbl_admin_complain_status, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array=new JSONArray(response);
                    for (int i=0;i<array.length();i++)
                    {
                        JSONObject object=array.getJSONObject(i);

                        ComplainClassFile classFile=new ComplainClassFile();

                        classFile.setComplain_problem(object.getString("complain_problem"));
                        classFile.setComplain_id(object.getString("complain_id"));
                        classFile.setComplain_user_id(object.getString("complain_user_id"));
                        classFile.setComplain_hcat_id(object.getString("complain_hcat_id"));
                        classFile.setComplain_img_uri(object.getString("complain_img_uri"));
                        classFile.setComplain_date(object.getString("complain_date"));
                        classFile.setComplain_status(object.getString("complain_status"));
                        classFile.setComplain_v_date(object.getString("complain_v_date"));
                        classFile.setComplain_v_time(object.getString("complain_v_time"));
                        classFile.setComplain_date_time(object.getString("complain_date_time"));

                        classFileList.add(classFile);
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