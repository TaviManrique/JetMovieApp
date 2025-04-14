package com.tavimanrique.jetmovieapp.di

import android.content.Context
import androidx.room.Room
import com.tavimanrique.jetmovieapp.data.local.database.MovieDatabase
import com.tavimanrique.jetmovieapp.data.paging.MovieRemoteMediator
import com.tavimanrique.jetmovieapp.data.repository.MovieRepository
import com.tavimanrique.jetmovieapp.data.repository.MovieRepositoryImpl
import com.tavimanrique.jetmovieapp.domain.usecases.ValidatePasswordUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideValidatePasswordUseCase(): ValidatePasswordUseCase {
        return ValidatePasswordUseCase()
    }

    @Provides
    @Singleton
    fun provideMovieRepository(
        movieDatabase: MovieDatabase,
        movieRemoteMediator: MovieRemoteMediator,
        @ApplicationContext context: Context
    ): MovieRepository {
        return MovieRepositoryImpl(movieDatabase, movieRemoteMediator, context)
    }

    @Provides
    @Singleton
    fun provideMovieDatabase(
        @ApplicationContext context: Context
    ): MovieDatabase {
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            "movie_database"
        ).build()
    }

    @Provides
    fun provideMovieDao(database: MovieDatabase) = database.movieDao()

}