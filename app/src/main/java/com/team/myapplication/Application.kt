package com.team.myapplication

import android.app.Application
import com.team.myapplication.di.LoginRepositoryModule
import com.team.myapplication.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import java.util.logging.Level

class Application : Application() {
    companion object {
        val BASE_URL = "http://192.168.1.2:3000/"
        val DATABASE_NAME = "article_database"
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(org.koin.core.logger.Level.DEBUG)
            androidContext(this@Application)
            modules(listOf(presentationModule, LoginRepositoryModule))
        }
    }
}