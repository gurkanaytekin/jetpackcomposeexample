package com.gurkanaytekin.jetpackcomposeexample.data.api.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DeleteTaskResponse(
    @Json(name = "data")
    val `data`: Task?,
    @Json(name = "success")
    val success: Boolean
)