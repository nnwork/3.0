package com.customerservice.login.Helper;

import android.content.Intent;
import android.drm.DrmStore;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.customerservice.login.Adapters.HelperContactInfo;
import com.customerservice.login.Adapters.UserContactinfoAdapter;
import com.customerservice.login.ClassFiles.UserContactinfo;
import com.customerservice.login.R;
import com.customerservice.login.Utility.Config;
import com.customerservice.login.Watchman.WatchmanContactActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HelperContactActivity extends AppCompatActivity {
    ListView user_contactview;
    List<UserContactinfo> userContactinfoList=new ArrayList<>();
    HelperContactInfo adapter;
    UserContactinfo userContactinfo = new UserContactinfo();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper_contact);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        user_contactview = (ListView)findViewById(R.id.user_contactview);
        adapter=new HelperContactInfo(HelperContactActivity.this,userContactinfoList) ;
        user_contactview.setAdapter(adapter);


        adapter.setCustomButtonCall(new HelperContactInfo.customCallButton() {
            @Override
            public void onButtonClickListner(int position, String contact) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+contact));
                startActivity(intent);
            }
        });



        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.READ_GetUserContactinfo, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray array = new JSONArray(response);
                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject jsonObject = array.getJSONObject(i);



                        userContactinfo.setUser_name(jsonObject.getString("user_name"));
                        userContactinfo.setUsre_id(jsonObject.getString("usre_id"));
                        userContactinfo.setUser_contact(jsonObject.getString("user_contact"));
                        userContactinfo.setUser_flat_id(jsonObject.getString("user_flat_id"));
                        userContactinfoList.add(userContactinfo);

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

        RequestQueue queue = Volley.newRequestQueue(HelperContactActivity.this);
        queue.add(stringRequest);



    }


}
