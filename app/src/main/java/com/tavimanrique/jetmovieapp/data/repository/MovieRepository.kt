package com.tavimanrique.jetmovieapp.data.repository

import androidx.paging.PagingData
import com.tavimanrique.jetmovieapp.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(): Flow<PagingData<Movie>>
    suspend fun getMovieById(id: Int): Movie?
}
