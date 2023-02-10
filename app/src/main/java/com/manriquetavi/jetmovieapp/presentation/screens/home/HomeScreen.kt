package com.manriquetavi.jetmovieapp.presentation.screens.home

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.manriquetavi.jetmovieapp.common.components.CircularProgress
import com.manriquetavi.jetmovieapp.common.components.EmptyScreen
import com.manriquetavi.jetmovieapp.domain.model.Movie
import com.manriquetavi.jetmovieapp.domain.model.Response

@Composable
fun HomeScreen(
    movies: Response<List<Movie>?>,
    navigateToSearch: () -> Unit,
    navigateToDetail: (String) -> Unit
) {
    Scaffold(
        topBar = {
            HomeTopBar(
                onSearchClicked = navigateToSearch
            )
        }
    ) { paddingValues ->
        when(movies) {
            is Response.Loading -> CircularProgress()
            is Response.Success -> HomeContent(
                movies = movies.data,
                navigateToDetail = navigateToDetail,
                paddingValues = paddingValues
            )
            is Response.Error -> EmptyScreen(message = movies.message)
            is Response.Idle -> { }
        }
    }
}