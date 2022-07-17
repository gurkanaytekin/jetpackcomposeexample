package com.gurkanaytekin.jetpackcomposeexample.data.api.model


import androidx.compose.runtime.snapshots.SnapshotStateList
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetTaskListResponse(
    @Json(name = "count")
    val count: Int,
    @Json(name = "data")
    val `data`: List<Task>
)