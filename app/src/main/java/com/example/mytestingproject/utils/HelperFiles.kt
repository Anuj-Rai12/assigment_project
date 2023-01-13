package com.example.mytestingproject.utils

import android.app.Activity
import android.util.Log
import android.widget.Toast

fun Activity.msg(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun createLog(tag: String, msg: String) {
    Log.i(tag, "createLog: $msg")
}