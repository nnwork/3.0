package com.customerservice.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SendOTP extends AppCompatActivity {
    Button btnSendOtp;
    EditText sendotp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_otp);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnSendOtp=(Button)findViewById(R.id.btnSendOtp);
        sendotp=(EditText)findViewById(R.id.sendotp);
        btnSendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Sendotp=sendotp.getText().toString();

                if(Sendotp.length()==0)
                {
                    Toast.makeText(getApplicationContext(), "Please Enter OTP", Toast.LENGTH_SHORT).show();
                }
                else if(Sendotp.length()!=10)
                {
                    Toast.makeText(getApplicationContext(), "Please Enter Valid Mobile Number", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    String MobileNumber="8460467288";

                    if(MobileNumber.equals(Sendotp))
                    {
                        Intent intent = new Intent(getApplicationContext(),OTP.class);
                        startActivity(intent);
                    }
                }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
        });
    }
}