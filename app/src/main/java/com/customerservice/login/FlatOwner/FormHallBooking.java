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
import com.customerservice.login.ClassFiles.Hall;
import com.customerservice.login.R;
import com.customerservice.login.Utility.Config;
import com.customerservice.login.Utility.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class FormHallBooking extends AppCompatActivity {

    Button hallbookingbtn;
    EditText hallbookingsubject;
    TextView hallbookingamt,halltitle;
    Hall hallObject;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_hall_booking);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sessionManager = new SessionManager(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        hallbookingbtn=(Button)findViewById(R.id.hallbookingbtn);
        hallbookingamt=(TextView) findViewById(R.id.hallbookingamt);
        halltitle=(TextView) findViewById(R.id.halltitle);
        hallbookingsubject=(EditText)findViewById(R.id.hallbookingsubject);


        hallObject  =(Hall)getIntent().getSerializableExtra("hallObject");

        halltitle.setText(hallObject.getHall_title());
        hallbookingamt.setText(hallObject.getHallrent());
        Toast.makeText(this, "ddd"+hallbookingsubject.getText().toString(), Toast.LENGTH_SHORT).show();


        /////////////////////////DISP USER ID //////////////////////////////////////////
        hallbookingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                if (hallidstr.length() == 0) {
//                    Toast.makeText(FormHallBooking.this, "Please Enter Hall ID", Toast.LENGTH_SHORT).show();
//                } else if (hallbookingidstr.length() == 0) {
//                    Toast.makeText(FormHallBooking.this, "Please Enter HallBooking ID", Toast.LENGTH_SHORT).show();
//                } else if (hallbookingbookingdatestr.length() == 0) {
//                    Toast.makeText(FormHallBooking.this, "Please Enter HallBooking Date", Toast.LENGTH_SHORT).show();
//                } else if (hallbookingtimestr.length() == 0) {
//                    Toast.makeText(FormHallBooking.this, "Please Enter HallBooking times ID", Toast.LENGTH_SHORT).show();
//                } else if (hallbookingdatetimestr.length() == 0) {
//                    Toast.makeText(FormHallBooking.this, "Please Enter Date And Time ID", Toast.LENGTH_SHORT).show();
//                } else if (hallbookingamtstr.length() == 0) {
//                    Toast.makeText(FormHallBooking.this, "Please Enter Amount", Toast.LENGTH_SHORT).show();
//                } else if (hallbookingsubjectstr.length() == 0) {
//                    Toast.makeText(FormHallBooking.this, "Please Enter Subject", Toast.LENGTH_SHORT).show();
//                } else
//                    {
                       StringRequest hall_booking_re=new StringRequest(Request.Method.POST, Config.ADD_tbl_hall_booking, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if(response.equals("successfully"))
                                {
                                    Toast.makeText(FormHallBooking.this, "Hall_Booking Inserted Successfully", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(FormHallBooking.this, "error", Toast.LENGTH_SHORT).show();
                                }

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(FormHallBooking.this, "Server"+ error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }){
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String,String> hall_booking_params=new HashMap<>();
                                hall_booking_params.put("hallbookingamt",hallObject.getHallrent());
                                hall_booking_params.put("hallbookingsubject",hallbookingsubject.getText().toString());
                                
                                String hallid=hallObject.getHall_id();
                                hall_booking_params.put("hallid",hallid);
                                String usreid=sessionManager.getId();
                                hall_booking_params.put("usreid",usreid);

                                return hall_booking_params;
                            }
                        };
                        RequestQueue hall_booking_queue= Volley.newRequestQueue(FormHallBooking.this);
                        hall_booking_queue.add(hall_booking_re);
                    }
        });
    }

}
