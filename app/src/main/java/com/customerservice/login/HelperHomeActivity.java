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

import com.customerservice.login.FlatOwner.Formcomplain;
import com.customerservice.login.Helper.HelperComplainActivity;
import com.customerservice.login.Helper.HelperContactActivity;
import com.customerservice.login.Helper.HelperDashboardActivity;
import com.customerservice.login.Helper.HelperMyProfileActivity;

public class HelperHomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
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
        getMenuInflater().inflate(R.menu.helper_home, menu);
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

        if (id == R.id.nav_helper_complain) {
            Intent intent_compalain=new Intent(HelperHomeActivity.this, HelperComplainActivity.class);
            startActivity(intent_compalain);
            // Handle the camera action
        } else if (id == R.id.nav_helper_contact) {
            Intent intent_contact=new Intent(HelperHomeActivity.this, HelperContactActivity.class);
            startActivity(intent_contact);

        } else if (id == R.id.nav_helper_logout) {
            Intent intent_logout=new Intent(HelperHomeActivity.this,MainActivity.class);
            startActivity(intent_logout);

        } else if (id == R.id.nav_helper_change_password) {
            Intent intent_change_password=new Intent(HelperHomeActivity.this,MainActivity.class);
            startActivity(intent_change_password);

        } else if (id == R.id.nav_helper_mydashbord) {
            Intent intent_change_dashboard=new Intent(HelperHomeActivity.this, HelperDashboardActivity.class);
            startActivity(intent_change_dashboard);

        } else if (id == R.id.nav_helper_my_profile) {
            Intent intent_my_profile=new Intent(HelperHomeActivity.this, HelperMyProfileActivity.class);
            startActivity(intent_my_profile);
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
