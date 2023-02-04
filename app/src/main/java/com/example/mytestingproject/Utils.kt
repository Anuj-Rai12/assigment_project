package com.example.mytestingproject

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import com.google.android.material.snackbar.Snackbar

const val PERMISSION_ID = 1012

/**
 * Method to check for permissions
 */
fun checkPermissions(activity: Activity): Boolean {
    return ActivityCompat.checkSelfPermission(
        activity, Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
        activity, Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED
}

fun isLocGroundPermission(activity: Activity): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        ActivityCompat.checkSelfPermission(
            activity, Manifest.permission.ACCESS_BACKGROUND_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    } else true

}

/**
 * Method to request for permissions
 */
fun requestPermissionsPermission(context: Activity, arr: Array<String>) {
    ActivityCompat.requestPermissions(
        context, arr, PERMISSION_ID
    )
}

/**
 * Method to check if location is enabled
 * @return true || false
 */
 fun isLocationEnabled(activity: Activity): Boolean {
    val locationManager = activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager?
    return locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
        LocationManager.NETWORK_PROVIDER
    )
}

fun View.showSandbar(msg: String, length: Int = Snackbar.LENGTH_SHORT, color: Int? = null) {
    val snackBar = Snackbar.make(this, msg, length)
    color?.let {
        snackBar.view.setBackgroundColor(it)
    }
    snackBar.show()
}

fun createLog(tag:String,msg:String) {
    Log.i(tag, "createLog: $msg")
}