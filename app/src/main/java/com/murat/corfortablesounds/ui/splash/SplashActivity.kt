package com.murat.corfortablesounds.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import com.murat.corfortablesounds.ui.MainActivity
import com.murat.corfortablesounds.R
import com.murat.corfortablesounds.core.BaseActivity
import com.murat.corfortablesounds.databinding.ActivitySplashBinding


class SplashActivity :
    BaseActivity<SplashActivityViewModel, ActivitySplashBinding>(SplashActivityViewModel::class.java) {
    override fun initViewModel(viewModel: SplashActivityViewModel) {
        binding.viewModel = viewModel
    }

    override fun getLayoutRes() = R.layout.activity_splash

    lateinit var handler: Handler

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        super.onCreate(savedInstanceState)
        handler = Handler()

        handler.postDelayed(
            {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            },
            1000
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(null)
    }
}