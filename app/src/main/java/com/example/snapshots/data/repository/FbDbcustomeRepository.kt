package com.example.snapshots.data.repository

import com.example.snapshots.domain.model.ResultModel
import com.example.snapshots.data.model.response.SnapshotResponse
import io.reactivex.Single

interface FbDbcustomeRepository {

    fun setLike(snapshot: SnapshotResponse, checked:Boolean): Single<ResultModel>

}