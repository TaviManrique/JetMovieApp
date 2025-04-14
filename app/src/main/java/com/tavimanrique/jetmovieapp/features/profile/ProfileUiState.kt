package com.tavimanrique.jetmovieapp.features.profile

sealed interface ProfileUiState {
    data object Loading: ProfileUiState
    data object Idle: ProfileUiState
}