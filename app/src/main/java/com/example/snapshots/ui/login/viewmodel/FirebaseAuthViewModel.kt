package com.example.snapshots.ui.login.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.core.content.contentValuesOf
import androidx.lifecycle.ViewModel
import com.example.snapshots.domain.usecase.FbAuthUseCase
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FirebaseAuthViewModel @Inject constructor(
    var fbAuthUseCase: FbAuthUseCase
):ViewModel(){

    fun firebaseAuth(email : String, password: String){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    /*Toast.makeText(baseContext, "signInWithEmail:success",
                        Toast.LENGTH_SHORT).show()*/
                    val user = task.result.user
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    /*Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()*/
                    updateUI(null)
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        user?.let {
            // Name, email address, and profile photo Url
            val name = it.displayName
            val email = it.email
            val photoUrl = it.photoUrl

            // Check if user's email is verified
            val emailVerified = it.isEmailVerified

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            val uid = it.uid
        }
    }

}