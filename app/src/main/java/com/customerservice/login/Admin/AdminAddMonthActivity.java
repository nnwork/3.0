package com.customerservice.login.Admin;

import android.os.Bundle;
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

public class AdminAddMonthActivity extends AppCompatActivity {
    Toolbar toolbar;
    EditText AddMonthID,AddAmountID;
    TextInputLayout input_layout_AddMonthID,input_layout_AddAmountID;
    Button  btnUserSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_month);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AddMonthID=(EditText)findViewById(R.id.AddMonthID);
        AddAmountID=(EditText)findViewById(R.id.AddAmountID);

        input_layout_AddMonthID=(TextInputLayout)findViewById(R.id.input_layout_AddMonthID);
        input_layout_AddAmountID=(TextInputLayout)findViewById(R.id.input_layout_AddAmountID);

        btnUserSubmit=(Button) findViewById(R.id.btnUserSubmit);

        btnUserSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AddMonthID.getText().toString().length()==0)
                {
                    Toast.makeText(AdminAddMonthActivity.this, "Enter ValidData", Toast.LENGTH_SHORT).show();
                }
                else if(AddAmountID.getText().toString().length()==0){
                    Toast.makeText(AdminAddMonthActivity.this, "Enter ValidData", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    StringRequest stringRequest=new StringRequest(Request.Method.POST, Config.ADD_Month, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(response.equals("Success"))
                            {
                                Toast.makeText(AdminAddMonthActivity.this, "Success", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(AdminAddMonthActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(AdminAddMonthActivity.this, "Server Error : "+error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params=new HashMap<>();
                            params.put("AddMonthID",AddMonthID.getText().toString());
                            params.put("AddAmountID",AddAmountID.getText().toString());
                            return params;
                        }
                    };

                    RequestQueue requestQueue= Volley.newRequestQueue(AdminAddMonthActivity.this);
                    requestQueue.add(stringRequest);
                }


            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
