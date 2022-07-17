package com.gurkanaytekin.jetpackcomposeexample.data.api.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UpdateTaskRequest(
    @Json(name = "completed")
    val completed: Boolean
)