package com.example.snapshots.data.model.response

data class AllSnapshotsResponse(
    val list_allSnapshots : MutableList<SnapshotResponse> = mutableListOf()
)
