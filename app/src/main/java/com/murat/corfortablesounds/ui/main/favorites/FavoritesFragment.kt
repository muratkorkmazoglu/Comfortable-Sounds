package com.murat.corfortablesounds.ui.main.favorites

import androidx.lifecycle.Observer
import com.murat.corfortablesounds.R
import com.murat.corfortablesounds.core.BaseFragment
import com.murat.corfortablesounds.databinding.FragmentFavoritesBinding


class FavoritesFragment : BaseFragment<FavoritesViewModel, FragmentFavoritesBinding>(FavoritesViewModel::class.java) {

    override fun initViewModel() {
        mBinding.viewModel = viewModel
    }

    override fun getLayoutRes(): Int = R.layout.fragment_favorites

    override fun init() {
        super.init()
        handleProgress()

    }


    private fun handleProgress() {
        if (viewModel.progressLiveData.hasActiveObservers())
            viewModel.progressLiveData.removeObservers(this)

        viewModel.progressLiveData.observe(
            viewLifecycleOwner,
            Observer<Boolean> {
                it?.let {
                    if (it)
                        showProgress()
                    else
                        hideProgress()
                }
            }
        )
    }

}
