package com.manriquetavi.jetmovieapp.presentation.screens.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manriquetavi.jetmovieapp.usecases.datastore.DataStoreUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val dataStoreUseCases: DataStoreUseCases
): ViewModel() {
    fun saveOnBoardingPageState(isCompleted: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreUseCases.saveOnBoardingPageUseCase(isCompleted)
        }
    }
}