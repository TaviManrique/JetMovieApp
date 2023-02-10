package com.manriquetavi.jetmovieapp.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.accompanist.pager.ExperimentalPagerApi
import com.manriquetavi.jetmovieapp.presentation.screens.detail.DetailScreen
import com.manriquetavi.jetmovieapp.presentation.screens.detail.DetailViewModel
import com.manriquetavi.jetmovieapp.presentation.screens.home.HomeScreen
import com.manriquetavi.jetmovieapp.presentation.screens.home.HomeViewModel
import com.manriquetavi.jetmovieapp.presentation.screens.map.MapScreen
import com.manriquetavi.jetmovieapp.presentation.screens.map.MapViewModel
import com.manriquetavi.jetmovieapp.presentation.screens.search.SearchScreen
import com.manriquetavi.jetmovieapp.presentation.screens.search.SearchViewModel
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
            },
            navigateToDetail = {
                navController.navigate(Screen.Detail.passDetailId(movieId = it))
            }
        )
        searchRoute(
            navigateToDetail = {
                navController.navigate(Screen.Detail.passDetailId(movieId = it))
            },
            navigateToHome = {
                navController.popBackStack()
            }
        )
        detailRoute(
            navigateToMap = { latitude, longitude ->
                navController.navigate(Screen.Map.passLatitudeLongitude(latitude, longitude))
            },
            navigateBack = {
                navController.popBackStack()
            }
        )
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
    navigateToSearch: () -> Unit,
    navigateToDetail: (String) -> Unit
) {
    composable(route = Screen.Home.route) {
        val homeViewModel: HomeViewModel = hiltViewModel()
        val movies = homeViewModel.moviesResponse
        HomeScreen(
            navigateToSearch = navigateToSearch,
            navigateToDetail = navigateToDetail,
            movies = movies
        )
    }
}

fun NavGraphBuilder.searchRoute(
    navigateToDetail: (String) -> Unit,
    navigateToHome: () -> Unit
) {
    composable(route = Screen.Search.route) {
        val searchViewModel: SearchViewModel = hiltViewModel()
        val movies = searchViewModel.moviesResponse
        val searchQuery = searchViewModel.searchQuery.value


        SearchScreen(
            movies = movies,
            searchQuery = searchQuery,
            updateSearchQuery = { searchViewModel.updateSearchQuery(it) },
            searchMovies =  { searchViewModel.searchMovies(it) },
            navigateToDetail = navigateToDetail,
            navigateToHome = navigateToHome
        )
    }
}

fun NavGraphBuilder.detailRoute(
    navigateToMap: (String, String) -> Unit,
    navigateBack: () -> Unit
) {
    composable(
        route = Screen.Detail.route,
        arguments = listOf(
            navArgument(name = "movieId") {
                type = NavType.StringType
                nullable = false
            }
        )
    ) {
        val detailViewModel: DetailViewModel = hiltViewModel()
        val movie by detailViewModel.selectedMovie.collectAsState()
        DetailScreen(
            movie = movie,
            navigateToMap = navigateToMap,
            navigateBack = navigateBack
        )
    }
}

fun NavGraphBuilder.mapRoute() {
    composable(
        route = Screen.Map.route,
        arguments = listOf(
            navArgument(name = "latitude") {
                type = NavType.StringType
                nullable = false
            },
            navArgument(name = "longitude") {
                type = NavType.StringType
                nullable = false
            }
        )
    ) {
        val mapViewModel: MapViewModel = hiltViewModel()
        val latitude = mapViewModel.latitude
        val longitude = mapViewModel.longitude
        MapScreen(
            latitude = latitude,
            longitude = longitude
        )
    }
}