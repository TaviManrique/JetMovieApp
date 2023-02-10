package com.manriquetavi.jetmovieapp.usecases.firestore


data class FirestoreUseCases(
    val getAllMoviesUseCase: GetAllMoviesUseCase,
    val searchMoviesUseCase: SearchMoviesUseCase
)