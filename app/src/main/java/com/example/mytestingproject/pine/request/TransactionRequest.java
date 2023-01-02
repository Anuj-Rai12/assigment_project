package com.example.mytestingproject.pine.request;

import com.example.mytestingproject.unit.GsonUtils;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class TransactionRequest {
    @SerializedName("APP_ID")
    public String aPP_ID;
    @SerializedName("Detail")
    public Detail detail;
    @SerializedName("Header")
    public Header header;

    @Override
    public String toString() {
        return GsonUtils.fromJsonToString(this);
    }
}
