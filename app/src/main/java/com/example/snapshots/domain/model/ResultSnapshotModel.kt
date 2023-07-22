package com.example.snapshots.domain.model

import android.os.Parcelable
import io.reactivex.Observable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue



@Parcelize
data class ResultSnapshotModel(
    var code: String = "",
    var message: String = "",
    val isSuccess:Boolean = false,
    val listSnapshotModel: @RawValue MutableList<SnapshotModel> = mutableListOf()
):Parcelable