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

import com.customerservice.login.Admin.AccountReport;
import com.customerservice.login.Admin.AdminComplainActivity;
import com.customerservice.login.Admin.AdminComplainStatusActivity;
import com.customerservice.login.Admin.AdminViewBuildingActivity;
import com.customerservice.login.Admin.AdminVisitorActivity;
import com.customerservice.login.Admin.EventsViewActivity;
import com.customerservice.login.Admin.ExpencesActivity;
import com.customerservice.login.Admin.FundsReportActivity;
import com.customerservice.login.Admin.FundsViewActivity;
import com.customerservice.login.Admin.HallBookingViewActivity;
import com.customerservice.login.Admin.HallViewActivity;
import com.customerservice.login.Admin.AdminHelperActivity;
import com.customerservice.login.Admin.IncomeActivity;
import com.customerservice.login.Admin.MaintenanceViewActivity;
import com.customerservice.login.Admin.MonthlyAmountActivity;
import com.customerservice.login.Admin.OwnerListActivity;
import com.customerservice.login.Admin.SuggestionViewActivity;
import com.customerservice.login.Admin.FlatViewActivity;
import com.customerservice.login.Admin.AdminWatchmanActivity;
import com.customerservice.login.Utility.SessionManager;

public class AdminHomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
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
        session=new SessionManager(this);
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
        getMenuInflater().inflate(R.menu.admin_home, menu);
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

        if (id == R.id.nav_building) {
            Intent intent=new Intent(AdminHomeActivity.this, AdminViewBuildingActivity.class);
            startActivity(intent);
        }
        else if(id==R.id.nav_Flat)
        {
            Intent intent = new Intent(AdminHomeActivity.this, FlatViewActivity.class);
            startActivity(intent);
        }
        else if(id==R.id.nav_owner)
        {
            Intent intent = new Intent(AdminHomeActivity.this, OwnerListActivity.class);
            startActivity(intent);
        }
        else if(id==R.id.nav_events)
        {
            Intent intent = new Intent(AdminHomeActivity.this, EventsViewActivity.class);
            startActivity(intent);
        }else if(id==R.id.nav_suggestion)
        {
            Intent intent = new Intent(AdminHomeActivity.this, SuggestionViewActivity.class);
            startActivity(intent);
        }

        else if(id==R.id.nav_helper)
        {
            Intent intent = new Intent(AdminHomeActivity.this, AdminHelperActivity.class);
            startActivity(intent);
        }
        else if(id==R.id.nav_suggestion)
        {
            Intent intent = new Intent(AdminHomeActivity.this, SuggestionViewActivity.class);
            startActivity(intent);
        }
        else if(id==R.id.nav_monthly_amount)
        {
            Intent intent = new Intent(AdminHomeActivity.this, MonthlyAmountActivity.class);
            startActivity(intent);
        }
        else if(id==R.id.nav_Maintenance)
        {
            Intent intent = new Intent(AdminHomeActivity.this, MaintenanceViewActivity.class);
            startActivity(intent);
        }
        else if(id==R.id.nav_visitor)
        {
            Intent intent = new Intent(AdminHomeActivity.this, AdminVisitorActivity.class);
            startActivity(intent);
        }
        else if(id==R.id.nav_watchman)
        {
            Intent intent = new Intent(AdminHomeActivity.this, AdminWatchmanActivity.class);
            startActivity(intent);
        }
        else if(id==R.id.nav_fund)
        {
            Intent intent = new Intent(AdminHomeActivity.this, FundsViewActivity.class);
            startActivity(intent);
        }
        else if(id==R.id.nav_fund_report)
        {
            Intent intent = new Intent(AdminHomeActivity.this, FundsReportActivity.class);
            startActivity(intent);
        }
        else if(id==R.id.nav_income)
        {
            Intent intent = new Intent(AdminHomeActivity.this, IncomeActivity.class);
            startActivity(intent);
        }
        else if(id==R.id.nav_expences)
        {
            Intent intent = new Intent(AdminHomeActivity.this, ExpencesActivity.class);
            startActivity(intent);
        }
        else if(id==R.id.nav_account_Report)
        {
            Intent intent = new Intent(AdminHomeActivity.this, AccountReport.class);
            startActivity(intent);
        }
        else if(id==R.id.nav_hall)
        {
            Intent intent = new Intent(AdminHomeActivity.this, HallViewActivity.class);
            startActivity(intent);
        }
        else if(id==R.id.nav_hall_booking)
        {
            Intent intent = new Intent(AdminHomeActivity.this, HallBookingViewActivity.class);
            startActivity(intent);
        }
        else if(id==R.id.nav_hall)
        {
            Intent intent = new Intent(AdminHomeActivity.this, HallViewActivity.class);
            startActivity(intent);
        }
        else if(id==R.id.nav_new_complain)
        {
            Intent intent = new Intent(AdminHomeActivity.this, AdminComplainActivity.class);
            startActivity(intent);
        }
        else if(id==R.id.nav_Process_Complain)
        {
            Intent intent = new Intent(AdminHomeActivity.this, AdminComplainStatusActivity.class);
            startActivity(intent);
        }
        else if(id==R.id.nav_admin_logout)
        {
            session.setLogin(false);
            session.setId("");
            session.setName("");
            session.setContact("");
            session.setType("");
            Intent intent = new Intent(AdminHomeActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
