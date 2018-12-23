package com.example.wakeel.qrcodereader;

import android.os.Parcel;
import android.os.Parcelable;

public class PaymentObject implements Parcelable {
    private String TAG_00_PAYLOAD_FORMAT_INDICATOR;
    private String TAG_01_POINT_INITIATION_METHOD;
    private String TAG_02_MERCHANT_IDENTIFIER_VISA;
    private String TAG_04_MERCHANT_IDENTIFIER_MASTERCARD;
    private String TAG_52_MERCHANT_CATEGORY_CODE;
    private String TAG_53_TRANSACTION_CURRENCY_CODE;
    private String TAG_58_COUNTRY_CODE;
    private String TAG_59_MERCHANT_NAME;
    private String TAG_60_MERCHANT_CITY;
    private String TAG_61_POSTAL_CODE;
    private String TAG_63_CRC;

    public PaymentObject() {
    }

    protected PaymentObject(Parcel in) {
        TAG_00_PAYLOAD_FORMAT_INDICATOR = in.readString();
        TAG_01_POINT_INITIATION_METHOD = in.readString();
        TAG_02_MERCHANT_IDENTIFIER_VISA = in.readString();
        TAG_04_MERCHANT_IDENTIFIER_MASTERCARD = in.readString();
        TAG_52_MERCHANT_CATEGORY_CODE = in.readString();
        TAG_53_TRANSACTION_CURRENCY_CODE = in.readString();
        TAG_58_COUNTRY_CODE = in.readString();
        TAG_59_MERCHANT_NAME = in.readString();
        TAG_60_MERCHANT_CITY = in.readString();
        TAG_61_POSTAL_CODE = in.readString();
        TAG_63_CRC = in.readString();
    }

    public static final Creator<PaymentObject> CREATOR = new Creator<PaymentObject>() {
        @Override
        public PaymentObject createFromParcel(Parcel in) {
            return new PaymentObject(in);
        }

        @Override
        public PaymentObject[] newArray(int size) {
            return new PaymentObject[size];
        }
    };

    public String getTAG_00_PAYLOAD_FORMAT_INDICATOR() {
        return TAG_00_PAYLOAD_FORMAT_INDICATOR;
    }

    public void setTAG_00_PAYLOAD_FORMAT_INDICATOR(String TAG_00_PAYLOAD_FORMAT_INDICATOR) {
        this.TAG_00_PAYLOAD_FORMAT_INDICATOR = TAG_00_PAYLOAD_FORMAT_INDICATOR;
    }

    public String getTAG_01_POINT_INITIATION_METHOD() {
        return TAG_01_POINT_INITIATION_METHOD;
    }

    public void setTAG_01_POINT_INITIATION_METHOD(String TAG_01_POINT_INITIATION_METHOD) {
        this.TAG_01_POINT_INITIATION_METHOD = TAG_01_POINT_INITIATION_METHOD;
    }

    public String getTAG_02_MERCHANT_IDENTIFIER_VISA() {
        return TAG_02_MERCHANT_IDENTIFIER_VISA;
    }

    public void setTAG_02_MERCHANT_IDENTIFIER_VISA(String TAG_02_MERCHANT_IDENTIFIER_VISA) {
        this.TAG_02_MERCHANT_IDENTIFIER_VISA = TAG_02_MERCHANT_IDENTIFIER_VISA;
    }

    public String getTAG_04_MERCHANT_IDENTIFIER_MASTERCARD() {
        return TAG_04_MERCHANT_IDENTIFIER_MASTERCARD;
    }

    public void setTAG_04_MERCHANT_IDENTIFIER_MASTERCARD(String TAG_04_MERCHANT_IDENTIFIER_MASTERCARD) {
        this.TAG_04_MERCHANT_IDENTIFIER_MASTERCARD = TAG_04_MERCHANT_IDENTIFIER_MASTERCARD;
    }

    public String getTAG_52_MERCHANT_CATEGORY_CODE() {
        return TAG_52_MERCHANT_CATEGORY_CODE;
    }

    public void setTAG_52_MERCHANT_CATEGORY_CODE(String TAG_52_MERCHANT_CATEGORY_CODE) {
        this.TAG_52_MERCHANT_CATEGORY_CODE = TAG_52_MERCHANT_CATEGORY_CODE;
    }

    public String getTAG_53_TRANSACTION_CURRENCY_CODE() {
        return TAG_53_TRANSACTION_CURRENCY_CODE;
    }

    public void setTAG_53_TRANSACTION_CURRENCY_CODE(String TAG_53_TRANSACTION_CURRENCY_CODE) {
        this.TAG_53_TRANSACTION_CURRENCY_CODE = TAG_53_TRANSACTION_CURRENCY_CODE;
    }

    public String getTAG_58_COUNTRY_CODE() {
        return TAG_58_COUNTRY_CODE;
    }

    public void setTAG_58_COUNTRY_CODE(String TAG_58_COUNTRY_CODE) {
        this.TAG_58_COUNTRY_CODE = TAG_58_COUNTRY_CODE;
    }

    public String getTAG_59_MERCHANT_NAME() {
        return TAG_59_MERCHANT_NAME;
    }

    public void setTAG_59_MERCHANT_NAME(String TAG_59_MERCHANT_NAME) {
        this.TAG_59_MERCHANT_NAME = TAG_59_MERCHANT_NAME;
    }

    public String getTAG_60_MERCHANT_CITY() {
        return TAG_60_MERCHANT_CITY;
    }

    public void setTAG_60_MERCHANT_CITY(String TAG_60_MERCHANT_CITY) {
        this.TAG_60_MERCHANT_CITY = TAG_60_MERCHANT_CITY;
    }

    public String getTAG_61_POSTAL_CODE() {
        return TAG_61_POSTAL_CODE;
    }

    public void setTAG_61_POSTAL_CODE(String TAG_61_POSTAL_CODE) {
        this.TAG_61_POSTAL_CODE = TAG_61_POSTAL_CODE;
    }

    public String getTAG_63_CRC() {
        return TAG_63_CRC;
    }

    public void setTAG_63_CRC(String TAG_63_CRC) {
        this.TAG_63_CRC = TAG_63_CRC;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(TAG_00_PAYLOAD_FORMAT_INDICATOR);
        dest.writeString(TAG_01_POINT_INITIATION_METHOD);
        dest.writeString(TAG_02_MERCHANT_IDENTIFIER_VISA);
        dest.writeString(TAG_04_MERCHANT_IDENTIFIER_MASTERCARD);
        dest.writeString(TAG_52_MERCHANT_CATEGORY_CODE);
        dest.writeString(TAG_53_TRANSACTION_CURRENCY_CODE);
        dest.writeString(TAG_58_COUNTRY_CODE);
        dest.writeString(TAG_59_MERCHANT_NAME);
        dest.writeString(TAG_60_MERCHANT_CITY);
        dest.writeString(TAG_61_POSTAL_CODE);
        dest.writeString(TAG_63_CRC);
    }
}
