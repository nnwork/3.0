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
    TextView halltitle,hallcapacity,hallrent;
    Button payment;

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
        payment= findViewById(R.id.payment);

        hallObject = (Hall)getIntent().getSerializableExtra("hallObject");

        halltitle.setText(hallObject.getHall_title());
        hallcapacity.setText(hallObject.getHall_capacity());
        hallrent.setText(hallObject.getHallrent());


        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OwnerHallDetailActivity.this,FormHallBooking.class);
                intent.putExtra("hallObject",hallObject);
                startActivity(intent);
            }
        });

    }

}
