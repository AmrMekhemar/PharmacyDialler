package com.team.myapplication

import android.Manifest
import android.content.pm.PackageManager
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.team.myapplication.register.model.Coordinates
import kotlinx.android.synthetic.main.fragment_maps.*

class MapsFragment : Fragment(), OnMapReadyCallback {
    companion object {
        private const val REQUEST_LOCATION = 1
        private const val TAG = "MapsFragment"
    }

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var map: GoogleMap
    private var coordinates: Coordinates? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
        setupLocationClient()
        PickCoordinatesBTN.setOnClickListener {
            findNavController().navigate(MapsFragmentDirections.actionMapsFragmentToRegisterFragment(
                coordinates?.lat.toString()
            , coordinates?.lon.toString()
            ))
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
        } else {
            // 3
            fusedLocationClient.lastLocation.addOnCompleteListener {
                val location = it.result
                if (location != null) {
                    // 4
                    val latLng = LatLng(location.latitude, location.longitude)
                     coordinates = Coordinates(location.latitude, location.longitude)
                    // 5
                    map.addMarker(
                        MarkerOptions().position(latLng)
                            .title("You are here!")
                    )
                    // 6
                    val update = CameraUpdateFactory.newLatLngZoom(latLng, 16.0f)
                    // 7
                    map.moveCamera(update)
                } else {
                    // 8
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