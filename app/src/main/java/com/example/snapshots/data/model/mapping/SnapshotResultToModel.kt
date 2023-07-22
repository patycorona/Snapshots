package com.example.snapshots.data.model.mapping

import com.example.snapshots.data.model.response.SnapshotResponse
import com.example.snapshots.domain.model.ResultModel
import com.example.snapshots.domain.model.SnapshotModel
import io.reactivex.Observable

internal fun ResultModel.toModel() =
    ResultModel(code = code, message = message,isSuccess = isSuccess)





internal fun MutableList<SnapshotResponse>.toModel(): MutableList<SnapshotModel> {
    val list: MutableList<SnapshotModel> = mutableListOf()
    this.map { lS ->
        list.add(
            SnapshotModel(
                title = lS.title,
                photoUrl = lS.photoUrl
            )
        )
    }
    return list
}
