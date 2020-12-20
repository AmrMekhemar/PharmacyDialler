package com.team.myapplication.ui.news


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.team.myapplication.R
import com.team.myapplication.model.news.Article


class NewsAdapter(val articles : List<Article>, val listener: (Int) -> Unit) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder?>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemPoster: ImageView = itemView.findViewById(R.id.news_item_imageView)
        var itemTitle: TextView = itemView.findViewById(R.id.news_item_textView)
        var viewHolderView: View

        init {
            viewHolderView = itemView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val articleItem: Article = articles[position]
        holder.itemTitle.text = articleItem.title

        val imagePath: String = articleItem.urlToImage
        Picasso.get()
            .load(imagePath)
            .placeholder(R.drawable.medicine)
            .into(holder.itemPoster)

        holder.viewHolderView.setOnClickListener {

        }

    }


    override fun getItemCount(): Int {
        return articles.size
    }


}