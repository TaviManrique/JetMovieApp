package com.manriquetavi.jetmovieapp.data

import com.manriquetavi.jetmovieapp.domain.repository.DataStoreOperations
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataStoreRepository @Inject constructor(
    private val dataStore: DataStoreOperations
) {
    suspend fun saveOnBoardingPageState(isCompleted: Boolean) { dataStore.saveOnBoardingState(isCompleted = isCompleted) }
    fun readOnBoardingPageState(): Flow<Boolean> = dataStore.readOnBoardingState()
}