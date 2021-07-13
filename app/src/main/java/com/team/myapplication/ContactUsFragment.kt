package com.team.myapplication

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_contact_us.*


/**
 * A simple [Fragment] subclass.
 * Use the [ContactUsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ContactUsFragment : Fragment() {

    private val REQUEST_CODE = 1000
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_us, container, false)
    }


    // trigger what happens when the user clicks on the fab
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED -> {
                requestCallPermission()
            }
        }
        phoneTV.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Toast.makeText(activity,"Call permission denied",Toast.LENGTH_SHORT)
                    .show()
            }
            else  (makeCall("201014822248"))
        }
       mailTV.setOnClickListener {
           composeEmail(arrayOf("amr.tahhan@outlook.com"),"")
       }
    }
    /**
     * a function to make a call
     * @param phoneNumber :String
     * */
    private fun makeCall(phoneNumber: String) {
        val callIntent = Intent(Intent.ACTION_CALL)
        callIntent.data = Uri.parse("tel: $phoneNumber") //change the number
        startActivity(callIntent)
    }

    private fun requestCallPermission() {
        requestPermissions(
            arrayOf("android.permission.CALL_PHONE"),
            REQUEST_CODE
        )
    }

    fun composeEmail(addresses: Array<String?>?, subject: String?) {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:") // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses)
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
            startActivity(intent)
    }
}