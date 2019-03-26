package com.customerservice.login.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
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
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class FormFund extends AppCompatActivity {

    Toolbar toolbar;
    EditText fundid,fundtitle,fundamt,funddate;
    TextInputLayout input_layout_fundid_fundid,input_layout_fundid_fundtitle,input_layout_fundid_fundamt,input_layout_fundid_funddate;


    Button fundbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_fund);
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

        fundbtn=(Button)findViewById(R.id.fundbtn);

        fundid=(EditText)findViewById(R.id.fundid);
        fundtitle=(EditText)findViewById(R.id.fundtitle);
        fundamt=(EditText)findViewById(R.id.fundamt);
        funddate=(EditText)findViewById(R.id.funddate);

        input_layout_fundid_fundid=(TextInputLayout)findViewById(R.id.input_layout_fundid);
        input_layout_fundid_fundtitle=(TextInputLayout)findViewById(R.id.input_layout_fundtitle);
        input_layout_fundid_fundamt=(TextInputLayout)findViewById(R.id.input_layout_fundamt);
        input_layout_fundid_funddate=(TextInputLayout)findViewById(R.id.input_layout_funddate);

        fundbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String fundtitlestr = fundtitle.getText().toString();
                String fundamtstr = fundamt.getText().toString();
                String funddatestr = funddate.getText().toString();

                if (fundtitlestr.length() == 0) {
                    Toast.makeText(FormFund.this, "Please Enter Fund Title", Toast.LENGTH_SHORT).show();
                } else if (fundamtstr.length() == 0) {
                    Toast.makeText(FormFund.this, "Please Enter Fund Amount ", Toast.LENGTH_SHORT).show();
                } else if (funddatestr.length() == 0) {
                    Toast.makeText(FormFund.this, "Please Enter Fund Date", Toast.LENGTH_SHORT).show();
                } else
                    {
                        StringRequest fund_rq=new StringRequest(Request.Method.POST, Config.ADD_tbl_fund, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.equals("successfully"))
                                    {
                                        Toast.makeText(FormFund.this, "Form Inserted Successfully", Toast.LENGTH_SHORT).show();
                                    }
                                else
                                    {
                                        Toast.makeText(FormFund.this, "error", Toast.LENGTH_SHORT).show();
                                    }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }){
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String,String> fuparams=new HashMap<>();
                                fuparams.put("fundtitle",fundtitle.getText().toString());
                                fuparams.put("fundamt",fundamt.getText().toString());
                                return fuparams;
                            }
                        };
                        RequestQueue fuqueue= Volley.newRequestQueue(FormFund.this);
                        fuqueue.add(fund_rq);
                    }
            }
        });
    }
}
