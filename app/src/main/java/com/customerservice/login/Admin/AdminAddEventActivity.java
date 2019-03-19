package com.customerservice.login.Admin;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

public class AdminAddEventActivity extends AppCompatActivity {
    Spinner SpinAddCatID;
    EditText AddEventTitle,AddEventDesc,AddEventVenue,AddedDateTime;
    EditText AddEventAddress,AddEventLandmark,AddPincode,AddEvenDate,AddEventTime,AddEventSpeciality;
    Button btnUserSubmit;

    ArrayList<String> cat_id_list=new ArrayList<>();
    ArrayList<String> cat_name_list=new ArrayList<>();
    ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_event);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //EventSpinner
        SpinAddCatID=(Spinner)findViewById(R.id.SpinAddCat);
        //End

        //EventEdittextBegin
        AddEventTitle=(EditText)findViewById(R.id.AddEventTitle);
        AddEventDesc=(EditText)findViewById(R.id.AddEventDesc);
        AddEventVenue=(EditText)findViewById(R.id.AddEventVenue);
        AddEventAddress=(EditText)findViewById(R.id.AddEventAddress);
        AddEventLandmark=(EditText)findViewById(R.id.AddEventLandmark);
        AddPincode=(EditText)findViewById(R.id.AddPincode);
        AddEvenDate=(EditText)findViewById(R.id.AddEventDate);
        AddEventTime=(EditText)findViewById(R.id.AddEventTime);
        AddEventSpeciality=(EditText)findViewById(R.id.AddEventSpeciality);
        AddedDateTime=(EditText)findViewById(R.id.AddedDateTime);
        //End

        //ButtonSubmit
        btnUserSubmit=(Button)findViewById(R.id.btnUserSubmit);
        //End

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.READ_Category, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int  i=0;i<array.length();i++)
                    {
                        JSONObject jsonObject =array.getJSONObject(i);
                        String  cat_id = jsonObject.getString("cat_id");
                        String cat_name= jsonObject.getString("cat_name");

                        cat_id_list.add(cat_id);
                        cat_name_list.add(cat_name);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                finally {
                    arrayAdapter=new ArrayAdapter(AdminAddEventActivity.this,android.R.layout.simple_dropdown_item_1line,cat_name_list);
                    SpinAddCatID.setAdapter(arrayAdapter);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AdminAddEventActivity.this, "Server Error"+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue=Volley.newRequestQueue(AdminAddEventActivity.this);
        requestQueue.add(stringRequest);





        btnUserSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GetDataBegin
           final     String EventTitle = AddEventTitle.getText().toString();
           final     String EventDesc = AddEventDesc.getText().toString();
           final     String EventVenue = AddEventVenue.getText().toString();
           final     String EventAddress = AddEventAddress.getText().toString();
           final     String EventLandmark = AddEventLandmark.getText().toString();
           final     String EventPincode = AddPincode.getText().toString();
           final     String EventDate = AddEvenDate.getText().toString();
           final     String EventTime = AddEventTime.getText().toString();
           final     String EventSpeciality = AddEventSpeciality.getText().toString();
           final     String EventDateTime=AddedDateTime.getText().toString();

                //End

                if(EventTitle.length()==0)
                {
                    Toast.makeText(AdminAddEventActivity.this,"Enter Valid Input",Toast.LENGTH_SHORT).show();
                }
                else if(EventDesc.length()==0)
                {
                    Toast.makeText(AdminAddEventActivity.this,"Enter Valid Input",Toast.LENGTH_SHORT).show();
                }
                else if(EventVenue.length()==0)
                {
                    Toast.makeText(AdminAddEventActivity.this,"Enter Valid Input",Toast.LENGTH_SHORT).show();
                }
                else if(EventAddress.length()==0)
                {
                    Toast.makeText(AdminAddEventActivity.this,"Enter Valid Input",Toast.LENGTH_SHORT).show();
                }
                else if(EventLandmark.length()==0)
                {
                    Toast.makeText(AdminAddEventActivity.this,"Enter Valid Input",Toast.LENGTH_SHORT).show();
                }
                else if(EventPincode.length()==0)
                {
                    Toast.makeText(AdminAddEventActivity.this,"Enter Valid Input",Toast.LENGTH_SHORT).show();
                }
               else if(EventDate.length()==0)
                {
                   Toast.makeText(AdminAddEventActivity.this,"Enter Valid Input",Toast.LENGTH_SHORT).show();
                }
                else if(EventTime.length()==0)
                {
                    Toast.makeText(AdminAddEventActivity.this,"Enter Valid Input",Toast.LENGTH_SHORT).show();
                }
                else if(EventSpeciality.length()==0)
                {
                    Toast.makeText(AdminAddEventActivity.this,"Enter Valid Input",Toast.LENGTH_SHORT).show();
                }
                else if(EventDateTime.length()==0)
                {
                    Toast.makeText(AdminAddEventActivity.this,"Enter Valid Input",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    StringRequest stringRequest=new StringRequest(Request.Method.POST, Config.Add_Event, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(response.equals("Success"))
                            {
                                Toast.makeText(AdminAddEventActivity.this, "Success", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(AdminAddEventActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(AdminAddEventActivity.this, "Server Error"+error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params=new HashMap<>();
                            int position=SpinAddCatID.getSelectedItemPosition();
                            String cat_id=cat_id_list.get(position);
                            params.put("EventTitle",EventTitle);
                            params.put("EventDesc",EventDesc);
                            params.put("EventVenue",EventVenue);
                            params.put("EventAddress",EventAddress);
                            params.put("EventLandmark",EventLandmark);
                            params.put("EventPincode",EventPincode);
                           // params.put("EventDate",EventDate);
                           // params.put("EventTime",EventTime);
                            params.put("EventSpeciality",EventSpeciality);
                            params.put("cat_id",cat_id);
                           // params.put("EventDateTime",EventDateTime);

                            return params;
                        }
                    };

                    RequestQueue requestQueue= Volley.newRequestQueue(AdminAddEventActivity.this);
                    requestQueue.add(stringRequest);
                }


                Toast.makeText(AdminAddEventActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
