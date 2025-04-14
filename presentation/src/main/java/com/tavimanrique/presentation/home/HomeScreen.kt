package com.tavimanrique.presentation.home

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.tavimanrique.domain.model.Movie
import androidx.paging.LoadState
import com.tavimanrique.presentation.common.MovieItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    movies: LazyPagingItems<Movie>,
    onProfileClick: () -> Unit,
    onMovieClick: (Int) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
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
                HomeContent(movies = movies, padding = padding, onMovieClick = onMovieClick)
            }
        }
    }
}

@Composable
fun HomeContent(
    padding: PaddingValues,
    movies: LazyPagingItems<Movie>,
    onMovieClick: (Int) -> Unit
) {
    val listState = rememberLazyListState()
    LaunchedEffect(Unit) {
        listState.scrollToItem(0)
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
            movies[index]?.let { MovieItem(movie = it, onClick = { onMovieClick(it.id) }) }
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
            else -> Unit
        }
    }
}
