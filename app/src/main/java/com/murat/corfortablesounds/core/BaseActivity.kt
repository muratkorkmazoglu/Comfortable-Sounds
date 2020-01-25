package com.murat.corfortablesounds.core

import android.app.Dialog
import android.content.SharedPreferences
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders
import com.murat.corfortablesounds.utils.AppProgress
import javax.inject.Inject

abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding>(private val mViewModelClass: Class<VM>) : AppCompatActivity() {

    @LayoutRes
    abstract fun getLayoutRes(): Int

    @Inject
    lateinit var pref: SharedPreferences

    var dialog: Dialog? = null

    val binding by lazy {
        DataBindingUtil.setContentView(this, getLayoutRes()) as DB
    }

    val viewModel by lazy {
        ViewModelProviders.of(this).get(mViewModelClass)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel(viewModel)
        initDialog()
    }

    /**
     *
     *  You need override this method.
     *  And you need to set viewModel to binding: binding.viewModel = viewModel
     *
     */

    abstract fun initViewModel(viewModel: VM)

    fun isShow(): Boolean? {
        return dialog?.isShowing
    }

    private fun initDialog() {
        dialog = AppProgress.progressDialog(this)
    }

    fun showProgress() {
        runOnUiThread {
            dialog?.show()
        }
    }

    fun hideProgress() {
        runOnUiThread {
            dialog?.dismiss()
        }
    }
}