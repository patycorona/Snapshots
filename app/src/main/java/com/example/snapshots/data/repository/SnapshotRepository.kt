package com.example.snapshots.data.repository

import com.example.snapshots.data.model.request.SnapshotRequest
import com.example.snapshots.domain.model.ResultModel
import com.example.snapshots.domain.model.SnapshotModel
import io.reactivex.Observable
import io.reactivex.Single

interface SnapshotRepository {
    fun getSnapshotsDb(): Observable<MutableList<SnapshotModel>>

    fun addSnapshot(snapshotR : SnapshotRequest): Single<ResultModel>
}