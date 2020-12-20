package com.team.myapplication.ui.news

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.team.myapplication.R
import kotlinx.android.synthetic.main.fragment_news.*


class NewsFragment : Fragment() {
companion object{
    var TAG = "NewsFragment"
}
    private lateinit var newsViewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        newsViewModel =
            ViewModelProvider(this).get(NewsViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_news, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        news_rv.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        newsViewModel.loadArticles()?.observe(this.viewLifecycleOwner, Observer {
            Log.d(TAG,"articles: $it")
            news_rv.adapter = NewsAdapter(it){
                // adapter listener
            } })
    }
}