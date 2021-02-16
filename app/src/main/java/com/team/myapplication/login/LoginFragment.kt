package com.team.myapplication.login

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.team.myapplication.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginFragment : Fragment() {

    private val TAG = "LoginFragment"
    private val networkStatusChecker: NetworkStatusChecker by inject()
    private val viewModel: LoginViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().nav_view.visibility = View.INVISIBLE
        signIn_btn.setOnClickListener {
            progressImage.visibility = View.VISIBLE
            lifecycleScope.launch {
                val loginObject = LoginObject(
                    emailET.text.toString(),
                    passwordET.text.toString()
                )
                val body = viewModel.login(loginObject)
                Log.d(TAG, "body is : ${body.message}")
                errorMsgTV.text = body.message
                delay(200)
                errorMsgTV.visibility = View.VISIBLE
                progressImage.visibility = View.INVISIBLE
                if (body.message == "success"){
                    SharedPrefsManager(requireContext()).token = body.token
                    findNavController().navigate(
                        LoginFragmentDirections.actionLoginFragmentToNavigationCurrent())
                }

            }

        }

        register_btn.setOnClickListener {
            findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            )
        }

    }

}