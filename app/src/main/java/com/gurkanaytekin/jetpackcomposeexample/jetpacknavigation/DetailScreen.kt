package com.gurkanaytekin.jetpackcomposeexample.jetpacknavigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DetailScreen(id: Int) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Merhaba Detay $id")
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    DetailScreen(id = 10)
}