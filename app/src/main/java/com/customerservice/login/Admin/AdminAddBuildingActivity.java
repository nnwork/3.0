package com.customerservice.login.Admin;

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
import android.widget.Spinner;
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

public class AdminAddBuildingActivity extends AppCompatActivity {
    EditText edtBuildingName;
    Button btnUserSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_building);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        edtBuildingName=(EditText) findViewById(R.id.edtBuildingName);
        btnUserSubmit=(Button)findViewById(R.id.btnUserSubmit);

        btnUserSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtBuildingName.getText().toString().length()==0)
                {
                    Toast.makeText(AdminAddBuildingActivity.this, "Enter Building Name", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //server Request
                    StringRequest str=new StringRequest(Request.Method.POST, Config.ADD_BUILDING, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(response.equals("success"))
                            {
                                Toast.makeText(AdminAddBuildingActivity.this, "Building Added Successfully!", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(AdminAddBuildingActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(AdminAddBuildingActivity.this, "Server Error : "+error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params=new HashMap<>();
                            params.put("BuildingName",edtBuildingName.getText().toString());
                            return params;
                        }
                    };

                    RequestQueue q= Volley.newRequestQueue(AdminAddBuildingActivity.this);
                    q.add(str);
                    //server Request
                }
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}