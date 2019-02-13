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
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.customerservice.login.Adapters.MaintenanceAdapter;
import com.customerservice.login.Adapters.MonthAmountAdapter;
import com.customerservice.login.ClassFiles.MonthAmount;
import com.customerservice.login.R;
import com.customerservice.login.Utility.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MonthlyAmountActivity extends AppCompatActivity {
    ListView monthamountview;
    List<MonthAmount> monthAmountList = new ArrayList<>();
    MonthAmountAdapter monthAmountAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_amount);
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
                Intent intent = new Intent(MonthlyAmountActivity.this,AdminAddMonthActivity.class);
                startActivity(intent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        monthamountview = (ListView)findViewById(R.id.monthlyamountview);
        monthAmountAdapter=new MonthAmountAdapter(MonthlyAmountActivity.this,monthAmountList);
        monthamountview.setAdapter(monthAmountAdapter);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.READ_MonthlyAmount, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i=0; i<array.length();i++)
                    {
                        JSONObject jsonObject = array.getJSONObject(i);

                        MonthAmount item = new MonthAmount();

                        item.setMonth(jsonObject.getString("month"));
                        item.setMonth(jsonObject.getString("month_amount"));
                        monthAmountList.add(item);


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                finally {
                    monthAmountAdapter.notifyDataSetChanged();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue queue = Volley.newRequestQueue(MonthlyAmountActivity.this);
        queue.add(stringRequest);

    }

}
