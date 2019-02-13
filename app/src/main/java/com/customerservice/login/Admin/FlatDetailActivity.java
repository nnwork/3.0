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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.customerservice.login.ClassFiles.Flat;
import com.customerservice.login.R;
import com.customerservice.login.Utility.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FlatDetailActivity extends AppCompatActivity {

    TextView txtFlatNumber,txtusername,txtusercontact,txtuseremail,txtuserpassword,txtuser_is_block,txtuser_is_rent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flat_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txtFlatNumber=(TextView)findViewById(R.id.txtFlatNumber);
        txtusername=(TextView)findViewById(R.id.txtusername);
        txtuseremail=(TextView)findViewById(R.id.txtuseremail);
        txtusercontact=(TextView)findViewById(R.id.txtusercontact);
        txtuserpassword=(TextView)findViewById(R.id.txtuserpassword);
        txtuser_is_block=(TextView)findViewById(R.id.txtuser_is_block);
        txtuser_is_rent=(TextView)findViewById(R.id.txtuser_is_rent);
       // txtFlatNumber=(TextView)findViewById(R.id.txtFlatNumber);





        final String FlatId = getIntent().getStringExtra("FlatId");
        final String FlatNumber = getIntent().getStringExtra("FlatNumber");


        Toast.makeText(FlatDetailActivity.this, "Rsponse"+FlatId, Toast.LENGTH_SHORT).show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.READ_FlatUserDetail, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(FlatDetailActivity.this, "Rsponse"+response, Toast.LENGTH_SHORT).show();
                Toast.makeText(FlatDetailActivity.this, "Rsponse"+response, Toast.LENGTH_SHORT).show();
                Toast.makeText(FlatDetailActivity.this, "Rsponse"+response, Toast.LENGTH_SHORT).show();
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String user_name = jsonObject.getString("user_name");
                        String user_contact= jsonObject.getString("user_contact");
                        String user_email= jsonObject.getString("user_email");
                        String user_password= jsonObject.getString("user_password");
                        String user_is_block= jsonObject.getString("user_is_block");
                        String user_is_rent= jsonObject.getString("user_is_rent");

                        txtFlatNumber.setText(FlatNumber);
                        txtusername.setText(user_name);
                        txtusercontact.setText(user_contact);
                        txtuseremail.setText(user_email);
                        txtuserpassword.setText(user_password);
                        txtuser_is_block.setText(user_is_block);
                        txtuser_is_rent.setText(user_is_rent);



                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params = new HashMap<>();
                params.put("FlatId",FlatId);
                return  params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(FlatDetailActivity.this);
        queue.add(stringRequest);

    }

}
