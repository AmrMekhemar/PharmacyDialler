package com.team.myapplication.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {
private val TAG = "LoginViewModel"
    private val loginStatus = MutableLiveData<LoginStatus>()

    fun getLoginStatus(): LiveData<LoginStatus> = loginStatus

    suspend fun login(username: String, password: String) :Any {
        loginStatus.value = LoginStatus.Loading()
      return  repository.login(username, password, object : Callback {
            override fun onSuccess() {
                loginStatus.value = LoginStatus.Success()
            }

            override fun onError() {
                loginStatus.value = LoginStatus.Error()
            }
        })
    }
}

sealed class LoginStatus {
    class Error() : LoginStatus()
    class Success() : LoginStatus()
    class Loading() : LoginStatus()
}