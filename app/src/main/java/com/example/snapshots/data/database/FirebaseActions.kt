package com.example.snapshots.data.database

import com.example.snapshots.data.model.request.UserRequest
import com.example.snapshots.domain.model.ConstantGeneral.Companion.PROPERTY_LIKE_LIST
import com.example.snapshots.domain.model.ResultModel
import com.example.snapshots.ui.login.viewmodel.FirebaseAuthViewModel
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.rxjava3.core.Single

class FirebaseActions {

    fun userRegisterFirebase(userRequest: UserRequest) : Single<ResultModel> {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(userRequest.email, userRequest.pwd)
        return Single.just(ResultModel(code = "0", message = "") )
    }
}