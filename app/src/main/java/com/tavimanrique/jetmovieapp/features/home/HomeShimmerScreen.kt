package com.tavimanrique.jetmovieapp.features.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tavimanrique.jetmovieapp.common.MovieItemShimmer

@Composable
fun HomeShimmerScreen(
    padding: PaddingValues,
) {
    LazyColumn(
        contentPadding = padding,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(20) {
            MovieItemShimmer()
        }
    }
}

