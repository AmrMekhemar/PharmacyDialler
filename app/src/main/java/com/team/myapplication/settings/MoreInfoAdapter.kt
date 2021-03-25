package com.team.myapplication.settings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.team.myapplication.R
import com.team.myapplication.news.model.news.MoreInfoItem


class MoreInfoAdapter(private val moreInfoItems: List<MoreInfoItem>, val listener: (MoreInfoItem) -> Unit) :
    RecyclerView.Adapter<MoreInfoAdapter.ViewHolder?>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemIcon: ImageView = itemView.findViewById(R.id.item_icon)
        var itemText: TextView = itemView.findViewById(R.id.item_tv)
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
        holder.itemView.setOnClickListener {
            listener(item)
        }
    }


    override fun getItemCount(): Int {
        return moreInfoItems.size
    }


}