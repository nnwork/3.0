package com.customerservice.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.customerservice.login.Utility.SessionManager;
import com.customerservice.login.Watchman.NewVisitorActivity;
import com.customerservice.login.Watchman.WatchmanContactActivity;
import com.customerservice.login.Watchman.WatchmanEmergencyActivity;
import com.customerservice.login.Watchman.WatchmanVisitorActivity;
import com.customerservice.login.Watchman.WatchmanVisitorReportActivity;
import com.customerservice.login.Watchman.WatchmenSuggestion;

public class WatchMenHomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_men_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        session=new SessionManager(this);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.watch_men_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_watchman_new_visitor) {
            Intent intent_visitor = new Intent(WatchMenHomeActivity.this, NewVisitorActivity.class);
            startActivity(intent_visitor);
            // Handle the camera action
        } else if (id == R.id.nav_watchman_new_visitor_report) {
            Intent intent_visitor_report = new Intent(WatchMenHomeActivity.this, WatchmanVisitorReportActivity.class);
            startActivity(intent_visitor_report);
        }else if (id == R.id.nav_watchman_visitor) {
            Intent intent_visitor = new Intent(WatchMenHomeActivity.this, WatchmanVisitorActivity.class);
            startActivity(intent_visitor);

        }else if (id == R.id.nav_watchman_new_visitor_your_suggestion) {
            Intent intent_suggestion = new Intent(WatchMenHomeActivity.this, WatchmenSuggestion.class);
            startActivity(intent_suggestion);

        } else if (id == R.id.nav_watchman_new_visitor_emergency) {
            Intent intent_emergency = new Intent(WatchMenHomeActivity.this, WatchmanEmergencyActivity.class);
            startActivity(intent_emergency);

        } else if (id == R.id.nav_watchman_new_visitor_contact) {
            Intent intent_contact = new Intent(WatchMenHomeActivity.this, WatchmanContactActivity.class);
            startActivity(intent_contact);

        }
        else if (id == R.id.nav_watchman_new_visitor_logout) {
            session.setLogin(false);
            session.setId("");
            session.setName("");
            session.setContact("");
            session.setType("");
            Intent intent_logout = new Intent(WatchMenHomeActivity.this, MainActivity.class);
            startActivity(intent_logout);
            finish();

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
    }
