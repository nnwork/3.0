package com.customerservice.login.Helper;

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

public class HelperComplainDetailsActivity extends AppCompatActivity {
    TextView helper_complain_display_id,helper_complain_display_user_id,helper_complain_display_hcat_id,helper_complain_display_problems;
    TextView helper_complain_display_img_uri,helper_complain_display_date,helper_complain_display_status,helper_complain_display_v_date;
    TextView helper_complain_display_v_time,helper_complain_display_date_time;
    ComplainClassFile classFileobj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper_complain_details);
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

        helper_complain_display_user_id=(TextView)findViewById(R.id.helper_complain_display_user_id);
        helper_complain_display_problems=(TextView)findViewById(R.id.helper_complain_display_problems);
        helper_complain_display_img_uri=(TextView)findViewById(R.id.helper_complain_display_img_uri);
        helper_complain_display_v_date=(TextView)findViewById(R.id.helper_complain_display_v_date);
        helper_complain_display_v_time=(TextView)findViewById(R.id.helper_complain_display_v_time);
        helper_complain_display_date=(TextView)findViewById(R.id.helper_complain_display_date);

        classFileobj=(ComplainClassFile) getIntent().getSerializableExtra("complainObject");

        StringRequest request=new StringRequest(Request.Method.POST, Config.READ_HelperUserComplain, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray array=new JSONArray(response);

                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject object=array.getJSONObject(i);
                        ComplainClassFile classFile=new ComplainClassFile();

                        String user_name=object.getString("user_name");
                        helper_complain_display_user_id.setText(user_name);

                        classFile.setComplain_problem(object.getString("complain_problem"));
                        classFile.setComplain_date(object.getString("complain_date"));
                        classFile.setComplain_problem(object.getString("complainObject"));

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
                params.put("complain_id",classFileobj.getComplain_id());
                return params;
            }
        };
        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(request);

        helper_complain_display_problems.setText(classFileobj.getComplain_problem());
        helper_complain_display_date.setText(classFileobj.getComplain_date());

    }
}
