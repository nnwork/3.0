package com.customerservice.login.FlatOwner;

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
import com.customerservice.login.Utility.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class OwnerSuggestion extends AppCompatActivity {

    Button suggetionbtn;
    EditText suggestiontext;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_suggestion);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sessionManager = new SessionManager(this);
        suggetionbtn=(Button)findViewById(R.id.suggetionbtn);
        suggestiontext=(EditText)findViewById(R.id.suggestiontext);

        suggetionbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String suggestionfield_str=suggestiontext.getText().toString();


                if (suggestionfield_str.length() == 0)
                {
                    Toast.makeText(OwnerSuggestion.this, "Please Enter suggestionFiled", Toast.LENGTH_SHORT).show();
                }
                else
                    {
                        StringRequest suggestion_rq=new StringRequest(Request.Method.POST,Config.ADD_tbl_suggestion, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if(response.equals("successfully"))
                                {
                                    Toast.makeText(OwnerSuggestion.this, "suggestion Inserted Successfully", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(OwnerSuggestion.this, "error", Toast.LENGTH_SHORT).show();
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
                                su_params.put("suggestion_text",suggestiontext.getText().toString());
                                su_params.put("userid",sessionManager.getId());
                                return su_params;
                            }
                        };
                        RequestQueue su_queue= Volley.newRequestQueue(OwnerSuggestion.this);
                        su_queue.add(suggestion_rq);
                    }

            }
        });
    }

}