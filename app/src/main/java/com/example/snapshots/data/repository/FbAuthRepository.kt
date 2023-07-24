package com.example.snapshots.data.repository

import com.example.snapshots.data.model.request.UserRequest
import com.example.snapshots.domain.model.ResultModel
import io.reactivex.Single

interface FbAuthRepository {
    fun loginFireBase(userRequest: UserRequest): Single<ResultModel>
}