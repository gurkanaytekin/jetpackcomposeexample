package com.gurkanaytekin.jetpackcomposeexample.data.api.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Task(
    @Json(name = "completed")
    val completed: Boolean,
    @Json(name = "createdAt")
    val createdAt: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "_id")
    val id: String,
    @Json(name = "owner")
    val owner: String,
    @Json(name = "updatedAt")
    val updatedAt: String,
    @Json(name = "__v")
    val v: Int
)