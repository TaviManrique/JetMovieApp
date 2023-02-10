package com.manriquetavi.jetmovieapp.presentation.screens.detail

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.manriquetavi.jetmovieapp.common.components.CircularProgress
import com.manriquetavi.jetmovieapp.common.components.EmptyScreen
import com.manriquetavi.jetmovieapp.domain.model.Movie
import com.manriquetavi.jetmovieapp.domain.model.Response

@Composable
fun DetailScreen(
    movie: Response<Movie?>,
    navigateToMap: (String, String) -> Unit,
    navigateBack: () -> Unit
) {
    when(movie) {
        is Response.Loading -> CircularProgress()
        is Response.Success ->
            if (movie.data != null) {
                Scaffold(
                    topBar = {
                        DetailTopBar(
                            latitude = movie.data.geoPoint?.latitude,
                            longitude = movie.data.geoPoint?.longitude,
                            navigateToMap = navigateToMap,
                            navigateBack = navigateBack
                        )
                    }
                ) { paddingValues ->
                    DetailContent(
                        movie = movie.data,
                        paddingValues = paddingValues
                    )
                }
            } else {
                EmptyScreen(message = "No Data")
            }
        is Response.Error -> EmptyScreen(message = movie.message)
        is Response.Idle -> {}
    }
}