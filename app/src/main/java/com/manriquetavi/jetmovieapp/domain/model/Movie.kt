package com.manriquetavi.jetmovieapp.domain.model

import androidx.room.PrimaryKey
import com.google.firebase.firestore.GeoPoint

data class Movie(
    @PrimaryKey(autoGenerate = false)
    val id: String? = "",
    val title: String? = "",
    val description: String? = "",
    val image: String? = "",
    val stars: String? = "",
    val geoPoint: GeoPoint? = null,
    val director: String? = ""
)