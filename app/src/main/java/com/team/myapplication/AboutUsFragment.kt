package com.team.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_about_us.*


class AboutUsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about_us, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().nav_view.visibility = View.GONE
        denoteTV.setOnClickListener {
            findNavController().navigate(AboutUsFragmentDirections.actionAboutUsFragmentToContactUsFragment())
        }
        getHelpTV.setOnClickListener {
            findNavController().navigate(AboutUsFragmentDirections.actionAboutUsFragmentToContactUsFragment())
        }
    }


}