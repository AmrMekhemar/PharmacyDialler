package com.team.myapplication.register

import androidx.lifecycle.ViewModel
import com.team.myapplication.login.Callback
import com.team.myapplication.register.model.LocationAsCoordinates
import com.team.myapplication.toast
import splitties.init.appCtx

class RegisterViewModel(private val registerRepo:RegisterRepository): ViewModel() {

    suspend fun register(
        name: String,
        email: String,
        password: String,
        confirmPassword: String,
        phones: String,
        locationAsAddress: String,
        locationAsCoordinates: LocationAsCoordinates) :Any {


        return  registerRepo.register( name,
            email,
            password,
            confirmPassword,
            phones,
            locationAsAddress,
            locationAsCoordinates,
            object : Callback {
                override fun onSuccess() {
                    appCtx.toast("Registered")
                }

                override fun onError() {
                    appCtx.toast("Not Registered due to an error")
                }

            }
             )
    }
}