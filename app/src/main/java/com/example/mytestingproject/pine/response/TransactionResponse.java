package com.example.mytestingproject.pine.response;

import com.example.mytestingproject.unit.GsonUtils;
import com.google.gson.annotations.SerializedName;

public class TransactionResponse {
    @SerializedName("Header")
    public Header header;
    @SerializedName("Response")
    public Response response;

    @Override
    public String toString() {
        return GsonUtils.fromJsonToString(this);
    }
}
