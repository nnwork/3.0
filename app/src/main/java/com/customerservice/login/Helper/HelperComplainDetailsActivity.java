package com.customerservice.login.Helper;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.customerservice.login.ClassFiles.ComplainClassFile;
import com.customerservice.login.R;
import com.customerservice.login.Utility.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HelperComplainDetailsActivity extends AppCompatActivity {
    TextView helper_complain_display_user_name,helper_complain_display_problems,helper_complain_display_user_contact;
    TextView helper_complain_display_date,helper_complain_display_status,helper_complain_display_date_time,helper_complain_building_name,helper_complain_flatnumber;
    ImageView helper_complain_display_img_uri;
    ComplainClassFile complainObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper_complain_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        helper_complain_display_user_name=(TextView)findViewById(R.id.helper_complain_display_user_name);
        helper_complain_display_user_contact=(TextView)findViewById(R.id.helper_complain_display_user_contact);
        //helper_complain_display_problems=(TextView)findViewById(R.id.helper_complain_display_problems);
        helper_complain_display_img_uri=(ImageView)findViewById(R.id.helper_complain_display_img_uri);
        helper_complain_display_date_time=(TextView)findViewById(R.id.helper_complain_display_date_time);
       helper_complain_display_status=(TextView)findViewById(R.id.helper_complain_display_status);
        helper_complain_display_problems=(TextView)findViewById(R.id.helper_complain_display_problems);
        helper_complain_flatnumber=(TextView)findViewById(R.id.helper_complain_flatnumber);
        helper_complain_building_name=(TextView)findViewById(R.id.helper_complain_building_name);

        complainObject=(ComplainClassFile) getIntent().getSerializableExtra("complainObject");

        StringRequest request=new StringRequest(Request.Method.POST, Config.READ_HelperUserComplain, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray array=new JSONArray(response);

                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject object=array.getJSONObject(i);
                        ComplainClassFile classFile=new ComplainClassFile();
                        helper_complain_flatnumber.setText(object.getString("FlatNumber"));
                        helper_complain_building_name.setText(object.getString("BuildingId"));

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

        Glide.with(HelperComplainDetailsActivity.this).load(Config.BASE_URL+"complain/"+complainObject.getComplain_img_uri()).into(helper_complain_display_img_uri);
         helper_complain_display_user_name.setText(complainObject.getUser_name());
         helper_complain_display_user_contact.setText(complainObject.getUser_contact());
         helper_complain_display_date_time.setText(complainObject.getComplain_date_time());
         helper_complain_display_status.setText(complainObject.getComplain_status());
        helper_complain_display_problems.setText(complainObject.getComplain_problem());

    }
}
