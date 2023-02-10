package com.manriquetavi.jetmovieapp.usecases.firestore

import com.manriquetavi.jetmovieapp.data.FirestoreDataRepository

class GetSelectedMovieUseCase(
    private val firestoreDataRepository: FirestoreDataRepository
) {
    operator fun invoke(movieId: String) = firestoreDataRepository.getSelectedMovie(movieId = movieId)
}