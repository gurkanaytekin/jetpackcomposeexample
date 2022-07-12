package com.gurkanaytekin.jetpackcomposeexample.components

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun AppBar() {
    TopAppBar(
        title = { Text(text = "AppBar") }
    )
}

@Preview(showBackground = true)
@Composable
fun AppBarPreview() {
    AppBar()
}