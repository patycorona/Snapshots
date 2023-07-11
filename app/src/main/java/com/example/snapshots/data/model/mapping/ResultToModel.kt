package com.example.snapshots.data.model.mapping

import com.example.snapshots.data.model.response.ResultResponse
import com.example.snapshots.data.model.response.UserRegisterResponse
import com.example.snapshots.data.model.response.UserResponse
import com.example.snapshots.domain.model.ResultModel

internal fun UserRegisterResponse.toModel() =
    ResultModel(code = code, message = message,isSuccess = isSuccess)

internal fun UserResponse.toModel() =
    ResultModel(code = code, message = message,isSuccess = isSuccess)