package com.customerservice.login.Watchman;

import android.os.Bundle;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.customerservice.login.Admin.AdminAddFlatActivity;
import com.customerservice.login.Admin.AdminAddUserActivity;
import com.customerservice.login.R;
import com.customerservice.login.Utility.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AddVisitorActivity extends AppCompatActivity {

        Spinner spinBuildingID,spinFlatid;
        EditText visitorname;
        Button addvisitor;
        ArrayList<String> buildingNameList=new ArrayList<>();
        ArrayList<String> buildingIdList=new ArrayList<>();

        ArrayList<String> FlatIDlist=new ArrayList<>();
        ArrayList<String> FlatNumberlist=new ArrayList<>();
        ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_visitor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinBuildingID= (Spinner)findViewById(R.id.spinBuildingID);
        spinFlatid=(Spinner)findViewById(R.id.spinFlatid);
        visitorname=(EditText)findViewById(R.id.visitorname);


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
                    adapter=new ArrayAdapter(AddVisitorActivity.this,android.R.layout.simple_dropdown_item_1line,buildingNameList);
                    spinBuildingID.setAdapter(adapter);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue q= Volley.newRequestQueue(AddVisitorActivity.this);
        q.add(s);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.READ_Flat, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array=new JSONArray(response);
                    for (int i=0;i<array.length();i++)
                    {
                        JSONObject jsonObject = array.getJSONObject(i);

                        String Flatid=jsonObject.getString("FlatId");
                        String FlatNumber=jsonObject.getString("FlatNumber");

                        FlatIDlist.add(Flatid);
                        FlatNumberlist.add(FlatNumber);


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                finally {
                    adapter = new ArrayAdapter(AddVisitorActivity.this,android.R.layout.simple_dropdown_item_1line, FlatNumberlist);
                    spinFlatid.setAdapter(adapter);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(AdminAddUserActivity.this, "", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue=Volley.newRequestQueue(AddVisitorActivity.this);
        queue.add(stringRequest);
        //FlatNumerEnd



    }

}
