package com.example.snapshots.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.snapshots.R
import com.example.snapshots.databinding.ActivityMainBinding
import com.example.snapshots.ui.Home.views.HomeFragment
import com.example.snapshots.ui.component.Screen
import com.example.snapshots.ui.login.views.LoginFragment
import com.example.snapshots.ui.profile.views.ProfileFragment
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(){

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        changeScreen(Screen.LoginFragment)
    }

    private fun changeFragment(fragment: Fragment) {
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.hostFragment, fragment)
        ft.commit()
    }

    fun changeScreen(typeScreen: Screen) {
        when (typeScreen) {
            Screen.LoginFragment -> {
                binding.bottomNav.visibility = View.INVISIBLE
                openLoginFragment()
            }
        }
    }

    private fun openLoginFragment() {
        changeFragment(LoginFragment.newInstance())
    }



}