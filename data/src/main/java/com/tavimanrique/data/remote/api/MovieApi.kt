package com.tavimanrique.data.remote.api

import com.tavimanrique.data.remote.dto.UpcomingMoviesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("page") page: Int,
        @Query("language") language: String = "en-US"
    ): UpcomingMoviesDto
}
