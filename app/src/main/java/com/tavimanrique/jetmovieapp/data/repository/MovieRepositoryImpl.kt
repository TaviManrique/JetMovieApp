package com.tavimanrique.jetmovieapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.tavimanrique.jetmovieapp.data.local.database.MovieDatabase
import com.tavimanrique.jetmovieapp.data.local.mapper.toDomain
import com.tavimanrique.jetmovieapp.data.paging.MovieRemoteMediator
import com.tavimanrique.jetmovieapp.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class MovieRepositoryImpl @Inject constructor(
    private val movieDatabase: MovieDatabase,
    private val remoteMediator: MovieRemoteMediator
) : MovieRepository {

    override fun getMovies(): Flow<PagingData<Movie>> {
        val pagingSourceFactory = { movieDatabase.movieDao().getAllMovies() }

        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            remoteMediator = remoteMediator,
            pagingSourceFactory = pagingSourceFactory
        ).flow
            .map { pagingData ->
                pagingData.map { it.toDomain() }
            }
    }

    override suspend fun getMovieById(id: Int): Movie? {
        return movieDatabase.movieDao().getMovieById(id)?.toDomain()
    }
}