package com.tavimanrique.data.repository

import androidx.paging.PagingData
import com.tavimanrique.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(): Flow<PagingData<Movie>>
    suspend fun getMovieById(id: Int): Movie?
    suspend fun clearUserData()
}
