package com.example.snapshots.domain.usecase

import com.example.snapshots.data.repository.SnapshotRepository
import com.example.snapshots.data.model.response.SnapshotResponse
import io.reactivex.Single
import javax.inject.Inject

//@Inject constructor(var snapshotRepository: SnapshotRepository)
class SnapshotUseCase @Inject constructor(var snapshotRepository: SnapshotRepository)  {
//    fun getSnapshotsDb(): Single<MutableList<SnapshotResponse>> =
//        snapshotRepository.getSnapshotsDb()
}