package com.example.mytestingproject



import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mytestingproject.databinding.ActivityMainBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding: ActivityMainBinding
    private var mMap: GoogleMap? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        // Add a marker in Sydney and move the camera
        // Add a marker in Sydney and move the camera
        val TutorialsPoint = LatLng(21.0, 57.0)
        mMap!!.addMarker(MarkerOptions().position(TutorialsPoint).title("Tutorialspoint.com"))
        mMap!!.moveCamera(CameraUpdateFactory.newLatLng(TutorialsPoint))
    }


}