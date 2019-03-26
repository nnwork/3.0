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
import android.widget.Toast;

public class OTP extends AppCompatActivity {
    EditText otptext;
    Button otpsubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        otptext= (EditText)findViewById(R.id.otptext);
        otpsubmit=(Button)findViewById(R.id.btnSubmit);

        otpsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otpt=otptext.getText().toString();
                if(otpt.length()==0)
                {
                    Toast.makeText(getApplicationContext(), "Please Enter Mobile Number", Toast.LENGTH_SHORT).show();
                }
                else if(otpt.length()!=4)
                {
                    Toast.makeText(getApplicationContext(), "Please Enter Valid OTP", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    String otp="1234";

                    if(otpt.equals(otp))
                    {
                        Intent intent = new Intent(getApplicationContext(),PwdChange.class);
                        startActivity(intent);
                    }
                }
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
