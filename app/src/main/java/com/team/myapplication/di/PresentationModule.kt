package com.team.myapplication.di

import android.net.ConnectivityManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.team.myapplication.NetworkStatusChecker
import com.team.myapplication.login.LoginRepository
import com.team.myapplication.login.LoginRepositoryImpl
import com.team.myapplication.login.LoginViewModel
import com.team.myapplication.register.RegisterRepository
import com.team.myapplication.register.RegisterRepositoryImpl
import com.team.myapplication.register.RegisterViewModel
import org.koin.dsl.module
import splitties.init.appCtx

val presentationModule = module {
    fun provideConnectivityManager(): ConnectivityManager {
        return appCtx.getSystemService(ConnectivityManager::class.java)
    }

    fun provideNetworkStatusChecker(): NetworkStatusChecker {
        return NetworkStatusChecker()
    }
    single { provideConnectivityManager() }
    single { provideNetworkStatusChecker() }
    fun provideLayoutManager(): LinearLayoutManager {
        return LinearLayoutManager(appCtx, RecyclerView.VERTICAL, false)
    }
    factory { provideLayoutManager() }

    single<LoginRepository> { LoginRepositoryImpl(get()) }
    single<RegisterRepository> { RegisterRepositoryImpl(get()) }
    single { LoginViewModel(get()) }
    single { RegisterViewModel(get()) }
}