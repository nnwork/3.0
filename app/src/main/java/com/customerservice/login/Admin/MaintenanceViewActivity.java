package com.customerservice.login.Admin;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.customerservice.login.Adapters.MaintenanceAdapter;
import com.customerservice.login.ClassFiles.Maintenance;
import com.customerservice.login.R;
import com.customerservice.login.Utility.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MaintenanceViewActivity extends AppCompatActivity {

    ListView maintenanaceview;
    List<Maintenance>maintenanceList  = new ArrayList<>();
    MaintenanceAdapter maintenanceAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenanceview);
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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        maintenanaceview=(ListView)findViewById(R.id.maintenanaceview);
        maintenanceAdapter=new MaintenanceAdapter(MaintenanceViewActivity.this,maintenanceList);
        maintenanaceview.setAdapter(maintenanceAdapter);

      //  Toast.makeText(this, "ss", Toast.LENGTH_SHORT).show();
        StringRequest request = new StringRequest(Request.Method.POST, Config.READ_Maintenace, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array  = new JSONArray(response);

                    for (int i=0;i<array.length();i++)
                    {
                        JSONObject object=array.getJSONObject(i);

                        Maintenance item = new Maintenance();
                        item.setAmount(object.getString("amount"));
                        maintenanceList.add(item);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                finally {
                    maintenanceAdapter.notifyDataSetChanged();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MaintenanceViewActivity.this, "Error"+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(MaintenanceViewActivity.this);
        queue.add(request);
    }
}
