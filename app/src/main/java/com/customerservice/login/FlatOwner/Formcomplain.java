package com.customerservice.login.FlatOwner;

import android.content.Intent;
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

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class Formcomplain extends AppCompatActivity {

    Button complainbtn;
    EditText complainid,complainproblem,complain_date,complain_v_date,complain_v_time,complain_datet_ime;
    Spinner complainstatus,complainuserid,complainfcatid;

    //ArrayList<String> buildingNameList=new ArrayList<>();
    //ArrayList<String> buildingIdList=new ArrayList<>();

    ArrayList<String> usre_id_List=new ArrayList<>();
    ArrayList<String> user_name_List=new ArrayList<>();

    ArrayList<String> hcat_id_List=new ArrayList<>();
    ArrayList<String> hcat_name_List=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formcomplain);
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

        complainbtn = (Button) findViewById(R.id.complainbtn);

        complainid = (EditText) findViewById(R.id.complainid);
        complainproblem = (EditText) findViewById(R.id.complainproblem);
        complain_date = (EditText) findViewById(R.id.complain_date);
        complain_v_date = (EditText) findViewById(R.id.complain_v_date);
        complain_v_time = (EditText) findViewById(R.id.complain_v_time);
        complain_datet_ime = (EditText) findViewById(R.id.complain_datet_ime);

       final Spinner spinner_complainstatus =(Spinner) findViewById(R.id.complainstatus);
       final Spinner spinner_complainfcatid=(Spinner)findViewById(R.id.complainfcatid);


        //Display helper_category
        StringRequest complains_halpercat_disp_rq=new StringRequest(Request.Method.POST, Config.Disp_tbl_halper_comlain, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray co_help_cat_JsonArray = new JSONArray(response);

                    for (int j = 0; j < co_help_cat_JsonArray.length(); j++) {

                        JSONObject co_help_cat_JsonObject = co_help_cat_JsonArray.getJSONObject(j);

                        String hcat_id = co_help_cat_JsonObject.getString("hcat_id");
                        String hcat_name = co_help_cat_JsonObject.getString("hcat_name");

                        hcat_id_List.add(hcat_id);
                        hcat_name_List.add(hcat_name);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                finally {
                    ArrayAdapter co_help_cat_ArrayAdapter=new ArrayAdapter(Formcomplain.this,android.R.layout.simple_dropdown_item_1line,hcat_name_List);
                    spinner_complainfcatid.setAdapter(co_help_cat_ArrayAdapter);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return super.getParams();

            }
        };
        RequestQueue disp_halpercat_queue=Volley.newRequestQueue(Formcomplain.this);
        disp_halpercat_queue.add(complains_halpercat_disp_rq);


        /////////////////////////////////////
        //Disp user complain
            StringRequest complains_user_disp_rq=new StringRequest(Request.Method.POST, Config.Disp_tbl_user_comlain, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray co_jsonArray=new JSONArray(response);
                        for(int i=0;i<co_jsonArray.length();i++)
                        {
                            JSONObject co_jsonObject=co_jsonArray.getJSONObject(i);


                            String usre_id=co_jsonObject.getString("usre_id");
                            String user_name=co_jsonObject.getString("user_name");

                            user_name_List.add(user_name);
                            usre_id_List.add(usre_id);


                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    finally {
                        ArrayAdapter co_arrayAdapter=new ArrayAdapter(Formcomplain.this,android.R.layout.simple_dropdown_item_1line,user_name_List);
                        spinner_complainstatus.setAdapter(co_arrayAdapter);
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    return super.getParams();
                }
            };
            RequestQueue co_user_disp_queue=Volley.newRequestQueue(Formcomplain.this);
        co_user_disp_queue.add(complains_user_disp_rq);

        ////Diplay/////////////////////////////////////////////////////////////////////
        complainbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String complainidstr = complainid.getText().toString();
               // Toast.makeText(Formcomplain.this, "complainidstr" + complainid, Toast.LENGTH_SHORT).show();

                final String  complainproblemstr = complainproblem.getText().toString();
               // Toast.makeText(Formcomplain.this, "complainproblemstr" + complainproblem, Toast.LENGTH_SHORT).show();

                String complain_datestr = complain_date.getText().toString();
               // Toast.makeText(Formcomplain.this, "complain_datestr" + complain_datestr, Toast.LENGTH_SHORT).show();

                String complain_v_datestr = complain_v_date.getText().toString();
               // Toast.makeText(Formcomplain.this, "complain_v_datestr" + complain_v_date, Toast.LENGTH_SHORT).show();

                String complain_v_timestr = complain_v_time.getText().toString();
              // Toast.makeText(Formcomplain.this, "complain_v_timestr" + complain_v_time, Toast.LENGTH_SHORT).show();

                String complain_datet_imestr = complain_datet_ime.getText().toString();
              //  Toast.makeText(Formcomplain.this, "complain_datet_imestr" + complain_datet_ime, Toast.LENGTH_SHORT).show();


//                if (complainidstr.length() == 0) {
//                    Toast.makeText(Formcomplain.this, "Please Enter complain ID", Toast.LENGTH_SHORT).show();
//                } else if (complainproblemstr.length() == 0) {
//                    Toast.makeText(Formcomplain.this, "Please Enter problem ID", Toast.LENGTH_SHORT).show();
//                } else if (complain_datestr.length() == 0) {
//                    Toast.makeText(Formcomplain.this, "Please Enter Complain Date", Toast.LENGTH_SHORT).show();
//                } else if (complain_v_datestr.length() == 0) {
//                    Toast.makeText(Formcomplain.this, "Please Enter Complain V Date", Toast.LENGTH_SHORT).show();
//                } else if (complain_v_timestr.length() == 0) {
//                    Toast.makeText(Formcomplain.this, "Please Enter Complain V Time", Toast.LENGTH_SHORT).show();
//                } else if (complain_datet_imestr.length() == 0) {
//                    Toast.makeText(Formcomplain.this, "Please Enter Date Time", Toast.LENGTH_SHORT).show();
//                }
//                else
//                {

                    StringRequest comlain_rq=new StringRequest(Request.Method.POST,Config.ADD_tbl_complain, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Toast.makeText(Formcomplain.this, "Response : "+response, Toast.LENGTH_SHORT).show();

                            if(response.equals("successfully"))
                            {
                                Toast.makeText(Formcomplain.this, "Complain Inserted Successfully", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(Formcomplain.this, "error", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Formcomplain.this, "Server Error : "+error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                        ////user table
                            Map<String,String> co_params=new HashMap<>();
                            co_params.put("complainproblem",complainproblemstr);

                            int user_position=spinner_complainstatus.getSelectedItemPosition();
                            String usreid=usre_id_List.get(user_position);
                            co_params.put("usreid",usreid);

                        //////helper_categori
                            int help_cat_position=spinner_complainfcatid.getSelectedItemPosition();
                            String hcatid=hcat_id_List.get(help_cat_position);
                            co_params.put("hcatid",hcatid);

                            return co_params;
                        }
                    };
                    RequestQueue co_queue=Volley.newRequestQueue(Formcomplain.this);
                    co_queue.add(comlain_rq);
                //}
            }
        });

    }
}