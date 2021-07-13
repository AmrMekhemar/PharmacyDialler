package com.team.myapplication.customerProfile

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.team.myapplication.*
import com.team.myapplication.customerProfile.model.CustomerProfile
import com.team.myapplication.register.RegisterFragment
import com.team.myapplication.register.model.Coordinates
import com.team.myapplication.toast
import com.vansuita.pickimage.bundle.PickSetup
import com.vansuita.pickimage.dialog.PickImageDialog
import com.vansuita.pickimage.enums.EPickType
import kotlinx.android.synthetic.main.edit_alert_dialog.*
import kotlinx.android.synthetic.main.fragment_customer_profile.*
import kotlinx.android.synthetic.main.fragment_order.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import splitties.init.appCtx
import splitties.toast.toast
import java.io.ByteArrayOutputStream
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
    private val pickImage = 100
    private val CAMERA_REQUEST = 1888
    private val MY_CAMERA_PERMISSION_CODE = 100
    private val photo: String? by lazy {
        SharedPrefsManager(
            appCtx
        ).profilePhotoString
    }
    val token: String? by lazy {
        SharedPrefsManager(
            appCtx
        ).token
    }
    private val manipulatedToken = "aaabbb$token"
    private var editString = ""
    private var passwordString = ""
    private var confirmPasswordString = ""

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
        editPassword()
        editCoordinates()
        editProfilePic()
    }

    @SuppressLint("SetTextI18n")
    private fun editPassword() {
        iv_editPassword.setOnClickListener {
            val builder: AlertDialog.Builder = activity.let {
                AlertDialog.Builder(it)
            }
            val inflater = activity?.layoutInflater
            v = inflater?.inflate(R.layout.edit_password_alert_dialog, null)
            v?.findViewById<EditText>(R.id.oldPasswordET)?.doOnTextChanged { text, start, before, count ->
                editString = text.toString()
                if (editString.length<8)
                v?.findViewById<TextView>(R.id.passwordErrorTV)?.text = "Wrong password"
                else v?.findViewById<TextView>(R.id.passwordErrorTV)?.text = ""

            }
            v?.findViewById<EditText>(R.id.editTextTextPassword)?.doOnTextChanged { text, start, before, count ->
                passwordString = text.toString()
                if (passwordString.length<8)
                    v?.findViewById<TextView>(R.id.passwordErrorTV)?.
                    text = "Your Password must be at least 8 characters"
                else v?.findViewById<TextView>(R.id.passwordErrorTV)?.text = ""
            }
            v?.findViewById<EditText>(R.id.editTextTextPasswordConfirm)?.doOnTextChanged { text, start, before, count ->
                confirmPasswordString = text.toString()
                when {
                    confirmPasswordString.length<8 -> v?.findViewById<TextView>(R.id.passwordErrorTV)?.text = "Your Password must be at least 8 characters"
                    passwordString != confirmPasswordString -> v?.findViewById<TextView>(R.id.passwordErrorTV)?.text = "Password and confirm password doesn't match"
                    else -> v?.findViewById<TextView>(R.id.passwordErrorTV)?.text = ""
                }
            }
            builder.setTitle("Reset your Password")
                ?.setView(v)
            builder .setPositiveButton("Reset") { _, _ ->
                    appCtx.toast("Edited")

                Snackbar.make(requireView(),"Changes won't take effect until re-login",Snackbar.LENGTH_SHORT).show()
                    Log.d(TAG, "sent token: $manipulatedToken")
                    lifecycleScope.launch(Dispatchers.Main) {
                        if (v?.findViewById<TextView>(R.id.passwordErrorTV)?.text ==""){

                         //   val body =
                                viewModel.editCustomerPassword(
                                manipulatedToken,
                                EditPasswordRequest(editString,passwordString,confirmPasswordString)
                            )
                        //    Log.d(TAG, "phone request return is: ${body.message}")
                            Snackbar.make(requireView(),"Changes won't take effect until re-login",Snackbar.LENGTH_SHORT).show()
                        }

                    }
                }
                .setNegativeButton("Cancel") { _, _ ->
                    appCtx.toast("canceled")
                }
                .show()
        }
    }

    @SuppressLint("WrongConstant")
    private fun editProfilePic() {
        iv_editProfilePic.setOnClickListener {
            val setup = PickSetup()
                .setTitle("Pick an image from")
                .setTitleColor(Color.BLACK)
                .setBackgroundColor(Color.WHITE)
                .setCancelText("Cancel")
                .setCancelTextColor(Color.BLACK)
                .setButtonTextColor(Color.BLACK)
                .setDimAmount(16f)
                .setFlip(true).setMaxSize(60)
                .setPickTypes(EPickType.GALLERY, EPickType.CAMERA)
                .setCameraButtonText("Camera")
                .setGalleryButtonText("Gallery")
                .setIconGravity(Gravity.START)
                .setButtonOrientation(LinearLayout.HORIZONTAL)
                .setSystemDialog(false)
                .setGalleryIcon(R.drawable.gallery)
                .setCameraIcon(R.drawable.camera)
                .setGalleryChooserTitle("Gallery")
                .setCameraChooserTitle("Camera")
            PickImageDialog.build(setup)
                .setOnPickResult {
                    iv_image.setImageBitmap(it.bitmap)
                    Snackbar.make(requireView(),"Changes won't take effect until re-login",Snackbar.LENGTH_SHORT).show()
                    lifecycleScope.launch(Dispatchers.IO) {
                        getBase64String(it.bitmap)?.let { imageString -> PhotoDataClass(imageString) }
                            ?.let { photoDataClassObject ->
                                viewModel.editCustomerPhoto(
                                    manipulatedToken,
                                    photoDataClassObject
                                )
                            }
                    }
                }.show(activity)
        }

    }

    private fun editAddress() {
        iv_editAddress.setOnClickListener {
            getAlertDialog("Edit Address")
                .setPositiveButton("Edit") { _, _ ->
                    appCtx.toast("Edited")
                    Snackbar.make(requireView(),"Changes won't take effect until re-login",Snackbar.LENGTH_SHORT).show()
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
                    Snackbar.make(requireView(),"Changes won't take effect until re-login",Snackbar.LENGTH_SHORT).show()
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
                    Snackbar.make(requireView(),"Changes won't take effect until re-login",Snackbar.LENGTH_SHORT).show()
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

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    private fun initViews(profile: CustomerProfile) {
        tv_email.text = profile.email
        tv_name.text = profile.name
        tv_address.text = profile.locationAsAddress
        tv_coordinates.text = "${profile.locationAsCoordinates.coordinates.lat} , " +
                "${profile.locationAsCoordinates.coordinates.lon}"
        tv_age.text = profile.age.toString()
        tv_phone.text = profile.phone
        if (!photo.isNullOrBlank()) {
           val bit =  convertToBitmap(photo!!)
            bit.let {
                iv_image.setImageBitmap(it)
            }

        }
    }

    private fun getAlertDialog(alert: String): AlertDialog.Builder {
        val builder: AlertDialog.Builder = activity.let {
            AlertDialog.Builder(it)
        }
        val inflater = activity?.layoutInflater
        v = inflater?.inflate(R.layout.edit_alert_dialog, null)
        v?.findViewById<EditText>(R.id.stringET)?.doOnTextChanged { text, start, before, count ->
            editString = text.toString()
        }
        builder.setTitle(alert)
            ?.setView(v)
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
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            requestLocationPermissions()
            getCurrentLocation()

        } else {
            fusedLocationClient.lastLocation.addOnCompleteListener {
                val location = it.result
                if (location != null) {
                    coordinates = Coordinates(location.latitude, location.longitude)
                    toast("location has been edited")
                    Snackbar.make(requireView(),"Changes won't take effect until re-login",Snackbar.LENGTH_SHORT).show()
                    if (coordinates != null)
                        lifecycleScope.launch {
                            if (manipulatedToken.length > 10)
                                viewModel.editCustomerCoordinates(manipulatedToken, coordinates!!)
                        }
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


    @RequiresApi(Build.VERSION_CODES.O)
    private fun convertToBitmap(image: String): Bitmap? {
        val decodedString: ByteArray = Base64.getMimeDecoder().decode(image)
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
    }

    private fun getBase64String(bitmap: Bitmap): String? {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
        return android.util.Base64.encodeToString(byteArray, android.util.Base64.DEFAULT)
    }
}