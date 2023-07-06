package com.example.snapshots.domain.usecase

import com.example.snapshots.data.model.request.UserRequest
import com.example.snapshots.data.model.response.UserRegisterResponse
import com.example.snapshots.data.repository.UserRepository
import com.example.snapshots.domain.model.ResultModel
import io.reactivex.Single
import javax.inject.Inject

class UserUseCase @Inject constructor(var userRepository: UserRepository){

    fun userRegisterFirebase(userRequest: UserRequest): Single<ResultModel> =
        userRepository.userRegisterFirebase(userRequest)

}