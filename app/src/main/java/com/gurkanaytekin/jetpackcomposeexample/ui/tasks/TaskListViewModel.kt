package com.gurkanaytekin.jetpackcomposeexample.ui.tasks

import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.gurkanaytekin.jetpackcomposeexample.data.api.model.AddTaskRequest
import com.gurkanaytekin.jetpackcomposeexample.data.api.model.Task
import com.gurkanaytekin.jetpackcomposeexample.data.api.model.User
import com.gurkanaytekin.jetpackcomposeexample.data.repository.TaskRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject
/*
interface ITaskListViewModel {
    var navHostController: NavController
    var tasks: List<Task>
    var navTask: String
    fun setNavigation(navHostController: NavController)
    fun changeCompleted(id: String, completed: Boolean)
    fun setNavigation(navHostController: NavController)
    fun changeCompleted(id: String, completed: Boolean)
}*/

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val taskRepo: TaskRepo,
) : ViewModel() {

    var navHostController: NavController? = null
    //var tasks = mutableStateListOf<Task>()
    var tasks by mutableStateOf(emptyList<Task>())
    var newTask by mutableStateOf("")

    fun setNavigation(navHostController: NavController) {
        this.navHostController = navHostController
    }
    fun changeCompleted(id: String, completed: Boolean) {
        //tasks?.find { it.id == id }?.completed = completed
        tasks = tasks.mapIndexed { index, task ->
            if(task.id == id) task.copy(completed = completed)
            else task
        }
        Log.d("viewmodel", tasks.toString())
    }

    fun addTask() {
        viewModelScope.launch {
            try {
                val newTaskResponse = taskRepo.addTask(AddTaskRequest(newTask))
                tasks = tasks + newTaskResponse.data
                Log.d("tasklist", tasks.toString())
            } catch (e: IOException) {
                Log.d("login", e.toString())
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