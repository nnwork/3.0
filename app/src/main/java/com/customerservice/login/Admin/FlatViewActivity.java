package com.customerservice.login.Admin;

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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.customerservice.login.Adapters.BuildingAdapter;
import com.customerservice.login.Adapters.FlatAdapter;
import com.customerservice.login.ClassFiles.Building;
import com.customerservice.login.ClassFiles.Flat;
import com.customerservice.login.R;
import com.customerservice.login.Utility.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlatViewActivity extends AppCompatActivity {

    ListView flatlistview;
    FlatAdapter adapter;
    List<Flat> flatList=new ArrayList<Flat>();
    String buildingid="all";

    Building objectBiulding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flat_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        if(getIntent().hasExtra("objectBuilding"))
        {
            objectBiulding = (Building) getIntent().getSerializableExtra("objectBuilding");
            buildingid=objectBiulding.getBuildingId();
        }
        else
        {
            buildingid="all";
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FlatViewActivity.this, AdminAddFlatActivity.class);
                startActivity(intent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        flatlistview = (ListView)findViewById(R.id.flatlistview);
        adapter=new FlatAdapter(FlatViewActivity.this,flatList);
        flatlistview.setAdapter(adapter);

        flatlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Flat item = flatList.get(position);

                Intent intent = new Intent(FlatViewActivity.this,FlatDetailActivity.class);
                intent.putExtra("objectFlat",item);
                startActivity(intent);
            }
        });

        StringRequest request=new StringRequest(Request.Method.POST, Config.READ_Flat, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try
                {

                JSONArray jsonArray = new JSONArray(response);
                 for (int i=0;i<jsonArray.length();i++) {
                     JSONObject jsonObject = jsonArray.getJSONObject(i);

                     Flat item = new Flat();
                     item.setFlatNumber(jsonObject.getString("FlatNumber"));
                     item.setFlatId(jsonObject.getString("FlatId"));
                     item.setBuildingId(jsonObject.getString("BuildingId"));
                     item.setBuldingName(jsonObject.getString("BuildingName"));
                     flatList.add(item);

                 }
                } catch (JSONException e)
                {
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
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params = new HashMap<>();
                params.put("BuildingId",buildingid);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(FlatViewActivity.this);
        requestQueue.add(request);
    }

}
