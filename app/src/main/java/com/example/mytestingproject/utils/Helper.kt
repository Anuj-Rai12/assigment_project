package com.example.mytestingproject.utils

import com.google.gson.Gson


object Headers{
    const val xxxUrlCode="application/x-www-form-urlencoded"
    const val profileUrl="https://app.myparkings.com/API_G1/Profile_Gate.php"
}
fun <T> serializeToJson(bmp: T): String? {
    val gson = Gson()
    return gson.toJson(bmp)
}


inline fun <reified T> deserializeFromJson(jsonFile: String?): T? {
    val gson = Gson()
    return gson.fromJson(jsonFile, T::class.java)
}
