package com.gurkanaytekin.jetpackcomposeexample.data.repository
import android.util.Log
import com.gurkanaytekin.jetpackcomposeexample.data.api.TodoApi
import com.gurkanaytekin.jetpackcomposeexample.data.api.model.*
import javax.inject.Inject

class TaskRepo @Inject constructor(
    private val taskApi: TodoApi
) {
    suspend fun getTaskList(): GetTaskListResponse {
        return taskApi.GetTasks()
    }
    suspend fun login(loginRequest: LoginRequest): LoginResponse {
        return taskApi.Login(loginRequest)
    }
    suspend fun addTask(addTaskRequest: AddTaskRequest): AddTaskResponse {
        return taskApi.addTask(addTaskRequest)
    }
    suspend fun updateTask(newCompletedStatus: Boolean, id: String): UpdateTaskResponse {
        return taskApi.updateTask(UpdateTaskRequest(completed = newCompletedStatus), id)
    }
    suspend fun deleteTask(id: String): UpdateTaskResponse {
        return taskApi.deleteTask(id)
    }
}
