package com.team.myapplication.login

import com.team.myapplication.LoginObject
import com.team.myapplication.RemoteApiService
import retrofit2.http.Body

class LoginRepositoryImpl(private val apiService: RemoteApiService) : LoginRepository {
    private val loginObject: LoginObject
        get() = LoginObject("amr.tahhan@outlook.com", "Aa123456789")

    override suspend fun login(username: String, password: String, callback: Callback):Any{
        callback.onSuccess()
        return apiService.logIn(loginObject)

    }
}