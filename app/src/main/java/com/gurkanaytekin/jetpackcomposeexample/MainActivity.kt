package com.gurkanaytekin.jetpackcomposeexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.gurkanaytekin.jetpackcomposeexample.components.AppBar
import com.gurkanaytekin.jetpackcomposeexample.components.BottomBar
import com.gurkanaytekin.jetpackcomposeexample.jetpacknavigation.Navigation
import com.gurkanaytekin.jetpackcomposeexample.ui.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            Scaffold(topBar = { AppBar(navController) },
                bottomBar = {
                    BottomBar(navController = navController)
                }) {
                Box(modifier = Modifier.padding(it)) {
                    Navigation(navController)
                }
            }
        }
    }
}
