package com.team.myapplication

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.team.myapplication.login.LoginFragmentDirections
import com.team.myapplication.login.LoginStatus
import com.team.myapplication.login.LoginViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: LoginViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        actionBar?.setHomeButtonEnabled(false)

        if (savedInstanceState== null){
            val prefsManager = SharedPrefsManager(this)
            if (!prefsManager.token.isNullOrBlank()) {
                nav_view.visibility = View.VISIBLE
                findNavController(R.id.nav_host_fragment).
                navigate(LoginFragmentDirections.actionLoginFragmentToNavigationCurrent())
            }
        }

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        /**
         * following comment for setting up the nav controller with the action bar
         */
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_history,
//                R.id.navigation_news,
//                R.id.navigation_more,
//                R.id.navigation_current,
//                R.id.navigation_pharmacy
//            )
//        )
////        setupActionBarWithNavController(navController, appBarConfiguration)
        nav_view.setupWithNavController(navController)
        viewModel.getLoginStatus().observe(this, Observer {
            when (it) {
                is LoginStatus.Loading -> this.toast("Loading")
                is LoginStatus.Success -> {
                    this.toast("Logged in Successfully")
                }
                is LoginStatus.Error -> this.toast("Error")
            }
        })
    }


}