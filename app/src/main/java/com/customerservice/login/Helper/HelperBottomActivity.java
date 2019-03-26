package com.customerservice.login.Helper;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.customerservice.login.MainActivity;
import com.customerservice.login.R;
import com.customerservice.login.Utility.SessionManager;

public class HelperBottomActivity extends AppCompatActivity {
    private ActionBar toolbar;
    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper_bottom);
        toolbar = getSupportActionBar();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        session = new SessionManager(getApplicationContext());

        toolbar.setTitle("Shop");
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    toolbar.setTitle("Home");
                    fragment = new HelperDashboardFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_complain:
                    toolbar.setTitle("Complain");
                    fragment = new ComplainFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_notification:
                    toolbar.setTitle("Notification");
                    fragment = new NotificationFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_setting:
                    toolbar.setTitle("Setting");
                    fragment = new SettingFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_Log_out:
                    toolbar.setTitle("Logout");
                    session.setLogin(false);
                    session.setId("");
                    session.setName("");
                    session.setContact("");
                    session.setType("");
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
            }

            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}
