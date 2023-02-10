package com.manriquetavi.jetmovieapp.presentation.screens.home

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
class HomeViewModel @Inject constructor(
    private val firestoreUseCases: FirestoreUseCases
): ViewModel() {

    var moviesResponse by mutableStateOf<Response<List<Movie>?>>(Response.Idle)
        private set

    init {
        getAllMovies()
    }

    private fun getAllMovies() {
        moviesResponse = Response.Loading
        viewModelScope.launch(Dispatchers.IO) {
            firestoreUseCases.getAllMoviesUseCase().collect {
                moviesResponse = it
            }
        }
    }
}