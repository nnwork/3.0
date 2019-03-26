package com.customerservice.login.Admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.customerservice.login.MainActivity;
import com.customerservice.login.R;

public class AdminDashboardActivity extends AppCompatActivity {


    LinearLayout linbuilding,linowner,linflat,linadminaddcategory,linAddEventId,linHelperCategoryID,linMonthID,linLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        linbuilding=(LinearLayout)findViewById(R.id.linbuilding);
        linbuilding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminDashboardActivity.this,AdminAddBuildingActivity.class);
                startActivity(intent);
            }
        });

        linLogin=(LinearLayout)findViewById(R.id.linLogin);
        linLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminDashboardActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        linflat=(LinearLayout)findViewById(R.id.linflat);
        linflat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminDashboardActivity.this,AdminAddFlatActivity.class);
                startActivity(intent);
            }
        });


        linowner=(LinearLayout)findViewById(R.id.linowner);
        linowner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminDashboardActivity.this,AdminAddUserActivity.class);
                startActivity(intent);
            }
        });
        linadminaddcategory=(LinearLayout)findViewById(R.id.linadminaddcategory);
        linadminaddcategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(AdminDashboardActivity.this,AdminAddCategoryActivity.class);
                    startActivity(intent);
                }
        });

        linAddEventId=(LinearLayout)findViewById(R.id.linAddEventId);
        linAddEventId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminDashboardActivity.this,AdminAddEventActivity.class);
                startActivity(intent);
            }
        });

        linHelperCategoryID=(LinearLayout)findViewById(R.id.linHelperCategoryID);
        linHelperCategoryID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminDashboardActivity.this,AdminAddHelperCategoryActivity.class);
                startActivity(intent);
            }
        });

        linMonthID=(LinearLayout)findViewById(R.id.linMonthID);
        linMonthID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminDashboardActivity.this,AdminAddMonthActivity.class);
                startActivity(intent);
            }
        });


    }
}
