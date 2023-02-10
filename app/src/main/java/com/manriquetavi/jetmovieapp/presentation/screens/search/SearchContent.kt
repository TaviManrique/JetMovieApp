package com.manriquetavi.jetmovieapp.presentation.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.manriquetavi.jetmovieapp.common.components.MovieItem
import com.manriquetavi.jetmovieapp.domain.model.Movie
import com.manriquetavi.jetmovieapp.ui.theme.SMALL_PADDING

@Composable
fun SearchContent(
    movies: List<Movie>,
    navigateToDetail: (String) -> Unit,
    paddingValues: PaddingValues
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        contentPadding = PaddingValues(all = SMALL_PADDING),
        verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
    ) {
        items(movies) { movie ->
            MovieItem(
                movie = movie,
                navigateToDetail = navigateToDetail
            )
        }
    }
}