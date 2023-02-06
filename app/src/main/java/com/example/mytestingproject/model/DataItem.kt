package com.example.mytestingproject.model

import com.example.mytestingproject.R
import com.google.android.gms.maps.model.LatLng

data class DataItem(
    val title: String,
    val coo: LatLng,
    val snippet: String,
    val img: Int = R.drawable.ic_user
) {
    companion object {
        val myParkingLs = arrayListOf(
            DataItem(
                "HARIGO(TEST)",
                LatLng(28.616163, 77.378804),
                "Snippet for Hari go(TEST)"
            ),
            DataItem(
                "Transport Authority Sarai Kale Khan",
                LatLng(28.616163, 77.260847),
                "Snippet for Transport Authority Sarai Kale Khan"
            ),
            DataItem(
                "SHAHEEN BAGH",
                LatLng(28.542561, 77.300970),
                "Snippet for SHAHEEN BAGH"
            )
        )
    }
}
