package com.tavimanrique.presentation.detail

import com.tavimanrique.domain.model.Movie

sealed interface DetailUiState {
    data object Loading: DetailUiState
    data class Error(val errorMessage: String): DetailUiState
    data class Success(val movie: Movie): DetailUiState
}