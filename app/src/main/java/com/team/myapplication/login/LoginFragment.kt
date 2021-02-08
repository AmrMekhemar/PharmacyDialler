package com.team.myapplication.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.team.myapplication.LoginObject
import com.team.myapplication.NetworkStatusChecker
import com.team.myapplication.R
import com.team.myapplication.toast
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.Dispatchers
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
       var loginStatus = Any()
        viewModel.getLoginStatus().observe(viewLifecycleOwner, Observer {
            loginStatus = it
            when (it) {
                is LoginStatus.Loading -> requireContext().toast("Loading")
                is LoginStatus.Success -> requireContext().toast("Success")
                is LoginStatus.Error -> requireContext().toast("Error")
            }
        })

        signIn_btn.setOnClickListener {

            lifecycleScope.launch {
                val loginObject = LoginObject(
                    emailET.text.toString(),
                    passwordET.text.toString()
                )
                val body = viewModel.login(loginObject)
                Log.d(TAG, "body is : $body")
                errorMsgTV.text = body.message
                errorMsgTV.visibility = View.VISIBLE
                if (loginStatus is LoginStatus.Success  )
                    LoginFragmentDirections.actionLoginFragmentToMobileNavigation()
            }

        }

        register_btn.setOnClickListener {
            findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            )
        }
//        signIn_btn.setOnClickListener {
//            findNavController().navigate(
//
//            )
//

//
//        }
    }
}