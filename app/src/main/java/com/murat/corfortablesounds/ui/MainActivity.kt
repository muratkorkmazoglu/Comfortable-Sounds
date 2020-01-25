package com.murat.corfortablesounds.ui

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.murat.corfortablesounds.R
import com.murat.corfortablesounds.core.BaseActivity
import com.murat.corfortablesounds.databinding.ActivityMainBinding
import com.murat.corfortablesounds.utils.extensions.hide
import com.murat.corfortablesounds.utils.extensions.show
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.defaultSharedPreferences
import org.jetbrains.anko.sdk27.coroutines.onClick
import timber.log.Timber


class MainActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding>(MainActivityViewModel::class.java) {
    override fun getLayoutRes() = R.layout.activity_main

    override fun initViewModel(viewModel: MainActivityViewModel) {
        binding.viewModel = viewModel
    }

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        super.onCreate(savedInstanceState)
        setTransparentStatusBar()
        setupNavigation()

    }

    private fun setupNavigation() {
        val navController = findNavController(R.id.container_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.favoritesFragment,
                R.id.categoriesFragment,
                R.id.categoryListFragment
            )
        )
        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration)
        binding.bottomNavigation.itemIconTintList = null
        binding.bottomNavigation.setupWithNavController(navController)
        binding.bottomNavigation.clipChildren = false
        binding.bottomNavigation.setOnNavigationItemReselectedListener {
            when (NavHostFragment.findNavController(container_fragment).currentDestination?.id) {
                R.id.favoritesFragment -> Timber.v("Reselect blocked.")
                R.id.categoriesFragment -> Timber.v("Reselect blocked.")
                else -> NavigationUI.onNavDestinationSelected(it, navController)
            }
        }

        binding.backButton.onClick {
            onBackPressed()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        navController.addOnDestinationChangedListener { _, _, _ ->
            val currentDestination = NavHostFragment.findNavController(container_fragment).currentDestination
            when (currentDestination?.id) {
                R.id.favoritesFragment -> {
                    binding.appBarLayout.show()
                    binding.toolbar.title = ""
                    binding.bottomNavigation.show()
                    binding.backButton.hide()
                    viewModel.toolbarTitle.set(currentDestination.label.toString())
                }
                R.id.categoriesFragment -> {
                    binding.appBarLayout.show()
                    binding.toolbar.title = ""
                    binding.bottomNavigation.show()
                    viewModel.toolbarTitle.set(currentDestination.label.toString())
                    binding.backButton.hide()
                }
                R.id.categoryListFragment -> {
                    binding.appBarLayout.show()
                    binding.toolbar.title = ""
                    binding.bottomNavigation.show()
                    viewModel.toolbarTitle.set(currentDestination.label.toString())
                    binding.backButton.hide()
                }
                else -> {
                    binding.toolbar.title = ""
                    binding.appBarLayout.show()
                    viewModel.toolbarTitle.set(currentDestination?.label.toString())
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.container_fragment)
        NavigationUI.onNavDestinationSelected(item, navController)
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.container_fragment)
        return navController.navigateUp()
    }

    private fun setTransparentStatusBar() {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                statusBarColor = Color.TRANSPARENT
            }
        }
    }
}