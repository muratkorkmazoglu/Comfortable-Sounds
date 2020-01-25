package com.murat.corfortablesounds.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.murat.corfortablesounds.db.dao.SoundsDao
import com.murat.corfortablesounds.db.entities.SoundsEntitiy


@Database(
    entities = [
        SoundsEntitiy::class
    ],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun soundsDao(): SoundsDao
}
