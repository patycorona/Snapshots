package com.example.snapshots.domain.usecase

import com.example.snapshots.data.model.response.SnapshotResponse
import com.example.snapshots.data.repository.FbDbcustomeRepository
import com.example.snapshots.domain.model.SnapshotModel
import io.reactivex.Observable
import javax.inject.Inject

class FirebaseDatabaseCustomUseCase @Inject constructor(
   var fbDbcustomeRepository: FbDbcustomeRepository
) {
    fun setLike(snapshot: SnapshotResponse, checked: Boolean):
            Observable<MutableList<SnapshotModel>> =
        fbDbcustomeRepository.setLike(snapshot, checked)
}