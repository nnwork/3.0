package com.customerservice.login.FlatOwner;

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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.customerservice.login.Adapters.OwnerFundAdapter;
import com.customerservice.login.ClassFiles.Funds;
import com.customerservice.login.R;
import com.customerservice.login.Utility.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;




public class OwnerFundActivity extends AppCompatActivity {
    ListView owner_fund_listview;
    OwnerFundAdapter adapter;
    List<Funds>fundsList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_fund);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        owner_fund_listview=(ListView)findViewById(R.id.owner_fund_listview);
        adapter=new OwnerFundAdapter(OwnerFundActivity.this,fundsList);
        owner_fund_listview.setAdapter(adapter);

        owner_fund_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Funds funds = fundsList.get(position);
                Intent intent = new Intent(OwnerFundActivity.this,OwnerFundDetail.class);
                intent.putExtra("fundObject",funds);
                startActivity(intent);


            }
        });


        StringRequest request=new StringRequest(Request.Method.POST, Config.list_tbl_fund, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array=new JSONArray(response);
                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject object=array.getJSONObject(i);

                        Funds funds=new Funds();

                        funds.setFund_id(object.getString("fund_id"));
                        funds.setFund_title(object.getString("fund_title"));
                        funds.setFund_amt(object.getString("fund_amt"));
                        funds.setFund_date_time(object.getString("fund_date_time"));
                        fundsList.add(funds);
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
