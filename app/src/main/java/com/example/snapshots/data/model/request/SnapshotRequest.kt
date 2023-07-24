package com.example.snapshots.data.model.request

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class SnapshotRequest(
    @get:Exclude var id:String = "",
    var title: String = "",
    var photoUrl: String = "",
    var likeList: Map<String, Boolean> = mutableMapOf()
)


