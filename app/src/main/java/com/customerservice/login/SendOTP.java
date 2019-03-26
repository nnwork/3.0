package com.customerservice.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.customerservice.login.Admin.AdminAddEventActivity;
import com.customerservice.login.Utility.Config;

import java.util.HashMap;
import java.util.Map;

public class SendOTP extends AppCompatActivity {
    Button btnSendOtp;
    EditText user_contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_otp);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnSendOtp=(Button)findViewById(R.id.btnSendOtp);
        user_contact=(EditText)findViewById(R.id.user_contact);
        btnSendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String usercontact=user_contact.getText().toString();

                if(usercontact.length()!=10)
                {
                    Toast.makeText(getApplicationContext(), "Please Enter Valid Mobile Number", Toast.LENGTH_SHORT).show();
                }
                else
                {

                   btnSendOtp.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.forgotpassword, new Response.Listener<String>() {
                               @Override
                               public void onResponse(String response) {
                                   if(response.equals("success"))
                                   {
                                       Toast.makeText(SendOTP.this, "Your Password send to your mobile number", Toast.LENGTH_SHORT).show();
                                   }
                                   else
                                   {
                                       Toast.makeText(SendOTP.this, "Error", Toast.LENGTH_SHORT).show();
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

                                   params.put("user_contact",usercontact);
                                   // params.put("EventDateTime",EventDateTime);
                                   return params;
                               }
                           };
                           RequestQueue queue = Volley.newRequestQueue(SendOTP.this);
                           queue.add(stringRequest);
                       }
                   });
                }

    }
        });

    }
}