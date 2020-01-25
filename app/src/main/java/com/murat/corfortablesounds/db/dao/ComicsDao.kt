package com.mrtteknology.app.kotlinkarikaturapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.mrtteknology.app.kotlinkarikaturapp.db.entities.ComicsEntity
import com.mrtteknology.app.kotlinkarikaturapp.db.entities.TokenEntity

@Dao
interface ComicsDao {

    @Insert(onConflict = REPLACE)
    fun insertComic(comics: ComicsEntity)

    @Query("SELECT * FROM Comic")
    fun getLikesComics(): LiveData<List<ComicsEntity>>

    @Query("DELETE FROM Comic WHERE id = :comicId")
    fun deleteComic(comicId: Int)

    @Query("SELECT * FROM Comic WHERE id = :comicId")
    fun getComic(comicId: Int): ComicsEntity?

    @Insert(onConflict = REPLACE)
    fun insertToken(token: TokenEntity?)

    @Query("SELECT * FROM Token")
    fun getToken(): TokenEntity?
}