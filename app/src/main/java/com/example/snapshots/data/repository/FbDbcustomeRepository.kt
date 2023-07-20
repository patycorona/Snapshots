package com.example.snapshots.data.repository

import com.example.snapshots.domain.model.ResultModel
import com.example.snapshots.data.model.response.SnapshotResponse
import io.reactivex.rxjava3.core.Single

interface FbDbcustomeRepository {

    fun setLike(snapshot: SnapshotResponse, checked:Boolean): Single<ResultModel>

}