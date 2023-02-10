package com.manriquetavi.jetmovieapp.presentation.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import com.manriquetavi.jetmovieapp.common.components.MovieItem
import com.manriquetavi.jetmovieapp.domain.model.Movie
import com.manriquetavi.jetmovieapp.ui.theme.MEDIUM_PADDING

@Composable
fun HomeContent(
    movies: List<Movie>?,
    navigateToDetail: (String) -> Unit,
    paddingValues: PaddingValues
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        contentPadding = PaddingValues(all = MEDIUM_PADDING),
        verticalArrangement = Arrangement.spacedBy(MEDIUM_PADDING)
    ) {
        movies?.let {
            items(movies) { movie ->
                MovieItem(
                    movie = movie,
                    navigateToDetail = navigateToDetail
                )
            }
        }
    }
}