package com.team.myapplication.login

import com.team.myapplication.LoginObject
import com.team.myapplication.LoginReturnBody


interface LoginRepository {
    suspend fun login(loginObject: LoginObject, callback: Callback): LoginReturnBody
}

interface Callback {
    fun onSuccess()
    fun onError()
}