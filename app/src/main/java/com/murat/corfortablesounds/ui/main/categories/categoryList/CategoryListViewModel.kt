package com.murat.corfortablesounds.ui.main.categories.categoryList

import android.app.Application
import androidx.databinding.ObservableField
import com.murat.corfortablesounds.App
import com.murat.corfortablesounds.core.BaseViewModel


class CategoryListViewModel(app: Application) : BaseViewModel(app) {


    init {
        (app as? App)?.component?.inject(this)
    }
}