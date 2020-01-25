package com.murat.corfortablesounds.di.component

import android.content.Context
import android.content.SharedPreferences
import com.murat.corfortablesounds.App
import com.murat.corfortablesounds.di.module.ApplicationModule
import com.murat.corfortablesounds.di.module.DatabaseModule
import com.murat.corfortablesounds.di.module.NetModule
import com.murat.corfortablesounds.ui.MainActivityViewModel
import com.murat.corfortablesounds.ui.main.categories.CategoriesViewModel
import com.murat.corfortablesounds.ui.main.categories.categoryList.CategoryListViewModel
import com.murat.corfortablesounds.ui.main.favorites.FavoritesViewModel
import com.murat.corfortablesounds.ui.splash.SplashActivityViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton

@Component(modules = arrayOf(ApplicationModule::class, NetModule::class, DatabaseModule::class))

interface ApplicationComponent {
    fun app(): App
    fun context(): Context
    fun preferences(): SharedPreferences
    fun inject(mainActivityViewModel: MainActivityViewModel)
    fun inject(splashActivityViewModel: SplashActivityViewModel)
    fun inject(categoriesFragmentViewModel: CategoriesViewModel)
    fun inject(favoritesViewModel: FavoritesViewModel)
    fun inject(categoryListViewModel: CategoryListViewModel)
}
