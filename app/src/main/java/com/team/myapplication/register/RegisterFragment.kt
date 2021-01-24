package com.team.myapplication.register

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.team.myapplication.NetworkStatusChecker
import com.team.myapplication.R
import com.team.myapplication.register.model.Coordinates
import com.team.myapplication.register.model.LocationAsCoordinates
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class RegisterFragment : Fragment() {
    private val TAG = "RegisterFragment"
    private val networkStatusChecker: NetworkStatusChecker by inject()
    private val viewModel : RegisterViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        register_btn.setOnClickListener {
            lifecycleScope.launch {
               val body =  viewModel.register("","","","",""
                ,"",LocationAsCoordinates(coordinates = Coordinates(1,1)))
                Log.d(TAG,"body= $body")
            }
        }
    }

}