package com.example.testkhajakhoj

import android.location.Location

data class Restaurant(
    var id: String = "",
    val name: String = "",
    val address: String = "",
    val cuisineType: String = "",  // Changed to a single cuisine type
    val openTime: String = "",
    val closeTime: String = "",
    val contactNumber: String = "",
    val bikeParking: Boolean = false,
    val carParking: Boolean = false,
    var wifi: Boolean = false,
    val rating: Double = 0.0,  // Changed to double
    val location: String = ""
)
