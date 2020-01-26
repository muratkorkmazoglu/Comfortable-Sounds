package com.murat.corfortablesounds.ui.main.favorites.adapter
import android.app.Activity
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.murat.corfortablesounds.R
import com.murat.corfortablesounds.core.BaseAdapter
import com.murat.corfortablesounds.databinding.FavoritesItemLayoutBinding
import com.murat.corfortablesounds.db.entities.SoundsEntitiy
import com.murat.corfortablesounds.ui.main.favorites.FavoritesViewModel
import org.jetbrains.anko.sdk27.coroutines.onClick

class FavoritesAdapter(private val callBack: (SoundsEntitiy, Int) -> Unit) :
    BaseAdapter<SoundsEntitiy>(categoryListDiffCallback) {

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        lateinit var mBinding: ViewDataBinding
        val viewModel = FavoritesViewModel((parent.context as Activity).application)
        mBinding = DataBindingUtil.inflate<FavoritesItemLayoutBinding>(
            LayoutInflater.from(parent.context),
            R.layout.favorites_item_layout,
            parent,
            false
        )
        mBinding.viewModel = viewModel

        mBinding.favImg.onClick {

        }

        return mBinding
    }

    override fun bind(binding: ViewDataBinding, position: Int) {
        (binding as FavoritesItemLayoutBinding).viewModel?.setFavoritesItem(
            getItem(position),
            position
        )
        binding.favImg.setImageResource(R.drawable.ic_favorite)
        binding.favImg.isSelected = true
        binding.executePendingBindings()
    }
}

val categoryListDiffCallback = object : DiffUtil.ItemCallback<SoundsEntitiy>() {
    override fun areContentsTheSame(oldItem: SoundsEntitiy, newItem: SoundsEntitiy): Boolean =
        oldItem == newItem

    override fun areItemsTheSame(oldItem: SoundsEntitiy, newItem: SoundsEntitiy): Boolean =
        oldItem == newItem
}