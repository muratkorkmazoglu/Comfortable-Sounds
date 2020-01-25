package com.murat.corfortablesounds.ui.main.categories.categoryList

import android.annotation.SuppressLint
import android.app.Application
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
    val getJsonData = MutableLiveData<Resource<JsonData>>()

    init {
        (app as? App)?.component?.inject(this)
    }

    @SuppressLint("CheckResult")
    fun getSoundList(){
        baseApi.getJsonData()
            .subscribeOn(Schedulers.io())
            .map { Resource.success(it) }
            .onErrorReturn { Resource.error(it) }
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