package com.manriquetavi.jetmovieapp.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.accompanist.pager.ExperimentalPagerApi
import com.manriquetavi.jetmovieapp.presentation.screens.detail.DetailScreen
import com.manriquetavi.jetmovieapp.presentation.screens.home.HomeScreen
import com.manriquetavi.jetmovieapp.presentation.screens.home.HomeViewModel
import com.manriquetavi.jetmovieapp.presentation.screens.map.MapScreen
import com.manriquetavi.jetmovieapp.presentation.screens.welcome.WelcomeScreen
import com.manriquetavi.jetmovieapp.presentation.screens.welcome.WelcomeViewModel

@ExperimentalPagerApi
@ExperimentalFoundationApi
@Composable
fun SetUpNavGraph(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        welcomeRoute(
            navigateToHome = {
                navController.popBackStack()
                navController.navigate(Screen.Home.route)
            }
        )
        homeRoute(
            navigateToSearch = {
                navController.navigate(Screen.Search.route)
            }
        )
        detailRoute()
        mapRoute()
    }
}

@ExperimentalPagerApi
fun NavGraphBuilder.welcomeRoute(
    navigateToHome: () -> Unit
) {
    composable(route = Screen.Welcome.route) {
        val welcomeViewModel: WelcomeViewModel = hiltViewModel()
        WelcomeScreen(
            navigateToHome = navigateToHome,
            saveOnBoardingPageState = { welcomeViewModel.saveOnBoardingPageState(isCompleted = true) }
        )
    }
}

fun NavGraphBuilder.homeRoute(
    navigateToSearch: () -> Unit
) {
    composable(route = Screen.Home.route) {
        val homeViewModel: HomeViewModel = hiltViewModel()
        val movies = homeViewModel.moviesResponse
        HomeScreen(
            navigateToSearch = navigateToSearch,
            movies = movies
        )
    }
}

fun NavGraphBuilder.detailRoute() {
    composable(
        route = Screen.Detail.route,
        arguments = listOf(navArgument(name = "movieId") {
            type = NavType.StringType
            nullable = false
        })
    ) {
        DetailScreen()
    }
}

fun NavGraphBuilder.mapRoute() {
    composable(route = Screen.Map.route) {
        MapScreen()
    }
}