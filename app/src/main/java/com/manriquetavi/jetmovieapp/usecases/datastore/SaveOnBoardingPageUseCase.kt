package com.manriquetavi.jetmovieapp.usecases.datastore

import com.manriquetavi.jetmovieapp.data.DataStoreRepository

class SaveOnBoardingPageUseCase(
    private val dataStoreRepository: DataStoreRepository
) {
    suspend operator fun invoke(isCompleted: Boolean) = dataStoreRepository.saveOnBoardingPageState(isCompleted)
}