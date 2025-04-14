package com.tavimanrique.data.repository

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.tavimanrique.domain.model.Movie
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import androidx.core.content.edit
import com.tavimanrique.data.local.database.MovieDatabase
import com.tavimanrique.data.local.mapper.toDomain
import com.tavimanrique.data.paging.MovieRemoteMediator

@OptIn(ExperimentalPagingApi::class)
class MovieRepositoryImpl @Inject constructor(
    private val movieDatabase: MovieDatabase,
    private val remoteMediator: MovieRemoteMediator,
    @ApplicationContext private val context: Context
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

    override suspend fun clearUserData() {
        movieDatabase.movieDao().clearAll()
        context.getSharedPreferences("movie_prefs", Context.MODE_PRIVATE)
            .edit { clear() }
    }
}