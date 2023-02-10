package com.manriquetavi.jetmovieapp.usecases.firestore

import com.manriquetavi.jetmovieapp.data.FirestoreDataRepository

class GetAllMoviesUseCase(
    private val firestoreDataRepository: FirestoreDataRepository
) {
    operator fun invoke() = firestoreDataRepository.getAllMovies()
}