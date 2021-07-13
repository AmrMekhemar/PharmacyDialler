package com.team.myapplication.specificOrder

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.team.myapplication.R
import com.team.myapplication.SharedPrefsManager
import com.team.myapplication.SpecificOrderResponse
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_specific_order.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel
import splitties.init.appCtx
import splitties.toast.toast


class SpecificOrderFragment : Fragment() {
    private val TAG = "SpecificOrderFragment"
    val token: String? by lazy {
        "aaabbb" + SharedPrefsManager(
            appCtx
        ).token
    }
    lateinit var  decodedByte : Bitmap
    private val viewModel: SpecificOrderViewModel by viewModel()
    private lateinit var args: SpecificOrderFragmentArgs
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_specific_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().nav_view.visibility = View.GONE
        arguments?.let {
            args = SpecificOrderFragmentArgs.fromBundle(it)
        }

        prescriptionDetails_imageView.setOnClickListener {
//            val intent = Intent()
//            intent.action = Intent.ACTION_VIEW
//            intent.data = decodedByte as Uri
//            startActivity(intent)
        }
        cancel_icon.setOnClickListener {
            lifecycleScope.launch {
               val body= token?.let { it1 -> viewModel.cancelOrder(it1,
                   CancelRequest(args.orderId)

               ) }
                findNavController().navigate(SpecificOrderFragmentDirections
                    .actionSpecificOrderFragmentToNavigationPharmacy())
                if (body?.message != null) {
                    Log.d(TAG, body.message)
                    toast(body.message)
                }
            }
        }
        lifecycleScope.launch {
            val body = token?.let { viewModel.getSpecificOrder(it, args.orderId) }
            Log.d(TAG, "specific order is: $body")
            Log.d(TAG, "token $token")
            withContext(Dispatchers.Main) {
                if (body != null) {
                    populateUI(body)
                }
            }
        }
    }

    private fun populateUI(body: SpecificOrderResponse) {
        if (body.orderData.orderByTexting != null){
            PrescriptionDetails_tv.text = body.orderData.orderByTexting
        }
        else PrescriptionDetails_tv.visibility = View.GONE
        if (body.orderData.orderByPhoto != null) {
            val decodedString: ByteArray =
                Base64.decode(body.orderData.orderByPhoto, Base64.DEFAULT or Base64.NO_WRAP)
            decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
            prescriptionDetails_imageView.setImageBitmap(decodedByte)
            prescriptionDetails_imageView.visibility = View.VISIBLE

        } else prescriptionDetails_imageView.visibility = View.GONE

        pharmacy_name.text = "Pharmacy name: "+ body.pharmacyData.name
        pharmacy_address.text =  "Pharmacy Address: "+ body.pharmacyData.locationAsAddress
        order_date_tv.text = "Order Date: "+ body.orderData.date.substring(0,10)
        order_time_tv.text = "Order Time: "+body.orderData.date.substring(11,19)
        if (body.orderData.globalStatus == "accepted" ||
            body.orderData.globalStatus =="notAccepted")
            cancel_icon.visibility = View.VISIBLE
    }
}
