package com.tavimanrique.jetmovieapp.features.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tavimanrique.jetmovieapp.data.repository.MovieRepository
import com.tavimanrique.jetmovieapp.features.detail.DetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: MovieRepository
): ViewModel(){

    private val _uiState = MutableStateFlow<ProfileUiState>(ProfileUiState.Idle)
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    fun logout(onComplete: () -> Unit) {
        viewModelScope.launch {
            _uiState.value = ProfileUiState.Loading
            try {
                repository.clearUserData()
                onComplete()
            } catch (e: Exception) {
                _uiState.value = ProfileUiState.Idle
            }
        }
    }

}