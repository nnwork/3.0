package com.customerservice.login.Helper;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.customerservice.login.ChangePasswordActivity;
import com.customerservice.login.MainActivity;
import com.customerservice.login.R;
import com.customerservice.login.Utility.SessionManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {
LinearLayout btnchangepassword,btnprofile;
ImageView btnlogout;
    SessionManager session;
    Fragment fragment = null;
    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        session = new SessionManager(getActivity());
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        btnprofile = view.findViewById(R.id.btnprofile);
        btnchangepassword = view.findViewById(R.id.btnchangepassword);
        btnlogout = view.findViewById(R.id.btnlogout);

        btnprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),HelperMyProfileActivity.class);
                startActivity(intent);

            }
        });

        btnchangepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChangePasswordActivity.class);
                startActivity(intent);

            }
        });

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.setLogin(false);
                session.setId("");
                session.setName("");
                session.setContact("");
                session.setType("");
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack();

            }
        });

        return view;
    }

}
