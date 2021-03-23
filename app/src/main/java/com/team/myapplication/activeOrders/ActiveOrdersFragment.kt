package com.team.myapplication.activeOrders

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.team.myapplication.R
import com.team.myapplication.SharedPrefsManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import splitties.init.appCtx


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
            val body = viewModel.getOrderHistory(token)
            Log.e(TAG,"active orders: $body")
        }

    }


}