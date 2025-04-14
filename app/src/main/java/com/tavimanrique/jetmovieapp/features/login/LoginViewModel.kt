package com.tavimanrique.jetmovieapp.features.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tavimanrique.jetmovieapp.domain.usecases.ValidatePasswordUseCase
import com.tavimanrique.jetmovieapp.util.PasswordResultParser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val validatePasswordUseCase: ValidatePasswordUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _eventFlow = MutableSharedFlow<LoginSingleEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: LoginUiEvent) {
        when(event) {
            is LoginUiEvent.EmailChanged -> {
                _uiState.value = _uiState.value.copy(email = event.email)
            }
            is LoginUiEvent.PasswordChanged -> {
                _uiState.value = _uiState.value.copy(password = event.password)
            }
            is LoginUiEvent.ValidateLogin -> {
                val passwordResult = validatePasswordUseCase(_uiState.value.password)
                showError(message = PasswordResultParser.parseErrorMessage(passwordResult))
                if (_uiState.value.errorMessage.isNullOrEmpty()) {
                    simulateCallServiceLogin(
                        email = _uiState.value.email,
                        password = _uiState.value.password
                    )
                }


            }
            is LoginUiEvent.DismissError -> {
                _uiState.value = _uiState.value.copy(
                    showErrorDialog = false,
                    errorMessage = null
                )
            }
        }
    }

    private fun showError(message: String?) {
        _uiState.value = _uiState.value.copy(
            errorMessage = message,
            showErrorDialog = !message.isNullOrEmpty()
        )
    }

    private fun simulateCallServiceLogin(
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            delay(2000)
            val success = (email == "Admin" && password == "Password*123")
            if (success) {
                _eventFlow.emit(LoginSingleEvent.NavigateToMain)
            } else {
                showError("Login failed. Please check your credentials and try again.")
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }
    }
}