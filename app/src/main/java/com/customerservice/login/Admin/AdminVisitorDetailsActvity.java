package com.customerservice.login.Admin;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.customerservice.login.ClassFiles.Visitor;
import com.customerservice.login.R;
import com.customerservice.login.Utility.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AdminVisitorDetailsActvity extends AppCompatActivity {
    TextView owner_visitors_id,admin_visiters_name,admin_visiters_contect,admin_visitors_flat_id,admin_visitors_photo;
    TextView admin_visitors_watchman_id,admin_visitors_v_date,admin_visitors_v_time,admin_visitors_flat_approve,admin_visitors_app_date_time;
    TextView admin_visitors_exit_date_time;
    Visitor visitorobj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_visitor_details_actvity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        admin_visiters_contect=(TextView)findViewById(R.id.admin_visiters_contect);
        admin_visitors_flat_id=(TextView)findViewById(R.id.admin_visitors_flat_id);
        admin_visitors_photo=(TextView)findViewById(R.id.admin_visitors_photo);
        admin_visitors_watchman_id=(TextView)findViewById(R.id.admin_visitors_watchman_id);
        admin_visitors_app_date_time=(TextView)findViewById(R.id.admin_visitors_app_date_time);
        admin_visitors_exit_date_time=(TextView)findViewById(R.id.admin_visitors_exit_date_time);

        visitorobj=(Visitor)getIntent().getSerializableExtra("visitorobj");

//        StringRequest request=new StringRequest(Request.Method.POST, Config.READ_AdminUserVisitor, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONArray array=new JSONArray(response);
//                    for(int i=0;i<array.length();i++)
//                    {
//                        JSONObject object=array.getJSONObject(i);
//
//
//
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
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String>params=new HashMap<>();
//                params.put("visitors_id",visitorobj.getVisitors_id());
//                return params;
//            }
//        };
//        RequestQueue queue= Volley.newRequestQueue(this);
//        queue.add(request);

        admin_visiters_contect.setText(visitorobj.getVisitors_contect());
        admin_visitors_photo.setText(visitorobj.getVisitors_photo());
        admin_visitors_app_date_time.setText(visitorobj.getVisitors_app_date_time());
        admin_visitors_exit_date_time.setText(visitorobj.getVisitors_exit_date_time());
    }
}
