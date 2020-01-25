package com.murat.corfortablesounds.core

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.murat.corfortablesounds.R


object BindingAdapter {

  /*  @JvmStatic
    @BindingAdapter("app:setOnClickListener")
    fun setOnClickListener(view: ImageView, comics: ComicsEntity) {
        view.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable("comics", comics)
            view.findNavController().navigate(R.id.singleLikedFragment, bundle)
        }
    }*/


    @JvmStatic
    @BindingAdapter("app:visibility")
    fun setVisibility(view: View, isVisible: Int) {
        view.visibility = View.GONE
        if (isVisible > 0) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE
        }
    }

    @JvmStatic
    @BindingAdapter("app:setDrawableLink")
    fun setDrawableLink(view: ImageView, link: String?) {
        if (link.isNullOrEmpty())
            return

        Glide.with(view.context).load(link)
            .thumbnail(Glide.with(view.context).load(R.drawable.loading_gif))
            .fitCenter()
            .into(view);
    }

    @JvmStatic
    @BindingAdapter("app:setCategoryListFragment")
    fun setCategoryListFragment(view: CardView, name: String?) {
        view.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("categoryName", name)
            view.findNavController().navigate(R.id.categoryListFragment)
        }
    }

}