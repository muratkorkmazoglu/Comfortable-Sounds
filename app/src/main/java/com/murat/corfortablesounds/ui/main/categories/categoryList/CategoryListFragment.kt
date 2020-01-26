package com.murat.corfortablesounds.ui.main.categories.categoryList

import androidx.lifecycle.Observer
import com.murat.corfortablesounds.R
import com.murat.corfortablesounds.core.BaseFragment
import com.murat.corfortablesounds.core.Resource
import com.murat.corfortablesounds.databinding.FragmentCategoryListBinding
import com.murat.corfortablesounds.db.entities.SoundsEntitiy
import com.murat.corfortablesounds.ui.main.categories.categoryList.adapter.CategoryListAdapter

class CategoryListFragment : BaseFragment<CategoryListViewModel, FragmentCategoryListBinding>(
    CategoryListViewModel::class.java
) {

    override fun initViewModel() {
        mBinding.viewModel = viewModel
    }

    override fun getLayoutRes(): Int = R.layout.fragment_category_list

    override fun init() {
        super.init()
        handleProgress()
        handleAdapter()
        getJsonData()

    }

    private fun getJsonData() {
        viewModel.getSoundList()
        viewModel.getJsonData.observe(viewLifecycleOwner, Observer<Resource<List<SoundsEntitiy>>> {
            it.let {
                (mBinding.itemsRecycler.adapter as CategoryListAdapter).submitList(it?.data)
            }
        })
    }

    private fun handleAdapter() {
        val categoryListAdapter =
            CategoryListAdapter { item, position, selected ->
                if (selected) {
                    viewModel.saveFavorite(item)
                } else {
                    viewModel.deleteFavorite(item)
                }
            }
        mBinding.itemsRecycler.adapter = categoryListAdapter
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