package com.example.snapshots.data.repository

import com.example.snapshots.data.model.request.UserRequest
import com.example.snapshots.domain.model.ResultModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import io.reactivex.rxjava3.core.Single

interface UserRepository {
    fun userRegisterFirebase(userRequest: UserRequest): Single<ResultModel>
}