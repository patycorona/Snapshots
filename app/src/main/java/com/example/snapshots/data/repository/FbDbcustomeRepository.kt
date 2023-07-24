package com.example.snapshots.data.repository

import com.example.snapshots.data.model.response.SnapshotResponse
import com.example.snapshots.domain.model.SnapshotModel
import io.reactivex.Observable

interface FbDbcustomeRepository {

    fun setLike(snapshot: SnapshotResponse, checked:Boolean):
            Observable<MutableList<SnapshotModel>>

}