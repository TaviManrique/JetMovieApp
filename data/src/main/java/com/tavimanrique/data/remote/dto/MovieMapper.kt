package com.tavimanrique.data.remote.dto

import com.tavimanrique.domain.model.Movie

fun MovieDto.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        description = overview,
        imageUrl = "https://image.tmdb.org/t/p/w500${poster_path.orEmpty()}",
        releaseDate = release_date.orEmpty(),
        rating = vote_average
    )
}
