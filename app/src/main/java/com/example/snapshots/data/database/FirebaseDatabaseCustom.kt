package com.example.snapshots.data.database

import com.example.snapshots.R
import com.example.snapshots.data.model.request.SnapshotRequest
import com.example.snapshots.domain.model.ConstantGeneral.Companion.PATH_SNAPSHOTS
import com.example.snapshots.domain.model.ConstantGeneral.Companion.PROPERTY_LIKE_LIST
import com.example.snapshots.domain.model.ResultModel
import com.example.snapshots.data.model.response.SnapshotResponse
import com.example.snapshots.domain.model.SnapshotModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import io.reactivex.rxjava3.core.Single

class FirebaseDatabaseCustom {
    //pendiente por hacer
    val databaseRefence = FirebaseDatabase.getInstance().reference.child(PATH_SNAPSHOTS)
    val storageReference = FirebaseStorage.getInstance().reference


    fun setLike(snapshot: SnapshotResponse, checked:Boolean) : Single<ResultModel>{
            databaseRefence.child(snapshot.id).child(PROPERTY_LIKE_LIST)
                .child(FirebaseAuth.getInstance().currentUser!!.uid).setValue(if (checked)  {true} else{null})
        return Single.just(ResultModel(code = "0", message = "") )
    }

//    fun addSnapshot(snapshot : SnapshotRequest):Single<ResultModel>{
//        storageReference.child(PATH_SNAPSHOTS).child(R.string.nameFolder.toString())
//        val key = databaseRefence.push().key!!
//
//        val snapshot = SnapshotModel(key, title, photoUrl)
//        databasePreference.child(key).setValue(snapshot)
//    }
}