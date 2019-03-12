package com.customerservice.login.FlatOwner;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.customerservice.login.ClassFiles.Hall;
import com.customerservice.login.R;
import com.customerservice.login.Utility.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class FormHallBooking extends AppCompatActivity {

    Button hallbookingbtn;
    EditText hallbookingbookingdate,hallbookingtime,hallbookingsubject;
    TextView hallbookingamt,halltitle;
   // Spinner  hallbookingstatus,hall_status;

    ArrayList<String>hall_id_list=new ArrayList<>();
    ArrayList<String>hall_title_list=new ArrayList<>();

    ArrayList<String>usre_id_list=new ArrayList<>();
    ArrayList<String>user_name_list=new ArrayList<>();
    Hall hallBookingObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_hall_booking);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        hallbookingbtn=(Button)findViewById(R.id.hallbookingbtn);

        //hallname=(EditText)findViewById(R.id.hallname);

        //hallbookingbookingdate=(EditText)findViewById(R.id.hallbookingbookingdate);
        //hallbookingtime=(EditText)findViewById(R.id.hallbookingtime);

        hallbookingamt=(TextView) findViewById(R.id.hallbookingamt);
        halltitle=(TextView) findViewById(R.id.halltitle);
        hallbookingsubject=(EditText)findViewById(R.id.hallbookingsubject);

     //   hall_status=(Spinner)findViewById(R.id.hall_status);

        Toast.makeText(this, ""+getIntent().getStringExtra("halltitle"), Toast.LENGTH_SHORT).show();


        halltitle.setText(getIntent().getStringExtra("halltitle"));
        hallbookingamt.setText(getIntent().getStringExtra("hallrent"));

        /////////////////////////DISP HALL ID //////////////////////////////////////////
//        StringRequest disp_hid_rq=new StringRequest(Request.Method.POST, Config.Disp_tbl_hall_booking, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONArray hall_id_JsonArray=new JSONArray(response);
//
//                    for(int i=0;i<hall_id_JsonArray.length();i++)
//                    {
//                        JSONObject disp_hid_obj=hall_id_JsonArray.getJSONObject(i);
//
//                        String hall_id=disp_hid_obj.getString("hall_id");
//                        String hall_title=disp_hid_obj.getString("hall_title");
//                        hall_id_list.add(hall_id);
//                        hall_title_list.add(hall_title);
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                finally {
//                    ArrayAdapter adapter_hid=new ArrayAdapter(FormHallBooking.this,android.R.layout.simple_dropdown_item_1line,hall_title_list);
//                    hall_status.setAdapter(adapter_hid);
//                }
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        RequestQueue disp_hid_queue=Volley.newRequestQueue(FormHallBooking.this);
//        disp_hid_queue.add(disp_hid_rq);

        /////////////////////////DISP HALL ID //////////////////////////////////////////

        /////////////////////////DISP USER ID //////////////////////////////////////////
//        StringRequest user_disp_rq=new StringRequest(Request.Method.POST, Config.Disp_tbl_user_hall, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONArray user_disp_jsonArray=new JSONArray(response);
//                    for (int i=0;i<user_disp_jsonArray.length();i++)
//                    {
//                        JSONObject user_disp_jsonObject=user_disp_jsonArray.getJSONObject(i);
//
//                        String usre_id=user_disp_jsonObject.getString("usre_id");
//                        String user_name=user_disp_jsonObject.getString("user_name");
//
//                        usre_id_list.add(usre_id);
//                        user_name_list.add(user_name);
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                finally {
//                    ArrayAdapter user_disp_adapter=new ArrayAdapter(FormHallBooking.this,android.R.layout.simple_dropdown_item_1line,user_name_list);
//                //    hallbookingusername.setAdapter(user_disp_adapter);
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(FormHallBooking.this, "Server"+ error.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        RequestQueue user_disp_queue=Volley.newRequestQueue(FormHallBooking.this);
//        user_disp_queue.add(user_disp_rq);
//





        /////////////////////////DISP USER ID //////////////////////////////////////////
        hallbookingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // String hallidstr = hallid.getText().toString();
               // Toast.makeText(FormHallBooking.this, "hallidstr" + hallid, Toast.LENGTH_SHORT).show();

                //String hallbookingidstr = hallbookingid.getText().toString();
                //Toast.makeText(FormHallBooking.this, "hallbookingidstr" + hallbookingid, Toast.LENGTH_SHORT).show();

                String hallbookingbookingdatestr = hallbookingbookingdate.getText().toString();
                //Toast.makeText(FormHallBooking.this, "hallbookingbookingdatestr" + hallbookingbookingdate, Toast.LENGTH_SHORT).show();

                String hallbookingtimestr = hallbookingtime.getText().toString();
                //Toast.makeText(FormHallBooking.this, "hallbookingtimestr" + hallbookingtime, Toast.LENGTH_SHORT).show();

            //    String hallbookingdatetimestr = hallbookingdatetime.getText().toString();//
                //Toast.makeText(FormHallBooking.this, "hallbookingdatetimestr" + hallbookingdatetime, Toast.LENGTH_SHORT).show();

                String hallbookingamtstr = hallbookingamt.getText().toString();
                //Toast.makeText(FormHallBooking.this, "hallbookingamtstr" + hallbookingamt, Toast.LENGTH_SHORT).show();

                String hallbookingsubjectstr = hallbookingsubject.getText().toString();
                //Toast.makeText(FormHallBooking.this, "hallbookingsubjectstr" + hallbookingsubject, Toast.LENGTH_SHORT).show();

//                if (hallidstr.length() == 0) {
//                    Toast.makeText(FormHallBooking.this, "Please Enter Hall ID", Toast.LENGTH_SHORT).show();
//                } else if (hallbookingidstr.length() == 0) {
//                    Toast.makeText(FormHallBooking.this, "Please Enter HallBooking ID", Toast.LENGTH_SHORT).show();
//                } else if (hallbookingbookingdatestr.length() == 0) {
//                    Toast.makeText(FormHallBooking.this, "Please Enter HallBooking Date", Toast.LENGTH_SHORT).show();
//                } else if (hallbookingtimestr.length() == 0) {
//                    Toast.makeText(FormHallBooking.this, "Please Enter HallBooking times ID", Toast.LENGTH_SHORT).show();
//                } else if (hallbookingdatetimestr.length() == 0) {
//                    Toast.makeText(FormHallBooking.this, "Please Enter Date And Time ID", Toast.LENGTH_SHORT).show();
//                } else if (hallbookingamtstr.length() == 0) {
//                    Toast.makeText(FormHallBooking.this, "Please Enter Amount", Toast.LENGTH_SHORT).show();
//                } else if (hallbookingsubjectstr.length() == 0) {
//                    Toast.makeText(FormHallBooking.this, "Please Enter Subject", Toast.LENGTH_SHORT).show();
//                } else
//                    {
                       StringRequest hall_booking_re=new StringRequest(Request.Method.POST, Config.ADD_tbl_hall_booking, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if(response.equals("successfully"))
                                {
                                    Toast.makeText(FormHallBooking.this, "Hall_Booking Inserted Successfully", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(FormHallBooking.this, "error", Toast.LENGTH_SHORT).show();
                                }

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(FormHallBooking.this, "Server"+ error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }){
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String,String> hall_booking_params=new HashMap<>();
                                hall_booking_params.put("hallbookingamt",hallbookingamt.getText().toString());
                                hall_booking_params.put("hallbookingsubject",hallbookingsubject.getText().toString());

                               // int id_position=hall_status.getSelectedItemPosition();
                                //String hallid=hall_id_list.get(id_position);
                                //hall_booking_params.put("hallid",hallid);


                             //   int user_position=hallbookinguserid.getSelectedItemPosition();
                              //  String usreid=usre_id_list.get(user_position);
                            //    hall_booking_params.put("usreid",usreid);

                                return hall_booking_params;
                            }
                        };
                        RequestQueue hall_booking_queue= Volley.newRequestQueue(FormHallBooking.this);
                        hall_booking_queue.add(hall_booking_re);
                    }
        });
    }

}
