package com.example.snapshots.data.repository

import com.example.snapshots.data.model.request.UserRequest
import com.example.snapshots.domain.model.ResultModel
import io.reactivex.Single

interface UserRepository {
    fun userRegisterFirebase(userRequest: UserRequest): Single<ResultModel>
}