package com.manriquetavi.jetmovieapp.di

import com.manriquetavi.jetmovieapp.data.DataStoreRepository
import com.manriquetavi.jetmovieapp.data.FirestoreDataRepository
import com.manriquetavi.jetmovieapp.usecases.datastore.DataStoreUseCases
import com.manriquetavi.jetmovieapp.usecases.datastore.ReadOnBoardingPageUseCase
import com.manriquetavi.jetmovieapp.usecases.datastore.SaveOnBoardingPageUseCase
import com.manriquetavi.jetmovieapp.usecases.firestore.FirestoreUseCases
import com.manriquetavi.jetmovieapp.usecases.firestore.GetAllMoviesUseCase
import com.manriquetavi.jetmovieapp.usecases.firestore.SearchMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Provides
    @Singleton
    fun provideDataStoreUseCases(dataStoreRepository: DataStoreRepository): DataStoreUseCases {
        return DataStoreUseCases(
            saveOnBoardingPageUseCase = SaveOnBoardingPageUseCase(dataStoreRepository),
            readOnBoardingPageUseCase = ReadOnBoardingPageUseCase(dataStoreRepository)
        )
    }

    @Provides
    @Singleton
    fun provideFirestoreUseCases(firestoreDataRepository: FirestoreDataRepository): FirestoreUseCases {
        return FirestoreUseCases(
            getAllMoviesUseCase = GetAllMoviesUseCase(firestoreDataRepository),
            searchMoviesUseCase = SearchMoviesUseCase(firestoreDataRepository)
        )
    }
}