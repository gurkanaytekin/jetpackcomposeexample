package com.gurkanaytekin.jetpackcomposeexample.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.gurkanaytekin.jetpackcomposeexample.jetpacknavigation.Screen


@Composable
fun BottomBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    if(currentDestination?.route == null || currentDestination?.route == Screen.LoginScreen.route) {
        null
    } else {
        BottomAppBar { /* Bottom app bar content */ }
    }

}

@Preview(showBackground = true)
@Composable
fun BottomBarPreview() {
    AppBar(rememberNavController())
}