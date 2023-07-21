package com.example.snapshots.data.database

import com.example.snapshots.R
import com.example.snapshots.data.model.request.SnapshotRequest
import com.example.snapshots.data.model.response.SnapshotResponse
import com.example.snapshots.domain.model.ConstantGeneral.Companion.CODE
import com.example.snapshots.domain.model.ConstantGeneral.Companion.MSG_PHOTO_SUCCESS
import com.example.snapshots.domain.model.ConstantGeneral.Companion.PATH_SNAPSHOTS
import com.example.snapshots.domain.model.ConstantGeneral.Companion.PROPERTY_LIKE_LIST
import com.example.snapshots.domain.model.ResultModel
import com.example.snapshots.domain.model.SnapshotModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import io.reactivex.Single

class FirebaseDatabaseCustom {
    //pendiente por hacer
    val databaseRefence = FirebaseDatabase.getInstance().reference.child(PATH_SNAPSHOTS)

    fun setLike(snapshot: SnapshotResponse, checked:Boolean) : Single<ResultModel>{
            databaseRefence.child(snapshot.id).child(PROPERTY_LIKE_LIST)
                .child(FirebaseAuth.getInstance().currentUser!!.uid).setValue(if (checked)  {true} else{null})
        return Single.just(ResultModel(code = CODE, message = "") )
    }

    //    fun getSnapshotsDb(): io.reactivex.Single<FirebaseRecyclerOptions<SnapshotResponse>> {
//        val query = FirebaseDatabase.getInstance().reference
//            .child(PATH_SNAPSHOTS)
//        return io.reactivex.Single.just(
//            FirebaseRecyclerOptions.Builder<SnapshotResponse>()
//            .setQuery(query, SnapshotResponse::class.java).build())
//    }

}