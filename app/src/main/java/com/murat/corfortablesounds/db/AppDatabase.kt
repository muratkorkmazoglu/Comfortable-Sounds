package com.mrtteknology.app.kotlinkarikaturapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mrtteknology.app.kotlinkarikaturapp.db.dao.ComicsDao
import com.mrtteknology.app.kotlinkarikaturapp.db.entities.ComicsEntity
import com.mrtteknology.app.kotlinkarikaturapp.db.entities.TokenEntity

@Database(
    entities = [
        ComicsEntity::class,
        TokenEntity::class
    ],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun comicsDao(): ComicsDao
}
