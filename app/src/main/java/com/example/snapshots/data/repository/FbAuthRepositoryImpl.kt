package com.example.snapshots.data.repository

import com.example.snapshots.data.database.FirebaseActions
import com.example.snapshots.data.model.mapping.toModel
import com.example.snapshots.data.model.request.UserRequest
import com.example.snapshots.domain.model.ResultModel
import io.reactivex.Single
import javax.inject.Inject

class FbAuthRepositoryImpl @Inject constructor(
    var firebaseActions: FirebaseActions
):FbAuthRepository {
    override fun loginFireBase (userRequest: UserRequest): Single<ResultModel> =
        firebaseActions.loginFireBase(userRequest)
            .map { userResponse ->
                userResponse.toModel()
            }
}