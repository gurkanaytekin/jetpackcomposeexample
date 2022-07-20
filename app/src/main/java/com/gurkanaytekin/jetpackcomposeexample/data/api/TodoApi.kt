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

    @PUT(ApiConstants.BASE_URL + "task/{id}")
    suspend fun updateTask(@Body updateTask: UpdateTaskRequest, @Path("id") path: String,): UpdateTaskResponse

    @DELETE(ApiConstants.BASE_URL + "task/{id}")
    suspend fun deleteTask(@Path("id") path: String,): DeleteTaskResponse
}