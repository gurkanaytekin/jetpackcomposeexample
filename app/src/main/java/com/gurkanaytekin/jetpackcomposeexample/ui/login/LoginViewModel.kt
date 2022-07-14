package com.gurkanaytekin.jetpackcomposeexample.ui.login

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.gurkanaytekin.jetpackcomposeexample.data.api.model.LoginRequest
import com.gurkanaytekin.jetpackcomposeexample.data.api.model.User
import com.gurkanaytekin.jetpackcomposeexample.data.repository.TaskRepo
import com.gurkanaytekin.jetpackcomposeexample.jetpacknavigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

class UiState {
    lateinit var email : String
    lateinit var password: String
}


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val taskRepo: TaskRepo,
) : ViewModel() {
    var navHostController: NavController? = null

    var email by mutableStateOf("gurkanaytekin@yandex.com.tr")
    var password by mutableStateOf("12345678")
    var callServiceState by mutableStateOf(false)

    fun setNavigation(navHostController: NavController) {
        this.navHostController = navHostController
    }

    fun singIn() {
        viewModelScope.launch {
            try {
                callServiceState = true
                val login = taskRepo.login(LoginRequest(email, password))
                Log.d("login", login.toString())
                navHostController?.navigate(Screen.TaskListScreen.route)
            } catch (e: IOException) {
                Log.d("login", e.toString())
            } finally {
                callServiceState = false
            }
        }
    }
}