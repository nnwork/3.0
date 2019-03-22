package com.customerservice.login.FlatOwner;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.customerservice.login.Admin.Formhall;
import com.customerservice.login.R;
import com.customerservice.login.Utility.Config;
import com.customerservice.login.Utility.SessionManager;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class Formcomplain extends AppCompatActivity {

    Button complainbtn;
    EditText complainproblem;
    ImageView complainimg;
    Spinner complainfcatid;
    private static final int STORAGE_PERMISSION_CODE = 123;
    Uri filePath;
    int PICK_IMAGE_REQUEST = 111;
    Bitmap bitmap;
    String Complainid;
    ArrayList<String> hcat_id_List=new ArrayList<>();
    ArrayList<String> hcat_name_List=new ArrayList<>();

    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formcomplain);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        complainbtn = (Button) findViewById(R.id.complainbtn);
        complainproblem = (EditText) findViewById(R.id.complainproblem);
        complainimg=(ImageView) findViewById(R.id.complainimg);

        sessionManager = new SessionManager(this);
       //final Spinner spinner_complainstatus =(Spinner) findViewById(R.id.complainstatus);
       final Spinner spinner_complainfcatid=(Spinner)findViewById(R.id.complainfcatid);

        requestStoragePermission();

        complainimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);
            }
        });
        //Display helper_category
        StringRequest complains_halpercat_disp_rq=new StringRequest(Request.Method.POST, Config.Disp_tbl_halper_comlain, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                   // Toast.makeText(Formcomplain.this, ""+response, Toast.LENGTH_SHORT).show();

                    JSONArray co_help_cat_JsonArray = new JSONArray(response);

                    for (int j = 0; j < co_help_cat_JsonArray.length(); j++) {

                        JSONObject co_help_cat_JsonObject = co_help_cat_JsonArray.getJSONObject(j);

                        String hcat_id = co_help_cat_JsonObject.getString("hcat_id");
                        String hcat_name = co_help_cat_JsonObject.getString("hcat_name");

                        hcat_id_List.add(hcat_id);
                        hcat_name_List.add(hcat_name);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                finally {
                    ArrayAdapter co_help_cat_ArrayAdapter=new ArrayAdapter(Formcomplain.this,android.R.layout.simple_dropdown_item_1line,hcat_name_List);
                    spinner_complainfcatid.setAdapter(co_help_cat_ArrayAdapter);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue disp_halpercat_queue=Volley.newRequestQueue(Formcomplain.this);
        disp_halpercat_queue.add(complains_halpercat_disp_rq);

        ////Diplay/////////////////////////////////////////////////////////////////////
        complainbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String  complainproblemstr = complainproblem.getText().toString();

//                if (complainidstr.length() == 0) {
//                    Toast.makeText(Formcomplain.this, "Please Enter complain ID", Toast.LENGTH_SHORT).show();
//                } else if (complainproblemstr.length() == 0) {
//                    Toast.makeText(Formcomplain.this, "Please Enter problem ID", Toast.LENGTH_SHORT).show();
//                } else if (complain_datestr.length() == 0) {
//                    Toast.makeText(Formcomplain.this, "Please Enter Complain Date", Toast.LENGTH_SHORT).show();
//                } else if (complain_v_datestr.length() == 0) {
//                    Toast.makeText(Formcomplain.this, "Please Enter Complain V Date", Toast.LENGTH_SHORT).show();
//                } else if (complain_v_timestr.length() == 0) {
//                    Toast.makeText(Formcomplain.this, "Please Enter Complain V Time", Toast.LENGTH_SHORT).show();
//                } else if (complain_datet_imestr.length() == 0) {
//                    Toast.makeText(Formcomplain.this, "Please Enter Date Time", Toast.LENGTH_SHORT).show();
//                }
//                else
//                {

                    StringRequest comlain_rq=new StringRequest(Request.Method.POST,Config.ADD_tbl_complain, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //Toast.makeText(Formcomplain.this, ""+sessionManager.getId(), Toast.LENGTH_SHORT).show();
                            //Toast.makeText(Formcomplain.this, "Response : "+response, Toast.LENGTH_SHORT).show();

                            if(response.equals("error"))
                            {
                                Toast.makeText(Formcomplain.this, "error", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Complainid=response;
                                //Toast.makeText(Formcomplain.this, ""+Complainid, Toast.LENGTH_SHORT).show();
                                uploadMultipart(Complainid);
                                Toast.makeText(Formcomplain.this, "Complain Inserted Successfully", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Formcomplain.this, "Server Error : "+error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                        ////user table
                            Map<String,String> co_params=new HashMap<>();
                            co_params.put("complainproblem",complainproblemstr);

                            co_params.put("userid",sessionManager.getId());
                        //////helper_categori
                            int help_cat_position=spinner_complainfcatid.getSelectedItemPosition();
                            String hcatid=hcat_id_List.get(help_cat_position);
                            co_params.put("hcatid",hcatid);
                            return co_params;
                        }
                    };
                    RequestQueue co_queue=Volley.newRequestQueue(Formcomplain.this);
                    co_queue.add(comlain_rq);
                //}
            }
        });

    }
    public void uploadMultipart(String id) {
        //getting name for the image
        //getting the actual path of the image
        String path = getPath(filePath);
        //Uploading code
        try {
            String uploadId = UUID.randomUUID().toString();

            //Creating a multi part request
            new MultipartUploadRequest(this, uploadId,Config.UPLOAD_Complain_IMAGE)
                    .addFileToUpload(path, "image") //Adding file
                    .addParameter("Complainid", id) //Adding text parameter to the request
                    .setNotificationConfig(new UploadNotificationConfig())
                    .setMaxRetries(2)
                    .startUpload(); //Starting the upload

        } catch (Exception exc) {
            Toast.makeText(this, exc.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    //method to get the file path from uri
    public String getPath(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getContentResolver().query(
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                //getting image from gallery

                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                complainimg.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    //Requesting permission
    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }


    //This method will be called when the user will tap on allow or deny
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if (requestCode == STORAGE_PERMISSION_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Displaying a toast
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }

}