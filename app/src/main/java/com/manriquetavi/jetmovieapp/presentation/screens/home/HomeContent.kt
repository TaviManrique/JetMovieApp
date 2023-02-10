package com.manriquetavi.jetmovieapp.presentation.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import com.manriquetavi.jetmovieapp.common.components.HomeMovieItem
import com.manriquetavi.jetmovieapp.domain.model.Movie
import com.manriquetavi.jetmovieapp.ui.theme.SMALL_PADDING

@Composable
fun HomeContent(
    movies: List<Movie>?,
    navigateToSearch: () -> Unit,
    paddingValues: PaddingValues
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        contentPadding = PaddingValues(all = SMALL_PADDING),
        verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
    ) {
        movies?.let {
            items(movies) { movie ->
                HomeMovieItem(
                    movie = movie,
                    navigateToSearch = navigateToSearch
                )
            }
        }
    }
}