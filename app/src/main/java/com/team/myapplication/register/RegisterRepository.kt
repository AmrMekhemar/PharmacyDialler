package com.team.myapplication.register

import com.team.myapplication.login.Callback
import com.team.myapplication.register.model.LocationAsCoordinates

interface RegisterRepository {
    suspend fun register(
         name: String,
         email: String,
         password: String,
         confirmPassword: String,
         phones: String,
         locationAsAddress: String,
         locationAsCoordinates: LocationAsCoordinates,
         callback: Callback):Any
}

