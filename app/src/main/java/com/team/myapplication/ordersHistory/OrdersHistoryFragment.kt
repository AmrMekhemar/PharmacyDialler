package com.team.myapplication.ordersHistory

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.team.myapplication.R
import com.team.myapplication.SharedPrefsManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject
import splitties.init.appCtx


class OrdersHistoryFragment : Fragment() {
    val TAG = "OrdersHistoryFragment"
    val orderHistoryViewModel by lazy {
        ViewModelProviders.of(this).get(OrderHistoryViewModel::class.java)
    }
    val token: String? by lazy {
        "aaabbb" + SharedPrefsManager(
            appCtx
        ).token
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().nav_view.visibility = View.VISIBLE
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch {
          val body =   token?.let { orderHistoryViewModel.getOrderHistory(it) }
            if (body != null) {
                Log.d(TAG,"order history body is: ${body.message}")
                Log.d(TAG,"order history orders: ${body.customerOrders}")
            }
            withContext(Dispatchers.Main){
                if (body!= null) {
                    val layoutManager by inject<LinearLayoutManager>()
                    pharmacyOrders_rv.layoutManager = layoutManager
                    pharmacyOrders_rv.adapter = body.customerOrders?.let { OrdersAdapter(it){
                        findNavController().navigate(OrdersHistoryFragmentDirections.actionNavigationHistoryToSpecificOrderFragment(it))
                    } }
                }
            }
        }
    }


}