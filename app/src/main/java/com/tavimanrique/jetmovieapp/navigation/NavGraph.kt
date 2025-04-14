package com.tavimanrique.jetmovieapp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tavimanrique.jetmovieapp.features.main.MainScreen

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
        composable(route = Graph.DRAWER) {
            MainScreen(rootNavController = rootNavController)
        }
    }

}

val NavHostController.canGoBack: Boolean
    get() = this.currentBackStackEntry?.getLifecycle()?.currentState == Lifecycle.State.RESUMED

object Graph{
    const val ROOT = "root_graph"
    const val AUTH = "auth_graph"
    const val DRAWER = "drawer_graph"
}