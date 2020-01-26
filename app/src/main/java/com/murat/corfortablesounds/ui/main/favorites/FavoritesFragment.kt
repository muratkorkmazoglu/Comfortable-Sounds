package com.murat.corfortablesounds.ui.main.favorites

import android.widget.Toast
import androidx.lifecycle.Observer
import com.murat.corfortablesounds.R
import com.murat.corfortablesounds.core.BaseFragment
import com.murat.corfortablesounds.databinding.FragmentFavoritesBinding
import com.murat.corfortablesounds.ui.main.categories.categoryList.adapter.CategoryListAdapter
import com.murat.corfortablesounds.ui.main.favorites.adapter.FavoritesAdapter

class FavoritesFragment :
    BaseFragment<FavoritesViewModel, FragmentFavoritesBinding>(FavoritesViewModel::class.java) {

    override fun initViewModel() {
        mBinding.viewModel = viewModel
    }

    override fun getLayoutRes(): Int = R.layout.fragment_favorites

    override fun init() {
        super.init()
        handleProgress()
        getFavorites()
        handleAdapter()

    }

    private fun handleAdapter() {
        val favoritesAdapter =
            FavoritesAdapter { item, position ->

                viewModel.removeFavorites(item)
            }
        mBinding.favoritesRecycler.adapter = favoritesAdapter
    }

    private fun getFavorites() {
        viewModel.getFavorites()
        viewModel.favoritesList.observe(viewLifecycleOwner, Observer {
            it.let {
                (mBinding.favoritesRecycler.adapter as FavoritesAdapter).submitList(it)
            }
        })
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
