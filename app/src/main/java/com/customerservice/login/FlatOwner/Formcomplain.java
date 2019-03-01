package com.customerservice.login.FlatOwner;

import android.content.Intent;
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
import com.customerservice.login.Utility.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class Formcomplain extends AppCompatActivity {

    Button complainbtn;
    EditText complainproblem;
    Spinner complainfcatid;

    ArrayList<String> hcat_id_List=new ArrayList<>();
    ArrayList<String> hcat_name_List=new ArrayList<>();

    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formcomplain);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        complainbtn = (Button) findViewById(R.id.complainbtn);
        complainproblem = (EditText) findViewById(R.id.complainproblem);

        sessionManager = new SessionManager(this);
       //final Spinner spinner_complainstatus =(Spinner) findViewById(R.id.complainstatus);
       final Spinner spinner_complainfcatid=(Spinner)findViewById(R.id.complainfcatid);


        //Display helper_category
        StringRequest complains_halpercat_disp_rq=new StringRequest(Request.Method.POST, Config.Disp_tbl_halper_comlain, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Toast.makeText(Formcomplain.this, ""+response, Toast.LENGTH_SHORT).show();

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
        });
        RequestQueue disp_halpercat_queue=Volley.newRequestQueue(Formcomplain.this);
        disp_halpercat_queue.add(complains_halpercat_disp_rq);

        ////Diplay/////////////////////////////////////////////////////////////////////
        complainbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String  complainproblemstr = complainproblem.getText().toString();

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
                            Toast.makeText(Formcomplain.this, ""+sessionManager.getId(), Toast.LENGTH_SHORT).show();
                            //Toast.makeText(Formcomplain.this, "Response : "+response, Toast.LENGTH_SHORT).show();

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

                            co_params.put("userid",sessionManager.getId());


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