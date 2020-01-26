package com.murat.corfortablesounds.db.dao

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.murat.corfortablesounds.db.entities.SoundsEntitiy

@Dao
interface SoundsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSound(data: SoundsEntitiy)

    @Query("SELECT * FROM Sounds")
    fun getSounds(): List<SoundsEntitiy>

    @Query("DELETE FROM Sounds WHERE id = :id")
    fun deleteSounds(id: Int)

    @Query("SELECT * FROM Sounds WHERE id = :id")
    fun getSound(id: Int): SoundsEntitiy?
}