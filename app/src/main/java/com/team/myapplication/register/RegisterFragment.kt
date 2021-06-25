package com.team.myapplication.register

import android.Manifest
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
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
import splitties.toast.toast
import java.util.*


class RegisterFragment : Fragment(), OnMapReadyCallback {
    private val TAG = "RegisterFragment"
    private val networkStatusChecker: NetworkStatusChecker by inject()
    private val viewModel: RegisterViewModel by viewModel()
    private lateinit var args: RegisterFragmentArgs
    private var coordinates: Coordinates? = null
    private lateinit var picker: DatePickerDialog
    companion object {
        private const val REQUEST_LOCATION = 1
        private const val TAG = "MapsFragment"
    }
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var map: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().nav_view.visibility = View.GONE
        setupLocationClient()
//        arguments?.let {
//            args = RegisterFragmentArgs.fromBundle(it)
//            val lat = args.lat
//            val lon = args.lon
//            coordinates = Coordinates(lat.toDouble(), lon.toDouble())
//            Log.d(TAG, lat)
//        }

        birthDateET.setOnClickListener {
            val cldr: Calendar = Calendar.getInstance()
            val day: Int = cldr.get(Calendar.DAY_OF_MONTH)
            val month: Int = cldr.get(Calendar.MONTH)
            val year: Int = cldr.get(Calendar.YEAR)
            // date picker dialog
            // date picker dialog
            picker = DatePickerDialog(
                requireContext(),
                OnDateSetListener { _, mYear, monthOfYear, dayOfMonth ->
                    birthDateET.text = "$mYear-$monthOfYear-$dayOfMonth"   },
                year,
                month,
                day
            )
            picker.show()
        }

        haveAccount_btn.setOnClickListener {
            findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
        }
        pickLocationBTN.setOnClickListener {
            getCurrentLocation()
           // findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToMapsFragment())
        }
        register_btn.setOnClickListener {
            lifecycleScope.launch {
                if (coordinates == null) {
                    requireContext().toast("Pick up your coordinates and fill all of the fields")
                } else {
                    val registerObject = RegisterObject(
                        nameET.text.toString(),
                        emailET.text.toString(),
                        passwordET.text.toString(),
                        confirmPasswordET.text.toString(),
                        mutableListOf(phoneNumberET.text.toString()),
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
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        getCurrentLocation()
    }

    private fun setupLocationClient() {
        fusedLocationClient =
            LocationServices.getFusedLocationProviderClient(activity)
    }

    private fun requestLocationPermissions() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            REQUEST_LOCATION
        )

    }

    private fun getCurrentLocation() {
        // 1
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            // 2
            requestLocationPermissions()
            getCurrentLocation()

        } else {
            // 3
            fusedLocationClient.lastLocation.addOnCompleteListener {
                val location = it.result
                if (location != null) {
                    // 4
//                    val latLng = LatLng(location.latitude, location.longitude)
                    coordinates = Coordinates(location.latitude, location.longitude)
                    toast("location has been picked")
                    // 5
//                    map.addMarker(
//                        MarkerOptions().position(latLng)
//                            .title("You are here!")
//                    )
                    // 6
//                    val update = CameraUpdateFactory.newLatLngZoom(latLng, 16.0f)
                    // 7
            //        map.moveCamera(update)
                } else {
                    requireContext().toast("Can't get your location,check that gps is enabled")
                    Log.e(TAG, "No location found")
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_LOCATION) {
            if (grantResults.size == 1 && grantResults[0] ==
                PackageManager.PERMISSION_GRANTED
            ) {
                getCurrentLocation()
            } else {
                Log.e(TAG, "Location permission denied")
            }
        }
    }
}