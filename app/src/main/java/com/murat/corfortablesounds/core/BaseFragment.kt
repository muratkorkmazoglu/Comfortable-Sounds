package com.murat.corfortablesounds.core

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation


abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding>(private val mViewModelClass: Class<VM>) : Fragment() {
    lateinit var viewModel: VM
    open lateinit var mBinding: DB
    var dialog: Dialog? = null

    fun init(inflater: LayoutInflater, container: ViewGroup) {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)
    }
    open fun init() {}
    @LayoutRes
    abstract fun getLayoutRes(): Int

    abstract fun initViewModel()

    private fun getViewM(): VM = ViewModelProviders.of(this).get(mViewModelClass)
    open fun onInject() {}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewM()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        init(inflater, container!!)
        initViewModel()
        init()

        super.onCreateView(inflater, container, savedInstanceState)
        return mBinding.root
    }

    open fun refresh() {}

    open fun navigate(action: Int) {
        view?.let { _view ->
            Navigation.findNavController(_view).navigate(action)
        }
    }

    fun showProgress() {
        activity?.runOnUiThread {
            if (activity != null)
                if ((activity as? BaseActivity<*, *>)?.isShow() == false)
                    (activity as? BaseActivity<*, *>)?.dialog?.show()
        }
    }

    fun hideProgress() {
        activity?.runOnUiThread {
            if (activity != null)
                (activity as BaseActivity<*, *>).dialog?.dismiss()
        }
    }

}