package com.example.snapshots.data.repository

import com.example.snapshots.data.database.FirebaseDatabaseCustom
import com.example.snapshots.data.model.mapping.toModel
import com.example.snapshots.data.model.response.SnapshotResponse
import com.example.snapshots.domain.model.SnapshotModel
import io.reactivex.Observable
import javax.inject.Inject

class FbDbcustomeRepositoryImpl @Inject constructor (
    var firebaseDatabaseCustom : FirebaseDatabaseCustom):FbDbcustomeRepository {

    override fun setLike(snapshot: SnapshotResponse, checked: Boolean):
            Observable<MutableList<SnapshotModel>> =
        firebaseDatabaseCustom.setLike(snapshot, checked)
        .map { FBDBC ->
            FBDBC.toModel()
        }

}