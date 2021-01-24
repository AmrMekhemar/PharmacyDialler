package com.team.myapplication.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.team.myapplication.R
import com.team.myapplication.news.model.news.MoreInfoItem
import kotlinx.android.synthetic.main.fragment_more.*


class MoreInfoFragment : Fragment() {
    val infoItems = listOf(
        MoreInfoItem(R.drawable.settings,"Settings"),
        MoreInfoItem(R.drawable.phone,"Contact Us"),
        MoreInfoItem(R.drawable.info,"About Us"),
        MoreInfoItem(R.drawable.logout,"Logout")
    )


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_more, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        moreInfo_rv.layoutManager = layoutManager
        val dividerItemDecoration = DividerItemDecoration(
            moreInfo_rv.context,
            layoutManager.orientation
        )
        moreInfo_rv.addItemDecoration(dividerItemDecoration)
        moreInfo_rv.adapter =
            MoreInfoAdapter(infoItems) {}
    }
}