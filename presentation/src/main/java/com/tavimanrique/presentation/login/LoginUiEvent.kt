package com.tavimanrique.presentation.login

sealed interface LoginUiEvent {
    data class EmailChanged(val email: String) : LoginUiEvent
    data class PasswordChanged(val password: String) : LoginUiEvent
    data object ValidateLogin : LoginUiEvent
    data object DismissError : LoginUiEvent
}

sealed class LoginSingleEvent {
    data object NavigateToMain : LoginSingleEvent()
}