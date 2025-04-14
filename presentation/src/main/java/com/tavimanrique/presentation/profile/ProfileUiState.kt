package com.tavimanrique.presentation.profile

sealed interface ProfileUiState {
    data object Loading: ProfileUiState
    data object Idle: ProfileUiState
}