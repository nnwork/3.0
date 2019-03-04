    package com.customerservice.login.FlatOwner;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.customerservice.login.ClassFiles.Visitor;
import com.customerservice.login.R;
import com.customerservice.login.Utility.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

    public class OwnerVisitorDetailsActivity extends AppCompatActivity {
    TextView owner_visitors_id,owner_visiters_name,owner_visiters_contect,owner_visitors_flat_id,owner_visitors_photo,owner_visitors_watchman_id;
    TextView owner_visitors_v_date,owner_visitors_v_time,owner_visitors_flat_approve,owner_visitors_app_date_time,owner_visitors_exit_date_time,txtbuildingname;
    Visitor visitoribject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_visitor_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        visitoribject=(Visitor)getIntent().getSerializableExtra("visitoribject");

        txtbuildingname=(TextView)findViewById(R.id.txtbuildingname);
        owner_visitors_id=(TextView)findViewById(R.id.owner_visitors_id);
        owner_visiters_name=(TextView)findViewById(R.id.owner_visiters_name);
        owner_visiters_contect=(TextView)findViewById(R.id.owner_visiters_contect);
        owner_visitors_flat_id=(TextView)findViewById(R.id.owner_visitors_flat_id);
        owner_visitors_photo=(TextView)findViewById(R.id.owner_visitors_photo);
        owner_visitors_watchman_id=(TextView)findViewById(R.id.owner_visitors_watchman_id);

        owner_visitors_v_date=(TextView)findViewById(R.id.owner_visitors_v_date);
        owner_visitors_v_time=(TextView)findViewById(R.id.owner_visitors_v_time);
        owner_visitors_flat_approve=(TextView)findViewById(R.id.owner_visitors_flat_approve);
        owner_visitors_app_date_time=(TextView)findViewById(R.id.owner_visitors_app_date_time);
        owner_visitors_exit_date_time=(TextView)findViewById(R.id.owner_visitors_exit_date_time);

        StringRequest request=new StringRequest(Request.Method.POST, Config.READ_OwnerUserVisitor, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array=new JSONArray(response);
                    for(int i=0;i<array.length();i++) {
                        JSONObject object = array.getJSONObject(i);

                        String watchmen = object.getString("watchmen");
                        owner_visitors_watchman_id.setText(watchmen);

                        String FlatNumber = object.getString("FlatNumber");
                        owner_visitors_flat_id.setText(FlatNumber);

                        String BuildingName = object.getString("BuildingName");
                        txtbuildingname.setText(BuildingName);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(OwnerVisitorDetailsActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
            }
        })  {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("visitors_id",visitoribject.getVisitors_id());

                return params;
            }
        };
        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(request);

        //toolbar.setTitle(visitoribject.getVisitors_name());

        owner_visiters_name.setText(visitoribject.getVisitors_name());
        owner_visiters_contect.setText(visitoribject.getVisitors_contect());
        owner_visitors_photo.setText(visitoribject.getVisitors_photo());
        owner_visitors_v_date.setText(visitoribject.getVisitors_v_date());
        owner_visitors_v_time.setText(visitoribject.getVisitors_v_time());
        owner_visitors_flat_approve.setText(visitoribject.getVisitors_flat_approve());
        owner_visitors_app_date_time.setText(visitoribject.getVisitors_app_date_time());
        owner_visitors_exit_date_time.setText(visitoribject.getVisitors_exit_date_time());

    }

}
