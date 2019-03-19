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
import com.customerservice.login.ClassFiles.ComplainClassFile;
import com.customerservice.login.R;
import com.customerservice.login.Utility.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AdminComaplainDetailsActivity extends AppCompatActivity {
    TextView admin_complain_id,admin_complain_user_id,admin_complain_hcat_id,admin_complain_problem,admin_complain_img_uri;
    TextView admin_complain_date,admin_complain_status,admin_complain_v_date,admin_complain_v_time,admin_complain_date_time;
    ComplainClassFile complainobj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_comaplain_details);
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


        admin_complain_user_id=(TextView)findViewById(R.id.admin_complain_user_id);
        admin_complain_hcat_id=(TextView)findViewById(R.id.admin_complain_hcat_id);
        admin_complain_problem=(TextView)findViewById(R.id.admin_complain_problem);
        admin_complain_img_uri=(TextView)findViewById(R.id.admin_complain_img_uri);

        admin_complain_date=(TextView)findViewById(R.id.admin_complain_date);
        admin_complain_status=(TextView)findViewById(R.id.admin_complain_status);
        admin_complain_v_date=(TextView)findViewById(R.id.admin_complain_v_date);
        admin_complain_v_time=(TextView)findViewById(R.id.admin_complain_v_time);
        admin_complain_date_time=(TextView)findViewById(R.id.admin_complain_date_time);

        complainobj=(ComplainClassFile)getIntent().getSerializableExtra("complainobj");

        StringRequest request=new StringRequest(Request.Method.POST, Config.list_tbl_admin_complain_details, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array=new JSONArray(response);
                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject object=array.getJSONObject(i);

                        String user_name=object.getString("user_name");
                        admin_complain_user_id.setText(user_name);

                        String hcat_name=object.getString("hcat_name");
                        admin_complain_hcat_id.setText(hcat_name);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("complain_id",complainobj.getComplain_id());
                return params;
            }
        };
        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(request);
        admin_complain_user_id.setText(complainobj.getComplain_user_id());
        admin_complain_hcat_id.setText(complainobj.getComplain_hcat_id());
        admin_complain_img_uri.setText(complainobj.getComplain_img_uri());
        admin_complain_date.setText(complainobj.getComplain_date());
        admin_complain_status.setText(complainobj.getComplain_status());
        admin_complain_v_date.setText(complainobj.getComplain_v_date());
        admin_complain_v_time.setText(complainobj.getComplain_v_time());
        admin_complain_date_time.setText(complainobj.getComplain_date_time());

    }

}
