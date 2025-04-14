package com.tavimanrique.jetmovieapp.features.login

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val showErrorDialog: Boolean = false
)