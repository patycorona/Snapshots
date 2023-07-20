package com.example.snapshots.data.repository

import com.example.snapshots.data.database.FirebaseDatabaseCustom
import com.example.snapshots.domain.model.ResultModel
import com.example.snapshots.data.model.response.SnapshotResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class FbDbcustomeRepositoryImpl @Inject constructor (var firebaseDatabaseCustom : FirebaseDatabaseCustom):FbDbcustomeRepository {

    override fun setLike(snapshot: SnapshotResponse, checked: Boolean): Single<ResultModel> = firebaseDatabaseCustom.setLike(snapshot, checked)

}