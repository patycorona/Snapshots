package com.example.snapshots.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentManager
import com.example.snapshots.R
import com.example.snapshots.databinding.ActivityMainBinding
import com.example.snapshots.domain.model.ConstantGeneral
import com.example.snapshots.ui.Home.HomeFragment
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private var firebaseAuth: FirebaseAuth? = null
    private lateinit var authListener: FirebaseAuth.AuthStateListener

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
        firebaseAuth = FirebaseAuth.getInstance()
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
            }
        }
    }

    private fun setUpBottomNav(fragmentManager: FragmentManager){

        val homeFragment = HomeFragment()

    }

    override fun onResume() {
        super.onResume()
        firebaseAuth?.addAuthStateListener(authListener)
    }

    override fun onPause(){
        super.onPause()
        firebaseAuth?.removeAuthStateListener(authListener)
    }

}