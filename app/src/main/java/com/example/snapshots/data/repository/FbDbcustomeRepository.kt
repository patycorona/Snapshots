package com.example.snapshots.data.repository

import com.example.snapshots.domain.model.ResultModel
import com.example.snapshots.domain.model.Snapshot
import io.reactivex.rxjava3.core.Single

interface FbDbcustomeRepository {

    fun setLike(snapshot: Snapshot, checked:Boolean): Single<ResultModel>

}