package com.example.mytestingproject.model


import com.google.gson.annotations.SerializedName

data class OperatorID(
    @SerializedName("Message") val message: String,
    @SerializedName("Status") val status: String,
    @SerializedName("User") val user: User
)