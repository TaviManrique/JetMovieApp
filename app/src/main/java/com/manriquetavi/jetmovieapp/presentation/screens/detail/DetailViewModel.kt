package com.manriquetavi.jetmovieapp.presentation.screens.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manriquetavi.jetmovieapp.domain.model.Movie
import com.manriquetavi.jetmovieapp.domain.model.Response
import com.manriquetavi.jetmovieapp.usecases.firestore.FirestoreUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val firestoreUseCases: FirestoreUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _selectedMovie: MutableStateFlow<Response<Movie?>> = MutableStateFlow(Response.Idle)
    val selectedMovie: StateFlow<Response<Movie?>>  = _selectedMovie

    init {
        _selectedMovie.value = Response.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val movieId = savedStateHandle.get<String>("movieId")
            movieId?.let {
                firestoreUseCases.getSelectedMovieUseCase(movieId = movieId).collect {
                    _selectedMovie.value = it
                }
            }
        }
    }
}