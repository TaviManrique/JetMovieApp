package com.tavimanrique.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tavimanrique.data.local.dao.MovieDao
import com.tavimanrique.data.local.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
