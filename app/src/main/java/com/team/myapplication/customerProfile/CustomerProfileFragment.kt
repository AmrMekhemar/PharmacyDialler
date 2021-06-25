package com.team.myapplication.customerProfile

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.team.myapplication.NetworkStatusChecker
import com.team.myapplication.R
import com.team.myapplication.SharedPrefsManager
import com.team.myapplication.customerProfile.model.CustomerProfile
import com.team.myapplication.toast
import kotlinx.android.synthetic.main.edit_alert_dialog.*
import kotlinx.android.synthetic.main.fragment_customer_profile.*
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import splitties.init.appCtx
import java.util.*

/**
 * A simple [Fragment] subclass.
 * Use the [CustomerProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
private const val TAG = "CustomerProfileFragment"

class CustomerProfileFragment : Fragment() {
    private val networkStatusChecker: NetworkStatusChecker by inject()
    private val viewModel: CustomerProfileViewModel by viewModel()

    val token: String? by lazy {
        SharedPrefsManager(
            appCtx
        ).token
    }
    val string = ""

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
        Log.d(TAG, "token before object: $token")
        val chunks = token!!.split(".").toTypedArray()
        val decoder = Base64.getDecoder()
//        val header = String(decoder.decode(chunks[0]))
        val payload = String(decoder.decode(chunks[1]))
        val profile = Gson().fromJson(payload, CustomerProfile::class.java)
        Log.d(TAG, "profile object: $profile")
        initViews(profile)
        editAddress()
        editName()
        editPhone()
    }

    private fun editAddress() {
        iv_editAddress.setOnClickListener {
            getAlertDialog("Edit Address")
                .setPositiveButton("Edit") { _, _ ->
                    appCtx.toast("Edited")
                    lifecycleScope.launch {
                        viewModel.editCustomerAddress(newStringET.text.toString())
                    }
                }
                .setNegativeButton("Cancel") { _, _ ->
                    appCtx.toast("canceled")
                }
                .show()
        }
    }

    private fun editPhone() {
        iv_editAddress.setOnClickListener {
            getAlertDialog("Edit Phone")
                .setPositiveButton("Edit") { _, _ ->
                    appCtx.toast("Edited")
                    lifecycleScope.launch {
                        viewModel.editCustomerPhone(newStringET.text.toString())
                    }
                }
                .setNegativeButton("Cancel") { _, _ ->
                    appCtx.toast("canceled")
                }
                .show()
        }
    }

    private fun editName() {
        iv_editAddress.setOnClickListener {
            getAlertDialog("Edit Name")
                .setPositiveButton("Edit") { _, _ ->
                    appCtx.toast("Edited")
                    lifecycleScope.launch {
                        viewModel.editCustomerName(newStringET.text.toString())
                    }
                }
                .setNegativeButton("Cancel") { _, _ ->
                    appCtx.toast("canceled")
                }
                .show()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initViews(profile: CustomerProfile) {
        tv_email.text = profile.email
        tv_name.text = profile.name
        tv_address.text = profile.locationAsAddress
        password.text = "${profile.locationAsCoordinates.coordinates.lat} , " +
                "${profile.locationAsCoordinates.coordinates.lon}"
        tv_age.text = profile.age.toString()
        tv_phone.text = profile.phone
    }

    private fun getAlertDialog(alert: String): AlertDialog.Builder {
        // 1. Instantiate an <code><a href="/reference/android/app/AlertDialog.Builder.html">AlertDialog.Builder</a></code> with its constructor
        val builder: AlertDialog.Builder = activity.let {
            AlertDialog.Builder(it)
        }
// 2. Chain together various setter methods to set the dialog characteristics
        builder.setTitle(alert)
            ?.setView(R.layout.edit_alert_dialog)
// 3. Get the <code><a href="/reference/android/app/AlertDialog.html">AlertDialog</a></code> from <code><a href="/reference/android/app/AlertDialog.Builder.html#create()">create()</a></code>
        val dialog: AlertDialog? = builder.create()
        return builder
    }
}