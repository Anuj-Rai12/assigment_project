package com.example.mytestingproject

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.mytestingproject.model.DataItem
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragmentTwo : Fragment(), GoogleMap.OnMarkerClickListener {

    @SuppressLint("MissingPermission")
    private val callback = OnMapReadyCallback { googleMap ->
        googleMap.isTrafficEnabled=true
        if (ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION) ==
            PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) ==
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
        googleMap.setOnMarkerClickListener(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps_two, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    private fun resizeMapIcons(img: Int, width: Int=60, height: Int=100): Bitmap? {
        val imageBitmap = BitmapFactory.decodeResource(resources, img)
        return Bitmap.createScaledBitmap(imageBitmap, width, height, false)
    }


    override fun onMarkerClick(map: Marker): Boolean {
        Toast.makeText(requireActivity(), "${map.title} is Clicked", Toast.LENGTH_SHORT).show()
        return true
    }

}