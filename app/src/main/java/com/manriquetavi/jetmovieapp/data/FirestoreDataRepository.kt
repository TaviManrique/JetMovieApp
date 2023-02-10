package com.manriquetavi.jetmovieapp.data

import com.manriquetavi.jetmovieapp.domain.model.Movie
import com.manriquetavi.jetmovieapp.domain.model.Response
import com.manriquetavi.jetmovieapp.domain.repository.FirestoreDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FirestoreDataRepository @Inject constructor(
    private val firestoreDataSource: FirestoreDataSource
) {
    fun getAllMovies(): Flow<Response<List<Movie>?>> = firestoreDataSource.getAllMovies()
    fun searchMovies(query: String): Flow<Response<List<Movie>?>> = firestoreDataSource.searchMovies(query = query)
}