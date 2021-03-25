package com.team.myapplication.specificOrder

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.team.myapplication.R
import com.team.myapplication.SharedPrefsManager
import com.team.myapplication.register.RegisterFragmentArgs
import com.team.myapplication.register.model.Coordinates
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import splitties.init.appCtx

class SpecificOrderFragment : Fragment() {
    private val TAG = "SpecificOrderFragment"
    val token: String? by lazy {
       "aaabbb"+ SharedPrefsManager(
            appCtx
        ).token
    }
    private val viewModel : SpecificOrderViewModel by viewModel()
    private lateinit var args : SpecificOrderFragmentArgs
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_specific_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().nav_view.visibility = View.INVISIBLE
        arguments?.let {
            args = SpecificOrderFragmentArgs.fromBundle(it)
        }

        lifecycleScope.launch {
          val body = token?.let { viewModel.getSpecificOrder(it,args.orderId) }
            Log.d(TAG, "specific order is: $body")
        }
    }
}
