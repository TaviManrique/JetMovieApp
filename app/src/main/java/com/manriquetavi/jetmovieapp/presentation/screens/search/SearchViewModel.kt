package com.manriquetavi.jetmovieapp.presentation.screens.search

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manriquetavi.jetmovieapp.domain.model.Movie
import com.manriquetavi.jetmovieapp.domain.model.Response
import com.manriquetavi.jetmovieapp.usecases.firestore.FirestoreUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val firestoreUseCases: FirestoreUseCases
): ViewModel() {

    var moviesResponse by mutableStateOf<Response<List<Movie>?>>(Response.Idle)
        private set

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun searchMovies(query: String) {
        moviesResponse = Response.Loading
        viewModelScope.launch(Dispatchers.IO) {
            firestoreUseCases.searchMoviesUseCase(query).collect {
                moviesResponse = it
            }
        }
    }
}