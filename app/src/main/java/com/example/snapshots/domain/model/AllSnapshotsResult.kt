package com.example.snapshots.domain.model

import com.example.snapshots.data.model.response.SnapshotResponse

class AllSnapshotsResult (
    var sussess:Boolean = false,
    var list_snapshot :MutableList<SnapshotResponse> = mutableListOf()
        )
