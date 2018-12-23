package com.example.wakeel.qrcodereader;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class ResultActivity extends AppCompatActivity {
    TextView data1Txt, data2Txt, data3Txt, data4Txt, data5Txt, data6Txt,
            data7Txt, data8Txt, data9Txt, data10Txt, data11Txt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        data1Txt = findViewById(R.id.data1_txt);
        data2Txt = findViewById(R.id.data2_txt);
        data3Txt = findViewById(R.id.data3_txt);
        data4Txt = findViewById(R.id.data4_txt);
        data5Txt = findViewById(R.id.data5_txt);
        data6Txt = findViewById(R.id.data6_txt);
        data7Txt = findViewById(R.id.data7_txt);
        data8Txt = findViewById(R.id.data8_txt);
        data9Txt = findViewById(R.id.data9_txt);
        data10Txt = findViewById(R.id.data10_txt);
        data11Txt = findViewById(R.id.data11_txt);
        if (getIntent().getExtras().getParcelable("payment_data") != null) {
            PaymentObject paymentObject = getIntent().getExtras().getParcelable("payment_data");
            data1Txt.setText(paymentObject.getTAG_00_PAYLOAD_FORMAT_INDICATOR());
            data2Txt.setText(paymentObject.getTAG_01_POINT_INITIATION_METHOD());
            data3Txt.setText(paymentObject.getTAG_02_MERCHANT_IDENTIFIER_VISA());
            data4Txt.setText(paymentObject.getTAG_04_MERCHANT_IDENTIFIER_MASTERCARD());
            data5Txt.setText(paymentObject.getTAG_52_MERCHANT_CATEGORY_CODE());
            data6Txt.setText(paymentObject.getTAG_53_TRANSACTION_CURRENCY_CODE());
            data7Txt.setText(paymentObject.getTAG_58_COUNTRY_CODE());
            data8Txt.setText(paymentObject.getTAG_59_MERCHANT_NAME());
            data9Txt.setText(paymentObject.getTAG_60_MERCHANT_CITY());
            data10Txt.setText(paymentObject.getTAG_61_POSTAL_CODE());
            data11Txt.setText(paymentObject.getTAG_63_CRC());
        } else {
            data1Txt.setText("Error!!!");
        }
    }
}
