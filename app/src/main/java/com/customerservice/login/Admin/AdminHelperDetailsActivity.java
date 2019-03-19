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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.customerservice.login.ClassFiles.HelperCategory;
import com.customerservice.login.ClassFiles.User;
import com.customerservice.login.R;
import com.customerservice.login.Utility.Config;

import java.util.Map;

public class AdminHelperDetailsActivity extends AppCompatActivity {
    TextView admin_usre_id,admin_user_name,admin_user_contact,admin_user_email,admin_user_password,admin_user_flat_id,admin_total_member;
    TextView admin_user_is_block,admin_id,admin_user_regis_date_time,admin_user_aadhar_number,admin_aadhar_image_url,admin_user_is_available;
    User helperUserobj;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_helper_details);
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
        //admin_user_name=(TextView)findViewById(R.id.admin_user_name);
        admin_user_contact=(TextView)findViewById(R.id.admin_user_contact);

        admin_user_email=(TextView)findViewById(R.id.admin_user_email);
        admin_user_flat_id=(TextView)findViewById(R.id.admin_user_flat_id);
        admin_user_is_block=(TextView)findViewById(R.id.admin_user_is_block);

        admin_id=(TextView)findViewById(R.id.admin_id);
        admin_user_regis_date_time=(TextView)findViewById(R.id.admin_user_regis_date_time);
        admin_user_aadhar_number=(TextView)findViewById(R.id.admin_user_aadhar_number);

        admin_aadhar_image_url=(TextView)findViewById(R.id.admin_aadhar_image_url);
        admin_total_member=(TextView)findViewById(R.id.admin_total_member);
        admin_user_is_available=(TextView)findViewById(R.id.admin_user_is_available);

        helperUserobj=(User) getIntent().getSerializableExtra("helperUserobj");

//        StringRequest request=new StringRequest(Request.Method.POST, Config.Details_Admin_Helper, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//
//            }
//        };
//        RequestQueue queue=-Volley.newRequestQueue(this);
//        queue.add(request);
        admin_usre_id.setText(helperUserobj.getUsre_id());
        //admin_user_name.setText(helperUserobj.getUser_name());
        admin_user_contact.setText(helperUserobj.getUser_contact());

        admin_user_email.setText(helperUserobj.getUser_email());
        admin_user_flat_id.setText(helperUserobj.getUser_flat_id());
        admin_user_is_block.setText(helperUserobj.getUser_is_block());

        admin_id.setText(helperUserobj.getAdmin_id());
        admin_user_regis_date_time.setText(helperUserobj.getRegis_date_time());
        admin_user_aadhar_number.setText(helperUserobj.getAadhar_number());

        admin_aadhar_image_url.setText(helperUserobj.getAadhar_image_url());
        admin_total_member.setText(helperUserobj.getTotal_member());
        admin_user_is_available.setText(helperUserobj.getUser_is_available());

        btn=(Button)findViewById(R.id.ComaplainDetails);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminHelperDetailsActivity.this, ""+helperUserobj.getHcat_id(), Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(AdminHelperDetailsActivity.this,AdminComplainActivity.class);

                //intent.putExtra("helperid",helperUserobj.getUsre_id());
                intent.putExtra("helpercatid",helperUserobj.getHcat_id());
                startActivity(intent);
            }
        });

    }
}