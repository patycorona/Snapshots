package com.example.snapshots.domain.usecase

import com.example.snapshots.data.repository.FbAuthRepository
import javax.inject.Inject

class FbAuthUseCase @Inject constructor(
    var fbAuthRepository: FbAuthRepository
) {
}