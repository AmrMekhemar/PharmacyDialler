package com.team.myapplication.register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.team.myapplication.NetworkStatusChecker
import com.team.myapplication.R
import com.team.myapplication.register.model.Coordinates
import com.team.myapplication.register.model.LocationAsCoordinates
import com.team.myapplication.register.model.RegisterObject
import com.team.myapplication.toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class RegisterFragment : Fragment() {
    private val TAG = "RegisterFragment"
    private val networkStatusChecker: NetworkStatusChecker by inject()
    private val viewModel: RegisterViewModel by viewModel()
    private lateinit var args: RegisterFragmentArgs
    private var coordinates: Coordinates? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().nav_view.visibility = View.INVISIBLE
        if (nav_view != null) {
            nav_view.visibility = View.INVISIBLE
        }
        arguments?.let {
            args = RegisterFragmentArgs.fromBundle(it)
            val lat = args.lat
            val lon = args.lon
            coordinates = Coordinates(lat.toDouble(), lon.toDouble())
            Log.d(TAG, lat)
        }

        haveAccount_btn.setOnClickListener {
            findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
        }
        pickLocationBTN.setOnClickListener {
            findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToMapsFragment())
        }
        register_btn.setOnClickListener {
            lifecycleScope.launch {
                if (coordinates != null && coordinates!!.lat == 0.0) {
                    requireContext().toast("Pick up your coordinates and fill all of the fields")
                } else {
                    val registerObject = RegisterObject(
                        nameET.text.toString(),
                        emailET.text.toString(),
                        passwordET.text.toString(),
                        confirmPasswordET.text.toString(),
                        phoneNumberET.text.toString(),
                        addressET.text.toString(),
                        LocationAsCoordinates(coordinates = coordinates!!),
                        birthDateET.text.toString(),
                        genderET.text.toString()
                    )
                    val body = viewModel.register(registerObject)
                    Log.d(TAG, "body= $body")
                    when (body.message) {
                        "success" -> {
                            findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
                        }
                        else -> {
                            errorTV.text = body.message
                            errorTV.visibility = View.VISIBLE
                            logoIV.visibility = View.INVISIBLE
                        }
                    }
                }

            }
        }

    }
}