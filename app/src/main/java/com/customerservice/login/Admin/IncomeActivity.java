package com.customerservice.login.Admin;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.customerservice.login.Adapters.BuildingAdapterRecycle;
import com.customerservice.login.Adapters.ExpencesAdapter;
import com.customerservice.login.Adapters.IncomeAdapter;
import com.customerservice.login.ClassFiles.Building;
import com.customerservice.login.ClassFiles.Expences;
import com.customerservice.login.ClassFiles.Maintenance;
import com.customerservice.login.R;
import com.customerservice.login.Utility.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class IncomeActivity extends AppCompatActivity {
    RecyclerView incomeview;
    IncomeAdapter adapter;
    List<Maintenance> maintenanceList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        incomeview=(RecyclerView)findViewById(R.id.incomeview);
        incomeview.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        incomeview.setLayoutManager(mLayoutManager);
        incomeview.setItemAnimator(new DefaultItemAnimator());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapter = new IncomeAdapter(maintenanceList);
        incomeview.setAdapter(adapter);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.READ_AdminUserMaintenance, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               // Toast.makeText(IncomeActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                try {
                    JSONArray array= new JSONArray(response);
                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject object = array.getJSONObject(i);
                       // Toast.makeText(IncomeActivity.this, ""+object.getString("month"), Toast.LENGTH_SHORT).show();
                        Maintenance item = new Maintenance();
                        item.setAmount(object.getString("amount"));
                        item.setUser_name(object.getString("user_name"));
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
                    adapter.notifyDataSetChanged();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(IncomeActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue queue = Volley.newRequestQueue(IncomeActivity.this);
        queue.add(stringRequest);
    }

}

