package com.example.mytestingproject.model


import com.google.gson.annotations.SerializedName

data class Truck(
    @SerializedName("Range_End") val rangeEnd: Int,
    @SerializedName("Range_Price") val rangePrice: Int,
    @SerializedName("Range_Start") val rangeStart: Int
)