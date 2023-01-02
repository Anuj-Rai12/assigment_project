package com.example.mytestingproject.unit

import android.util.Log

object Utils {

    fun createLogcat(tag:String,msg:String){
        Log.i(tag, "Result --> $msg")
    }

}