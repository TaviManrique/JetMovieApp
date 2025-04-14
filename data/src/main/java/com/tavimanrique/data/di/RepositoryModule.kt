package com.tavimanrique.data.di

import android.content.Context
import com.tavimanrique.data.local.database.MovieDatabase
import com.tavimanrique.data.paging.MovieRemoteMediator
import com.tavimanrique.data.repository.MovieRepository
import com.tavimanrique.data.repository.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideMovieRepository(
        movieDatabase: MovieDatabase,
        movieRemoteMediator: MovieRemoteMediator,
        @ApplicationContext context: Context
    ): MovieRepository {
        return MovieRepositoryImpl(movieDatabase, movieRemoteMediator, context)
    }
}