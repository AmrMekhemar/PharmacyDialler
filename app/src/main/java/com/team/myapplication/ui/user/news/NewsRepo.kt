package com.team.myapplication.ui.user.news

import androidx.lifecycle.MutableLiveData
import com.team.myapplication.model.news.Article

interface NewsRepo {
    fun loadNews() : MutableLiveData<List<Article>>?
}