package com.murat.corfortablesounds

import com.google.firebase.FirebaseApp
import com.murat.corfortablesounds.di.component.DaggerApplicationComponent
import com.murat.corfortablesounds.di.module.ApplicationModule

class App : android.app.Application() {

    val component by lazy {
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}
