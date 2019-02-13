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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class FormSuggestion extends AppCompatActivity {

    Button suggetionbtn;
    EditText suggestionid,suggestionfield,suggestiondatetime;
    Spinner suggestionuserid;

    ArrayList<String> usre_id_list=new ArrayList<>();
    ArrayList<String>user_name_list=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_suggestion);
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

        suggetionbtn=(Button)findViewById(R.id.suggetionbtn);

        suggestionid=(EditText)findViewById(R.id.suggestionid);
        suggestionfield=(EditText)findViewById(R.id.suggestionfield);
        suggestiondatetime=(EditText)findViewById(R.id.suggestiondatetime);

        suggestionuserid=(Spinner)findViewById(R.id.suggestionuserid);

        /////////////////////////DISP USER ID //////////////////////////////////////////
        StringRequest user_disp_rq=new StringRequest(Request.Method.POST, Config.Disp_tbl_user_suggestion, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray user_disp_jsonArray=new JSONArray(response);
                    for (int i=0;i<user_disp_jsonArray.length();i++)
                    {
                        JSONObject user_disp_jsonObject=user_disp_jsonArray.getJSONObject(i);

                        String usre_id=user_disp_jsonObject.getString("usre_id");
                        String user_name=user_disp_jsonObject.getString("user_name");

                        usre_id_list.add(usre_id);
                        user_name_list.add(user_name);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                finally {
                    ArrayAdapter user_disp_adapter=new ArrayAdapter(FormSuggestion.this,android.R.layout.simple_dropdown_item_1line,user_name_list);
                    suggestionuserid.setAdapter(user_disp_adapter);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue user_disp_queue=Volley.newRequestQueue(FormSuggestion.this);
        user_disp_queue.add(user_disp_rq);

        /////////////////////////DISP USER ID //////////////////////////////////////////


        suggetionbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String suggestionid_str=suggestionid.getText().toString();
                Toast.makeText(FormSuggestion.this, "suggestionid_str"+suggestionid, Toast.LENGTH_SHORT).show();

                String suggestionfield_str=suggestionfield.getText().toString();
                Toast.makeText(FormSuggestion.this, "suggestionfield_str"+suggestionfield, Toast.LENGTH_SHORT).show();

                String suggestiondatetime_str=suggestiondatetime.getText().toString();
                Toast.makeText(FormSuggestion.this, "suggestiondatetime_str"+suggestiondatetime, Toast.LENGTH_SHORT).show();

                if(suggestionid_str.length() == 0)
                {
                    Toast.makeText(FormSuggestion.this, "Please Enter suggestionid", Toast.LENGTH_SHORT).show();
                }
                else if (suggestionfield_str.length() == 0)
                {
                    Toast.makeText(FormSuggestion.this, "Please Enter suggestionFiled", Toast.LENGTH_SHORT).show();
                }
                else if (suggestiondatetime_str.length() == 0)
                {
                    Toast.makeText(FormSuggestion.this, "Please  Enter suggestiondate & time", Toast.LENGTH_SHORT).show();
                }
                else
                    {
                        StringRequest suggestion_rq=new StringRequest(Request.Method.POST,Config.ADD_tbl_suggestion, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if(response.equals("successfully"))
                                {
                                    Toast.makeText(FormSuggestion.this, "suggestion Inserted Successfully", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(FormSuggestion.this, "error", Toast.LENGTH_SHORT).show();
                                }

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }){
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String,String> su_params=new HashMap<>();
                                su_params.put("suggestionfield",suggestionfield.getText().toString());

                                int user_position=suggestionuserid.getSelectedItemPosition();
                                String usreid=usre_id_list.get(user_position);
                                su_params.put("usreid",usreid);

                                return su_params;
                            }
                        };
                        RequestQueue su_queue= Volley.newRequestQueue(FormSuggestion.this);
                        su_queue.add(suggestion_rq);
                    }

            }
        });
    }

}