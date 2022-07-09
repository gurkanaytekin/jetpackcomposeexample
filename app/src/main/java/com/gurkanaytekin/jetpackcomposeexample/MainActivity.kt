package com.gurkanaytekin.jetpackcomposeexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.gurkanaytekin.jetpackcomposeexample.components.AppBar
import com.gurkanaytekin.jetpackcomposeexample.jetpacknavigation.Navigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppBar()
            Navigation()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Navigation()
}