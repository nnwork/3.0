package com.customerservice.login.FlatOwner;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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

public class OwnerComplainDetailsActivity extends AppCompatActivity {
    TextView complain_id,complain_user_id,complain_fcat_id,complain_problem,complain_img_uri,complain_date,complain_status;
    TextView complain_v_date,complain_v_time,complain_date_time;
    ComplainClassFile complainObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_complain_details);
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

        complain_problem=(TextView)findViewById(R.id.owner_complain_display_problems);
        complain_img_uri=(TextView)findViewById(R.id.owner_complain_display_img_uri);
        complain_status=(TextView)findViewById(R.id.owner_complain_display_status);

        complain_user_id=(TextView)findViewById(R.id.owner_complain_display_user_id);
        complain_fcat_id=(TextView)findViewById(R.id.owner_complain_display_fcat_id);

        complainObject=(ComplainClassFile) getIntent().getSerializableExtra("complainObject");
   //     final  String complainid = getIntent().getStringExtra("complainid");

        StringRequest request=new StringRequest(Request.Method.POST, Config.READ_OwnerUserComplain, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(OwnerComplainDetailsActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                try {
                    JSONArray array=new JSONArray(response);

                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject object=array.getJSONObject(i);

                        String user_name=object.getString("user_name");
                        complain_user_id.setText(user_name);


                        String hcat_name=object.getString("hcat_name");
                        complain_fcat_id.setText(hcat_name);
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
                params.put("complain_id",complainObject.getComplain_id());
                return params;
            }
        };
        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(request);

        toolbar.setTitle(complainObject.getComplain_problem());
        complain_problem.setText(complainObject.getComplain_problem());
        complain_img_uri.setText(complainObject.getComplain_img_uri());
        complain_status.setText(complainObject.getComplain_status());

    }

}
