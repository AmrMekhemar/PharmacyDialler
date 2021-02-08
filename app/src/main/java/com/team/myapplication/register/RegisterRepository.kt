package com.team.myapplication.register

import com.team.myapplication.login.Callback
import com.team.myapplication.register.model.LocationAsCoordinates
import com.team.myapplication.register.model.RegisterObject
import com.team.myapplication.register.model.RegisterReturnBody

interface RegisterRepository {
    suspend fun register(
         registerObject: RegisterObject,
         callback: Callback):RegisterReturnBody
}

