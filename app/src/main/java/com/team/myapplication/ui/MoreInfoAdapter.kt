package com.team.myapplication.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.team.myapplication.R
import com.team.myapplication.model.news.MoreInfoItem


class MoreInfoAdapter(val moreInfoItems : List<MoreInfoItem>, val listener: (Int) -> Unit) :
    RecyclerView.Adapter<MoreInfoAdapter.ViewHolder?>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemIcon: ImageView = itemView.findViewById(R.id.item_icon)
        var itemText: TextView = itemView.findViewById(R.id.item_tv)
        var viewHolderView: View

        init {
            viewHolderView = itemView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.more_info_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: MoreInfoItem = moreInfoItems[position]
        holder.itemText.text = item.text

        holder.itemIcon.setImageResource(item.icon)

        holder.viewHolderView.setOnClickListener {

        }

    }


    override fun getItemCount(): Int {
        return moreInfoItems.size
    }


}