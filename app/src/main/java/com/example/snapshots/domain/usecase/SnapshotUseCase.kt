package com.example.snapshots.domain.usecase

import com.example.snapshots.data.model.request.SnapshotRequest
import com.example.snapshots.data.repository.SnapshotRepository
import com.example.snapshots.domain.model.ResultModel
import com.example.snapshots.domain.model.SnapshotModel
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class SnapshotUseCase @Inject constructor(
    var snapshotRepository: SnapshotRepository)  {

    fun addSnapshot(snapshotR: SnapshotRequest): Single<ResultModel> =
        snapshotRepository.addSnapshot(snapshotR)

    fun getSnapshotsDb(): Observable<MutableList<SnapshotModel>> =
        snapshotRepository.getSnapshotsDb()

    fun deleteSnapshot(id:String): Observable<MutableList<SnapshotModel>> =
        snapshotRepository.deleteSnapshot(id)
}