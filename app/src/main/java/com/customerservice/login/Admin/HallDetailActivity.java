package com.customerservice.login.Admin;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.customerservice.login.ClassFiles.Hall;
import com.customerservice.login.FlatOwner.FormHallBooking;
import com.customerservice.login.FlatOwner.OwnerHallDetailActivity;
import com.customerservice.login.R;

public class HallDetailActivity extends AppCompatActivity {
    TextView halltitle,hallcapacity,hallstatus,hallrent;
    Button payment;
    Hall hallObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ViewFlipper viewFlipper = (ViewFlipper)findViewById(R.id.flipperid);
        viewFlipper.startFlipping();

        halltitle = findViewById(R.id.halltitle);
        hallcapacity= findViewById(R.id.hallcapacity);
        hallstatus= findViewById(R.id.hallstatus);
        hallrent= findViewById(R.id.hallrent);
        payment=findViewById(R.id.payment);

        hallObject = (Hall)getIntent().getSerializableExtra("hallObject");
        halltitle.setText(hallObject.getHall_title());
        hallcapacity.setText(hallObject.getHall_capacity());
        //hallstatus.setText(hallObject.getHall_booking_status());
        hallrent.setText(hallObject.getHallrent());



    payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HallDetailActivity.this, HallBookingViewActivity.class);
                intent.putExtra("hall_id",hallObject.getHall_id());

                startActivity(intent);
                Toast.makeText(HallDetailActivity.this, ""+hallObject.getHall_id(), Toast.LENGTH_SHORT).show();

            }
        });
    }

}


