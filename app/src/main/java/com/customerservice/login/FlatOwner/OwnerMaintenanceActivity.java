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
import com.customerservice.login.Adapters.OwnerMaintenanceAdapter;
import com.customerservice.login.ClassFiles.OwnerMaintenance;
import com.customerservice.login.R;
import com.customerservice.login.Utility.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;




public class OwnerMaintenanceActivity extends AppCompatActivity {
    ListView owner_maintenance_listview;
    OwnerMaintenanceAdapter adapter;
    List<OwnerMaintenance>ownerMaintenanceList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_maintenance);
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

        owner_maintenance_listview=(ListView)findViewById(R.id.owner_maintenance_listview);
        adapter=new OwnerMaintenanceAdapter(OwnerMaintenanceActivity.this,ownerMaintenanceList);
        owner_maintenance_listview.setAdapter(adapter);

        StringRequest rq=new StringRequest(Request.Method.POST, Config.list_tbl_maintenance, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array=new JSONArray(response);
                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject object=array.getJSONObject(i);
                        OwnerMaintenance classfile=new OwnerMaintenance();

                        classfile.setStatus(object.getString("status"));
                        ownerMaintenanceList.add(classfile);
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
