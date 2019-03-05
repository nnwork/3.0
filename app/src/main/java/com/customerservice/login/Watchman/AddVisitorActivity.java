package com.customerservice.login.Watchman;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.customerservice.login.Admin.AdminAddFlatActivity;
import com.customerservice.login.Admin.AdminAddUserActivity;
import com.customerservice.login.R;
import com.customerservice.login.Utility.Config;
import com.customerservice.login.Utility.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddVisitorActivity extends AppCompatActivity {

        Spinner spinBuildingID,spinFlatid;
        EditText visitorname,visitorcontact;
        Button addvisitor;
        ArrayList<String> buildingNameList=new ArrayList<>();
        ArrayList<String> buildingIdList=new ArrayList<>();

        String BuildingId,BuildingName,Flatid,FlatNumber;
        ArrayList<String> FlatIDlist=new ArrayList<>();
        ArrayList<String> FlatNumberlist=new ArrayList<>();
        ArrayAdapter adapter,arrayAdapter;

        SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_visitor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinBuildingID = (Spinner) findViewById(R.id.spinBuildingID);
        spinFlatid = (Spinner) findViewById(R.id.spinFlatid);
        visitorname = (EditText) findViewById(R.id.visitorname);
        visitorcontact = (EditText) findViewById(R.id.visitorname);
        addvisitor = (Button) findViewById(R.id.addvisitor);
        sessionManager = new SessionManager(this);
        StringRequest s = new StringRequest(Request.Method.POST, Config.READ_BUILDINGS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject obj = array.getJSONObject(i);

                        BuildingId = obj.getString("BuildingId");
                        BuildingName = obj.getString("BuildingName");

                        buildingNameList.add(BuildingName);
                        buildingIdList.add(BuildingId);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    adapter = new ArrayAdapter(AddVisitorActivity.this, android.R.layout.simple_dropdown_item_1line, buildingNameList);
                    spinBuildingID.setAdapter(adapter);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue q = Volley.newRequestQueue(AddVisitorActivity.this);
        q.add(s);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.READ_Flat, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject = array.getJSONObject(i);

                        Flatid = jsonObject.getString("FlatId");
                        FlatNumber = jsonObject.getString("FlatNumber");

                        FlatIDlist.add(Flatid);
                        FlatNumberlist.add(FlatNumber);


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    arrayAdapter = new ArrayAdapter(AddVisitorActivity.this, android.R.layout.simple_dropdown_item_1line, FlatNumberlist);
                    spinFlatid.setAdapter(arrayAdapter);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(AdminAddUserActivity.this, "", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(AddVisitorActivity.this);
        queue.add(stringRequest);
        //FlatNumerEnd

        addvisitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringRequest request = new StringRequest(Request.Method.POST, Config.ADD_tbl_Visitors, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       // Toast.makeText(AddVisitorActivity.this, "Insert Successfull" + response, Toast.LENGTH_SHORT).show();
                        if (response.equals("successfully")) {
                            Toast.makeText(AddVisitorActivity.this, "Insert Successfull", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AddVisitorActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddVisitorActivity.this, "Server Error" + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        int position = spinFlatid.getSelectedItemPosition();
                        String fid = FlatNumberlist.get(position);
                        String Visitorname = visitorname.getText().toString();
                        String Visitorcontact = visitorcontact.getText().toString();
                        params.put("visitors_flat_id",fid);
                        params.put("Visitorname",Visitorname);
                        params.put("visitors_contect",Visitorcontact);
                        params.put("visitors_watchman_id",sessionManager.getId());

                        return params;
                    }
                };
                RequestQueue newRequestQueue = Volley.newRequestQueue(AddVisitorActivity.this);
                newRequestQueue.add(request);

            }

        });


    }



}
