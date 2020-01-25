package com.murat.corfortablesounds.ui.main.favorites

import android.app.Application
import androidx.databinding.ObservableField
import com.murat.corfortablesounds.App
import com.murat.corfortablesounds.core.BaseViewModel

class FavoritesViewModel(app: Application) : BaseViewModel(app) {

    var toolbarTitle: ObservableField<String> = ObservableField("")
    init {
        (app as? App)?.component?.inject(this)
    }
}