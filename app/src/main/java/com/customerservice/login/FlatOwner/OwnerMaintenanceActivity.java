package com.customerservice.login.FlatOwner;

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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.customerservice.login.Adapters.OwnerMaintenanceAdapter;
import com.customerservice.login.ClassFiles.Maintenance;
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
    List<Maintenance>ownerMaintenanceList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_maintenance);
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
                Intent Intentwidget=new Intent(OwnerMaintenanceActivity.this, OwnerMaintenanceWidgetActivity.class);
                startActivity(Intentwidget);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        owner_maintenance_listview=(ListView)findViewById(R.id.owner_maintenance_listview);
        adapter=new OwnerMaintenanceAdapter(OwnerMaintenanceActivity.this,ownerMaintenanceList);
        owner_maintenance_listview.setAdapter(adapter);

        owner_maintenance_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Maintenance item=ownerMaintenanceList.get(position);
                Intent intent=new Intent(OwnerMaintenanceActivity.this, OwnerMaintenanceDetailsActivity.class);
                intent.putExtra("maintenanceObject",item);
                startActivity(intent);
            }
        });
        StringRequest rq=new StringRequest(Request.Method.POST, Config.list_tbl_maintenance, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array=new JSONArray(response);
                   // Toast.makeText(OwnerMaintenanceActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject object=array.getJSONObject(i);
                        Maintenance classfile=new Maintenance();

                        classfile.setUser_id(object.getString("user_id"));
                        classfile.setStatus(object.getString("status"));
                        classfile.setAmount(object.getString("amount"));
                        classfile.setPay_date(object.getString("pay_date"));
                        classfile.setTransaction_number(object.getString("transaction_number"));
                        classfile.setPay_mode(object.getString("pay_mode"));

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
