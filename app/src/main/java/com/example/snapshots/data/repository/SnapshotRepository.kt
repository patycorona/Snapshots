package com.example.snapshots.data.repository

import com.example.snapshots.data.model.request.SnapshotRequest
import com.example.snapshots.data.model.response.SnapshotResponse
import com.example.snapshots.domain.model.ResultModel
import io.reactivex.Single

interface SnapshotRepository {
    //fun getSnapshotsDb(): Single<MutableList<SnapshotResponse>>

    fun addSnapshot(snapshotR : SnapshotRequest): Single<ResultModel>
}