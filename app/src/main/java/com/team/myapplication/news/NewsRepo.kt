package com.team.myapplication.news

import androidx.lifecycle.MutableLiveData
import com.team.myapplication.news.model.news.Article

interface NewsRepo {
    fun loadNews() : MutableLiveData<List<Article>>?
}