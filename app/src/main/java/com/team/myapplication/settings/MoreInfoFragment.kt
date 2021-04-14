package com.team.myapplication.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.team.myapplication.R
import com.team.myapplication.SharedPrefsManager
import com.team.myapplication.news.model.news.MoreInfoItem
import com.team.myapplication.toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_more.*
import splitties.init.appCtx


class MoreInfoFragment : Fragment() {
    private val infoItems = listOf(
        MoreInfoItem(R.drawable.settings, "Settings"),
        MoreInfoItem(R.drawable.phone, "Contact Us"),
        MoreInfoItem(R.drawable.info, "About Us"),
        MoreInfoItem(R.drawable.logout, "Logout")
    )


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_more, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().nav_view.visibility = View.VISIBLE
        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        moreInfo_rv.layoutManager = layoutManager
        val dividerItemDecoration = DividerItemDecoration(
            moreInfo_rv.context,
            layoutManager.orientation
        )
        moreInfo_rv.addItemDecoration(dividerItemDecoration)
        moreInfo_rv.adapter =
            MoreInfoAdapter(infoItems) {
                if (it.text == "Logout") {
                    appCtx.toast("Logged out")
                    requireActivity().nav_view.visibility = View.INVISIBLE
                    SharedPrefsManager(requireContext()).token = ""
                    findNavController().navigate(MoreInfoFragmentDirections.actionNavigationMoreToLoginFragment())
                }  else if (it.text == "About Us") {
                    findNavController().navigate(MoreInfoFragmentDirections.actionNavigationMoreToAboutUsFragment())
                }

            }
    }
}