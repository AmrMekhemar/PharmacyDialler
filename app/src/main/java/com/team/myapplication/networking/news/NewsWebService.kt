package com.team.myapplication.networking.news


import com.team.myapplication.model.news.NewsResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsWebService {
    companion object{
        val retrofit: Retrofit
            get() = Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }


    @GET("top-headlines")
    fun loadNews(@Query("country") country: String?,
                 @Query("category") category: String?,
                 @Query("apiKey") apiKey: String?): Call<NewsResponse?>?

}