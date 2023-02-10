package com.manriquetavi.jetmovieapp.presentation.screens.map

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): ViewModel() {

    var latitude by mutableStateOf<String?>(null)
        private set
    var longitude by mutableStateOf<String?>(null)
        private set
    init {
        latitude = savedStateHandle.get<String>("latitude")
        longitude = savedStateHandle.get<String>("longitude")
    }
}