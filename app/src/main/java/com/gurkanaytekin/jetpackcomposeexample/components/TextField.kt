package com.gurkanaytekin.jetpackcomposeexample.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController


@Composable
fun OutlinedTextFieldComposable(value: String?, onInputChanged: (String) -> Unit, label: String, leadingIcon: ImageVector, keyboardType: KeyboardType = KeyboardType.Text) {
    val showPassword = remember { mutableStateOf(false) }
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        value = value?: "",
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
fun OutlinedTextFieldComposablePreview() {
    OutlinedTextFieldComposable("Deneme", onInputChanged = {}, label = "Label", leadingIcon = Icons.Filled.Email)
}