package com.gurkanaytekin.jetpackcomposeexample.data.api

import com.gurkanaytekin.jetpackcomposeexample.data.api.model.*
import retrofit2.http.*

interface TodoApi {

    @GET(ApiConstants.BASE_URL + "task")
    suspend fun GetTasks(): GetTaskListResponse

    @POST(ApiConstants.BASE_URL + "user/login")
    suspend fun Login(@Body loginRequest: LoginRequest): LoginResponse

    @POST(ApiConstants.BASE_URL + "task")
    suspend fun addTask(@Body addTaskRequest: AddTaskRequest): AddTaskResponse
}