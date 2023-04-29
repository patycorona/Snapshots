package com.example.snapshots.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.snapshots.R
import com.example.snapshots.databinding.ActivityMainBinding
import com.example.snapshots.domain.model.ConstantGeneral
import com.example.snapshots.ui.Home.views.HomeFragment
import com.example.snapshots.ui.profile.views.ProfileFragment
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity(){

    lateinit var binding: ActivityMainBinding
    //private var firebaseAuth: FirebaseAuth? = null

    lateinit var activeFragment: Fragment
    private lateinit var authListener: FirebaseAuth.AuthStateListener
    private var fragmentManager: FragmentManager? = null

    private  val authResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == RESULT_OK) Toast.makeText(this, getString(R.string.txt_welcome) , Toast.LENGTH_SHORT).show()
        else if(IdpResponse.fromResultIntent(it.data) == null) finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpAuth()
    }

    private fun setUpAuth(){
        //firebaseAuth = FirebaseAuth.getInstance()
        authListener = FirebaseAuth.AuthStateListener { it ->

            if(it.currentUser == null){
                authResult.launch(
                    AuthUI.getInstance().createSignInIntentBuilder()
                        .setIsSmartLockEnabled(false)//true si quieres que mueste las cuentas que tienes registradas en ese dispositivo
                        .setAvailableProviders(
                            listOf(
                                AuthUI.IdpConfig.EmailBuilder().build(),
                                AuthUI.IdpConfig.GoogleBuilder().build())
                        )
                        .build()
                )
            } else{
                ConstantGeneral.currentUser = it.currentUser!!
                val fragmentProfile =
                    fragmentManager?.findFragmentByTag(ProfileFragment::class.java.name)
                fragmentProfile?.let {
                    //(it as FragmentAux).refresh()
                }

                if (fragmentManager == null) {
                    fragmentManager = supportFragmentManager
                   // setUpBottomNav(fragmentManager!!)
                }
            }
        }
    }

    private fun setUpBottomNav(fragmentManager: FragmentManager){

        val homeFragment = HomeFragment()
        val profileFragment = ProfileFragment()

        activeFragment = homeFragment

        fragmentManager.beginTransaction()
            .add(R.id.hostFragment, profileFragment, ProfileFragment::class.java.name)
            .hide(profileFragment).commit()
        fragmentManager.beginTransaction()
            .add(R.id.hostFragment, homeFragment, HomeFragment::class.java.name).commit()

        binding.bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.action_home ->{
                    fragmentManager.beginTransaction().hide(activeFragment).show(homeFragment).commit()
                    activeFragment = homeFragment
                    true
                }
                R.id.action_profile ->{
                    fragmentManager.beginTransaction().hide(activeFragment).show(profileFragment).commit()
                    activeFragment = profileFragment
                    true
                }else -> false
            }
        }

        //este evento nos ayuda a posicionar la pag. al princio cuando pulso un boton de menú
        binding.bottomNav.setOnItemReselectedListener {
            when(it.itemId){
                //R.id.action_home -> (homeFragment as HomeAux).gotoTop()
            }
        }

    }

    override fun onResume() {
        super.onResume()
        firebaseAuth?.addAuthStateListener(authListener)
    }

    override fun onPause(){
        super.onPause()
        firebaseAuth?.removeAuthStateListener(authListener)
    }

    override fun showMessage(resId: Int, duration: Int) {
        Snackbar.make(binding.root, resId, duration)
            .setAnchorView(binding.bottomNav)
            .show()
    }

    override fun refresh() {
        TODO("Not yet implemented")
    }

}