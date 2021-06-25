package com.team.myapplication.customerProfile

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.team.myapplication.R
import com.team.myapplication.SharedPrefsManager
import com.team.myapplication.customerProfile.model.CustomerProfile
import kotlinx.android.synthetic.main.fragment_customer_profile.*
import splitties.init.appCtx
import java.util.*

/**
 * A simple [Fragment] subclass.
 * Use the [CustomerProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
private const val TAG = "CustomerProfileFragment"
class CustomerProfileFragment : Fragment() {
    val token: String? by lazy {
        SharedPrefsManager(
            appCtx
        ).token
    }

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
        Log.d(TAG,"token before object: $token")
        val chunks = token!!.split(".").toTypedArray()
        val decoder = Base64.getDecoder()
//        val header = String(decoder.decode(chunks[0]))
        val payload = String(decoder.decode(chunks[1]))
        val profile = Gson().fromJson(payload, CustomerProfile::class.java)
        Log.d(TAG,"profile object: $profile")
        initViews(profile)
    }

    @SuppressLint("SetTextI18n")
    private fun initViews(profile : CustomerProfile) {
        tv_email.text = profile.email
        tv_name.text = profile.name
        tv_address.text = profile.locationAsAddress
        password.text = "${profile.locationAsCoordinates.coordinates.lat} , " +
                "${profile.locationAsCoordinates.coordinates.lon}"
        tv_age.text = profile.age.toString()
        tv_phone.text = profile.phone
    }
}