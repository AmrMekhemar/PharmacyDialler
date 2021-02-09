package com.team.myapplication.order

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.team.myapplication.NearestPharmacyRequest
import com.team.myapplication.R
import com.team.myapplication.SharedPrefsManager
import com.team.myapplication.register.model.RegisterReturnBody
import kotlinx.android.synthetic.main.fragment_order.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import splitties.init.appCtx
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val TAG = "OrderFragment"
class OrderFragment : Fragment() {
    val token: String? by lazy {
        SharedPrefsManager(
            appCtx
        ).token
    }
    val viewModel: OrderViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val token = token
        if (token != null) {
            Log.d(TAG,token)
        } else {
            Log.d(TAG,"token:null")
        }
        orderBtn.setOnClickListener {
            val nearestPharmacyRequest=NearestPharmacyRequest(
                orderByTexting = "gfgtgfgsg",
                orderByPhoto = null,token =token!!
            )

            lifecycleScope.launch(Dispatchers.IO) {

                val body = viewModel.requestAnOrder(
                    nearestPharmacyRequest
                )

                Log.d(TAG,body.message)
                when(body.message){
                    "order saved"-> {makeButtonsNonClickable(body)}
                    else ->{
                        responseTV.text = body.message
                    }
                }
            }
        }
    }

    private fun makeButtonsNonClickable(body:RegisterReturnBody) {
        orderBtn.isClickable = false
        pickFromCamBtn.isClickable = false
        pickFromGalleryBtn.isClickable = false
        orderBtn.setBackgroundColor(Color.GRAY)
        pickFromCamBtn.setBackgroundColor(Color.GRAY)
        pickFromGalleryBtn.setBackgroundColor(Color.GRAY)
        responseTV.text = body.message
    }

}