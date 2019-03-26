package com.customerservice.login.FlatOwner;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.customerservice.login.R;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.util.HashMap;
import java.util.Random;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class OwnerMaintenanceWidgetActivity extends AppCompatActivity {

    Button btnpay;
    String amount;
    public static final String TAG = "PayUMoneySDK Sample";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_maintenance_widget);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        amount="10";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        btnpay=(Button) findViewById(R.id.btnpay);
//        btnpay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                makePayment(v);
//            }
//        });
//    }
//    private boolean isDouble(String str) {
//        try {
//            Double.parseDouble(str);
//            return true;
//        } catch (NumberFormatException e) {
//            return false;
//        }
//    }
//
//
//    private double getAmount() {
//
//
//        Double amount = 10.0;
//
//        if (isDouble(total)) {
//            amount = Double.parseDouble(txtamount.getText().toString());
//            return amount;
//        } else {
//            Toast.makeText(getApplicationContext(), "Paying Default Amount ₹10", Toast.LENGTH_LONG).show();
//            return amount;
//        }
//    }
//
//    public void makePayment(View view) {
//
//        String phone = "8866555469";
//        String productName = "product_name";
//        String firstName = "Keyur";
//        String txnId = "0nf7" + System.currentTimeMillis();
//        String email="keyur.patel7588@gmail.com";
//        String sUrl = "https://test.payumoney.com/mobileapp/payumoney/success.php";
//        String fUrl = "https://test.payumoney.com/mobileapp/payumoney/failure.php";
//        String udf1 = "";
//        String udf2 = "";
//        String udf3 = "";
//        String udf4 = "";
//        String udf5 = "";
//        boolean isDebug = true;
//        String key = "loFXbrHt";
//        String merchantId = "4934437" ;
//
//        PayUmoneySdkInitilizer.PaymentParam.Builder builder = new PayUmoneySdkInitilizer.PaymentParam.Builder();
//
//
//        builder.setAmount(getAmount())
//                .setTnxId(txnId)
//                .setPhone(phone)
//                .setProductName(productName)
//                .setFirstName(firstName)
//                .setEmail(email)
//                .setsUrl(sUrl)
//                .setfUrl(fUrl)
//                .setUdf1(udf1)
//                .setUdf2(udf2)
//                .setUdf3(udf3)
//                .setUdf4(udf4)
//                .setUdf5(udf5)
//                .setIsDebug(isDebug)
//                .setKey(key)
//                .setMerchantId(merchantId);
//
//        PayUmoneySdkInitilizer.PaymentParam paymentParam = builder.build();

//             server side call required to calculate hash with the help of <salt>
//             <salt> is already shared along with merchant <key>
     /*        serverCalculatedHash =sha512(key|txnid|amount|productinfo|firstname|email|udf1|udf2|udf3|udf4|udf5|<salt>)
             (e.g.)
             sha512(FCstqb|0nf7|10.0|product_name|piyush|piyush.jain@payu.in||||||MBgjYaFG)
             9f1ce50ba8995e970a23c33e665a990e648df8de3baf64a33e19815acd402275617a16041e421cfa10b7532369f5f12725c7fcf69e8d10da64c59087008590fc
*/

        // Recommended
//        calculateServerSideHashAndInitiatePayment(paymentParam);

//        testing purpose

       /* String salt = "";
        String serverCalculatedHash=hashCal(key+"|"+txnId+"|"+getAmount()+"|"+productName+"|"
                +firstName+"|"+email+"|"+udf1+"|"+udf2+"|"+udf3+"|"+udf4+"|"+udf5+"|"+salt);
        paymentParam.setMerchantHash(serverCalculatedHash);
        PayUmoneySdkInitilizer.startPaymentActivityForResult(MainActivity.this, paymentParam);*/

    }
//
//    public static String hashCal(String str) {
//        byte[] hashseq = str.getBytes();
//        StringBuilder hexString = new StringBuilder();
//        try {
//            MessageDigest algorithm = MessageDigest.getInstance("SHA-512");
//            algorithm.reset();
//            algorithm.update(hashseq);
//            byte messageDigest[] = algorithm.digest();
//            for (byte aMessageDigest : messageDigest) {
//                String hex = Integer.toHexString(0xFF & aMessageDigest);
//                if (hex.length() == 1) {
//                    hexString.append("0");
//                }
//                hexString.append(hex);
//            }
//        } catch (NoSuchAlgorithmException ignored) {
//        }
//        return hexString.toString();
//    }
//
//    private void calculateServerSideHashAndInitiatePayment(final PayUmoneySdkInitilizer.PaymentParam paymentParam) {
//
//        // Replace your server side hash generator API URL
//        String url = "https://test.payumoney.com/payment/op/calculateHashForTest";
//
//        Toast.makeText(this, "Please wait... Generating hash from server ... ", Toast.LENGTH_LONG).show();
//        StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//
//                    if (jsonObject.has(SdkConstants.STATUS)) {
//                        String status = jsonObject.optString(SdkConstants.STATUS);
//                        if (status != null || status.equals("1")) {
//
//                            String hash = jsonObject.getString(SdkConstants.RESULT);
//                            Log.i("app_activity", "Server calculated Hash :  " + hash);
//
//                            paymentParam.setMerchantHash(hash);
//
//                            PayUmoneySdkInitilizer.startPaymentActivityForResult(MainActivity.this, paymentParam);
//                        } else {
//                            Toast.makeText(MainActivity.this,
//                                    jsonObject.getString(SdkConstants.RESULT),
//                                    Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//                if (error instanceof NoConnectionError) {
//                    Toast.makeText(MainActivity.this,
//                            MainActivity.this.getString(R.string.connect_to_internet),
//                            Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(MainActivity.this,
//                            error.getMessage(),
//                            Toast.LENGTH_SHORT).show();
//
//                }
//
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                return paymentParam.getParams();
//            }
//        };
//        Volley.newRequestQueue(this).add(jsonObjectRequest);
//    }
//
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        if (requestCode == PayUmoneySdkInitilizer.PAYU_SDK_PAYMENT_REQUEST_CODE) {
//
//            /*if(data != null && data.hasExtra("result")){
//              String responsePayUmoney = data.getStringExtra("result");
//                if(SdkHelper.checkForValidString(responsePayUmoney))
//                    showDialogMessage(responsePayUmoney);
//            } else {
//                showDialogMessage("Unable to get Status of Payment");
//            }*/
//
//
//            if (resultCode == RESULT_OK) {
//                Log.i(TAG, "Success - Payment ID : " + data.getStringExtra(SdkConstants.PAYMENT_ID));
//                String paymentId = data.getStringExtra(SdkConstants.PAYMENT_ID);
//                showDialogMessage("Payment Success Id : " + paymentId);
//            } else if (resultCode == RESULT_CANCELED) {
//                Log.i(TAG, "failure");
//                showDialogMessage("cancelled");
//            } else if (resultCode == PayUmoneySdkInitilizer.RESULT_FAILED) {
//                Log.i("app_activity", "failure");
//
//                if (data != null) {
//                    if (data.getStringExtra(SdkConstants.RESULT).equals("cancel")) {
//
//                    } else {
//                        showDialogMessage("failure");
//                    }
//                }
//                //Write your code if there's no result
//            } else if (resultCode == PayUmoneySdkInitilizer.RESULT_BACK) {
//                Log.i(TAG, "User returned without login");
//                showDialogMessage("User returned without login");
//            }
//        }
//    }
//
//    private void showDialogMessage(String message) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle(TAG);
//        builder.setMessage(message);
//        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//        builder.show();
//
//    }
}
