package com.example.mytestingproject.network

import com.example.mytestingproject.utils.HelperUtils
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkCall {


    companion object {
        @Volatile
        private var INSTANCE: NetworkCall? = null

        fun getInstance(): NetworkCall {
            synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = NetworkCall()
                }
                return INSTANCE!!
            }
        }
    }

    private val httpInterceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }


    private val client = OkHttpClient.Builder().apply {
        connectTimeout(70, TimeUnit.SECONDS)
        readTimeout(70, TimeUnit.SECONDS)
        writeTimeout(70, TimeUnit.SECONDS).addInterceptor {
                val operation =
                    it.request().newBuilder().addHeader("Content-Type", "application/json").build()
                it.proceed(operation)
            }.addInterceptor(httpInterceptor)
    }.build()




    fun getRetrofit(): Retrofit {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).client(client)
            .baseUrl(HelperUtils.apiBaseUrl).build()
    }

}