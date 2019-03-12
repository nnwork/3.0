package com.customerservice.login.FlatOwner;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.customerservice.login.Admin.HallBookingDetailActivity;
import com.customerservice.login.ClassFiles.Hall;
import com.customerservice.login.R;
import com.customerservice.login.Utility.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class OwnerHallDetailActivity extends AppCompatActivity {
    TextView halltitle,hallcapacity,hallrent,hallbookingusername,hallbookingdate,hallbookingtime,hallbookingsubject,hallbookingstatus;
    Button payment;
    String hallbookingid;
    Hall hallObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_hall_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewFlipper viewFlipper = (ViewFlipper)findViewById(R.id.flipperid);
        viewFlipper.startFlipping();

        halltitle = findViewById(R.id.halltitle);
        hallcapacity= findViewById(R.id.hallcapacity);
        hallrent= findViewById(R.id.hallrent);
        payment=findViewById(R.id.payment);
        hallbookingusername= findViewById(R.id.hallbookingusername);
        hallbookingdate= findViewById(R.id.hallbookingdate);
        hallbookingtime=findViewById(R.id.hallbookingtime);
        hallbookingusername= findViewById(R.id.hallbookingusername);
        hallbookingsubject= findViewById(R.id.hallbookingsubject);
        hallbookingstatus=findViewById(R.id.hallbookingstatus);


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.Disp_tbl_hall_booking, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i=0;i<array.length();i++)
                    {
                        JSONObject object= array.getJSONObject(i);
                        String string =object.getString("hall_booking_id");
                        hallbookingusername.setText(object.getString("user_name"));
                        halltitle.setText(object.getString("hall_title"));
                        hallcapacity.setText(object.getString("hall_capacity"));
                        hallbookingsubject.setText(object.getString("hall_booking_subject"));
                        hallbookingstatus.setText(object.getString("hall_booking_status"));
                        hallbookingdate.setText(object.getString("hall_booking_b_time"));
                        hallbookingtime.setText(object.getString("hall_booking_date_time"));
                        hallrent.setText(object.getString("hallrent"));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(OwnerHallDetailActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params = new HashMap<>();
               // params.put("hall_booking_id",);
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(OwnerHallDetailActivity.this);
        queue.add(stringRequest);

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OwnerHallDetailActivity.this,FormHallBooking.class);
                startActivity(intent);
            }
        });

    }

}
