package com.team.myapplication.ordersHistory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.team.myapplication.Order
import com.team.myapplication.R


class OrdersAdapter(private val orders: List<Order>, val listener: (String) -> Unit) :
    RecyclerView.Adapter<OrdersAdapter.ViewHolder?>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var dateTV: TextView = itemView.findViewById(R.id.date_tv)
        var timeTV: TextView = itemView.findViewById(R.id.time_tv)
        var medicineTV: TextView = itemView.findViewById(R.id.medicine_tv)
        var statusTV: TextView = itemView.findViewById(R.id.order_status_tv)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.orders_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Order = orders[position]
        val date = item.date.substring(0,10)
        val time = item.date.substring(11,19)
        holder.dateTV.text =  date
        holder.timeTV.text =  time
        holder.statusTV.text =  item.globalStatus
        holder.medicineTV.text =  item.orderByTexting ?: "Order details is hidden"
        holder.itemView.setOnClickListener {
            listener(item._id)
        }
    }


    override fun getItemCount(): Int {
        return orders.size
    }


}