package com.tavimanrique.data.remote.dto

data class UpcomingMoviesDto(
    val page: Int,
    val results: List<MovieDto>,
    val total_pages: Int,
    val total_results: Int
)

