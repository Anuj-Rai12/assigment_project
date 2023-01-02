package com.example.mytestingproject.pine.request;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Detail {
    @SerializedName("BillingRefNo")
    public String billingRefNo;
    @SerializedName("Data")
    public ArrayList<Datum> data;
    @SerializedName("MobileNumberForEChargeSlip")
    public String mobileNumberForEChargeSlip;
    @SerializedName("PaymentAmount")
    public String paymentAmount;
    @SerializedName("TransactionType")
    public String transactionType;
}
