package com.example.mytestingproject

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.content.IntentSender.SendIntentException
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.example.mytestingproject.databinding.ActivityMainBinding
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.Task
import java.io.IOException
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var nowLocation: LatLng? = null
    private var userCurrentLocation: Location? = null
    private var client: FusedLocationProviderClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        checkPermission()

    }


    @SuppressLint("InlinedApi")
    private fun checkPermission() {
        val arr = arrayListOf<String>()

        if (!checkPermissions(this)) {
            arr.add(Manifest.permission.ACCESS_COARSE_LOCATION)
        }
        if (!checkPermissions(this)) {
            arr.add(Manifest.permission.ACCESS_FINE_LOCATION)
        }
        if (!isLocGroundPermission(this)) {
            arr.add(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
        }
        if (arr.isNotEmpty()) {
            requestPermissionsPermission(this, arr.toTypedArray())
        }
        if (!isLocationEnabled(this)) {
            buildAlertMessageNoGps()
        }
        if (arr.isEmpty()) {
            getLastLocation()
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        if (client != null) {
            client!!.lastLocation.addOnCompleteListener { task ->
                if (task.result == null) {
                    requestNewLocationData()
                } else {
                    userCurrentLocation = task.result
                    startLocationService()
                }
            }
        } else {
            requestNewLocationData()
        }
    }


    @SuppressLint("MissingPermission")
    private fun requestNewLocationData() {
        // Initializing LocationRequest
        // object with appropriate methods
        val mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 5
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1
        // setting LocationRequest
        // on FusedLocationClient
        client = LocationServices.getFusedLocationProviderClient(this)
        client?.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper())
        client?.lastLocation?.addOnCompleteListener { task ->

            if (task.result!=null){
                userCurrentLocation=task.result
            }
        }
    }


    private fun buildAlertMessageNoGps() {
        val locationRequest: LocationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 30 * 1000
        locationRequest.fastestInterval = 5 * 1000
        val builder: LocationSettingsRequest.Builder = LocationSettingsRequest.Builder()
            .addLocationRequest(LocationRequest())
        builder.setAlwaysShow(true) //this is the key ingredient
        val result: Task<LocationSettingsResponse> =
            LocationServices.getSettingsClient(this@MainActivity)
                .checkLocationSettings(builder.build())
        result.addOnCompleteListener { task ->
            try {
                val response: LocationSettingsResponse = task.getResult(ApiException::class.java)
                /**
                 * All location settings are satisfied. The client can initialize location requests here.
                 */
            } catch (exception: ApiException) {
                when (exception.statusCode) {
                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED ->                       // Location settings are not satisfied. But could be fixed by showing the user a dialog.
                        try {
                            // Cast to a resolvable exception.
                            val resolvable = exception as ResolvableApiException
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            resolvable.startResolutionForResult(
                                this@MainActivity,
                                REQUEST_CHECK_SETTINGS
                            )
                        } catch (e: SendIntentException) {
                            // Ignore the error.
                        } catch (e: ClassCastException) {
                            // Ignore, should be an impossible error.
                        }
                    LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {}
                }
            }
        }
    }


    private val mLocationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            val mLastLocation = locationResult.lastLocation
            userCurrentLocation = mLastLocation
            createLog("START_LOC","get location latitude ${userCurrentLocation?.latitude}")
            createLog("START_LOC","get location longitude ${userCurrentLocation?.longitude}")
            startLocationService()
        }
    }

    @SuppressLint("MissingPermission")
    private fun startLocationService() {
//        val b: ActivityShoplocationDialogBinding =
//            ActivityShoplocationDialogBinding.inflate(LayoutInflater.from(this@AddBusinessActivity))

     /*   val dialog = Dialog(this@MainActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(binding.root)
        dialog.show()*/
createLog("START_LOC","START LOCATION ITEM")
        MapsInitializer.initialize(this@MainActivity)
        binding.mapView.onCreate(null)
        binding.mapView.getMapAsync { googleMap -> // storing location to temporary variable
            createLog("START_LOC","USER_CURR_LOC $userCurrentLocation")
            val latLng = LatLng(
                userCurrentLocation?.latitude ?: 28.6161251,
                userCurrentLocation?.longitude ?: 77.378797
            ) //your lat lng
            val marker: Marker? = googleMap.addMarker(
                MarkerOptions()
                    .position(latLng)
                    .title(getString(R.string.long_press_marker))
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
            )

            // Enable GPS marker in Map
            googleMap.isMyLocationEnabled = true
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
            googleMap.uiSettings.isZoomControlsEnabled = true
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(15F), 1000, null)
            googleMap.setOnCameraMoveListener {
                val midLatLng: LatLng = googleMap.cameraPosition.target
                if (marker != null) {
                    marker.position = midLatLng
                    nowLocation = marker.position
                }
            }
        }
        //dialog.setCancelable(false)

        /*b.saveLocation.setOnClickListener(object : OnClickListener() {
            fun onClick(v: View?) {
                if (nowLocation != null) {
                    // if user location is null set the previous location fetched
                    location = GeoPoint(nowLocation.latitude, nowLocation.longitude)
                } else {
                    location = GeoPoint(
                        userCurrentLocation.getLatitude(),
                        userCurrentLocation.getLongitude()
                    )
                }
            }
        })*/
    }


    private fun getAddressText(location: LatLng): String? {
        var addresses: List<Address>? = null
        val geocoder = Geocoder(this@MainActivity, Locale.getDefault())
        try {
            addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
            // Here 1 represent max location result to returned, by documents it recommended 1 to 5
        } catch (e: IOException) {
            e.printStackTrace()
        }
        assert(addresses != null)
        return addresses!![0].getAddressLine(0)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_ID) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation()
            } else {
                binding.root.showSandbar("Permission denied!!")
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_CHECK_SETTINGS -> {
                when (resultCode) {
                    Activity.RESULT_OK -> {

                    }
                    Activity.RESULT_CANCELED -> {
                        binding.root.showSandbar("Permission denied!!")
                    }
                }
            }
        }
    }

    companion object {
        const val REQUEST_CHECK_SETTINGS = 1021
    }
}