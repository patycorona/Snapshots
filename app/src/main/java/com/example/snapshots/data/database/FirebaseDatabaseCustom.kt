package com.example.snapshots.data.database

import androidx.core.net.toUri
import com.example.snapshots.data.model.request.SnapshotRequest
import com.example.snapshots.data.model.response.SnapshotResponse
import com.example.snapshots.domain.model.ConstantGeneral
import com.example.snapshots.domain.model.ConstantGeneral.Companion.CODE
import com.example.snapshots.domain.model.ConstantGeneral.Companion.PATH_SNAPSHOTS
import com.example.snapshots.domain.model.ConstantGeneral.Companion.PROPERTY_LIKE_LIST
import com.example.snapshots.domain.model.ResultModel
import com.example.snapshots.domain.model.SnapshotModel
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import io.reactivex.Observable
import io.reactivex.Single


class FirebaseDatabaseCustom {
    //pendiente por hacer
    private val databaseRefence = FirebaseDatabase.getInstance().reference.child(PATH_SNAPSHOTS)
    private val storageReference = FirebaseStorage.getInstance().reference

    val img =
        "data:image/png;base64,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"

    fun setLike(snapshot: SnapshotResponse, checked: Boolean):
            Observable<MutableList<SnapshotResponse>> {

        if (checked)
            databaseRefence.child(snapshot.id).child(PROPERTY_LIKE_LIST)
                .child(FirebaseAuth.getInstance().currentUser!!.uid).setValue(checked)
        else databaseRefence.child(snapshot.id).child(PROPERTY_LIKE_LIST)
            .child(FirebaseAuth.getInstance().currentUser!!.uid).setValue(null)

        return Observable.just(buildMOcksetLike(snapshot, checked))
    }

    fun getSnapshotsDb(): Observable<MutableList<SnapshotResponse>> {
        val query = FirebaseDatabase.getInstance().reference
            .child(PATH_SNAPSHOTS)

        Observable.create { emitter ->
            query.child(PATH_SNAPSHOTS).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val list: MutableList<SnapshotResponse> = ArrayList<SnapshotResponse>()
                    val snapshotIterable = snapshot.children
                    val iterator: Iterator<DataSnapshot> = snapshotIterable.iterator()

                    while (iterator.hasNext()) {
                        val snapshotResponse: SnapshotResponse =
                            iterator.next().getValue(SnapshotResponse::class.java)
                                ?: SnapshotResponse()
                        list.add(snapshotResponse)
                    }
                    emitter.onNext(list)
                }

                override fun onCancelled(error: DatabaseError) {
                    emitter.onError(FirebaseException(error.message))
                }
            })
        }

        return Observable.just(buildMOck())
    }

    fun addSnapshot(snapshotR: SnapshotRequest): Single<ResultModel> {
        var result: ResultModel
        val key = databaseRefence.push().key!!

        val storageReb = storageReference.child(PATH_SNAPSHOTS)
            .child(FirebaseAuth.getInstance().currentUser!!.uid).child(key)

        val snapshot = SnapshotModel("", title = snapshotR.title, photoUrl = snapshotR.photoUrl)
        databaseRefence.child(key).setValue(snapshot)

        val photoUri = snapshotR.photoUrl.toUri()

        storageReb.putFile(photoUri)
            .addOnSuccessListener {
                val snapshot =
                    SnapshotModel("", title = snapshotR.title, photoUrl = snapshotR.photoUrl)
                databaseRefence.child(key).setValue(snapshot)
                result = ResultModel(CODE, ConstantGeneral.MSG_PHOTO_SUCCESS, true)
            }
            .addOnFailureListener {
                result = ResultModel(ConstantGeneral.ERROR, ConstantGeneral.MSG_PHOTO_ERROR, false)
            }

        result = ResultModel(CODE, ConstantGeneral.MSG_PHOTO_SUCCESS, true)
        return Single.just(result)
    }

    fun deleteSnapshot(id: String): Observable<MutableList<SnapshotResponse>> {

        val idDelete = FirebaseDatabase.getInstance().reference
            .child(PATH_SNAPSHOTS)
        databaseRefence.child(id).removeValue()

        return Observable.just(buildMOckDelete(id))
    }

    fun buildMOck(): MutableList<SnapshotResponse> {

        val data: MutableList<SnapshotResponse> = mutableListOf()

        for (i in 1..15) {
            data.add(
                SnapshotResponse(
                    id = i.toString(),
                    title = "IMAGEN $i",
                    photoUrl = img
                )
            )
        }
        return data
    }

    fun buildMOcksetLike(
        snapshot: SnapshotResponse,
        checked: Boolean
    ): MutableList<SnapshotResponse> {

        val data: MutableList<SnapshotResponse> = mutableListOf()

        for (i in 1..15) {
            if (i == snapshot.id.toInt()) {
                data.add(
                    SnapshotResponse(
                        id = i.toString(),
                        title = "IMAGEN $i",
                        photoUrl = img,
                        likeList = mapOf(i.toString() to checked)
                    )
                )
            }
        }
        return data
    }

    fun buildMOckDelete(id: String): MutableList<SnapshotResponse> {

        val data: MutableList<SnapshotResponse> = mutableListOf()

        for (i in 1..15) {
            if (i == id.toInt()) {
                data.remove(
                    SnapshotResponse(
                        id = i.toString(),
                        title = "IMAGEN $i",
                        photoUrl = img
                    )
                )
            } else {
                data.add(
                    SnapshotResponse(
                        id = i.toString(),
                        title = "IMAGEN $i",
                        photoUrl = img
                    )
                )
            }
        }
        return data
    }

}