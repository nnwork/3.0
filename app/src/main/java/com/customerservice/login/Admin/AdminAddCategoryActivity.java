package com.customerservice.login.Admin;

import android.os.Build;
import android.os.Bundle;
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

public class AdminAddCategoryActivity extends AppCompatActivity {
        EditText AddCategoryNameID;
        Button btnUserSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_category);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AddCategoryNameID=(EditText)findViewById(R.id.AddCategoryNameID);
        btnUserSubmit=(Button)findViewById(R.id.btnUserSubmit);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        btnUserSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AddCategoryNameID.getText().toString().length()==0) {
                    Toast.makeText(AdminAddCategoryActivity.this, "Enter Valid Category", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    StringRequest stringRequest=new StringRequest(Request.Method.POST, Config.ADD_Category, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                           // Toast.makeText(AdminAddCategoryActivity.this, response, Toast.LENGTH_SHORT).show();

                            if(response.equals("Success"))
                            {
                                Toast.makeText(AdminAddCategoryActivity.this, "Success", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(AdminAddCategoryActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(AdminAddCategoryActivity.this, "Server Error : "+error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params=new HashMap<>();
                            params.put("AddCategoryNameID",AddCategoryNameID.getText().toString());
                            return  params;
                        }
                    };

                    RequestQueue q= Volley.newRequestQueue(AdminAddCategoryActivity.this);
                    q.add(stringRequest);

                }
            }
        });

                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
