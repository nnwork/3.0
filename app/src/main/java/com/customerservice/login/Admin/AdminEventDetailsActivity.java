package com.customerservice.login.Admin;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.customerservice.login.ClassFiles.Events;
import com.customerservice.login.R;

public class AdminEventDetailsActivity extends AppCompatActivity {


    TextView txteventtitle,txteventdesc;


    Events eventObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_event_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txteventtitle=(TextView)findViewById(R.id.txteventtitle);
        txteventdesc=(TextView)findViewById(R.id.txteventdesc);

        eventObject=(Events) getIntent().getSerializableExtra("eventObject");
        toolbar.setTitle(eventObject.getEvent_title());

        txteventtitle.setText(eventObject.getEvent_title());


    }

}
