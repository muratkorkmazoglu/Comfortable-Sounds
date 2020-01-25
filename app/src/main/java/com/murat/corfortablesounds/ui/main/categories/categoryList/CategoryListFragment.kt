package com.murat.corfortablesounds.ui.main.categories.categoryList

import android.widget.Toast
import androidx.lifecycle.Observer
import com.murat.corfortablesounds.R
import com.murat.corfortablesounds.core.BaseFragment
import com.murat.corfortablesounds.core.Resource
import com.murat.corfortablesounds.databinding.FragmentCategoryListBinding
import com.murat.corfortablesounds.service.response.JsonData

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
        getJsonData()
        handleAdapter()

    }

    private fun getJsonData() {
        viewModel.getSoundList()
        viewModel.getJsonData.observe(viewLifecycleOwner, Observer<Resource<JsonData>> {
            it.let {
                Toast.makeText(context, it.data?.singerName, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun handleAdapter() {

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