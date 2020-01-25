package com.murat.corfortablesounds.core

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.murat.corfortablesounds.R
import timber.log.Timber

abstract class BaseBottomSheetFragment<VM : BaseViewModel>(private val mViewModelClass: Class<VM>) : BottomSheetDialogFragment() {
    lateinit var viewModel: VM
    private var behavior: BottomSheetBehavior<FrameLayout>? = null
    private var rootHeight: Int = 0
    private var rootView: View? = null


    @LayoutRes
    abstract fun getLayoutRes(): Int


    open fun init() {}


    abstract fun setBehavior(behavior: BottomSheetBehavior<*>?)

    override fun show(manager: FragmentManager, tag: String?) {
        try {
            val ft = manager.beginTransaction()
            ft.add(this, tag)
            ft.commitAllowingStateLoss()
        } catch (e: Throwable) {
            Timber.d(e, "Exception")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
        viewModel = getViewM()
    }

    open fun refresh() {}

    open fun navigate(action: Int) {
        view?.let { _view ->
            Navigation.findNavController(_view).navigate(action)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val bottomSheet = dialog!!.findViewById<FrameLayout>(R.id.design_bottom_sheet)
        dialog?.setCancelable(false)
        dialog?.setCanceledOnTouchOutside(false)
        behavior = BottomSheetBehavior.from(bottomSheet)
        initViewModel()
        init()
        if (view != null) {
            addGlobaLayoutListener(view!!)
        }
        Handler().postDelayed({
            dialog?.setCancelable(true)
            dialog?.setCanceledOnTouchOutside(true)
        }, 500)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(getLayoutRes(), container, false)
        return rootView
    }

    abstract fun initViewModel()
    private fun getViewM(): VM = ViewModelProviders.of(this).get(mViewModelClass)
    open fun onInject() {}

    private fun addGlobaLayoutListener(view: View) {
        view.addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
            override fun onLayoutChange(v: View, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int) {
                if (rootView?.measuredHeight != 0) {
                    rootHeight = rootView!!.measuredHeight
                    setBehavior(behavior)
                    v.removeOnLayoutChangeListener(this)
                }
            }
        })
    }

    fun getRootHeight(): Int {
        return if (rootHeight != 0) rootHeight else measureAndGetRootHeight()
    }

    private fun measureAndGetRootHeight(): Int {
        rootView?.measure(0, 0)
        rootHeight = rootView!!.measuredHeight
        return rootHeight
    }

}
