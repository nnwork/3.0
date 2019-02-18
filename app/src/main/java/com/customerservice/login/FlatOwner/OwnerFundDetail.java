package com.customerservice.login.FlatOwner;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.customerservice.login.Admin.FlatDetailActivity;
import com.customerservice.login.ClassFiles.Funds;
import com.customerservice.login.R;

public class OwnerFundDetail extends AppCompatActivity {
    TextView txtfund_title,txtfund_amt,txtfund_date_time;
    Funds fundObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_fund_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtfund_title = (TextView)findViewById(R.id.txtfund_title);
        txtfund_amt = (TextView)findViewById(R.id.txtfund_amt);
        txtfund_date_time = (TextView)findViewById(R.id.txtfund_date_time);

       String fund_title = getIntent().getStringExtra("fund_title");
       String fund_amt = getIntent().getStringExtra("fund_amt");
       String fund_date_time = getIntent().getStringExtra("fund_date_time");



        fundObject=(Funds)getIntent().getSerializableExtra("fundObject");
        txtfund_title.setText(fundObject.getFund_title());
        txtfund_amt.setText(fundObject.getFund_amt());
        txtfund_date_time.setText(fundObject.getFund_date_time());

    }

}
