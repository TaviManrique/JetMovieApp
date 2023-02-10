package com.manriquetavi.jetmovieapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.manriquetavi.jetmovieapp.data.DataStoreImpl
import com.manriquetavi.jetmovieapp.domain.repository.DataStoreOperations
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideDataStorePreferences(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(produceFile = { context.preferencesDataStoreFile("jet_movie_preferences") })
    }

    @Provides
    @Singleton
    fun provideDataStoreOperations(dataStore: DataStore<Preferences>): DataStoreOperations {
        return DataStoreImpl(dataStore = dataStore)
    }
}