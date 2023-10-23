package com.gurkanaytekin.jetpackcomposeexample.jetpacknavigation

sealed class Screen(val route: String) {
    object LoginScreen : Screen(route = "login_screen")
    object MainScreen : Screen(route = "main_screen")
    object TaskListScreen : Screen(route = "task_list_screen")
    object DetailScreen : Screen(route = "detail_screen?id={id}") {
        fun passId(id: Int = 0): String {
            return "detail_screen?id=${id}"
        }
    }
    object HITHScreen : Screen(route = "heroes_in_turkish_history")
}
