package com.example.snapshots.data.repository

import com.example.snapshots.data.network.CoreAuth
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class FbAuthRepositoryImpl @Inject constructor(
   // var coreAuth: CoreAuth
):FbAuthRepository {
    fun firebaseAuth(email : String, password: String) =  FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
}