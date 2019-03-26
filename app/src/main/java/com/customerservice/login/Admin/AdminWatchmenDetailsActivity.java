package com.customerservice.login.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.customerservice.login.ClassFiles.User;
import com.customerservice.login.R;

public class AdminWatchmenDetailsActivity extends AppCompatActivity {
    TextView admin_usre_id,admin_watchmen_name,admin_watchmen_contact,admin_watchmen_email,admin_watchmen_password,admin_total_member;
    TextView admin_watchmen_is_block,admin_id,admin_watchmen_regis_date_time,admin_watchmen_aadhar_number,admin_aadhar_image_url,admin_watchmen_is_available;
    User watchmenUserobj;
    Button VisitorDetailsbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_watchmen_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        admin_usre_id=(TextView)findViewById(R.id.admin_usre_id);
        //admin_watchmen_name=(TextView)findViewById(R.id.admin_watchmen_name);
        admin_watchmen_contact=(TextView)findViewById(R.id.admin_watchmen_contact);

        admin_watchmen_email=(TextView)findViewById(R.id.admin_watchmen_email);
        //admin_watchmen_flat_id=(TextView)findViewById(R.id.admin_watchmen_flat_id);
        admin_watchmen_is_block=(TextView)findViewById(R.id.admin_watchmen_is_block);

        admin_id=(TextView)findViewById(R.id.admin_id);
        admin_watchmen_regis_date_time=(TextView)findViewById(R.id.admin_watchmen_regis_date_time);
        admin_watchmen_aadhar_number=(TextView)findViewById(R.id.admin_watchmen_aadhar_number);

        admin_aadhar_image_url=(TextView)findViewById(R.id.admin_aadhar_image_url);
        admin_total_member=(TextView)findViewById(R.id.admin_total_member);
        admin_watchmen_is_available=(TextView)findViewById(R.id.admin_watchmen_is_available);
        watchmenUserobj=(User)getIntent().getSerializableExtra("watchmenUserobj");



        admin_usre_id.setText(watchmenUserobj.getUsre_id());
        //admin_watchmen_name.setText(watchmenUserobj.getUser_name());
        admin_watchmen_contact.setText(watchmenUserobj.getUser_contact());

        admin_watchmen_email.setText(watchmenUserobj.getUser_email());
        //admin_watchmen_flat_id.setText(watchmenUserobj.getUser_flat_id());
        admin_watchmen_is_block.setText(watchmenUserobj.getUser_is_block());

        admin_id.setText(watchmenUserobj.getAdmin_id());
        admin_watchmen_regis_date_time.setText(watchmenUserobj.getRegis_date_time());
        admin_watchmen_aadhar_number.setText(watchmenUserobj.getAadhar_number());

        admin_aadhar_image_url.setText(watchmenUserobj.getAadhar_image_url());
        admin_total_member.setText(watchmenUserobj.getTotal_member());
        admin_watchmen_is_available.setText(watchmenUserobj.getUser_is_available());


        VisitorDetailsbtn=(Button)findViewById(R.id.VisitorDetailsbtn);
        VisitorDetailsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminWatchmenDetailsActivity.this, ""+watchmenUserobj.getUsre_id(), Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(AdminWatchmenDetailsActivity.this,AdminVisitorActivity.class);

                //intent.putExtra("helperid",watchmenUserobj.getUsre_id());
                intent.putExtra("visitors_watchman_id",watchmenUserobj.getUsre_id());

                startActivity(intent);
            }
        });
    }

}
