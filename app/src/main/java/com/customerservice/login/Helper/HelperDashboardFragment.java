package com.customerservice.login.Helper;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.customerservice.login.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HelperDashboardFragment extends Fragment {


    public HelperDashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_helper_dashboard, container, false);
    return view;
    }

}
