package com.gurkanaytekin.jetpackcomposeexample.jetpacknavigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gurkanaytekin.jetpackcomposeexample.ui.login.LoginScreen
import androidx.hilt.navigation.compose.hiltViewModel
import com.gurkanaytekin.jetpackcomposeexample.ui.login.LoginViewModel
import java.util.*

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.LoginScreen.route) {
        composable(route = Screen.LoginScreen.route) {
            val parentEntry = remember(it) {
                navController.getBackStackEntry(Screen.LoginScreen.route)
            }

            val viewModel = hiltViewModel<LoginViewModel>(parentEntry)
            LoginScreen(viewModel, navController)
        }
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController)
        }
        composable(route = Screen.DetailScreen.route, arguments = listOf(
            navArgument("id") {
                type = NavType.IntType
            }
        )) {
            val id = it.arguments?.getInt("id")?: 0;
            Log.d("DetailScreen", "Parametre $id")
            DetailScreen(id)
        }
    }
}


