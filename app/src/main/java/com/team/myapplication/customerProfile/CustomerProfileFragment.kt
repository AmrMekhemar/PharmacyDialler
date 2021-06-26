package com.team.myapplication.customerProfile

import com.team.myapplication.NameDataClass
import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.gson.Gson
import com.team.myapplication.*
import com.team.myapplication.customerProfile.model.CustomerProfile
import com.team.myapplication.register.RegisterFragment
import com.team.myapplication.register.model.Coordinates
import com.team.myapplication.toast
import kotlinx.android.synthetic.main.edit_alert_dialog.*
import kotlinx.android.synthetic.main.fragment_customer_profile.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import splitties.init.appCtx
import splitties.toast.toast
import java.util.*

/**
 * A simple [Fragment] subclass.
 * Use the [CustomerProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
private const val TAG = "CustomerProfileFragment"

class CustomerProfileFragment : Fragment(), OnMapReadyCallback {
    private var coordinates: Coordinates? = null
    private lateinit var map: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var v: View? = null
    private val networkStatusChecker: NetworkStatusChecker by inject()
    private val viewModel: CustomerProfileViewModel by viewModel()
    val token: String? by lazy {
        SharedPrefsManager(
            appCtx
        ).token
    }
    private val manipulatedToken = "aaabbb$token"
    private var editString = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_customer_profile, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStart() {
        super.onStart()
        setupLocationClient()
        Log.d(TAG, "token before object: $token")
        val chunks = token!!.split(".").toTypedArray()
        val decoder = Base64.getDecoder()
        val payload = String(decoder.decode(chunks[1]))
        val profile = Gson().fromJson(payload, CustomerProfile::class.java)
        Log.d(TAG, "profile object: $profile")
        initViews(profile)
        editAddress()
        editName()
        editPhone()
        editCoordinates()
    }

    private fun editAddress() {
        iv_editAddress.setOnClickListener {
            getAlertDialog("Edit Address")
                .setPositiveButton("Edit") { _, _ ->
                    appCtx.toast("Edited")
                    lifecycleScope.launch {
                        manipulatedToken.let { it1 ->
                            viewModel.editCustomerAddress(it1, AddressDataClass(editString))
                        }
                    }
                }
                .setNegativeButton("Cancel") { _, _ ->
                    appCtx.toast("canceled")
                }
                .show()
        }
    }

    private fun editPhone() {
        iv_editPhone.setOnClickListener {
            getAlertDialog("Edit Phone")
                .setPositiveButton("Edit") { _, _ ->
                    appCtx.toast("Edited")
                    Log.d(TAG, "sent token: $manipulatedToken")
                    lifecycleScope.launch(Dispatchers.Main) {
                        val body = viewModel.editCustomerPhone(
                            manipulatedToken,
                            PhoneDataClass(listOf(editString))
                        )
                        Log.d(TAG, "phone request return is: ${body.message}")
                    }
                }
                .setNegativeButton("Cancel") { _, _ ->
                    appCtx.toast("canceled")
                }
                .show()
        }
    }

    private fun editName() {
        iv_editName.setOnClickListener {
            getAlertDialog("Edit Name")
                .setPositiveButton("Edit") { _, _ ->
                    appCtx.toast("Edited")
                    lifecycleScope.launch {
                        if (manipulatedToken.length > 10)
                            viewModel.editCustomerName(
                                manipulatedToken,
                                NameDataClass(editString)
                            )
                    }
                }
                .setNegativeButton("Cancel") { _, _ ->
                    appCtx.toast("canceled")
                }
                .show()
        }
    }

    private fun editCoordinates() {
        iv_editCoordinates.setOnClickListener {
            getCurrentLocation()

        }
    }

    @SuppressLint("SetTextI18n")
    private fun initViews(profile: CustomerProfile) {
        tv_email.text = profile.email
        tv_name.text = profile.name
        tv_address.text = profile.locationAsAddress
        tv_coordinates.text = "${profile.locationAsCoordinates.coordinates.lat} , " +
                "${profile.locationAsCoordinates.coordinates.lon}"
        tv_age.text = profile.age.toString()
        tv_phone.text = profile.phone
    }

    private fun getAlertDialog(alert: String): AlertDialog.Builder {
        // 1. Instantiate an <code><a href="/reference/android/app/AlertDialog.Builder.html">AlertDialog.Builder</a></code> with its constructor
        val builder: AlertDialog.Builder = activity.let {
            AlertDialog.Builder(it)
        }
        val inflater = activity?.layoutInflater
        v = inflater?.inflate(R.layout.edit_alert_dialog, null)
        v?.findViewById<EditText>(R.id.newStringET)?.doOnTextChanged { text, start, before, count ->
            editString = text.toString()
        }
// 2. Chain together various setter methods to set the dialog characteristics
        builder.setTitle(alert)
            ?.setView(v)
// 3. Get the <code><a href="/reference/android/app/AlertDialog.html">AlertDialog</a></code> from <code><a href="/reference/android/app/AlertDialog.Builder.html#create()">create()</a></code>
        return builder
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
            RegisterFragment.REQUEST_LOCATION
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
                    toast("location has been edited")
                    if (coordinates != null)
                        lifecycleScope.launch {
                            if (manipulatedToken.length > 10)
                                viewModel.editCustomerCoordinates(manipulatedToken, coordinates!!)
                        }
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
        if (requestCode == RegisterFragment.REQUEST_LOCATION) {
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