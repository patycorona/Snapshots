package com.example.snapshots.data.database

import com.example.snapshots.domain.model.ResultModel
import com.example.snapshots.domain.model.Snapshot
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.rxjava3.core.Single

class FirebaseDatabaseCustom {
    //pendiente por hacer
    private val SNAPSHOTS = "snapshots"
    private val LIKELIST = "likeList"
    val databaseRefence = FirebaseDatabase.getInstance().reference.child(SNAPSHOTS)



    fun setLike(snapshot: Snapshot, checked:Boolean) : Single<ResultModel>{
            databaseRefence.child(snapshot.id).child(LIKELIST)
                .child(FirebaseAuth.getInstance().currentUser!!.uid).setValue(if (checked)  {true} else{null})
        return Single.just(ResultModel(code = "0", message = "") )
    }
}