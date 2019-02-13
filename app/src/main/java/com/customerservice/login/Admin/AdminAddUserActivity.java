package com.customerservice.login.Admin;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.customerservice.login.MainActivity;
import com.customerservice.login.R;
import com.customerservice.login.Utility.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminAddUserActivity extends AppCompatActivity {
    EditText UserName,MobileNumber,UserEmail,UserPassword,AadharNumber,TotalFlatMember;
    Spinner UserFlat,UserType,HcategoryName;
    RadioButton BlockYes,BlockNo,RentYes,RentNo,YesUserisAvailable, NoUserisAvailable;
    Button btnUserSubmit;

    ArrayList<String> FlatIDlist=new ArrayList<>();
    ArrayList<String> FlatNumberlist=new ArrayList<>();
    ArrayList<String> UserTypelist=new ArrayList<>();

    ArrayAdapter arrayAdapter,usertypeadpter;


    ArrayList<String> hcatidlist=new ArrayList<>();
    ArrayList<String> hcatnamelist=new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_user);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //BegindEditTxtBoxStart
        UserName = (EditText)findViewById(R.id.UserName);
        MobileNumber=(EditText)findViewById(R.id.MobileNumber);
        UserEmail = (EditText)findViewById(R.id.UserEmail);
        UserPassword = (EditText)findViewById(R.id.UserPassword);
        AadharNumber = (EditText)findViewById(R.id.AadharNumber);
        TotalFlatMember = (EditText)findViewById(R.id.TotalFlatMember);
        //EditTextBoxEnd

        //BindSpinnerStart
        UserFlat = (Spinner) findViewById(R.id.UserFlat);
        HcategoryName=(Spinner)findViewById(R.id.HcategoryName);
        UserType = (Spinner) findViewById(R.id.UserType);
        //SpinnerEnd

        //BindRadioButton
        BlockYes=(RadioButton)findViewById(R.id.BlockYes);
        BlockNo=(RadioButton)findViewById(R.id.BlockNo);
        RentYes=(RadioButton)findViewById(R.id.RentYes);
        RentNo=(RadioButton)findViewById(R.id.RentNo);
        YesUserisAvailable=(RadioButton)findViewById(R.id.YesUserisAvailable);
        NoUserisAvailable=(RadioButton)findViewById(R.id.NoUserisAvailable);
        //EndRadioButton

        //ButtonUserSubmit
        btnUserSubmit=(Button)findViewById(R.id.btnUserSubmit);


        UserTypelist.add("Admin");
        UserTypelist.add("Employee");
        UserTypelist.add("Users");
        usertypeadpter = new ArrayAdapter(AdminAddUserActivity.this,android.R.layout.simple_dropdown_item_1line,UserTypelist);
        UserType.setAdapter(usertypeadpter);




        // HelperCategory
        StringRequest s=new StringRequest(Request.Method.POST, "http://192.168.1.146:8089/Webservices/getHelperCategory.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array=new JSONArray(response);
                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject obj=array.getJSONObject(i);
                        String hcat_id=obj.getString("hcat_id");
                        String hcat_name=obj.getString("hcat_name");

                        hcatidlist.add(hcat_id);
                        hcatnamelist.add(hcat_name);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                finally
                {
                  ArrayAdapter adapter=new ArrayAdapter(AdminAddUserActivity.this,android.R.layout.simple_dropdown_item_1line,hcatnamelist);
                    HcategoryName.setAdapter(adapter);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue queue=Volley.newRequestQueue(this);
        queue.add(s);
        //HelperCategory





        //FlatNumberBegin
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.READ_Flat, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array=new JSONArray(response);
                    for (int i=0;i<array.length();i++)
                    {
                        JSONObject jsonObject = array.getJSONObject(i);

                        String Flatid=jsonObject.getString("FlatId");
                        String FlatNumber=jsonObject.getString("FlatNumber");

                        FlatIDlist.add(Flatid);
                        FlatNumberlist.add(FlatNumber);


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                finally {
                    arrayAdapter = new ArrayAdapter(AdminAddUserActivity.this,android.R.layout.simple_dropdown_item_1line, FlatNumberlist);
                    UserFlat.setAdapter(arrayAdapter);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(AdminAddUserActivity.this, "", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue q=Volley.newRequestQueue(this);
        q.add(stringRequest);
        //FlatNumerEnd


        btnUserSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Username = UserName.getText().toString();
                final String Mobilenumber = MobileNumber.getText().toString();
                final String Useremail = UserEmail.getText().toString();
                final String Userpassword = UserPassword.getText().toString();
                final String Aadharnumber = AadharNumber.getText().toString();
                final String TotalFlatmember = TotalFlatMember.getText().toString();

                    if(Username.length()==0)
                    {
                        Toast.makeText(AdminAddUserActivity.this, "Enter Valid Data", Toast.LENGTH_SHORT).show();
                    }
                    else if(Mobilenumber.length()==0)
                    {
                        Toast.makeText(AdminAddUserActivity.this, "Enter Valid Data", Toast.LENGTH_SHORT).show();
                    }
                    else if (Useremail.length()==0)
                    {
                        Toast.makeText(AdminAddUserActivity.this, "Enter Valid Data", Toast.LENGTH_SHORT).show();
                    }
                    else if(Userpassword.length()==0)
                    {
                        Toast.makeText(AdminAddUserActivity.this, "Enter Valid Data", Toast.LENGTH_SHORT).show();
                    }
                    else if(Aadharnumber.length()==0)
                    {
                        Toast.makeText(AdminAddUserActivity.this, "Enter Valid Data", Toast.LENGTH_SHORT).show();
                    }
                    else if(TotalFlatmember.length()==0)
                    {
                        Toast.makeText(AdminAddUserActivity.this, "Enter Valid Data", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        StringRequest stringrequest = new StringRequest(Request.Method.POST, Config.Add_User, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if(response.equals("Success"))
                                {
                                    Toast.makeText(AdminAddUserActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(AdminAddUserActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                }

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(AdminAddUserActivity.this, "Server Error"+error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }){
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                               Map<String,String> params = new HashMap<>();
                               int position=HcategoryName.getSelectedItemPosition();
                               String hcat_id=hcatidlist.get(position);

                                int position1=UserType.getSelectedItemPosition();
                                String usertype=UserTypelist.get(position1);

                                params.put("Username",Username);
                                params.put("Mobilenumber",Mobilenumber);
                                params.put("Useremail",Useremail);
                                params.put("Userpassword",Userpassword);
                                params.put("Aadharnumber",Aadharnumber);
                                params.put("TotalFlatmember",TotalFlatmember);
                                params.put("hcat_id",hcat_id);
                                params.put("usertype",usertype);
                               return params;
                            }
                        };
                        RequestQueue requestQueue = Volley.newRequestQueue(AdminAddUserActivity.this);
                        requestQueue.add(stringrequest);
                    }

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
