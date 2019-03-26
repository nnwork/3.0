package com.customerservice.login.Admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.customerservice.login.Adapters.BuildingAdapter;
import com.customerservice.login.Adapters.BuildingAdapterRecycle;
import com.customerservice.login.ClassFiles.Building;
import com.customerservice.login.R;
import com.customerservice.login.RecyclerTouchListener;
import com.customerservice.login.Utility.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AdminViewBuildingActivity extends AppCompatActivity {


//    RecyclerView buildinglistview;
    GridView buildinggridview;
    BuildingAdapter  adapter;
    List<Building> buildingList=new ArrayList<>();
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_building);
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
                Intent intent= new Intent(AdminViewBuildingActivity.this,AdminAddBuildingActivity.class);
                startActivity(intent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        dialog=new ProgressDialog(this);
        dialog.setMessage("Please Wait...");
        dialog.setCancelable(false);

        buildinggridview=(GridView)findViewById(R.id.buildinggridview);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        buildinglistview.setLayoutManager(mLayoutManager);
//        buildinglistview.setItemAnimator(new DefaultItemAnimator());
//        buildinglistview.setLayoutManager(new GridLayoutManager(this, 2));
        adapter=new BuildingAdapter(AdminViewBuildingActivity.this,buildingList);
        buildinggridview.setAdapter(adapter);



        //Server DataString
        dialog.show();
        StringRequest str=new StringRequest(Request.Method.POST, Config.READ_BUILDINGS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dialog.dismiss();
                try {
                    JSONArray array=new JSONArray(response);
                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject obj=array.getJSONObject(i);

                        Building item=new Building();

                        item.setBuildingId(obj.getString("BuildingId"));
                        item.setBuildingName(obj.getString("BuildingName"));
                        item.setBuildingImage(obj.getString("BuildingImage"));
                        buildingList.add(item);
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
                dialog.dismiss();
            }
        });

        RequestQueue q= Volley.newRequestQueue(this);
        q.add(str);
        //


        //Flatview


//        buildinglistview.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), buildinglistview, new RecyclerTouchListener.ClickListener() {
//            @Override
//            public void onClick(View view, int position)
//            {
//                Building item = buildingList.get(position);
//                Intent intent = new Intent(AdminViewBuildingActivity.this,FlatViewActivity.class);
//                intent.putExtra("objectBuilding", item);
//                startActivity(intent);
//            }
//            @Override
//            public void onLongClick(View view, int position) {
//
//            }
//        }));




        buildinggridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Building item = buildingList.get(position);
               Intent intent = new Intent(AdminViewBuildingActivity.this,FlatViewActivity.class);
               intent.putExtra("objectBuilding", item);
               startActivity(intent);
            }
        });


    }

}
