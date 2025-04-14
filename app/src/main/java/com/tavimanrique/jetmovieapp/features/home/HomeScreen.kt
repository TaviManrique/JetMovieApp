package com.tavimanrique.jetmovieapp.features.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.tavimanrique.jetmovieapp.common.MovieItem
import com.tavimanrique.jetmovieapp.domain.model.Movie
import androidx.paging.LoadState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    movies: LazyPagingItems<Movie>,
    onProfileClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Movies") },
                actions = {
                    IconButton(onClick = onProfileClick) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Profile Icon in HomeScreen"
                        )
                    }
                }
            )
        }
    ) { padding ->
        when {
            movies.loadState.refresh is LoadState.Loading && movies.itemCount == 0 -> {
                Box(Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            movies.loadState.refresh is LoadState.Error && movies.itemCount == 0 -> {
                val error = (movies.loadState.refresh as LoadState.Error).error
                Box(Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                    Text("Error: ${error.localizedMessage}")
                }
            }
            else -> {
                HomeContent(movies = movies, padding = padding)
            }
        }
    }
}

@Composable
fun HomeContent(
    padding: PaddingValues,
    movies: LazyPagingItems<Movie>,
) {
    val listState = rememberLazyListState()
    val hasScrolledToTop = rememberSaveable { mutableStateOf(false) }
    LaunchedEffect(movies.loadState.refresh) {
        if (
            movies.loadState.refresh is LoadState.NotLoading &&
            movies.itemCount > 0 &&
            !hasScrolledToTop.value
        ) {
            listState.scrollToItem(0)
            hasScrolledToTop.value = true
        }
    }
    LazyColumn(
        state = listState,
        contentPadding = padding,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(
            count = movies.itemCount,
            key = { index ->
                movies[index]?.id ?: index
            }
        ) { index ->
            movies[index]?.let { MovieItem(movie = it) }
        }
        when (movies.loadState.append) {
            LoadState.Loading -> {
                item {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
            }
            is LoadState.Error -> {
                val error = (movies.loadState.append as LoadState.Error).error
                item {
                    Text(
                        text = "Error: ${error.localizedMessage}",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
            is LoadState.NotLoading -> { Unit }
        }
    }
}
