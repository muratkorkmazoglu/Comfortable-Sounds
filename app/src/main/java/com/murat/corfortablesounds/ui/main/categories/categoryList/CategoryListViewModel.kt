package com.murat.corfortablesounds.ui.main.categories.categoryList

import android.annotation.SuppressLint
import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.murat.corfortablesounds.App
import com.murat.corfortablesounds.core.BaseViewModel
import com.murat.corfortablesounds.core.Resource
import com.murat.corfortablesounds.core.Status
import com.murat.corfortablesounds.db.entities.SoundsEntitiy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.doAsync
import timber.log.Timber

class CategoryListViewModel(app: Application) : BaseViewModel(app) {
    val getJsonData = MutableLiveData<Resource<List<SoundsEntitiy>>>()
    var categoryItem = ObservableField<SoundsEntitiy>()
    var position = -1

    init {
        (app as? App)?.component?.inject(this)
    }

    fun setCategoryListItem(item: SoundsEntitiy, position: Int) {
        categoryItem.set(item)
        this.position = position
    }

    fun getSoundList(categoryName: String) {
        when (categoryName) {
            "birds" -> {
                getBirdsSound()
            }
            "piano" -> {
                getPianoSound()
            }
            "nature" -> {
                getNatureSound()
            }
        }
    }
    @SuppressLint("CheckResult")
    private fun getNatureSound() {
        baseApi.getNatureSounds()
            .subscribeOn(Schedulers.io())
            .map { Resource.success(it) }
            .onErrorReturn { Resource.error(it) }
            .doOnSubscribe { progressLiveData.postValue(true) }
            .doOnTerminate { progressLiveData.postValue(false) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it?.status) {
                    Status.SUCCESS -> getJsonData.postValue(it)
                    Status.LOADING -> ""
                    Status.ERROR -> Timber.e(it.error)
                }
            }
    }

    @SuppressLint("CheckResult")
    private fun getPianoSound() {
        baseApi.getPianoSounds()
            .subscribeOn(Schedulers.io())
            .map { Resource.success(it) }
            .onErrorReturn { Resource.error(it) }
            .doOnSubscribe { progressLiveData.postValue(true) }
            .doOnTerminate { progressLiveData.postValue(false) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it?.status) {
                    Status.SUCCESS -> getJsonData.postValue(it)
                    Status.LOADING -> ""
                    Status.ERROR -> Timber.e(it.error)
                }
            }
    }

    @SuppressLint("CheckResult")
    private fun getBirdsSound() {
        baseApi.getBirdsSound()
            .subscribeOn(Schedulers.io())
            .map { Resource.success(it) }
            .onErrorReturn { Resource.error(it) }
            .doOnSubscribe { progressLiveData.postValue(true) }
            .doOnTerminate { progressLiveData.postValue(false) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it?.status) {
                    Status.SUCCESS -> getJsonData.postValue(it)
                    Status.LOADING -> ""
                    Status.ERROR -> Timber.e(it.error)
                }
            }
    }

    fun saveFavorite(item: SoundsEntitiy) {
        doAsync {
            db.soundsDao().insertSound(item)
        }
    }

    fun deleteFavorite(item: SoundsEntitiy) {
        doAsync {
            db.soundsDao().deleteSounds(item.id)
        }
    }

}