package com.example.snapshots.data.repository

import com.example.snapshots.data.database.FirebaseActions
import com.example.snapshots.data.model.mapping.toModel
import com.example.snapshots.data.model.response.SnapshotResponse
import com.example.snapshots.domain.model.SnapshotModel
import io.reactivex.Single
import javax.inject.Inject
// @Inject constructor(
//    var firebaseActions: FirebaseActions
//)
class SnapshotRepositoryImpl@Inject constructor(
   var firebaseActions: FirebaseActions
): SnapshotRepository {

//    override fun getSnapshotsDb(): Single<MutableList<SnapshotModel>> =
//        firebaseActions.getSnapshotsDb()
//        .map{fbRO ->
//            fbRO.toModel()
//        }


}