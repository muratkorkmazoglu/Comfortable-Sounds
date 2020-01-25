package com.murat.corfortablesounds.ui.main.categories

import androidx.lifecycle.Observer
import com.murat.corfortablesounds.R
import com.murat.corfortablesounds.core.BaseFragment
import com.murat.corfortablesounds.databinding.FragmentCategoriesBinding


class CategoriesFragment :
    BaseFragment<CategoriesViewModel, FragmentCategoriesBinding>(CategoriesViewModel::class.java) {

    override fun initViewModel() {
        mBinding.viewModel = viewModel
    }

    override fun getLayoutRes(): Int = R.layout.fragment_categories

    override fun init() {
        super.init()
    }


}
