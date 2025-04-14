package com.tavimanrique.jetmovieapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import com.tavimanrique.jetmovieapp.features.detail.DetailScreen
import com.tavimanrique.jetmovieapp.features.detail.DetailViewModel
import com.tavimanrique.jetmovieapp.features.home.HomeScreen
import com.tavimanrique.jetmovieapp.features.home.HomeViewModel

@Composable
fun SetUpNavGraph(
    startDestination: String,
    rootNavController: NavHostController
) {
    NavHost(
        navController = rootNavController,
        route = Graph.ROOT,
        startDestination = startDestination
    ) {
        authNavGraph(rootNavController)
        composable(route = Screen.Home.route) {
            val viewModel: HomeViewModel = hiltViewModel()
            val movies = viewModel.movies.collectAsLazyPagingItems()
            HomeScreen(
                movies = movies,
                onProfileClick = { rootNavController.navigate(Screen.Profile.route) },
                onMovieClick = { rootNavController.navigate(Screen.Detail.createRoute(it)) }
            )
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument("movieId") {
                    type = NavType.IntType
                }
            )
        ) {
            val viewModel: DetailViewModel = hiltViewModel()
            val uiState = viewModel.uiState.collectAsStateWithLifecycle()
            DetailScreen(
                uiState = uiState.value,
                onBackClick = {
                    if (rootNavController.canGoBack) rootNavController.popBackStack()
                }
            )
        }
    }

}

val NavHostController.canGoBack: Boolean
    get() = this.currentBackStackEntry?.getLifecycle()?.currentState == Lifecycle.State.RESUMED

object Graph{
    const val ROOT = "root_graph"
    const val AUTH = "auth_graph"
}