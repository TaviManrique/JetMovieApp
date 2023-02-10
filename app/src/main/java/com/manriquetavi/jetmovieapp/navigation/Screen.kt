package com.manriquetavi.jetmovieapp.navigation

sealed class Screen(val route: String) {
    object Welcome: Screen(route = "welcome_screen")
    object Home: Screen(route = "home_screen")
    object Search: Screen(route = "search_screen")
    object Detail: Screen(route = "detail_screen/{movieId}") {
        fun passDetailId(movieId: String) = "detail_screen/$movieId"
    }
    object Map: Screen(route = "map_screen/{latitude}/{longitude}") {
        fun passLatitudeLongitude(latitude: String, longitude: String) = "map_screen/$latitude/$longitude"
    }
}