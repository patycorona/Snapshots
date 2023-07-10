package com.example.snapshots.data.database

import android.content.Context
import com.example.snapshots.data.model.request.UserRequest
import com.example.snapshots.data.model.response.UserRegisterResponse
import com.example.snapshots.domain.model.ConstantGeneral.Companion.CODE
import com.example.snapshots.domain.model.ConstantGeneral.Companion.ERROR
import com.example.snapshots.domain.model.ConstantGeneral.Companion.MSG_ERROR
import com.example.snapshots.domain.model.ConstantGeneral.Companion.MSG_SUCCESS
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Single
import javax.inject.Inject


class FirebaseActions @Inject constructor(context: Context) {
    val firebaseActions : FirebaseAuth = context.
    fun userRegisterFirebase(userRequest: UserRequest) : Single<UserRegisterResponse>{
        var userRegisterResponse = UserRegisterResponse()
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(userRequest.email, userRequest.pwd)
            .addOnCompleteListener(){ task ->
                userRegisterResponse =
                    if (task.isSuccessful) UserRegisterResponse(CODE, MSG_SUCCESS, true)
                    else UserRegisterResponse(ERROR, MSG_ERROR, false)
            }
        return Single.just(userRegisterResponse)
    }
}