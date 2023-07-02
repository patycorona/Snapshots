package com.example.snapshots.data.model.mapping

import com.example.snapshots.data.model.response.ResultResponse
import com.example.snapshots.domain.model.ResultModel

internal fun ResultResponse.toModel() =
    ResultModel(code = code, message = message)