package com.gurkanaytekin.jetpackcomposeexample.jetpacknavigation

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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gurkanaytekin.jetpackcomposeexample.R

@Composable
fun LoginScreen() {
    var emailValue by rememberSaveable { mutableStateOf("") }
    var passwordValue by rememberSaveable { mutableStateOf("") }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text(text = "Login")
        Row(modifier = Modifier
            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        }
        OutlinedTextFieldComposable(emailValue, onInputChanged = { emailValue = it }, label = "Username", leadingIcon = Icons.Filled.Email, keyboardType = KeyboardType.Email)
        OutlinedTextFieldComposable(passwordValue, onInputChanged = { passwordValue = it }, label = "Password", leadingIcon = Icons.Filled.Lock, keyboardType = KeyboardType.Password)
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Sıgn In")
        }
    }
}

@Composable
fun OutlinedTextFieldComposable(value: String, onInputChanged: (String) -> Unit, label: String, leadingIcon: ImageVector, keyboardType: KeyboardType = KeyboardType.Text) {
    val showPassword = remember { mutableStateOf(false) }
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        value = value,
        onValueChange = onInputChanged,
        label = { Text(label) },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = ImeAction.None),
        leadingIcon = {
            IconButton(onClick = {}) {
                Icon(imageVector = leadingIcon, contentDescription = "Email Icon")
            }
        },
        visualTransformation =
            if(keyboardType == KeyboardType.Password) {
                if (showPassword.value) VisualTransformation.None else PasswordVisualTransformation()
            } else VisualTransformation.None,

        trailingIcon = {
            if(keyboardType == KeyboardType.Password) {
                IconButton(onClick = {
                    showPassword.value = !showPassword.value
                }) {
                    Icon(imageVector = if(showPassword.value) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown, contentDescription = "Email Icon")
                }
            } else null
        },
    )
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}