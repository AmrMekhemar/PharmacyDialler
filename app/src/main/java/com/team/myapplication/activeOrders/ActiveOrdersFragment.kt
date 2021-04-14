package com.team.myapplication.activeOrders

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.team.myapplication.R
import com.team.myapplication.SharedPrefsManager
import com.team.myapplication.ordersHistory.OrdersAdapter
import com.team.myapplication.ordersHistory.OrdersHistoryFragmentDirections
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_active_orders.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class ActiveOrdersFragment : Fragment() {
    private val TAG = "ActiveOrdersFragment"
    private val viewModel: ActiveOrdersViewModel by viewModel()
    val token: String by lazy {
        "aaabbb" + SharedPrefsManager(requireContext()).token
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_active_orders, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().nav_view.visibility = View.VISIBLE
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch {
            val body = token.let { viewModel.getOrderHistory(it) }
            Log.d(TAG, "active orders message: ${body.message}")
            Log.d(TAG, "active orders: ${body.customerOrders}")
            withContext(Dispatchers.Main) {
                val layoutManager by inject<LinearLayoutManager>()
                active_ordersRV.layoutManager = layoutManager
                active_ordersRV.adapter = body.customerOrders?.let {
                    OrdersAdapter(it) {
                        findNavController().navigate(
                            ActiveOrdersFragmentDirections.actionNavigationCurrentToSpecificOrderFragment(
                                it
                            )
                        )
                    }
                }
                if (body.customerOrders == null){
                    no_current_placeholder.visibility = View.VISIBLE
                    no_current_orders_TV.visibility = View.VISIBLE
                }

        }
        }
    }
}
