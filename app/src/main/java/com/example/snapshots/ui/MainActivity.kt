package com.example.snapshots.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.snapshots.R
import com.example.snapshots.databinding.ActivityMainBinding
import com.example.snapshots.ui.Home.views.HomeFragment
import com.example.snapshots.ui.component.Screen
import com.example.snapshots.ui.login.views.LoginFragment
import com.example.snapshots.ui.user.views.UserRegisterFragment
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
            Screen.MainActivity -> {
                binding.bottomNav.visibility = View.INVISIBLE
            }
            Screen.LoginFragment -> {
                binding.bottomNav.visibility = View.INVISIBLE
                openLoginFragment()
            }
            Screen.UserRegisterFragment -> {
                binding.bottomNav.visibility = View.INVISIBLE
                openUserRegisterFragment()
            }
            Screen.HomeFragment ->{
                binding.bottomNav.visibility= View.INVISIBLE
                openHomeFragment()
            }
        }
    }

    private fun openLoginFragment() {
        changeFragment(LoginFragment.newInstance())
    }

    private fun openUserRegisterFragment() {
        changeFragment(UserRegisterFragment.newInstance())
    }

    private fun openHomeFragment(){
        changeFragment(HomeFragment.newInstance())
    }

}