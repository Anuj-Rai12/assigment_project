package com.example.mytestingproject.utils

import android.app.Activity
import android.widget.Toast

fun Activity.msg(string: String){
    Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
}