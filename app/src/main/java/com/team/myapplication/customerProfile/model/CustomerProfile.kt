package com.team.myapplication.customerProfile.model

import com.team.myapplication.register.model.LocationAsCoordinates

data class CustomerProfile(
    val _id: String,
    val name: String,
    val email: String,
    val phone: String,
    val locationAsAddress: String,
    val locationAsCoordinates: LocationAsCoordinates,
    val birthDate: String,
    val age: Int,
    val gender: String,
    val verified: Boolean,
    val isVerified: Boolean,
    val iat: Int
)
