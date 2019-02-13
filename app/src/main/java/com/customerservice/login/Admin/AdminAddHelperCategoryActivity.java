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

import java.util.HashMap;
import java.util.Map;

public class AdminAddHelperCategoryActivity extends AppCompatActivity {
    EditText AddedHelperCatNameID,AddedHelperIconID;
    Button btnUserSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_helper_category);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AddedHelperCatNameID = (EditText)findViewById(R.id.AddedHelperCatNameID);
        AddedHelperIconID = (EditText)findViewById(R.id.AddedHelperIconID);

        btnUserSubmit = (Button)findViewById(R.id.btnUserSubmit);

        btnUserSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AddedHelperCatNameID.getText().toString().length() == 0) {
                    Toast.makeText(AdminAddHelperCategoryActivity.this, "Enter Valid Helper Category", Toast.LENGTH_SHORT).show();
                }
                else if( AddedHelperIconID.getText().toString().length() == 0)
                {
                    Toast.makeText(AdminAddHelperCategoryActivity.this, "Enter Valid Helper Icon", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    StringRequest stringRequest=new StringRequest(Request.Method.POST, Config.ADD_HelperCategory, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(response.equals("Success"))
                            {
                                Toast.makeText(AdminAddHelperCategoryActivity.this, "Success", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(AdminAddHelperCategoryActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(AdminAddHelperCategoryActivity.this, "Server Error : "+error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map <String,String> params=new  HashMap<>();
                            params.put("AddedHelperCatNameID",AddedHelperCatNameID.getText().toString());
                            params.put("AddedHelperIconID",AddedHelperIconID.getText().toString());
                            return params;
                        }
                    };

                    RequestQueue requestQueue= Volley.newRequestQueue(AdminAddHelperCategoryActivity.this);
                    requestQueue.add(stringRequest);
                }
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
