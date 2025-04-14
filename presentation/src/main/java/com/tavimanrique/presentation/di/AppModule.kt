package com.tavimanrique.presentation.di

import com.tavimanrique.domain.usecases.ValidatePasswordUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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

}