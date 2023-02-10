package com.manriquetavi.jetmovieapp.di

import com.google.firebase.firestore.FirebaseFirestore
import com.manriquetavi.jetmovieapp.data.FirestoreDataImpl
import com.manriquetavi.jetmovieapp.domain.repository.FirestoreDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirestoreModule {

    @Provides
    @Singleton
    fun provideFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideFirestoreRepositoryImpl(firestore: FirebaseFirestore): FirestoreDataSource {
        return FirestoreDataImpl(firestore = firestore)
    }

}