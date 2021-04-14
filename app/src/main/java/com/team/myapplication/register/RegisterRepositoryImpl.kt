package com.team.myapplication.register

import android.util.Log
import com.team.myapplication.RemoteApiService
import com.team.myapplication.login.Callback
import com.team.myapplication.register.model.Coordinates
import com.team.myapplication.register.model.LocationAsCoordinates
import com.team.myapplication.register.model.RegisterObject
import com.team.myapplication.register.model.RegisterReturnBody

class RegisterRepositoryImpl(private val apiService: RemoteApiService) : RegisterRepository {
    private val TAG = "RegisterRepoImpl"
    override suspend fun register(
        registerObject: RegisterObject,
        callback: Callback
    ): RegisterReturnBody {
        val body = apiService.register(registerObject)
        when (body.message) {
            "success" -> callback.onSuccess()
            else -> callback.onError()
        }
        return body
    }
}