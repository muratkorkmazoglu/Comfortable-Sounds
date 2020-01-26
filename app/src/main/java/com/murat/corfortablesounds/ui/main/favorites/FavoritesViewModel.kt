package com.murat.corfortablesounds.ui.main.favorites

import android.app.Application
import android.media.AudioManager
import android.media.MediaPlayer
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.murat.corfortablesounds.App
import com.murat.corfortablesounds.core.BaseViewModel
import com.murat.corfortablesounds.db.entities.SoundsEntitiy
import org.jetbrains.anko.doAsync
import java.io.IOException

class FavoritesViewModel(app: Application) : BaseViewModel(app) {
    var favoritesList = MutableLiveData<List<SoundsEntitiy>>()
    var favoritesItem = ObservableField<SoundsEntitiy>()
    var position = -1
    val mediaPlayer = MediaPlayer()
    var isCreated = false

    init {
        (app as? App)?.component?.inject(this)
    }

    fun setFavoritesItem(item: SoundsEntitiy, position: Int) {
        favoritesItem.set(item)
        this.position = position
    }

    fun getFavorites() {
        doAsync {
            if (db.soundsDao().getSounds().isNotEmpty()) {
                favoritesList.postValue(db.soundsDao().getSounds())
            }
        }

    }

    fun removeFavorites(item: SoundsEntitiy) {
        doAsync {
            db.soundsDao().deleteSounds(item.id)
            getFavorites()
        }
    }

    fun handlePlayer(item: SoundsEntitiy) {
        if (!isCreated) {
            try {
                mediaPlayer.setDataSource(item.mp3)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            try {
                mediaPlayer.prepareAsync()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            isCreated = true
        }

    }

    fun startMedia() {
        mediaPlayer.setOnPreparedListener {
            mediaPlayer.start()
        }
    }

    fun stopMedia() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
            mediaPlayer.release()
            isCreated = false
        }

    }
}