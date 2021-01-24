package com.team.myapplication.di

import androidx.room.Room
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.team.myapplication.Application.Companion.BASE_URL
import com.team.myapplication.RemoteApiService
import com.team.myapplication.TLSSocketFactory
import com.team.myapplication.TrustManager
import com.team.myapplication.login.LoginRepository
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import splitties.init.appCtx
import java.security.cert.TrustAnchor
import javax.net.ssl.TrustManagerFactory

val LoginRepositoryModule = module {
    fun provideUseApi(retrofit: Retrofit): RemoteApiService {
        return retrofit.create(RemoteApiService::class.java)
    }
//
//    fun provideDB(): ArticleDB {
//        return Room.databaseBuilder(appCtx, ArticleDB::class.java, DATABASE_NAME)
//            .build()
//    }
//
//    fun provideDao(): ArticlesDAO {
//        return provideDB().articleDao()
//    }

    fun provideGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }

    fun provideHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
//        val tlsSocketFactory = TLSSocketFactory()
        //sslSocketFactory(tlsSocketFactory,TrustManager())
        return okHttpClientBuilder.build()
    }

    fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(factory))
            .client(client)
            .build()
    }

    single { provideGson() }
    single { provideHttpClient() }
    single { provideRetrofit(get(), get()) }
//    single { provideDB() }
//    single { provideDao() }
    single { provideUseApi(get()) }
}