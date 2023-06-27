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
    //var fbAuthUseCase: FbAuthUseCase
):ViewModel(){
    fun firebaseAuth(email : String, password: String) = FbAuthUseCase().firebaseAuth(email,password)
}