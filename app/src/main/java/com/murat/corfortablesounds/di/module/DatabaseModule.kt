package com.murat.corfortablesounds.di.module


import android.content.Context
import androidx.room.Room
import com.mrtteknology.app.kotlinkarikaturapp.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun getDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "example-db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}