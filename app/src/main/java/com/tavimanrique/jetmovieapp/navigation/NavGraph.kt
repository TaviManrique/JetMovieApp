package com.tavimanrique.jetmovieapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import com.tavimanrique.presentation.detail.DetailScreen
import com.tavimanrique.presentation.detail.DetailViewModel
import com.tavimanrique.presentation.home.HomeScreen
import com.tavimanrique.presentation.home.HomeViewModel
import com.tavimanrique.presentation.login.LoginScreen
import com.tavimanrique.presentation.login.LoginSingleEvent
import com.tavimanrique.presentation.login.LoginViewModel
import com.tavimanrique.presentation.profile.ProfileScreen
import com.tavimanrique.presentation.profile.ProfileViewModel

@Composable
fun SetUpNavGraph(
    startDestination: String,
    rootNavController: NavHostController
) {
    NavHost(
        navController = rootNavController,
        startDestination = startDestination
    ) {
        composable(route = Screen.Login.route) {
            val viewModel: LoginViewModel = hiltViewModel()
            val uiState = viewModel.uiState.collectAsStateWithLifecycle()
            LaunchedEffect(Unit) {
                viewModel.eventFlow.collect { event ->
                    when (event) {
                        is LoginSingleEvent.NavigateToMain -> {
                            rootNavController.navigate(Screen.Home.route) {
                                popUpTo(Screen.Login.route) { inclusive = true }
                            }
                        }
                    }
                }
            }
            LoginScreen(
                state = uiState.value,
                onEvent = viewModel::onEvent
            )
        }
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
                onBackClick = { if (rootNavController.canGoBack) rootNavController.popBackStack() }
            )
        }
        composable(route = Screen.Profile.route) {
            val viewModel: ProfileViewModel = hiltViewModel()
            val uiState = viewModel.uiState.collectAsStateWithLifecycle()
            ProfileScreen(
                uiState = uiState.value,
                onLogoutClick = {
                    viewModel.logout {
                        rootNavController.navigate(Screen.Login.route) {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                },
                onBackClick = { if (rootNavController.canGoBack) rootNavController.popBackStack() }
            )
        }
    }

}

val NavHostController.canGoBack: Boolean
    get() = this.currentBackStackEntry?.getLifecycle()?.currentState == Lifecycle.State.RESUMED
