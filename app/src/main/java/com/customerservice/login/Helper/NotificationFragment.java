package com.customerservice.login.Helper;


import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.customerservice.login.Adapters.HelperNotificationAdapter;
import com.customerservice.login.ClassFiles.Notification;
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
public class NotificationFragment extends Fragment {
    ListView listView;
    HelperNotificationAdapter adapter;
    List<Notification> notificationList=new ArrayList<>();

    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        listView=(ListView)view.findViewById(R.id.notification_list_view);
        adapter=new HelperNotificationAdapter(getActivity(),notificationList);
        listView.setAdapter(adapter);

        StringRequest request=new StringRequest(Request.Method.POST, Config.list_tbl_notification, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array=new JSONArray(response);
                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject object=array.getJSONObject(i);

                        Notification classfie=new Notification();
                        classfie.setNotification_id(object.getString("notification_id"));
                        classfie.setUser_name(object.getString("user_name"));
                        classfie.setUser_id(object.getString("user_id"));
                        classfie.setNotification_title(object.getString("title"));
                        classfie.setNotification_description(object.getString("description"));
                        classfie.setDescription_date_time(object.getString("date_time"));

                        notificationList.add(classfie);
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
        RequestQueue queue= Volley.newRequestQueue(getActivity());
        queue.add(request);
        return view;
    }

}
