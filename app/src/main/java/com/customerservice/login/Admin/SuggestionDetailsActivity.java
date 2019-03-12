package com.customerservice.login.Admin;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.customerservice.login.ClassFiles.Suggestion;
import com.customerservice.login.R;

import java.util.HashMap;
import java.util.Map;

public class SuggestionDetailsActivity extends AppCompatActivity {

    TextView textsugesstion,textsugesstiondate,textusernname;
    Suggestion objectSuggestion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textsugesstion = (TextView)findViewById(R.id.textsugesstion);
        textsugesstiondate=(TextView)findViewById(R.id.textsugesstiondate);
        textusernname=(TextView)findViewById(R.id.textusernname);

        objectSuggestion = (Suggestion) getIntent().getSerializableExtra("objectSuggestion");

        textusernname.setText(objectSuggestion.getUser_name());
        textsugesstion.setText(objectSuggestion.getSuggestion_text());
        textsugesstiondate.setText(objectSuggestion.getSuggestion_date_time());




    }

}
