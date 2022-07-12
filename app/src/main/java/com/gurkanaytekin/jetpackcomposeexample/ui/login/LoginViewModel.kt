package com.gurkanaytekin.jetpackcomposeexample.ui.login

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigator
import androidx.navigation.compose.rememberNavController
import com.gurkanaytekin.jetpackcomposeexample.data.api.model.LoginRequest
import com.gurkanaytekin.jetpackcomposeexample.data.api.model.Task
import com.gurkanaytekin.jetpackcomposeexample.data.api.model.User
import com.gurkanaytekin.jetpackcomposeexample.data.repository.TaskRepo
import com.gurkanaytekin.jetpackcomposeexample.jetpacknavigation.Screen
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
    private val taskRepo: TaskRepo,

) : ViewModel() {
    var navHostController: NavController? = null

    private var _state = mutableStateOf<User?>(null)
    val state: State<User?>
        get() = _state
    fun onUsernameChange(text: String) {
        _state.value?.email = text
    }
    fun setNavigation(navHostController: NavController) {
        this.navHostController = navHostController
    }

    fun singIn() {
        viewModelScope.launch {
            try {
                val login = taskRepo.login(LoginRequest("gurkanaytekin@yandex.com.tr", "12345678"))
                Log.d("login", login.toString())
                navHostController?.navigate(Screen.DetailScreen.passId(0))
            } catch (e: IOException) {
                Log.d("login", e.toString())
            }


        }
    }
}