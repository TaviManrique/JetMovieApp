package com.manriquetavi.jetmovieapp.presentation.screens.splash

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manriquetavi.jetmovieapp.navigation.Screen
import com.manriquetavi.jetmovieapp.usecases.datastore.DataStoreUseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val dataStoreUseCases: DataStoreUseCases
): ViewModel() {

    var isLoading by mutableStateOf(true)
        private set

    var startDestination by mutableStateOf(Screen.Welcome.route)
        private set

    init {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreUseCases.readOnBoardingPageUseCase().collect { isCompleted ->
                startDestination = if (isCompleted) Screen.Home.route else Screen.Welcome.route
            }
            isLoading = false
        }
    }
}