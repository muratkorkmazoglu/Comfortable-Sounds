package com.murat.corfortablesounds.core

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.FirebaseDatabase
import com.mrtteknology.app.kotlinkarikaturapp.db.AppDatabase
import javax.inject.Inject

open class BaseViewModel(app: Application) : RxAwareViewModel(app) {

    @Inject
    lateinit var pref: SharedPreferences

    @Inject
    lateinit var db: AppDatabase

    var progressLiveData = MutableLiveData<Boolean>()
}
