package com.customerservice.login.FlatOwner;

import android.content.Intent;
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
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.customerservice.login.ClassFiles.Hall;
import com.customerservice.login.R;

public class OwnerHallDetailActivity extends AppCompatActivity {
        TextView txtHalltitle,txthallcapacity;
        Button payment;
        Hall hallObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_hall_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ViewFlipper viewFlipper = (ViewFlipper)findViewById(R.id.flipperid);
        viewFlipper.startFlipping();

        txtHalltitle = findViewById(R.id.txtHalltitle);
        txthallcapacity= findViewById(R.id.txthallcapacity);
        payment=findViewById(R.id.payment);

        hallObject = (Hall)getIntent().getSerializableExtra("hallObject");
        txtHalltitle.setText(hallObject.getHall_title());
        txthallcapacity.setText(hallObject.getHall_capacity());

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OwnerHallDetailActivity.this,HallBookingActivity.class);
                startActivity(intent);
            }
        });
    }


}
