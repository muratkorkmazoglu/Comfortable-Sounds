package com.murat.corfortablesounds.ui.main.categories.categoryList.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.murat.corfortablesounds.R
import com.murat.corfortablesounds.core.BaseAdapter
import com.murat.corfortablesounds.databinding.ListItemLayoutBinding
import com.murat.corfortablesounds.service.response.JsonData
import com.murat.corfortablesounds.ui.main.categories.categoryList.CategoryListViewModel
import org.jetbrains.anko.sdk27.coroutines.onClick

class CategoryListAdapter(private val callBack: (JsonData, Int) -> Unit) :
    BaseAdapter<JsonData>(categoryListDiffCallback) {

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        lateinit var mBinding: ViewDataBinding
        val viewModel = CategoryListViewModel((parent.context as Activity).application)
        mBinding = DataBindingUtil.inflate<ListItemLayoutBinding>(
            LayoutInflater.from(parent.context),
            R.layout.list_item_layout,
            parent,
            false
        )
        mBinding.viewModel = viewModel

        mBinding.favImg.onClick {
            mBinding.viewModel?.categoryItem?.get()?.let {
                callBack.invoke(it, mBinding.viewModel!!.position)
                if (mBinding.favImg.isSelected) {
                    mBinding.favImg.isSelected=false
                    mBinding.favImg.setImageResource(R.drawable.ic_favorite_border)
                } else {
                    mBinding.favImg.isSelected=true
                    mBinding.favImg.setImageResource(R.drawable.ic_favorite)
                }
            }
        }


        return mBinding
    }

    override fun bind(binding: ViewDataBinding, position: Int) {
        (binding as ListItemLayoutBinding).viewModel?.setCategoryListItem(
            getItem(position),
            position
        )
        binding.favImg.setImageResource(R.drawable.ic_favorite_border)
        binding.executePendingBindings()
    }
}

val categoryListDiffCallback = object : DiffUtil.ItemCallback<JsonData>() {
    override fun areContentsTheSame(oldItem: JsonData, newItem: JsonData): Boolean =
        oldItem == newItem

    override fun areItemsTheSame(oldItem: JsonData, newItem: JsonData): Boolean =
        oldItem == newItem
}