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

import com.customerservice.login.ClassFiles.OwnerSuggestions;
import com.customerservice.login.FlatOwner.AccountsActivity;
import com.customerservice.login.FlatOwner.NotificationActivity;
import com.customerservice.login.FlatOwner.OwnerChangePasswordActivity;
import com.customerservice.login.FlatOwner.OwnerComplainActivity;
import com.customerservice.login.FlatOwner.OwnerEventActivity;
import com.customerservice.login.FlatOwner.OwnerFundActivity;
import com.customerservice.login.FlatOwner.OwnerHallActivity;
import com.customerservice.login.FlatOwner.OwnerMaintenanceActivity;
import com.customerservice.login.FlatOwner.OwnerProfileActivity;
import com.customerservice.login.FlatOwner.OwnerSuggestion;
import com.customerservice.login.FlatOwner.OwnerVisitorActivity;
import com.customerservice.login.Utility.SessionManager;

public class OwnerHomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    SessionManager session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        session= new SessionManager(this);
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
        getMenuInflater().inflate(R.menu.owner_home, menu);
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

        if(id==R.id.nav_owner_change_password)
        {
            Intent intent=new Intent(OwnerHomeActivity.this, OwnerChangePasswordActivity.class);
            startActivity(intent);
        }
        else if(id==R.id.nav_owner_events)
        {
            Intent intent=new Intent(OwnerHomeActivity.this, OwnerEventActivity.class);
            startActivity(intent);
        }
        else if(id==R.id.nav_owner_hall)
        {
            Intent intent=new Intent(OwnerHomeActivity.this, OwnerHallActivity.class);
            startActivity(intent);
        }
        else if(id==R.id.nav_owner_complain)
        {
            Intent intent=new Intent(OwnerHomeActivity.this, OwnerComplainActivity.class);
            startActivity(intent);
        }
        else if(id==R.id.nav_owner_maintenance)
        {
            Intent intent=new Intent(OwnerHomeActivity.this, OwnerMaintenanceActivity.class);
            startActivity(intent);
        }
        else if(id==R.id.nav_owner_fund)
        {
            Intent intent=new Intent(OwnerHomeActivity.this, OwnerFundActivity.class);
            startActivity(intent);
        }
        else if(id==R.id.nav_owner_yourSuggestion)
        {
            Intent intent=new Intent(OwnerHomeActivity.this, OwnerSuggestion.class);
            startActivity(intent);
        }
        else if(id==R.id.nav_owner_visitor_request)
        {
            Intent intent=new Intent(OwnerHomeActivity.this, OwnerVisitorActivity.class);
            startActivity(intent);
        }
        else if(id==R.id.nav_owner_accounts)
        {
            Intent intent=new Intent(OwnerHomeActivity.this, AccountsActivity.class);
            startActivity(intent);
        }
        else if(id==R.id.nav_owner_logout)
        {
            session.setLogin(false);
            session.setId("");
            session.setName("");
            session.setContact("");
            session.setType("");
            Intent intent = new Intent(OwnerHomeActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
