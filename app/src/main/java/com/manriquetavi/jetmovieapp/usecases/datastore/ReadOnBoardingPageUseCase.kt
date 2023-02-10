package com.manriquetavi.jetmovieapp.usecases.datastore

import com.manriquetavi.jetmovieapp.data.DataStoreRepository
import kotlinx.coroutines.flow.Flow

class ReadOnBoardingPageUseCase(
    private val dataStoreRepository: DataStoreRepository
) {
    operator fun invoke(): Flow<Boolean> = dataStoreRepository.readOnBoardingPageState()
}