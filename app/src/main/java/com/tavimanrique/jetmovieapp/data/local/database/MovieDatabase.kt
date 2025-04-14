package com.tavimanrique.jetmovieapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tavimanrique.jetmovieapp.data.local.dao.MovieDao
import com.tavimanrique.jetmovieapp.data.local.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
