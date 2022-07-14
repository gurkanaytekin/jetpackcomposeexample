package com.gurkanaytekin.jetpackcomposeexample.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TextFieldAlternative(value: String = "", onInputChanged: (String) -> Unit,) {
    Column {
        TextField(
            value = value,
            onValueChange = onInputChanged
        )
        Text("The textfield has this text: " + value)
    }
}