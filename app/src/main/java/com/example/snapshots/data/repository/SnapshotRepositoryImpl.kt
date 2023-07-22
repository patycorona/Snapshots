package com.example.snapshots.data.repository

import com.example.snapshots.data.database.FirebaseActions
import com.example.snapshots.data.model.mapping.toModel
import com.example.snapshots.data.model.request.SnapshotRequest
import com.example.snapshots.domain.model.ResultModel
import io.reactivex.Single
import javax.inject.Inject

class SnapshotRepositoryImpl @Inject constructor(
    var firebaseActions: FirebaseActions
): SnapshotRepository {
   
//    override fun getSnapshotsDb(): Single<MutableList<SnapshotModel>> =
//        firebaseDatabaseCustom.getSnapshotsDb()
//        .map{fbRO ->
//            fbRO.toModel()
//        }
   override fun addSnapshot(snapshotR: SnapshotRequest): Single<ResultModel> =
   firebaseActions.addSnapshot(snapshotR)
         .map { FBDBC->
            FBDBC.toModel()
         }


}