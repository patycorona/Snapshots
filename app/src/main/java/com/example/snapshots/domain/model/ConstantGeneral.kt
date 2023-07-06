package com.example.snapshots.domain.model

import com.google.firebase.auth.FirebaseUser

class ConstantGeneral {
    companion object {

        const val ERROR = "-1"
        const val CODE = "0"
        const val MSG_ERROR_AUTH = "Authentication failed."
        const val MSG_ERROR ="please try again"
        const val MSG_SUCCESS = "User created successfully"
        const val MSG_COMPLETE_INFO ="To continue complete the required fields"
        const val MSG_NOT_MATCH_PWD = "password and confirm password does not match"
        const val PATH_SNAPSHOTS = "snapshots"
        const val PROPERTY_LIKE_LIST = "likeList"

        lateinit var currentUser: FirebaseUser
    }
}