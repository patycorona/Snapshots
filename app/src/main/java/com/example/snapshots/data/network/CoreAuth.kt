package com.example.snapshots.data.network

import com.google.firebase.auth.FirebaseAuth

class CoreAuth {

    var firebaseAuth: FirebaseAuth? = null

    fun login(){
        firebaseAuth = FirebaseAuth.getInstance()
    }


}