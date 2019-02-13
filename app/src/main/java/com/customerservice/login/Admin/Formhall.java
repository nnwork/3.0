package com.customerservice.login.Admin;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.customerservice.login.R;
import com.customerservice.login.Utility.Config;

import java.util.HashMap;
import java.util.Map;



public class Formhall extends AppCompatActivity {

    Button hallbtn;
    EditText halltitle,hallcapacity;
    Button hallimg1,hallimg2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formhall);
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

        hallbtn=(Button)findViewById(R.id.hallbtn);

        halltitle=(EditText)findViewById(R.id.halltitle);
        hallcapacity=(EditText)findViewById(R.id.hallcapacity);

        hallimg1=(Button)findViewById(R.id.hallimg1);
        hallimg2=(Button)findViewById(R.id.hallimg2);

        hallbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String halltitlestr = halltitle.getText().toString();

                final String hallcapacitystr = hallcapacity.getText().toString();


                if (halltitlestr.length() == 0) {
                    Toast.makeText(Formhall.this, "Please Enter Hall Title", Toast.LENGTH_SHORT).show();
                } else if (hallcapacitystr.length() == 0) {
                    Toast.makeText(Formhall.this, "Please Enter Hall Capacity", Toast.LENGTH_SHORT).show();
                } else
                    {
                        StringRequest hall_rq=new StringRequest(Request.Method.POST, Config.ADD_tbl_hall, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if(response.equals("successfully"))
                                {
                                    Toast.makeText(Formhall.this, "Hall Record Inserted Successfully", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(Formhall.this, "error", Toast.LENGTH_SHORT).show();
                                }

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }){
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String,String> hall_params=new HashMap<>();
                                hall_params.put("halltitle",halltitlestr);
                                hall_params.put("hallcapacity",hallcapacitystr);
                                return hall_params;
                            }
                        };
                        RequestQueue hall_queue= Volley.newRequestQueue(Formhall.this);
                        hall_queue.add(hall_rq);
                    }
            }
        });
    }
}
