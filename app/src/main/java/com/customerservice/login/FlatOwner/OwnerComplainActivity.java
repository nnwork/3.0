package com.customerservice.login.FlatOwner;

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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.customerservice.login.Adapters.OwnerComplainAdapter;
import com.customerservice.login.ClassFiles.ComplainClassFile;
import com.customerservice.login.R;
import com.customerservice.login.Utility.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class OwnerComplainActivity extends AppCompatActivity {
    ListView owner_complain_list_view;
    OwnerComplainAdapter adapter;
    List<ComplainClassFile>complainClassFileList=new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_complain);
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
                Intent Intentwidget=new Intent(OwnerComplainActivity.this, Formcomplain.class);
                startActivity(Intentwidget);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        owner_complain_list_view=(ListView)findViewById(R.id.owner_complain_list_view);
        adapter=new OwnerComplainAdapter(OwnerComplainActivity.this,complainClassFileList) ;
        owner_complain_list_view.setAdapter(adapter);

        owner_complain_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ComplainClassFile item=complainClassFileList.get(position);
                Intent intent=new Intent(OwnerComplainActivity.this, OwnerComplainDetailsActivity.class);
                intent.putExtra("complainObject",item);
                startActivity(intent);
            }
        });

        StringRequest rq=new StringRequest(Request.Method.POST, Config.list_tbl_owner_complain, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array=new JSONArray(response);
                            for(int i=0;i<array.length();i++)
                            {
                                JSONObject object=array.getJSONObject(i);

                                ComplainClassFile classFile=new ComplainClassFile();

                                classFile.setComplain_problem(object.getString("complain_problem"));
                                classFile.setComplain_id(object.getString("complain_id"));
                                complainClassFileList.add(classFile);
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
        RequestQueue queue= Volley.newRequestQueue(OwnerComplainActivity.this);
        queue.add(rq);
    }

}
