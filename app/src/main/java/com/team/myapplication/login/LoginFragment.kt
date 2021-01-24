package com.team.myapplication.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.team.myapplication.NetworkStatusChecker
import com.team.myapplication.R
import com.team.myapplication.toast
import kotlinx.android.synthetic.main.activity_main.nav_view
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.launch
import okhttp3.internal.Util
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.EnumSet.of


class LoginFragment : Fragment() {

private val TAG = "LoginFragment"
    private val networkStatusChecker: NetworkStatusChecker by inject()
    private val viewModel : LoginViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLoginStatus().observe(viewLifecycleOwner, Observer {
            when(it) {
                is LoginStatus.Loading -> requireContext().toast("Loading")
                is LoginStatus.Success -> requireContext().toast("Success")
                is LoginStatus.Error -> requireContext().toast("Error")
            }
        })

        signIn_btn.setOnClickListener {
              lifecycleScope.launch{
            val body = viewModel.login(emailET.text.toString(),
                    passwordET.text.toString())
                Log.d(TAG,"body is : $body")

            }

        }

        register_btn.setOnClickListener {
findNavController().navigate(
    LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
)
        }
//        signIn_btn.setOnClickListener {
//            findNavController().navigate(
//                LoginFragmentDirections.actionLoginFragmentToMobileNavigation()
//            )
//
//
//        }
    }
}