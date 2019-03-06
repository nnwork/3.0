package com.customerservice.login.Admin;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.customerservice.login.Adapters.HallAdapter;
import com.customerservice.login.Adapters.SuggestionAdapter;
import com.customerservice.login.ClassFiles.Hall;
import com.customerservice.login.ClassFiles.Suggestion;
import com.customerservice.login.R;
import com.customerservice.login.Utility.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SuggestionViewActivity extends AppCompatActivity {
    ListView suggestionview;
    List<Suggestion>suggestionList = new ArrayList<>();
    SuggestionAdapter suggestionAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        suggestionview=(ListView)findViewById(R.id.suggestionview);
        suggestionAdapter= new SuggestionAdapter(SuggestionViewActivity.this,suggestionList);
        suggestionview.setAdapter(suggestionAdapter);


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.READ_Suggestion, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i=0;i<array.length();i++)
                    {
                        JSONObject object= array.getJSONObject(i);

                        Suggestion item = new Suggestion();
                        item.setUser_name(object.getString("user_name"));
                       // item.setSuggestion_text(object.getString("suggestion_text"));
                        suggestionList.add(item);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                finally {
                    suggestionAdapter.notifyDataSetChanged();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue queue = Volley.newRequestQueue(SuggestionViewActivity.this);
        queue.add(stringRequest);
    }

}
