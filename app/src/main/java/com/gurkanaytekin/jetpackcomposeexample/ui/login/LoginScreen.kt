package com.gurkanaytekin.jetpackcomposeexample.ui.login

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gurkanaytekin.jetpackcomposeexample.components.OutlinedTextFieldComposable

@Composable
fun LoginScreen(viewModel: LoginViewModel, navController: NavController) {
    viewModel.setNavigation(navController)
    Log.d("LoginScrre", viewModel.email)
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text(text = "Login")
        Row(modifier = Modifier
            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        }
        OutlinedTextFieldComposable(viewModel.email, onInputChanged = { viewModel.email = it}, label = "Username", leadingIcon = Icons.Filled.Email, keyboardType = KeyboardType.Email)
        OutlinedTextFieldComposable(viewModel.password, onInputChanged = {  viewModel.password = it  }, label = "Password", leadingIcon = Icons.Filled.Lock, keyboardType = KeyboardType.Password)
        Button(
            enabled = !viewModel.callServiceState,
            onClick = {
                viewModel.singIn()
            }
        ) {
            Text(text = "Sıgn In")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    val viewModel = hiltViewModel<LoginViewModel>()
    LoginScreen(viewModel, rememberNavController())
}