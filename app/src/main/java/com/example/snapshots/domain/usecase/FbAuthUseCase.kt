package com.example.snapshots.domain.usecase

import com.example.snapshots.data.model.request.UserRequest
import com.example.snapshots.data.repository.FbAuthRepository
import com.example.snapshots.data.repository.FbAuthRepositoryImpl
import com.example.snapshots.domain.model.ResultModel
import io.reactivex.Single
import javax.inject.Inject

class FbAuthUseCase @Inject constructor(var fbAuthRepository: FbAuthRepository) {
    fun loginFireBase(userRequest: UserRequest): Single<ResultModel> =
        fbAuthRepository.loginFireBase(userRequest)

}