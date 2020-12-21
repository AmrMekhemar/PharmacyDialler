package com.team.myapplication.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.fragment.findNavController
import com.team.myapplication.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_pharmacy_login.*


class PharmacyLoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pharmacy_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signIn_btn.setOnClickListener {
            val navOptions = NavOptions.Builder().setPopUpTo(R.id.userLoginFragment, true).build();
            findNavController().navigate(
                PharmacyLoginFragmentDirections.actionPharmacyLoginFragmentToMobileNavigation(),navOptions
            )
            nav_view.visibility = View.VISIBLE
        }
    }
}