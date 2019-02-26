package com.customerservice.login.FlatOwner;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.customerservice.login.ClassFiles.Maintenance;
import com.customerservice.login.R;
import com.customerservice.login.Utility.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OwnerMaintenanceDetailsActivity extends AppCompatActivity {
    TextView amount,status,pay_mode,owner_maintenance_display_user_id;
    Maintenance maintenanceObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_maintenance_design);
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

        amount=(TextView)findViewById(R.id.owner_maintenance_display_amount);
        status=(TextView)findViewById(R.id.owner_maintenance_display_status);
        pay_mode=(TextView)findViewById(R.id.owner_maintenance_display_pay_mode);
        owner_maintenance_display_user_id=(TextView)findViewById(R.id.owner_maintenance_display_user_id);

        maintenanceObject=(Maintenance) getIntent().getSerializableExtra("maintenanceObject");

        StringRequest request=new StringRequest(Request.Method.POST, Config.READ_OwnerUserMaintenance, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array=new JSONArray(response);
                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject object=array.getJSONObject(i);

                        String username=object.getString("user_name");
                        owner_maintenance_display_user_id.setText(username);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(request);

        toolbar.setTitle(maintenanceObject.getAmount());
        amount.setText(maintenanceObject.getAmount());
        status.setText(maintenanceObject.getStatus());
        pay_mode.setText(maintenanceObject.getPay_mode());
    }

}
