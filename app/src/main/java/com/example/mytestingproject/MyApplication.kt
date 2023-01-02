package com.example.mytestingproject

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class MyApplication:Application() {
    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var mycontext:Context
    }
    override fun onCreate() {
        super.onCreate()
        mycontext = this
    }
}