package com.manriquetavi.jetmovieapp.domain.repository

import com.manriquetavi.jetmovieapp.domain.model.Movie
import com.manriquetavi.jetmovieapp.domain.model.Response
import kotlinx.coroutines.flow.Flow

interface FirestoreDataSource {

    fun getAllMovies(): Flow<Response<List<Movie>?>>
    fun searchMovies(query: String): Flow<Response<List<Movie>?>>
    fun getSelectedMovie(movieId: String): Flow<Response<Movie?>>

}