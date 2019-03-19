package com.customerservice.login.Admin;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.customerservice.login.ClassFiles.Funds;
import com.customerservice.login.ClassFiles.User;
import com.customerservice.login.R;

public class OwnerDetailsActivity extends AppCompatActivity {

   TextView owner_name,owner_contact,owner_email,owner_status,owner_password,owner_is_block,owner_regis_date_time,owner_aadhar_number;
   User objectOwner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        owner_name=(TextView) findViewById(R.id.owner_name);
        owner_contact=(TextView) findViewById(R.id.owner_contact);
        owner_email=(TextView) findViewById(R.id.owner_email);
        owner_password=(TextView) findViewById(R.id.owner_password);
        owner_status=(TextView) findViewById(R.id.owner_status);
        owner_is_block=(TextView) findViewById(R.id.owner_is_block);
        owner_regis_date_time=(TextView) findViewById(R.id.owner_regis_date_time);
        owner_aadhar_number=(TextView) findViewById(R.id.owner_aadhar_number);


        objectOwner=(User)getIntent().getSerializableExtra("objectOwner");

        owner_name.setText(objectOwner.getUser_name());
        owner_contact.setText(objectOwner.getUser_contact());
        owner_email.setText(objectOwner.getUser_email());
        owner_password.setText(objectOwner.getUser_password());
        owner_status.setText(objectOwner.getUser_is_available());
        owner_is_block.setText(objectOwner.getUser_is_block());
        owner_regis_date_time.setText(objectOwner.getRegis_date_time());
        owner_aadhar_number.setText(objectOwner.getAadhar_number());
        owner_aadhar_number.setText(objectOwner.getAadhar_number());




        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
