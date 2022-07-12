package com.gurkanaytekin.jetpackcomposeexample.data.api

import com.gurkanaytekin.jetpackcomposeexample.data.api.model.LoginRequest
import com.gurkanaytekin.jetpackcomposeexample.data.api.model.LoginResponse
import com.gurkanaytekin.jetpackcomposeexample.data.api.model.Task
import retrofit2.http.*

interface TodoApi {

    @GET(ApiConstants.BASE_URL + "task")
    suspend fun GetTasks(): List<Task>

    @POST(ApiConstants.BASE_URL + "user/login")
    suspend fun Login(@Body loginRequest: LoginRequest): LoginResponse
}