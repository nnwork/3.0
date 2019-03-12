package com.customerservice.login.Admin;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
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

public class AdminMaintenanceDetailsActivity extends AppCompatActivity {

    TextView amount,status,pay_mode,user_name,transaction_number,pay_date,monthlyamount;
    Maintenance maintenanceObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_maintenance_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        amount=(TextView)findViewById(R.id.admin_maintenance_display_amount);
        status=(TextView)findViewById(R.id.admin_maintenance_display_status);
        pay_mode=(TextView)findViewById(R.id.admin_maintenance_display_pay_mode);
        user_name=(TextView)findViewById(R.id.admin_maintenance_display_user_name);
        transaction_number=(TextView)findViewById(R.id.admin_maintenance_display_transaction_number);
        pay_date=(TextView)findViewById(R.id.admin_maintenance_display_pay_date);
        monthlyamount=(TextView)findViewById(R.id.monthlyamount);
        maintenanceObject=(Maintenance) getIntent().getSerializableExtra("maintenanceObject");

//        StringRequest request=new StringRequest(Request.Method.POST, Config.READ_AdminUserMaintenance, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONArray array=new JSONArray(response);
//                    for(int i=0;i<array.length();i++)
//                    {
                      // JSONObject object=array.getJSONObject(i);
                        user_name.setText(maintenanceObject.getAmount());
                        //amount.setText(maintenanceObject.getAmount());
                        //status.setText(maintenanceObject.getAmount());
                        pay_date.setText(maintenanceObject.getPay_date());
                        transaction_number.setText(maintenanceObject.getTransaction_number());
                        //pay_mode.setText(maintenanceObject.getAmount());
                        monthlyamount.setText(maintenanceObject.getMonth());
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        RequestQueue queue= Volley.newRequestQueue(this);
//        queue.add(request);

        toolbar.setTitle(maintenanceObject.getAmount());
        amount.setText(maintenanceObject.getAmount());
        status.setText(maintenanceObject.getStatus());
        pay_mode.setText(maintenanceObject.getPay_mode());


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
        }
        return true;
    }
}
