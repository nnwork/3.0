package com.customerservice.login.Admin;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
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




public class Formexpence extends AppCompatActivity {

    Button expencebtn;
    EditText expencetitle,expenceamt,expencedate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formexpence);
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

        expencebtn=(Button)findViewById(R.id.expencebtn);

       // expenceid=(EditText) findViewById(R.id.expenceid);
        expencetitle=(EditText) findViewById(R.id.expencetitle);
        expenceamt=(EditText) findViewById(R.id.expenceamt);
        expencedate=(EditText) findViewById(R.id.expencedate);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        expencebtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               // String expenceidstr = expenceid.getText().toString();
                String expencetitlestr = expencetitle.getText().toString();
                String expenceamtstr = expenceamt.getText().toString();

                 if (expencetitlestr.length() == 0) {
                    Toast.makeText(Formexpence.this, "Please Enter expence Title", Toast.LENGTH_SHORT).show();
                } else if (expenceamtstr.length() == 0) {
                    Toast.makeText(Formexpence.this, "Please Enter Amount", Toast.LENGTH_SHORT).show();
            }
//                else if (expencedatestr.length() == 0) {
//                    Toast.makeText(Formexpence.this, "Please Enter Date", Toast.LENGTH_SHORT).show();
//                }
                 else
                    {
                        StringRequest expencetitleexrq=new StringRequest(Request.Method.POST, Config.ADD_tbl_expence, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if(response.equals("successfully"))
                                {
                                    Toast.makeText(Formexpence.this, "Expence Add Successfully", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(Formexpence.this, "error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(Formexpence.this, "Server Error"+error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }){
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String,String> exparams=new HashMap<>();
                                exparams.put("expencetitle",expencetitle.getText().toString());
                                exparams.put("expenceamt",expenceamt.getText().toString());
                                return exparams;
                            }
                        };
                        RequestQueue exqueue= Volley.newRequestQueue(Formexpence.this);
                        exqueue.add(expencetitleexrq);
                    }
            }
        });
    }

}
