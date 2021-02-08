package com.team.myapplication.login

import com.team.myapplication.LoginObject
import com.team.myapplication.LoginReturnBody
import com.team.myapplication.RemoteApiService
import retrofit2.http.Body

class LoginRepositoryImpl(private val apiService: RemoteApiService) : LoginRepository {


    override suspend fun login(loginObject: LoginObject, callback: Callback): LoginReturnBody {
        val loginReturnBody = apiService.logIn(loginObject)
        when (loginReturnBody.message) {
            "success" -> callback.onSuccess()
            else -> callback.onError()
        }
        return loginReturnBody

    }
}