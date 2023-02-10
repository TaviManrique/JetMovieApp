package com.manriquetavi.jetmovieapp.usecases.firestore

import com.manriquetavi.jetmovieapp.data.FirestoreDataRepository

class SearchMoviesUseCase(
    private val firestoreDataRepository: FirestoreDataRepository
) {
    operator fun invoke(query: String) = firestoreDataRepository.searchMovies(query = query)
}