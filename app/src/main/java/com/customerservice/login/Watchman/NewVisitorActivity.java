package com.customerservice.login.Watchman;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.customerservice.login.R;

public class NewVisitorActivity extends AppCompatActivity {
    Button btuaddvisitor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_visitor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        btuaddvisitor = (Button) findViewById(R.id.btuaddvisitor);
        btuaddvisitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewVisitorActivity.this,AddVisitorActivity.class);
                startActivity(intent);
            }
        });

    }

}
