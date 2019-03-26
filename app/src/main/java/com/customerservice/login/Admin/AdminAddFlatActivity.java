package com.customerservice.login.Admin;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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

public class AdminAddFlatActivity extends AppCompatActivity {
        Toolbar toolbar;
        EditText AddFlatNumberID, inputEmail, inputPassword;
        TextInputLayout input_layout_AddFlatNumberID;
        Spinner spinBuildingID;
        Button btnAddFlatSubmit;

        ArrayList<String> buildingNameList=new ArrayList<>();
        ArrayList<String> buildingIdList=new ArrayList<>();


        ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_flat);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        AddFlatNumberID=(EditText)findViewById(R.id.AddFlatNumberID);
        spinBuildingID=(Spinner)findViewById(R.id.spinBuildingID);
        btnAddFlatSubmit=(Button)findViewById(R.id.btnAddFlatSubmit);


        //
        StringRequest s=new StringRequest(Request.Method.POST, Config.READ_BUILDINGS, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray array=new JSONArray(response);
                        for(int i=0;i<array.length();i++)
                        {
                            JSONObject obj=array.getJSONObject(i);

                            String BuildingId=obj.getString("BuildingId");
                            String BuildingName=obj.getString("BuildingName");

                            buildingNameList.add(BuildingName);
                            buildingIdList.add(BuildingId);
                        }
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                    finally
                    {
                        adapter=new ArrayAdapter(AdminAddFlatActivity.this,android.R.layout.simple_dropdown_item_1line,buildingNameList);
                        spinBuildingID.setAdapter(adapter);
                    }
                }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue q=Volley.newRequestQueue(AdminAddFlatActivity.this);
        q.add(s);
        //




        btnAddFlatSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AddFlatNumberID.getText().toString().length()==0)
                {
                            Toast.makeText(AdminAddFlatActivity.this, "Please Enter Valid Flat Number", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    StringRequest stringRequest=new StringRequest(Request.Method.POST, Config.ADD_FLAT, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                                if(response.equals("Success"))
                                {
                                    Toast.makeText(AdminAddFlatActivity.this, "Flat Added Success Fully", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(AdminAddFlatActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(AdminAddFlatActivity.this, "Server Error"+error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params=new HashMap<>();
                            int position = spinBuildingID.getSelectedItemPosition();
                            String bid=buildingIdList.get(position);
                            params.put("AddFlatNumberID",AddFlatNumberID.getText().toString());
                            params.put("BuildingId",bid);
                            return params;
                        }
                    };
                    RequestQueue requestQueue= Volley.newRequestQueue(AdminAddFlatActivity.this);
                    requestQueue.add(stringRequest);
                }
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}