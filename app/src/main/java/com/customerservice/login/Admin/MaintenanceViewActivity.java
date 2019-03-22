package com.customerservice.login.Admin;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.customerservice.login.Adapters.MaintenanceAdapter;
import com.customerservice.login.ClassFiles.Building;
import com.customerservice.login.ClassFiles.Maintenance;
import com.customerservice.login.ClassFiles.MonthAmount;
import com.customerservice.login.R;
import com.customerservice.login.Utility.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.MissingFormatArgumentException;

public class MaintenanceViewActivity extends AppCompatActivity {

    ListView maintenanaceview;
    List<Maintenance>maintenanceList  = new ArrayList<>();
    MaintenanceAdapter maintenanceAdapter;

    String month_id;
    MonthAmount objectMonthamount;
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
        if(getIntent().hasExtra("objectMonthamount"))
        {
            objectMonthamount = (MonthAmount) getIntent().getSerializableExtra("objectMonthamount");
            month_id=objectMonthamount.getMonth_id();
          // Toast.makeText(this, ""+month_id, Toast.LENGTH_SHORT).show();

        }
        else
        {
            month_id="all";
            //Toast.makeText(this, ""+month_id, Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this, ""+month_id, Toast.LENGTH_SHORT).show();
        maintenanaceview=(ListView)findViewById(R.id.maintenanaceview);
        maintenanceAdapter=new MaintenanceAdapter(MaintenanceViewActivity.this,maintenanceList);
        maintenanaceview.setAdapter(maintenanceAdapter);

        objectMonthamount = (MonthAmount) getIntent().getSerializableExtra("objectMonthamount");

        maintenanaceview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Maintenance maintenance = maintenanceList.get(position);
                Intent intent = new Intent(MaintenanceViewActivity.this,AdminMaintenanceDetailsActivity
                        .class);
                intent.putExtra("maintenanceObject",maintenance);
                startActivity(intent);
            }
        });


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
                        item.setMaintenance_id(object.getString("maintenance_id"));
                        item.setUser_id(object.getString("user_id"));
                        item.setStatus(object.getString("status"));
                        item.setPay_date(object.getString("pay_date"));
                        item.setTransaction_number(object.getString("transaction_number"));
                        item.setPay_mode(object.getString("pay_mode"));
                        item.setMonth_id(object.getString("month_id"));
                        item.setMonth(object.getString("month"));
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
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>paramas= new HashMap<>();
               paramas.put("month_id",month_id);
                return paramas;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(MaintenanceViewActivity.this);
        queue.add(request);

    }

}
