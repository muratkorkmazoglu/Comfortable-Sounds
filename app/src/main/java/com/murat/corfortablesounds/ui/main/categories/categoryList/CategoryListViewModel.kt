package com.murat.corfortablesounds.ui.main.categories.categoryList

import android.annotation.SuppressLint
import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.murat.corfortablesounds.App
import com.murat.corfortablesounds.core.BaseViewModel
import com.murat.corfortablesounds.core.Resource
import com.murat.corfortablesounds.core.Status
import com.murat.corfortablesounds.service.response.JsonData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber


class CategoryListViewModel(app: Application) : BaseViewModel(app) {
    val getJsonData = MutableLiveData<Resource<List<JsonData>>>()
    var categoryItem = ObservableField<JsonData>()
    var position = -1

    init {
        (app as? App)?.component?.inject(this)
    }

    fun setCategoryListItem(item: JsonData, position: Int) {
        categoryItem.set(item)
        this.position = position
    }

    @SuppressLint("CheckResult")
    fun getSoundList(){
        baseApi.getJsonData()
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

}