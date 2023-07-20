package com.example.snapshots.domain.model

class SnapshotModel(
    var id:String = "",
    var title: String = "",
    var photoUrl: String = "",
    var likeList: Map<String, Boolean> = mutableMapOf()
)