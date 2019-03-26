package com.customerservice.login.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.customerservice.login.Adapters.BuildingAdapter;
import com.customerservice.login.Adapters.OwnerListAdapter;
import com.customerservice.login.ClassFiles.Building;
import com.customerservice.login.ClassFiles.User;
import com.customerservice.login.R;
import com.customerservice.login.Utility.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OwnerListActivity extends AppCompatActivity {
    ListView ownerlistview;
    OwnerListAdapter adapter;
    List<User> userList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ownerlistview=(ListView)findViewById(R.id.ownerlistview);
        adapter=new OwnerListAdapter(this,userList);
        ownerlistview.setAdapter(adapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Server DataString
        //dialog.show();
        StringRequest str=new StringRequest(Request.Method.POST, Config.READ_GetOwnerinfo, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
              //  dialog.dismiss();
                try {
                    JSONArray array=new JSONArray(response);
                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject obj=array.getJSONObject(i);

                        User item=new User();

                        item.setUser_name(obj.getString("user_name"));
                        item.setUser_contact(obj.getString("user_contact"));
                        item.setUser_is_available(obj.getString("user_is_available"));
                        item.setUser_email(obj.getString("user_email"));
                        item.setUser_password(obj.getString("user_password"));
                        item.setUser_type(obj.getString("user_type"));
                        item.setUser_is_block(obj.getString("user_is_block"));
                        item.setUser_is_rent(obj.getString("user_is_rent"));
                        item.setRegis_date_time(obj.getString("regis_date_time"));
                        item.setAadhar_number(obj.getString("aadhar_number"));
                        item.setAadhar_image_url(obj.getString("aadhar_image_url"));
                        item.setTotal_member(obj.getString("total_member"));
                        item.setHcat_id(obj.getString("hcat_id"));
                        item.setUser_flat_id(obj.getString("user_flat_id"));
                        item.setUsre_id(obj.getString("usre_id"));

                        userList.add(item);
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
                //dialog.dismiss();
            }
        });

        RequestQueue q= Volley.newRequestQueue(this);
        q.add(str);
        //


        //Flatview

        ownerlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User item = userList.get(position);
                Intent intent = new Intent(OwnerListActivity.this,OwnerDetailsActivity.class);
                intent.putExtra("objectOwner", item);
                startActivity(intent);
            }
        });


    }

}
