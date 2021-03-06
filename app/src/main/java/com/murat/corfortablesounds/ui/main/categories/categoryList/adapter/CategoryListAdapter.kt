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
import com.murat.corfortablesounds.db.entities.SoundsEntitiy
import com.murat.corfortablesounds.ui.main.categories.categoryList.CategoryListViewModel
import org.jetbrains.anko.sdk27.coroutines.onClick

class CategoryListAdapter(private val callBack: (SoundsEntitiy, Int,Boolean) -> Unit) :
    BaseAdapter<SoundsEntitiy>(categoryListDiffCallback) {

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
                if (mBinding.favImg.isSelected) {
                    mBinding.favImg.isSelected = false
                    mBinding.favImg.setImageResource(R.drawable.ic_favorite_border)
                    callBack.invoke(it, mBinding.viewModel!!.position, false)
                } else {
                    mBinding.favImg.isSelected = true
                    mBinding.favImg.setImageResource(R.drawable.ic_favorite)
                    callBack.invoke(it, mBinding.viewModel!!.position, true)
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

val categoryListDiffCallback = object : DiffUtil.ItemCallback<SoundsEntitiy>() {
    override fun areContentsTheSame(oldItem: SoundsEntitiy, newItem: SoundsEntitiy): Boolean =
        oldItem == newItem

    override fun areItemsTheSame(oldItem: SoundsEntitiy, newItem: SoundsEntitiy): Boolean =
        oldItem == newItem
}