package com.team.myapplication.order

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.PermissionChecker.checkSelfPermission
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.team.myapplication.NearestPharmacyRequest
import com.team.myapplication.R
import com.team.myapplication.SharedPrefsManager
import com.team.myapplication.register.model.RegisterReturnBody
import com.team.myapplication.toast
import kotlinx.android.synthetic.main.fragment_order.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel
import splitties.init.appCtx
import java.io.ByteArrayOutputStream


private const val TAG = "OrderFragment"

class OrderFragment : Fragment() {
    private val pickImage = 100
    private val CAMERA_REQUEST = 1888
    private val MY_CAMERA_PERMISSION_CODE = 100
    private var photo: String? = null
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

    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val token = "aaabbb$token"
        Log.d(TAG, token)
        orderBtn.setOnClickListener {
            val nearestPharmacyRequest = NearestPharmacyRequest(
                orderByTexting = orderET.text.toString(),
                orderByPhoto = photo
            )

            lifecycleScope.launch(Dispatchers.IO) {

                val body = viewModel.requestAnOrder(
                    token,
                    nearestPharmacyRequest
                )

                Log.d(TAG, body.message)
                withContext(Dispatchers.Main) {
                    when (body.message) {
                        "order saved" -> {
                            makeButtonsNonClickable(body)
                        }
                        else -> {

                            responseTV.text = body.message
                            responseTV.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
        pickAnImageFromGallery()
        pickFromCamBtn.setOnClickListener {
            if (checkSelfPermission(
                    appCtx,
                    Manifest.permission.CAMERA
                ) !== PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(
                    arrayOf(Manifest.permission.CAMERA),
                    MY_CAMERA_PERMISSION_CODE
                )
            } else {
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, CAMERA_REQUEST)
            }
        }
    }

    private fun pickAnImageFromGallery() {
        pickFromGalleryBtn.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }
    }

    private fun makeButtonsNonClickable(body: RegisterReturnBody) {
        orderBtn.isClickable = false
        pickFromCamBtn.isClickable = false
        pickFromGalleryBtn.isClickable = false
        orderBtn.setBackgroundColor(Color.GRAY)
        pickFromCamBtn.setBackgroundColor(Color.GRAY)
        pickFromGalleryBtn.setBackgroundColor(Color.GRAY)
        responseTV.text = body.message
        responseTV.visibility = View.VISIBLE
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == pickImage) {
            val selectedPhotoUri = data?.extras!!["data"] as Bitmap
            photo = getBase64String(selectedPhotoUri)
        }
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            val selectedPhotoUri = data?.extras!!["data"] as Bitmap
            photo = getBase64String(selectedPhotoUri)
        }
    }

//    private fun convertToBitmap(selectedPhotoUri: Uri): Bitmap? {
//        return if (Build.VERSION.SDK_INT < 28) {
//            MediaStore.Images.Media.getBitmap(
//                requireActivity().contentResolver,
//                selectedPhotoUri
//            )
//        } else {
//            val source =
//                ImageDecoder.createSource(
//                    requireActivity().contentResolver,
//                    selectedPhotoUri
//                )
//            ImageDecoder.decodeBitmap(source)
//        }
//    }

    private fun getBase64String(bitmap: Bitmap): String? {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                appCtx.toast("camera permission granted")
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, CAMERA_REQUEST)
            } else {
                appCtx.toast("camera permission denied")
            }
        }
    }


}