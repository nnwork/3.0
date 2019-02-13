package com.customerservice.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.customerservice.login.Admin.AdminDashboardActivity;
import com.customerservice.login.Utility.Config;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText Mobile_Number,Password;
    Button btnLogin;
    TextView forgot_pwd,change_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Mobile_Number=(EditText)findViewById(R.id.Mobile_Number);
        Password=(EditText)findViewById(R.id.Password);
        btnLogin=(Button)findViewById(R.id.btnLogin);
        forgot_pwd=(TextView)findViewById(R.id.forgot_pwd);
        change_pwd=(TextView)findViewById(R.id.ChangePassword);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String MobileNumber=Mobile_Number.getText().toString();
                String Passsword=Password.getText().toString();
                if(MobileNumber.length()==0)
                {
                    Toast.makeText(MainActivity.this, "Please Enter Mobile Number", Toast.LENGTH_SHORT).show();
                }
                else if(MobileNumber.length()!=10)
                {
                    Toast.makeText(MainActivity.this, "Please Enter Valid Mobile Number", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.READ_UserLogin, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject obj=new JSONObject(response);
                                String status=obj.getString("status");
                                if(status.equals("notfound"))
                                {
                                    Toast.makeText(MainActivity.this, "Mobile number and Password not found", Toast.LENGTH_SHORT).show();
                                }
                                else if(status.equals("block"))
                                {
                                    Toast.makeText(MainActivity.this, "You Are Block! Please Contact To Admin", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    String usre_id=obj.getString("usre_id");
                                    String user_name=obj.getString("user_name");
                                    String user_contact=obj.getString("user_contact");
                                    String user_type=obj.getString("user_type");


                                    //session

                                    //session

                                    if(user_type.equals("owner"))
                                    {
                                        Intent intent=new Intent(MainActivity.this,OwnerHomeActivity.class);
                                        startActivity(intent);
                                    }
                                    else if(user_type.equals("admin"))
                                    {
                                        Intent intent=new Intent(MainActivity.this, AdminHomeActivity.class);
                                        startActivity(intent);
                                    }
                                    else if(user_type.equals("helper"))
                                    {
                                        Intent intent=new Intent(MainActivity.this,HelperHomeActivity.class);
                                        startActivity(intent);
                                    }
                                    else if(user_type.equals("watchmen"))
                                    {
                                        Intent intent=new Intent(MainActivity.this,WatchMenHomeActivity.class);
                                        startActivity(intent);
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this, "Server Error"+error.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params = new HashMap<>();
                            String MobileNumber = Mobile_Number.getText().toString();
                            String password = Password.getText().toString();

                            params.put("MobileNumber",MobileNumber);
                            params.put("password",password);
                            return  params;
                        }
                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                    requestQueue.add(stringRequest);
                }

            }
        });


        forgot_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SendOTP.class);
                startActivity(intent);
            }
        });

        change_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
            }
        });
    }
}
