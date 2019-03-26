package com.customerservice.login.Helper;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.customerservice.login.FlatOwner.OwnerProfileActivity;
import com.customerservice.login.R;
import com.customerservice.login.Utility.Config;
import com.customerservice.login.Utility.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class HelperProfileFragment extends Fragment {
    SessionManager sessionManager;
    TextView user_name,user_contact,user_email,aadhar_number,user_pwd,heplercat;

    String hcat ;
    public HelperProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_helper_profile, container, false);

        sessionManager = new SessionManager(getActivity());
        user_name = (TextView) view.findViewById(R.id.user_name);
        user_contact = view.findViewById(R.id.user_contact);
        user_email = view.findViewById(R.id.user_email);
        aadhar_number = view.findViewById(R.id.aadhar_number);
        user_pwd = view.findViewById(R.id.user_pwd);
        heplercat = view.findViewById(R.id.heplercat);

        hcat = heplercat.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.READ_GetHelperprofile, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject =jsonArray.getJSONObject(i);

                        user_name.setText(jsonObject.getString("user_name"));
                        user_contact.setText(jsonObject.getString("user_contact"));
                        user_email.setText(jsonObject.getString("user_email"));
                        aadhar_number.setText(jsonObject.getString("aadhar_number"));
                        user_pwd.setText(jsonObject.getString("user_password"));
                        heplercat.setText(hcat.concat(" ").concat(jsonObject.getString("hcat_name")));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Server Error", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("usre_id",sessionManager.getId());
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(stringRequest);

        return view;

    }

}
