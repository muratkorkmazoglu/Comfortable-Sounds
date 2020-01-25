package com.murat.corfortablesounds.ui.splash

import android.app.Application
import com.murat.corfortablesounds.App
import com.murat.corfortablesounds.core.BaseViewModel

class SplashActivityViewModel(app: Application) : BaseViewModel(app) {

    init {
        (app as? App)?.component?.inject(this)
    }


}