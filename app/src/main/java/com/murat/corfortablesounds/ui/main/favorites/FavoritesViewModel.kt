package com.murat.corfortablesounds.ui.main.favorites

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.murat.corfortablesounds.App
import com.murat.corfortablesounds.core.BaseViewModel
import com.murat.corfortablesounds.db.entities.SoundsEntitiy
import org.jetbrains.anko.doAsync

class FavoritesViewModel(app: Application) : BaseViewModel(app) {
    var favoritesList = MutableLiveData<List<SoundsEntitiy>>()
    var favoritesItem = ObservableField<SoundsEntitiy>()
    var position = -1

    init {
        (app as? App)?.component?.inject(this)
    }

    fun setFavoritesItem(item: SoundsEntitiy, position: Int) {
        favoritesItem.set(item)
        this.position = position
    }

    fun getFavorites() {
        doAsync {
            if (db.soundsDao().getSounds().isNotEmpty()){
                favoritesList.postValue(db.soundsDao().getSounds())
            }
        }

    }
}