package com.gurkanaytekin.jetpackcomposeexample.ui.example

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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gurkanaytekin.jetpackcomposeexample.components.OutlinedTextFieldComposable

@Composable
fun ExampleScreen(navController: NavController) {
    var email by mutableStateOf("gurkanaytekin@yandex.com.tr")
    var password by mutableStateOf("12345678")
    var callServiceState by mutableStateOf(false)
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Login")
        OutlinedTextFieldComposable(email, onInputChanged = { email = it}, label = "Username", leadingIcon = Icons.Filled.Email, keyboardType = KeyboardType.Email)
        OutlinedTextFieldComposable(password, onInputChanged = {  password = it  }, label = "Password", leadingIcon = Icons.Filled.Lock, keyboardType = KeyboardType.Password)
        Button(
            modifier = Modifier.padding(top = 5.dp),
            enabled = !callServiceState,
            onClick = {}
        ) {
            Text(text = "Sign In")
        }
        Text(text = "Şifremi Unuttum")
    }
}


@Preview(showBackground = true)
@Composable
fun ExampleScreenPreview() {
    ExampleScreen(rememberNavController())
}