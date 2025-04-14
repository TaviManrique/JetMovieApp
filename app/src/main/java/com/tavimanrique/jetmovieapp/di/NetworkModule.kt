package com.tavimanrique.jetmovieapp.di

import com.tavimanrique.jetmovieapp.data.remote.api.MovieApi
import com.tavimanrique.jetmovieapp.data.remote.interceptor.ApiRequestInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private const val BEARER_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxYTBhNmYxOGVlYmQ5ZTBlODFjMDNlZDk5YjM0MDNkYiIsIm5iZiI6MTY3NTk4MDk5Ni45NDEsInN1YiI6IjYzZTU3MGM0YzJmZjNkMDBmMzE2MTI2NyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Imb7Z-wZijXNawTjYJDHX0VPPgGqpTNJkHOy508o5zU"

    @Provides
    fun provideAuthInterceptor(): Interceptor = ApiRequestInterceptor(BEARER_TOKEN)

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun provideMovieApi(retrofit: Retrofit): MovieApi =
        retrofit.create(MovieApi::class.java)
}

