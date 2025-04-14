package com.tavimanrique.data.local.mapper

import com.tavimanrique.data.local.entity.MovieEntity
import com.tavimanrique.data.remote.dto.MovieDto
import com.tavimanrique.domain.model.Movie

fun MovieEntity.toDomain() = Movie(
    id = id,
    title = title,
    description = overview,
    imageUrl = "https://image.tmdb.org/t/p/w500$posterPath",
    releaseDate = releaseDate.orEmpty(),
    rating = rating
)

fun MovieDto.toEntity() = MovieEntity(
    id = id,
    title = title,
    overview = overview,
    posterPath = poster_path.orEmpty(),
    releaseDate = release_date.orEmpty(),
    rating = vote_average
)