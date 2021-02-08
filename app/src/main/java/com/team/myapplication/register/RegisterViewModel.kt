package com.team.myapplication.register

import androidx.lifecycle.ViewModel
import com.team.myapplication.login.Callback
import com.team.myapplication.register.model.LocationAsCoordinates
import com.team.myapplication.register.model.RegisterObject
import com.team.myapplication.register.model.RegisterReturnBody
import com.team.myapplication.toast
import splitties.init.appCtx

class RegisterViewModel(private val registerRepo:RegisterRepository): ViewModel() {

    suspend fun register(
        registerObject: RegisterObject) :RegisterReturnBody {


        return  registerRepo.register( registerObject,
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