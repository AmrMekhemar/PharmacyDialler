package com.team.myapplication.news

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.team.myapplication.R
import kotlinx.android.synthetic.main.activity_main.*


class NewsFragment : Fragment() {
companion object{
    var TAG = "NewsFragment"
}
    private lateinit var newsViewModel: NewsViewModel
    private var news_rv : RecyclerView? = null
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
        news_rv = view.findViewById(R.id.news_rv)
        requireActivity().nav_view.visibility = View.VISIBLE
        news_rv?.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        newsViewModel.loadArticles()?.observe(this.viewLifecycleOwner, Observer {
            Log.d(TAG,"articles: $it")
            news_rv?.adapter = NewsAdapter(it){
                // adapter listener
            } })
    }
}