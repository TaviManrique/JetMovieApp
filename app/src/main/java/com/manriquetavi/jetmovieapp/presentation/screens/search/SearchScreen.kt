package com.manriquetavi.jetmovieapp.presentation.screens.search

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.manriquetavi.jetmovieapp.common.components.CircularProgress
import com.manriquetavi.jetmovieapp.common.components.EmptyScreen
import com.manriquetavi.jetmovieapp.common.components.SearchIdleScreen
import com.manriquetavi.jetmovieapp.domain.model.Movie
import com.manriquetavi.jetmovieapp.domain.model.Response

@Composable
fun SearchScreen(
    movies: Response<List<Movie>?>,
    searchQuery: String,
    updateSearchQuery: (String) -> Unit,
    searchMovies: (String) -> Unit,
    navigateToDetail: (String) -> Unit,
    navigateToHome: () -> Unit,
) {
    Scaffold(
        topBar = {
            SearchTopBar(
                text = searchQuery,
                onTextChange = updateSearchQuery,
                searchMovies = searchMovies,
                navigateToHome = navigateToHome
            )
        }
    ) { paddingValues ->
        when(movies) {
            is Response.Loading -> CircularProgress()
            is Response.Success ->
                if (movies.data.isNullOrEmpty()) SearchIdleScreen(message = "Didn't find movies")
                else SearchContent(
                    movies = movies.data,
                    navigateToDetail = navigateToDetail,
                    paddingValues = paddingValues
                )
            is Response.Error -> EmptyScreen(message = movies.message)
            is Response.Idle -> SearchIdleScreen(message = "Search Movies")
        }
    }
}