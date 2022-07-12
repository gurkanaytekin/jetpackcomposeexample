package com.gurkanaytekin.jetpackcomposeexample.data.api.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "age")
    val age: Int,
    @Json(name = "createdAt")
    val createdAt: String,
    @Json(name = "email")
    var email: String,
    @Json(name = "_id")
    val id: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "updatedAt")
    val updatedAt: String,
    @Json(name = "__v")
    val v: Int
)