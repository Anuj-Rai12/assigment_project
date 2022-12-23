package com.example.mytestingproject.api

import android.annotation.SuppressLint
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit


class APInstance {

    private val httpInterceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }


    private val client=OkHttpClient().newBuilder().apply {
        connectTimeout(70, TimeUnit.SECONDS)
        readTimeout(70, TimeUnit.SECONDS)
        writeTimeout(70, TimeUnit.SECONDS)
            .addInterceptor(httpInterceptor)
    }.build()

    //val mediaType="".toMediaTypeOrNull()
    companion object {
        fun createRequestBody(mediaType: MediaType?,body:String)=body.toRequestBody(mediaType)
        enum class TYPE{
            POST,
            GET
        }
    }

    @SuppressLint("SuspiciousIndentation")
    fun getResponse(url:String, method: Pair<String,RequestBody>, headers: HashMap<String,String>): Response {
        val reqBuilder=Request.Builder().url(url).method(method.first,method.second)
            headers.forEach { head->
                reqBuilder.addHeader(head.key,head.value)
            }
        return client.newCall(reqBuilder.build()).execute()
    }


   private var client2 = OkHttpClient().newBuilder().
           addInterceptor(httpInterceptor)
        .build()

    private var mediaType = "application/x-www-form-urlencoded".toMediaTypeOrNull()
    private var body: RequestBody = RequestBody.create(
        mediaType,
        "Device_Name=INFINIX MOBILITY LIMITED Infinix X688B&Device_Id=3eb8e470d38312b4&Device_Type=Android&App_Package=com.myparkingsindia.gatekeeper&Accesskey=9M5P&App_Version=3.0.1&Operator_Id=470e7a4f017a5476afb7eeb3f8b96f9b"
    )
    private var request: Request = Request.Builder()
        .url("https://app.myparkings.com/API_G1/Profile_Gate.php")
        .method("POST", body)
        .addHeader("Timestamp", "9b12778a03c745bac897d509328c6e75")
        .addHeader("Content-Type", "application/x-www-form-urlencoded")
        .build()

    var response: Response = client2.newCall(request).execute()

}