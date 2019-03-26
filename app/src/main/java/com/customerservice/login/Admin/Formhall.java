package com.customerservice.login.Admin;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.Image;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.customerservice.login.R;
import com.customerservice.login.Utility.Config;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import spencerstudios.com.fab_toast.FabToast;


public class Formhall extends AppCompatActivity {

    Toolbar toolbar;
    EditText halltitle,hallcapacity;
    TextInputLayout input_layout_halltitle,input_layout_hallcapacity;

    Button hallbtn;
    private static final int STORAGE_PERMISSION_CODE = 123;
    Uri filePath;
    int PICK_IMAGE_REQUEST = 111;
    Bitmap bitmap;
    String Hallid;
    ImageView hallimg1,hallimg2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formhall);
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        hallbtn=(Button)findViewById(R.id.hallbtn);

        halltitle=(EditText)findViewById(R.id.halltitle);
        hallcapacity=(EditText)findViewById(R.id.hallcapacity);

        hallimg1=(ImageView)findViewById(R.id.hallimg1);
        hallimg2=(ImageView)findViewById(R.id.hallimg2);

        input_layout_halltitle=(TextInputLayout) findViewById(R.id.input_layout_halltitle);
        input_layout_hallcapacity=(TextInputLayout) findViewById(R.id.input_layout_hallcapacity);
        requestStoragePermission();

        hallimg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);
            }
        });

        hallbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String halltitlestr = halltitle.getText().toString();

                final String hallcapacitystr = hallcapacity.getText().toString();


                if (halltitlestr.length() == 0) {
                    Toast.makeText(Formhall.this, "Please Enter Hall Title", Toast.LENGTH_SHORT).show();
                } else if (hallcapacitystr.length() == 0) {
                    Toast.makeText(Formhall.this, "Please Enter Hall Capacity", Toast.LENGTH_SHORT).show();
                } else
                    {
                        StringRequest hall_rq=new StringRequest(Request.Method.POST, Config.ADD_tbl_hall, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if(response.equals("error"))
                                {
                                    FabToast.makeText(Formhall.this, "Error", FabToast.LENGTH_LONG, FabToast.ERROR,  FabToast.POSITION_DEFAULT).show();
                                }
                                else
                                {
                                    Hallid=response;
                                    Toast.makeText(Formhall.this, ""+Hallid, Toast.LENGTH_SHORT).show();
                                    uploadMultipart(Hallid);
                                    FabToast.makeText(Formhall.this, "Successfully Added", FabToast.LENGTH_LONG, FabToast.SUCCESS,  FabToast.POSITION_DEFAULT).show();
                                }

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }){
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String,String> hall_params=new HashMap<>();
                                hall_params.put("halltitle",halltitlestr);
                                hall_params.put("hallcapacity",hallcapacitystr);
                                return hall_params;
                            }
                        };
                        RequestQueue hall_queue= Volley.newRequestQueue(Formhall.this);
                        hall_queue.add(hall_rq);
                    }
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
            new MultipartUploadRequest(this, uploadId,Config.UPLOAD_Hall_IMAGE)
                    .addFileToUpload(path, "image") //Adding file
                    .addParameter("hallid", id) //Adding text parameter to the request
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
                hallimg1.setImageBitmap(bitmap);
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
