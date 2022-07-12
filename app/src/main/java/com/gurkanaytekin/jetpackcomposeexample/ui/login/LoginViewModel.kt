package com.gurkanaytekin.jetpackcomposeexample.ui.login

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gurkanaytekin.jetpackcomposeexample.data.api.model.LoginRequest
import com.gurkanaytekin.jetpackcomposeexample.data.api.model.Task
import com.gurkanaytekin.jetpackcomposeexample.data.api.model.User
import com.gurkanaytekin.jetpackcomposeexample.data.repository.TaskRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val taskRepo: TaskRepo
) : ViewModel() {
    private var _state = MutableStateFlow<User?>(null)
    val state: MutableStateFlow<User?>
        get() = _state
    init {
        viewModelScope.launch {
            try {
                val taskRepo = taskRepo.login(LoginRequest("gurkanaytekin@yandex.com.tr", "12345678"))
                _state.value = taskRepo.user
                Log.d("login", taskRepo.toString())
            } catch (e: IOException) {
                Log.d("login", e.toString())
            }

        }
    }
}