package com.example.snapshots.domain.model

import com.google.firebase.auth.FirebaseUser

class ConstantGeneral {
    companion object {
/*        const val RC_GALLERY = 18
        const val RC_SIGN_IN = 21*/

        const val PATH_SNAPSHOTS = "snapshots"
        const val PROPERTY_LIKE_LIST = "likeList"

        lateinit var currentUser: FirebaseUser
    }
}