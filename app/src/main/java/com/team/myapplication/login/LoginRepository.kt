package com.team.myapplication.login

import retrofit2.http.Body


interface LoginRepository {
    suspend fun login(username: String, password: String, callback: Callback):Any
}

interface Callback {
    fun onSuccess()
    fun onError()
}