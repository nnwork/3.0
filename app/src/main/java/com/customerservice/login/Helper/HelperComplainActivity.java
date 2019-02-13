package com.customerservice.login.Helper;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.customerservice.login.Adapters.HelperComplainAdapter;
import com.customerservice.login.ClassFiles.ComplainClassFile;
import com.customerservice.login.FlatOwner.Formcomplain;
import com.customerservice.login.R;
import com.customerservice.login.Utility.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;




public class HelperComplainActivity extends AppCompatActivity {

    ListView list_view;
    HelperComplainAdapter helperComplainAdapter;
    List<ComplainClassFile> complainClassFileList =new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper_complain);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent intent=new Intent(HelperComplainActivity.this, Formcomplain.class);
                startActivity(intent);

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list_view=(ListView)findViewById(R.id.list_view);
        helperComplainAdapter=new HelperComplainAdapter(HelperComplainActivity.this, complainClassFileList);
        list_view.setAdapter(helperComplainAdapter);

        StringRequest rq=new StringRequest(Request.Method.POST, Config.list_tbl_complain, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array=new JSONArray(response);
                    for(int j=0;j<array.length();j++)
                    {
                        JSONObject object=array.getJSONObject(j);

                        ComplainClassFile ComplainClassFile =new ComplainClassFile();
                        ComplainClassFile.setComplain_problem(object.getString("complain_problem"));
                        complainClassFileList.add(ComplainClassFile);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                finally {
                    helperComplainAdapter.notifyDataSetChanged();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(rq);
    }

}
