package com.customerservice.login.FlatOwner;

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
import com.customerservice.login.Adapters.ExpencesAdapter;
import com.customerservice.login.Admin.ExpencesActivity;
import com.customerservice.login.ClassFiles.Expences;
import com.customerservice.login.R;
import com.customerservice.login.Utility.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AccountsActivity extends AppCompatActivity {
ListView accountlistview;
List<Expences>expencesList = new ArrayList<>();
ExpencesAdapter expencesAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        accountlistview = (ListView)findViewById(R.id.accountlistview);
        expencesAdapter = new ExpencesAdapter(AccountsActivity.this,expencesList);
        accountlistview.setAdapter(expencesAdapter);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.READ_Expences, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array= new JSONArray(response);
                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject jsonObject = array.getJSONObject(i);

                        Expences item = new Expences();

                        item.setExpense_title(jsonObject.getString("expense_title"));
                        item.setExpense_amt(jsonObject.getString("expense_amt"));
                        item.setExpense_date_time(jsonObject.getString("expense_date_time"));
                        expencesList.add(item);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                finally {
                    expencesAdapter.notifyDataSetChanged();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue queue = Volley.newRequestQueue(AccountsActivity.this);
        queue.add(stringRequest);

    }

}
