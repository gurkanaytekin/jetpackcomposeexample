package com.gurkanaytekin.jetpackcomposeexample.ui.tasks

import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.gurkanaytekin.jetpackcomposeexample.data.api.model.AddTaskRequest
import com.gurkanaytekin.jetpackcomposeexample.data.api.model.Task
import com.gurkanaytekin.jetpackcomposeexample.data.api.model.UpdateTaskRequest
import com.gurkanaytekin.jetpackcomposeexample.data.api.model.User
import com.gurkanaytekin.jetpackcomposeexample.data.repository.TaskRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val taskRepo: TaskRepo,
) : ViewModel() {

    var navHostController: NavController? = null
    var tasks by mutableStateOf(emptyList<Task>())
    var newTask by mutableStateOf("")
    var dialogStatus by mutableStateOf(false)
    var selectedIdForDeletion: String by mutableStateOf("")
    var serviceCalling by mutableStateOf(false)

    fun setNavigation(navHostController: NavController) {
        this.navHostController = navHostController
    }
    fun onChangeCompletedStatus(id: String, completed: Boolean) {
        viewModelScope.launch {
            val resultTask = taskRepo.updateTask(completed, id)
            tasks = tasks.mapIndexed { _, task ->
                if(task.id == id) task.copy(completed = resultTask.data.completed)
                else task
            }
            Log.d("viewmodel", tasks.toString())
        }

    }

    fun deleteTask() {
        viewModelScope.launch {
            val deleteTaskResponse = taskRepo.deleteTask(selectedIdForDeletion)
            if(deleteTaskResponse.success) {
                tasks = tasks.filterIndexed{ _, task ->
                    task.id != selectedIdForDeletion
                }
                Log.d("viewmodel", tasks.toString())
            }
        }

    }

    fun addTask() {
        viewModelScope.launch {
            try {
                serviceCalling = true
                val newTaskResponse = taskRepo.addTask(AddTaskRequest(newTask))
                tasks = tasks + newTaskResponse.data
                Log.d("tasklist", tasks.toString())
            } catch (e: IOException) {
                Log.d("login", e.toString())
            } finally {
                serviceCalling = false
            }
        }
    }

    init {
        viewModelScope.launch {
            try {
                tasks = taskRepo.getTaskList().data
                Log.d("tasklist", tasks.toString())
            } catch (e: IOException) {
                Log.d("login", e.toString())
            }
        }
    }
}