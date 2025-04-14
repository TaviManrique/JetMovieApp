package com.tavimanrique.jetmovieapp.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.tavimanrique.jetmovieapp.features.login.LoginScreen
import com.tavimanrique.jetmovieapp.features.login.LoginSingleEvent
import com.tavimanrique.jetmovieapp.features.login.LoginViewModel

fun NavGraphBuilder.authNavGraph(
    rootNavController: NavHostController
) {
    navigation(
        route = Graph.AUTH,
        startDestination = Screen.Login.route
    ) {
        composable(route = Screen.Login.route) {
            val viewModel: LoginViewModel = hiltViewModel()
            val uiState = viewModel.uiState.collectAsStateWithLifecycle()
            LaunchedEffect(Unit) {
                viewModel.eventFlow.collect { event ->
                    when (event) {
                        is LoginSingleEvent.NavigateToMain -> {
                            rootNavController.navigate(Graph.DRAWER) {
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
    }
}