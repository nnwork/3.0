package com.customerservice.login.FlatOwner;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.customerservice.login.Adapters.OwnerHallAdapter;
import com.customerservice.login.ClassFiles.Hall;
import com.customerservice.login.R;
import com.customerservice.login.Utility.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;




public class OwnerHallActivity extends AppCompatActivity {

    ListView list_view_hall;
    OwnerHallAdapter adapter;
    List<Hall> ownerHallList =new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_hall);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list_view_hall=(ListView)findViewById(R.id.list_view_hall);
        adapter=new OwnerHallAdapter(OwnerHallActivity.this, ownerHallList);
        list_view_hall.setAdapter(adapter);

        list_view_hall.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Hall hall = ownerHallList.get(position);
                Intent intent = new Intent(OwnerHallActivity.this,OwnerHallDetailActivity.class);
                intent.putExtra("hallObject",hall);
                startActivity(intent);

            }
        });

        
        StringRequest rq=new StringRequest(Request.Method.POST, Config.READ_Hall, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Toast.makeText(OwnerHallActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                    JSONArray array=new JSONArray(response);
                    //Toast.makeText(OwnerHallActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                            for (int i=0;i<array.length();i++)
                            {
                                JSONObject object=array.getJSONObject(i);

                                Hall ownerHall = new Hall();
                                ownerHall.setHall_id(object.getString("hall_id"));
                                ownerHall.setHall_title(object.getString("hall_title"));
                                ownerHall.setHall_capacity(object.getString("hall_capacity"));
                                ownerHall.setHallrent(object.getString("hallrent"));
                                ownerHall.setHall_img_1(object.getString("hall_img_1"));
                                ownerHall.setHall_img_2(object.getString("hall_img_1"));
                                ownerHallList.add(ownerHall);
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
                Toast.makeText(OwnerHallActivity.this, "Server Message"+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(rq);
    }


}
