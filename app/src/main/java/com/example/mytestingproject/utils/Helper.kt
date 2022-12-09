package com.example.mytestingproject.utils

import android.app.Activity
import android.content.Context
import android.os.Build
import android.widget.Toast
import com.example.mytestingproject.R

fun Activity.changeStatusBarColor(color: Int = R.color.main_color) {
    this.window?.statusBarColor = getColorInt(color)
}

fun Activity.getColorInt(color: Int): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        resources.getColor(color, null)
    } else {
        resources.getColor(color)
    }
}
fun Context.msg(string: String, time: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, string, time).show()
}