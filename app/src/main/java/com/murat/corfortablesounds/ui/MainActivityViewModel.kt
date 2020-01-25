package com.murat.corfortablesounds.ui

import android.app.Application
import androidx.databinding.ObservableField
import com.murat.corfortablesounds.App
import com.murat.corfortablesounds.core.BaseViewModel

class MainActivityViewModel(app: Application) : BaseViewModel(app) {

    var toolbarTitle: ObservableField<String> = ObservableField("")
    init {
        (app as? App)?.component?.inject(this)
    }
} 