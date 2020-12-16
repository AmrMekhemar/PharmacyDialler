package com.team.myapplication.ui.user.news

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.team.myapplication.R
import com.team.myapplication.model.news.Article
import com.team.myapplication.model.news.NewsResponse
import com.team.myapplication.networking.news.NewsWebService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepoImp : NewsRepo {
    companion object {
         var TAG = NewsRepoImp::class.java.name
    }
    var articleList: MutableLiveData<List<Article>>? = MutableLiveData()
    override fun loadNews(): MutableLiveData<List<Article>>? {
        val mService = NewsWebService.retrofit.create(NewsWebService::class.java)
        mService.loadNews("eg","health","4b9d06a498454da48992eec2590e60d2")!!
            .enqueue(object : Callback<NewsResponse?> {
                override fun onFailure(call: Call<NewsResponse?>, t: Throwable) {
                    Log.d(TAG, "message: $t")
                }

                override fun onResponse(
                    call: Call<NewsResponse?>,
                    response: Response<NewsResponse?>
                ) {
                    val newsResponse = response.body()
                    if (newsResponse != null)
                        articleList?.postValue(newsResponse.articles)
                    else{
                        articleList?.postValue(null)
                        Log.d(TAG, "message: news is null")
                    }

                }

            })
        return articleList
    }
}