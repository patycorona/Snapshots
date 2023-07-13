package com.example.snapshots.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.snapshots.R
import com.example.snapshots.databinding.ActivityMainBinding
import com.example.snapshots.ui.Home.views.HomeFragment
import com.example.snapshots.ui.add.views.AddFragment
import com.example.snapshots.ui.component.Screen
import com.example.snapshots.ui.login.views.LoginFragment
import com.example.snapshots.ui.profile.views.ProfileFragment
import com.example.snapshots.ui.user.views.UserRegisterFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(){

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListener()
        changeScreen(Screen.LoginFragment)
    }

    private fun changeFragment(fragment: Fragment) {
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.hostFragment, fragment)
        ft.commit()
    }

    private fun initListener(){
        binding.apply {
            bottomNav.setOnNavigationItemSelectedListener {
                when(it.itemId){
                    R.id.action_home -> {changeScreen(Screen.HomeFragment)}
                    R.id.action_add -> {changeScreen(Screen.AddFragment)}
                    R.id.action_profile -> {changeScreen(Screen.ProfileFragment)}
                }
                true
            }
        }
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
                binding.bottomNav.visibility= View.VISIBLE
                openHomeFragment()
            }
            Screen.ProfileFragment ->{
                binding.bottomNav.visibility = View.VISIBLE
                openProfileFragment()
            }
            Screen.AddFragment -> {
                binding.bottomNav.visibility = View.VISIBLE
                openAddFragment()
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

    private fun openProfileFragment(){
        changeFragment(ProfileFragment.newInstance())
    }

    private fun openAddFragment(){
        changeFragment(AddFragment.newInstance())
    }

}