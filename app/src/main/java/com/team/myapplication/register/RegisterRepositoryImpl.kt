package com.team.myapplication.register

import com.team.myapplication.RemoteApiService
import com.team.myapplication.login.Callback
import com.team.myapplication.register.model.Coordinates
import com.team.myapplication.register.model.LocationAsCoordinates
import com.team.myapplication.register.model.RegisterObject

class RegisterRepositoryImpl(private val apiService: RemoteApiService) :RegisterRepository {
    val registerObject = RegisterObject(
         "Amr",
         "amr.wf15@yahoo.com",
         "Aa123456789",
         "Aa123456789",
        "01122436770",
        "sh dfathffdf fdfby st"
            , LocationAsCoordinates(coordinates = Coordinates(154544,415584))
    )
    override suspend fun register(
        name: String,
        email: String,
        password: String,
        confirmPassword: String,
        phones: String,
        locationAsAddress: String,
        locationAsCoordinates: LocationAsCoordinates,
        callback: Callback
    ): Any {
        return apiService.register(registerObject)
    }
}