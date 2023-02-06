package com.example.mytestingproject

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.mytestingproject.databinding.ActivityMapsBinding
import com.example.mytestingproject.model.DataItem
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions


class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        /* val sydney = LatLng(-34.0, 151.0)
          mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
          mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))*/
        googleMap.isTrafficEnabled=true
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
            PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) ==
            PackageManager.PERMISSION_GRANTED) {
            googleMap.isMyLocationEnabled=true
        }
        googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        googleMap.uiSettings.isZoomControlsEnabled=true
        googleMap.uiSettings.isMyLocationButtonEnabled=true
        DataItem.myParkingLs.forEach {
            val options = MarkerOptions()
            options.position(it.coo)
            options.snippet(it.snippet)
            options.title(it.title)
            options.anchor(0.5f, 0.5f)
            options.icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons(it.img)!!))

            googleMap.addMarker(options)
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(it.coo, 17.0f))
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(it.coo, 17f))
        }
        googleMap.setOnMarkerClickListener(this@MapsActivity)
    }

    private fun resizeMapIcons(img: Int, width: Int=60, height: Int=100): Bitmap? {
        val imageBitmap = BitmapFactory.decodeResource(resources, img)
        return Bitmap.createScaledBitmap(imageBitmap, width, height, false)
    }

    override fun onMarkerClick(map: Marker): Boolean {
        Toast.makeText(this, "${map.title} is Clicked", Toast.LENGTH_SHORT).show()
        return true
    }
}

