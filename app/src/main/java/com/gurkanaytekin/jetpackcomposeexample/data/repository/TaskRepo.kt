package com.gurkanaytekin.jetpackcomposeexample.data.repository
import com.gurkanaytekin.jetpackcomposeexample.data.api.TodoApi
import com.gurkanaytekin.jetpackcomposeexample.data.api.model.LoginRequest
import com.gurkanaytekin.jetpackcomposeexample.data.api.model.LoginResponse
import com.gurkanaytekin.jetpackcomposeexample.data.api.model.Task
import javax.inject.Inject

class TaskRepo @Inject constructor(
    private val taskApi: TodoApi
) {
    suspend fun getTaskList(): List<Task> {
        return taskApi.GetTasks()
    }
    suspend fun login(loginRequest: LoginRequest): LoginResponse {
        return taskApi.Login(loginRequest)
    }
}
