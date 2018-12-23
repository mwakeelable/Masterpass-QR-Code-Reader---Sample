package com.example.wakeel.qrcodereader;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentResult;
import com.mastercard.mpqr.pushpayment.exception.FormatException;
import com.mastercard.mpqr.pushpayment.model.PushPaymentData;
import com.mastercard.mpqr.pushpayment.scan.PPIntentIntegrator;
import com.mastercard.mpqr.pushpayment.scan.constant.PPIntents;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView resultInfoTxt;
    Button scanBtn;
    private static final int PERMISSION_REQUEST_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        resultInfoTxt = findViewById(R.id.result_info_txt);
        scanBtn = findViewById(R.id.scan_btn);
        scanBtn.setOnClickListener(this);
    }

    private boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        return true;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA},
                PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    PPIntentIntegrator integrator = new PPIntentIntegrator(MainActivity.this);
                    integrator.setCameraId(0);
                    integrator.setBeepEnabled(false);
                    integrator.initiateScan();
                } else {
                    Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED) {
                            showMessageOKCancel("You need to allow access permissions",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermission();
                                            }
                                        }
                                    });
                        }
                    }
                }
                break;
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(MainActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            IntentResult result = PPIntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            PushPaymentData qrcode = (PushPaymentData) data.getSerializableExtra(PPIntents.PUSH_PAYMENT_DATA);
            if (qrcode != null) {
                PaymentObject paymentObject = new PaymentObject();
                paymentObject.setTAG_01_POINT_INITIATION_METHOD(qrcode.getPointOfInitiationMethod());
                paymentObject.setTAG_04_MERCHANT_IDENTIFIER_MASTERCARD(qrcode.getMerchantIdentifierMastercard04());
                paymentObject.setTAG_58_COUNTRY_CODE(qrcode.getCountryCode());
                paymentObject.setTAG_00_PAYLOAD_FORMAT_INDICATOR(qrcode.getPayloadFormatIndicator());
                paymentObject.setTAG_61_POSTAL_CODE(qrcode.getPostalCode());
                paymentObject.setTAG_53_TRANSACTION_CURRENCY_CODE(qrcode.getTransactionCurrencyCode());
                paymentObject.setTAG_59_MERCHANT_NAME(qrcode.getMerchantName());
                paymentObject.setTAG_63_CRC(qrcode.getCRC());
                paymentObject.setTAG_52_MERCHANT_CATEGORY_CODE(qrcode.getMerchantCategoryCode());
                paymentObject.setTAG_60_MERCHANT_CITY(qrcode.getMerchantCity());
                paymentObject.setTAG_02_MERCHANT_IDENTIFIER_VISA(qrcode.getMerchantIdentifierVisa02());
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra("payment_data", paymentObject);
                startActivity(intent);
            }
        } else if (resultCode == RESULT_CANCELED) {
            if (data != null) {
                FormatException e = (FormatException) data.getSerializableExtra(PPIntents.PARSE_ERROR);
                if (e != null) {
                    Toast.makeText(this, "Exception: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
                PushPaymentData qrcode = (PushPaymentData) data.getSerializableExtra(PPIntents.PUSH_PAYMENT_DATA);
                if (qrcode != null) {
                    String crc = qrcode.getCRC();
                    Toast.makeText(MainActivity.this, crc, Toast.LENGTH_SHORT).show();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.scan_btn:
                if (checkPermission()) {
                    PPIntentIntegrator integrator = new PPIntentIntegrator(MainActivity.this);
                    integrator.setCameraId(0);
                    integrator.setBeepEnabled(false);
                    integrator.initiateScan();
                } else
                    requestPermission();
        }
    }
}
