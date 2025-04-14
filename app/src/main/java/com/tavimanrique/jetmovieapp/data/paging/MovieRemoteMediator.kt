package com.tavimanrique.jetmovieapp.data.paging

import android.content.Context
import android.content.SharedPreferences
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.tavimanrique.jetmovieapp.data.local.database.MovieDatabase
import com.tavimanrique.jetmovieapp.data.local.entity.MovieEntity
import com.tavimanrique.jetmovieapp.data.local.mapper.toEntity
import com.tavimanrique.jetmovieapp.data.remote.api.MovieApi
import com.tavimanrique.jetmovieapp.data.util.isNetworkAvailable
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import javax.inject.Inject
import androidx.core.content.edit

@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator @Inject constructor(
    private val api: MovieApi,
    private val db: MovieDatabase,
    @ApplicationContext private val context: Context
) : RemoteMediator<Int, MovieEntity>() {

    private val movieDao = db.movieDao()
    private val currentPageKey = "movie_last_updated"
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("movie_prefs", Context.MODE_PRIVATE)
    private var currentPage = 1

    override suspend fun initialize(): InitializeAction {
        val currentTime = System.currentTimeMillis()
        val lastUpdated = sharedPreferences.getLong(currentPageKey, 0L)
        val diffInMinutes = (currentTime - lastUpdated) / 60000
        val cacheTimeoutMinutes = 1440L
        return if (diffInMinutes <= cacheTimeoutMinutes) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    currentPage = 1
                    currentPage
                }
                LoadType.APPEND -> currentPage + 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            }

            if (!isNetworkAvailable(context)) {
                return if (loadType == LoadType.REFRESH) {
                    MediatorResult.Error(IOException("Problems With The Internet Connection"))
                } else {
                    MediatorResult.Success(endOfPaginationReached = true)
                }
            }

            val response = api.getUpcomingMovies(page = page)
            val movies = response.results.map { it.toEntity() }

            db.withTransaction {
                if (loadType == LoadType.REFRESH && movies.isNotEmpty()) {
                    movieDao.clearAll()
                }
                movieDao.insertAll(movies)
            }
            if (loadType == LoadType.REFRESH) {
                sharedPreferences.edit { putLong(currentPageKey, System.currentTimeMillis()) }
            }
            val endReached = response.page >= response.total_pages
            if (loadType == LoadType.APPEND && !endReached) {
                currentPage = page
            }
            MediatorResult.Success(endOfPaginationReached = endReached)

        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}