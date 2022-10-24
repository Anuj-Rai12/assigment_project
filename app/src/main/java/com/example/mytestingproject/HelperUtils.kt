package com.example.mytestingproject

import android.app.Activity
import android.util.Log
import android.widget.Toast

fun Activity.toastMsg(string: String) {
    Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
}

fun setLogCat(title: String, msg: String) {
    Log.i(title, "setLogCat: $msg")
}