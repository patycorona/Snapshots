package com.example.snapshots.domain.usecase

import com.example.snapshots.data.model.request.SnapshotRequest
import com.example.snapshots.data.repository.SnapshotRepository
import com.example.snapshots.data.model.response.SnapshotResponse
import com.example.snapshots.domain.model.ResultModel
import com.example.snapshots.domain.model.SnapshotModel
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

//@Inject constructor(var snapshotRepository: SnapshotRepository)
class SnapshotUseCase @Inject constructor(
    var snapshotRepository: SnapshotRepository)  {

    fun addSnapshot(snapshotR: SnapshotRequest): Single<ResultModel> =
        snapshotRepository.addSnapshot(snapshotR)

    fun getSnapshotsDb(): Observable<MutableList<SnapshotModel>> =
        snapshotRepository.getSnapshotsDb()
}