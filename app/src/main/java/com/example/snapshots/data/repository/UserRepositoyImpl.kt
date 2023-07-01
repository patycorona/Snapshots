package com.example.snapshots.data.repository

import com.example.snapshots.data.database.FirebaseActions
import com.example.snapshots.data.model.request.UserRequest
import com.example.snapshots.domain.model.ResultModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class UserRepositoyImpl @Inject constructor(
    var firebaseActions: FirebaseActions
):UserRepository {

    override fun userRegisterFirebase(userRequest: UserRequest): Single<ResultModel> =
        firebaseActions.userRegisterFirebase(userRequest)

}