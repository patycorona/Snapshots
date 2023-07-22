package com.example.snapshots.data.repository

import com.example.snapshots.data.database.FirebaseActions
import com.example.snapshots.data.database.FirebaseDatabaseCustom
import com.example.snapshots.data.model.mapping.toModel
import com.example.snapshots.data.model.request.SnapshotRequest
import com.example.snapshots.data.model.response.SnapshotResponse
import com.example.snapshots.domain.model.ResultModel
import com.example.snapshots.domain.model.SnapshotModel
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class SnapshotRepositoryImpl @Inject constructor(
    private var firebaseActions: FirebaseActions,
    private var firebaseDatabaseCustom: FirebaseDatabaseCustom
) : SnapshotRepository {

    override fun getSnapshotsDb(): Observable<MutableList<SnapshotModel>> =
        firebaseDatabaseCustom.getSnapshotsDb()
            .map { list ->
                list.toModel()
            }


    override fun addSnapshot(snapshotR: SnapshotRequest): Single<ResultModel> =
        firebaseActions.addSnapshot(snapshotR)
            .map { FBDBC ->
                FBDBC.toModel()
            }


}