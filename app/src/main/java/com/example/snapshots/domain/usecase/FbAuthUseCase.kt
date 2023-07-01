package com.example.snapshots.domain.usecase

import com.example.snapshots.data.repository.FbAuthRepositoryImpl
import javax.inject.Inject

class FbAuthUseCase @Inject constructor(
    //var fbAuthRepository: FbAuthRepository
) {
    fun firebaseAuth(email : String, password: String) = FbAuthRepositoryImpl().firebaseAuth(email, password)
}