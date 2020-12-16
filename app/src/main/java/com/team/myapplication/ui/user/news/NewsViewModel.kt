package com.team.myapplication.ui.user.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.team.myapplication.model.news.Article

class NewsViewModel : ViewModel() {
    val repoImp = NewsRepoImp()
    var ArticleList: MutableLiveData<List<Article>>? = null
    fun loadArticles(): MutableLiveData<List<Article>>? = repoImp.loadNews()
    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}