package com.gurkanaytekin.jetpackcomposeexample.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable

@Composable
fun TextFieldAlternative(value: String = "", onInputChanged: (String) -> Unit,) {
    Column {
        TextField(
            value = value,
            onValueChange = onInputChanged
        )
    }
}