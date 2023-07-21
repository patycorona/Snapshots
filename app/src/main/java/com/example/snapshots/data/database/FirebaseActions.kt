package com.example.snapshots.data.database

import androidx.core.net.toUri
import com.example.snapshots.R
import com.example.snapshots.data.model.request.SnapshotRequest
import com.example.snapshots.data.model.request.UserRequest
import com.example.snapshots.data.model.response.UserRegisterResponse
import com.example.snapshots.data.model.response.UserResponse
import com.example.snapshots.domain.model.ConstantGeneral
import com.example.snapshots.domain.model.ConstantGeneral.Companion.CODE
import com.example.snapshots.domain.model.ConstantGeneral.Companion.ERROR
import com.example.snapshots.domain.model.ConstantGeneral.Companion.MSG_ERROR
import com.example.snapshots.domain.model.ConstantGeneral.Companion.MSG_ERROR_AUTH
import com.example.snapshots.domain.model.ConstantGeneral.Companion.MSG_LOGIN_SUCCESS
import com.example.snapshots.domain.model.ConstantGeneral.Companion.MSG_REGISTER_SUCCESS
import com.example.snapshots.domain.model.ConstantGeneral.Companion.PATH_SNAPSHOTS
import com.example.snapshots.domain.model.ResultModel
import com.example.snapshots.domain.model.SnapshotModel
import com.example.snapshots.ui.component.TypeAuth
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import io.reactivex.Single
import javax.inject.Inject


class FirebaseActions @Inject constructor() {

    private val firebaseAuth = FirebaseAuth.getInstance()
    //lateinit var authListener: FirebaseAuth.AuthStateListener
    private val databaseRefence = FirebaseDatabase.getInstance().reference.child(PATH_SNAPSHOTS)
    private val storageReference = FirebaseStorage.getInstance().reference
    private val storageReb = storageReference.child(PATH_SNAPSHOTS).child(R.string.nameFolder.toString())

    fun userRegisterFirebase(userRequest: UserRequest) : Single<UserRegisterResponse>{
        var userRegisterResponse = UserRegisterResponse(CODE, MSG_REGISTER_SUCCESS, true)
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(userRequest.email, userRequest.pwd)
            .addOnCompleteListener(){ task ->
                userRegisterResponse =
                    if (task.isSuccessful) UserRegisterResponse(CODE, MSG_REGISTER_SUCCESS, true)
                    else UserRegisterResponse(ERROR, MSG_ERROR, false)
            }
        return Single.just(userRegisterResponse)
    }

    fun loginFireBase(userRequest: UserRequest): Single<UserResponse>{
        var userResponse = UserResponse(CODE, MSG_LOGIN_SUCCESS, true)

        firebaseAuth.signInWithEmailAndPassword(userRequest.email, userRequest.pwd)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful){
                    userResponse = UserResponse(CODE, MSG_LOGIN_SUCCESS, true)
                    val user = task.result.user
                    updateUI(user)
                } else{
                    UserResponse(ERROR, MSG_ERROR_AUTH, false)
                    val user = task.result.user
                    updateUI(null)
                }
            }

        return Single.just(userResponse)
    }

    fun updateUI(user: FirebaseUser?) {
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

    fun addSnapshot(snapshotR : SnapshotRequest):Single<ResultModel>{
        var result = ResultModel()
        val key = databaseRefence.push().key!!
        val photoUri = snapshotR.photoUrl.toUri()

        storageReference.child(ConstantGeneral.PATH_SNAPSHOTS).child(R.string.nameFolder.toString())
        storageReb.putFile(photoUri!!)
            .addOnSuccessListener {
                val snapshot = SnapshotModel(key, snapshotR.title, snapshotR.photoUrl)
                databaseRefence.child(key).setValue(snapshot)
                result = ResultModel(CODE, ConstantGeneral.MSG_PHOTO_SUCCESS, true)
            }
            .addOnFailureListener{
                result = ResultModel(ERROR, ConstantGeneral.MSG_PHOTO_ERROR, false)
            }

        return Single.just(result)
    }
}