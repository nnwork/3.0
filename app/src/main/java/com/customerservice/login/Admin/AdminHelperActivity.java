package com.customerservice.login.Admin;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.customerservice.login.Adapters.HelperCategoryAdapter;
import com.customerservice.login.ClassFiles.HelperCategory;
import com.customerservice.login.ClassFiles.User;
import com.customerservice.login.R;
import com.customerservice.login.Utility.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AdminHelperActivity extends AppCompatActivity {
    ListView listView;
    List<User>userList = new ArrayList<>();
    HelperCategoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listView=(ListView)findViewById(R.id.helperviewlist111);

        adapter=new HelperCategoryAdapter(AdminHelperActivity.this,userList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User item=userList.get(position);
                Intent intent=new Intent(AdminHelperActivity.this,AdminHelperDetailsActivity.class);
                intent.putExtra("helperUserobj",item);
                startActivity(intent);
            }
        });

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.READ_Helper, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);

                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject object = array.getJSONObject(i);

                        User classfile = new User();

                        classfile.setUsre_id(object.getString("usre_id"));
                        classfile.setUser_name(object.getString("user_name"));
                        classfile.setUser_contact(object.getString("user_contact"));

                        classfile.setUser_email(object.getString("user_email"));
                        classfile.setUser_password(object.getString("user_password"));
                        classfile.setUser_type(object.getString("user_type"));

                        classfile.setUser_flat_id(object.getString("user_flat_id"));
                        classfile.setUser_is_block(object.getString("user_is_block"));
                        classfile.setUser_is_rent(object.getString("user_is_rent"));

                        classfile.setAdmin_id(object.getString("admin_id"));
                        classfile.setRegis_date_time(object.getString("regis_date_time"));
                        classfile.setAadhar_number(object.getString("aadhar_number"));

                        classfile.setAadhar_image_url(object.getString("aadhar_image_url"));
                        classfile.setTotal_member(object.getString("total_member"));
                        classfile.setHcat_id(object.getString("hcat_id"));
                        classfile.setUser_is_available(object.getString("user_is_available"));

                        userList.add(classfile);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                finally {
                    adapter.notifyDataSetChanged();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue queue = Volley.newRequestQueue(AdminHelperActivity.this);
        queue.add(stringRequest);
    }

}
