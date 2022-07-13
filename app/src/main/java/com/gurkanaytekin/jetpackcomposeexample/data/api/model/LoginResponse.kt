package com.gurkanaytekin.jetpackcomposeexample.data.api.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResponse(
    @Json(name = "token")
    val token: String,
    @Json(name = "user")
    val user: User
)