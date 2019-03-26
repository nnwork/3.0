package com.customerservice.login.Admin;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.customerservice.login.R;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.customerservice.login.Adapters.BuildingAdapterRecycle;
import com.customerservice.login.Adapters.ExpencesAdapter;
import com.customerservice.login.Adapters.IncomeAdapter;
import com.customerservice.login.ClassFiles.Building;
import com.customerservice.login.ClassFiles.Expences;
import com.customerservice.login.ClassFiles.Maintenance;
import com.customerservice.login.R;
import com.customerservice.login.Utility.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
/**
 * A simple {@link Fragment} subclass.
 */
public class IncomeFragment extends Fragment {

    RecyclerView incomeview;
    IncomeAdapter adapter;
    List<Maintenance> maintenanceList=new ArrayList<>();

    public IncomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_income, container, false);
        incomeview=(RecyclerView)rootView.findViewById(R.id.incomeview);
        incomeview.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        incomeview.setLayoutManager(mLayoutManager);
        incomeview.setItemAnimator(new DefaultItemAnimator());


        adapter = new IncomeAdapter(maintenanceList);
        incomeview.setAdapter(adapter);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.READ_AdminUserMaintenance, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Toast.makeText(IncomeActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                try {
                    JSONArray array= new JSONArray(response);
                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject object = array.getJSONObject(i);
                        // Toast.makeText(IncomeActivity.this, ""+object.getString("month"), Toast.LENGTH_SHORT).show();
                        Maintenance item = new Maintenance();
                        item.setAmount(object.getString("amount"));
                        item.setUser_name(object.getString("user_name"));
                        item.setMaintenance_id(object.getString("maintenance_id"));
                        item.setUser_id(object.getString("user_id"));
                        item.setStatus(object.getString("status"));
                        item.setPay_date(object.getString("pay_date"));
                        item.setTransaction_number(object.getString("transaction_number"));
                        item.setPay_mode(object.getString("pay_mode"));
                        item.setMonth_id(object.getString("month_id"));
                        item.setMonth(object.getString("month"));
                        maintenanceList.add(item);

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
                Toast.makeText(getActivity(), "Server Error", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(stringRequest);
        return rootView;
    }

}
