package com.tavimanrique.jetmovieapp.navigation

sealed class Screen(val route: String) {
    data object Login: Screen("login_screen")
    data object Home: Screen("home_screen")
    data object Profile: Screen("profile_screen")
    data object Detail: Screen("detail_screen/movie={movieId}") {
        fun createRoute(movieId: String): String {
            return "detail_screen/movie=$movieId"
        }
    }
}