package com.tavimanrique.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.tavimanrique.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    repository: MovieRepository
): ViewModel() {
    val movies: Flow<PagingData<com.tavimanrique.domain.model.Movie>> = repository
        .getMovies()
        .cachedIn(viewModelScope)
}