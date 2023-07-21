package com.example.snapshots.data.model.mapping

import com.example.snapshots.domain.model.ResultModel

internal fun ResultModel.toModel() =
    ResultModel(code = code, message = message,isSuccess = isSuccess)

//internal fun FirebaseRecyclerOptions<SnapshotResponse>.toModel(): MutableList<SnapshotModel>{
//    val list: MutableList<SnapshotModel> = mutableListOf()
//
////    list_allSnapshots.map { lS ->
////        list.add(
////            SnapshotModel(
////                title = lS.title,
////                photoUrl = lS.photoUrl
////            )
////        )
////    }
//    return list
//}
