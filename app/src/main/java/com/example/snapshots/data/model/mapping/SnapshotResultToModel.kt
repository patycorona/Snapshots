package com.example.snapshots.data.model.mapping

import com.example.snapshots.data.model.response.AllSnapshotsResponse
import com.example.snapshots.data.model.response.SnapshotResponse
import com.example.snapshots.domain.model.SnapshotModel
import com.firebase.ui.database.FirebaseRecyclerOptions


internal fun FirebaseRecyclerOptions<SnapshotResponse>.toModel(): MutableList<SnapshotModel>{
    val list: MutableList<SnapshotModel> = mutableListOf()

//    list_allSnapshots.map { lS ->
//        list.add(
//            SnapshotModel(
//                title = lS.title,
//                photoUrl = lS.photoUrl
//            )
//        )
//    }
    return list
}
