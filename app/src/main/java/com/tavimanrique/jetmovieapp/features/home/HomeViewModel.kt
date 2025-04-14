package com.tavimanrique.jetmovieapp.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.tavimanrique.jetmovieapp.data.repository.MovieRepository
import com.tavimanrique.jetmovieapp.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    repository: MovieRepository
): ViewModel() {
    val movies: Flow<PagingData<Movie>> = repository
        .getMovies()
        .cachedIn(viewModelScope)
}