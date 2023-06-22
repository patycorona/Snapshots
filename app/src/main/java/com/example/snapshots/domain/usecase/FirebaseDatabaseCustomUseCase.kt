package com.example.snapshots.domain.usecase

import com.example.snapshots.data.repository.FbDbcustomeRepository
import com.example.snapshots.domain.model.ResultModel
import com.example.snapshots.domain.model.Snapshot
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class FirebaseDatabaseCustomUseCase @Inject constructor(
   var fbDbcustomeRepository: FbDbcustomeRepository
) {

    fun setLike(snapshot: Snapshot, checked: Boolean): Single<ResultModel> =
        fbDbcustomeRepository.setLike(snapshot, checked)
}