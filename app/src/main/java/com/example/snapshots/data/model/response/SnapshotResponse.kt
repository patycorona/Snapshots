package com.example.snapshots.data.model.response

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class SnapshotResponse(
    var id:String = "",
    var title: String = "",
    var photoUrl: String = "",
    var likeList: Map<String, Boolean> = mutableMapOf()
)
