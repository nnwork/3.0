package com.customerservice.login.Helper;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.customerservice.login.Admin.AdminDashboardActivity;
import com.customerservice.login.MainActivity;
import com.customerservice.login.R;
import com.customerservice.login.Utility.Config;
import com.customerservice.login.Utility.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import spencerstudios.com.fab_toast.FabToast;

public class HelperMyProfileActivity extends AppCompatActivity {
    SessionManager sessionManager;
    TextView user_name,user_contact,user_email,aadhar_number,user_pwd,heplercat;
    String hcat ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper_my_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        sessionManager = new SessionManager(this);
        user_name = (TextView) findViewById(R.id.user_name);
        user_contact = findViewById(R.id.user_contact);
        user_email = findViewById(R.id.user_email);
        aadhar_number =findViewById(R.id.aadhar_number);
        user_pwd = findViewById(R.id.user_pwd);
        heplercat = findViewById(R.id.heplercat);

        hcat = heplercat.getText().toString();





        //Toast.makeText(OwnerProfileActivity.this, "jjj", Toast.LENGTH_LONG).show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.READ_GetHelperprofile, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject =jsonArray.getJSONObject(i);

                        user_name.setText(jsonObject.getString("user_name"));
                        user_contact.setText(jsonObject.getString("user_contact"));
                        user_email.setText(jsonObject.getString("user_email"));
                        aadhar_number.setText(jsonObject.getString("aadhar_number"));
                        user_pwd.setText(jsonObject.getString("user_password"));
                        heplercat.setText(hcat.concat(" ").concat(jsonObject.getString("hcat_name")));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                FabToast.makeText(HelperMyProfileActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("usre_id",sessionManager.getId());
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(HelperMyProfileActivity.this);
        queue.add(stringRequest);
    }

}
