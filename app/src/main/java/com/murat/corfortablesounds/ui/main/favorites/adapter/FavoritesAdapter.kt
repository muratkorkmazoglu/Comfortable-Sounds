package com.murat.corfortablesounds.ui.main.favorites.adapter

import android.app.Activity
import android.graphics.Color
import android.graphics.PorterDuff
import android.media.MediaPlayer
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.murat.corfortablesounds.R
import com.murat.corfortablesounds.core.BaseAdapter
import com.murat.corfortablesounds.databinding.FavoritesItemLayoutBinding
import com.murat.corfortablesounds.db.entities.SoundsEntitiy
import com.murat.corfortablesounds.ui.main.favorites.FavoritesViewModel
import org.jetbrains.anko.sdk27.coroutines.onClick

class FavoritesAdapter(private val callBack: (SoundsEntitiy, Int, String, Boolean) -> Unit) :
    BaseAdapter<SoundsEntitiy>(categoryListDiffCallback) {
    var flag = true
    lateinit var mediaPlayer: MediaPlayer
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
            mBinding.viewModel?.favoritesItem?.get()?.let {
                callBack.invoke(it, mBinding.viewModel!!.position, "favImg", false)
            }
        }
        mBinding.playImg.onClick {
            if (flag) {
                mediaPlayer = MediaPlayer()
                mBinding.viewModel?.favoritesItem?.get()?.let {
                    mediaPlayer.setDataSource(it.mp3)
                    mediaPlayer.prepare()
                    mediaPlayer.setVolume(0.5f, 0.5f)
                    flag = false
                }
            }
            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()
                flag = true
                mBinding.playImg.setImageResource(R.drawable.ic_play_arrow_black_24dp)
            } else {
                mediaPlayer.setOnPreparedListener {
                    mediaPlayer.start()
                }
                mBinding.playImg.setImageResource(R.drawable.ic_pause_black_24dp)
            }
        }
        mBinding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                if (p2){
                    var volumeNum = p1 / 100.0f
                    mediaPlayer.setVolume(volumeNum,volumeNum)
                }

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })

        return mBinding
    }

    override fun bind(binding: ViewDataBinding, position: Int) {
        (binding as FavoritesItemLayoutBinding).viewModel?.setFavoritesItem(
            getItem(position),
            position
        )
        binding.favImg.setImageResource(R.drawable.ic_favorite)
        binding.favImg.isSelected = true
        binding.playImg.setImageResource(R.drawable.ic_play_arrow_black_24dp)
        binding.playImg.isSelected = true
        mediaPlayer = MediaPlayer()
        binding.executePendingBindings()
    }
}

val categoryListDiffCallback = object : DiffUtil.ItemCallback<SoundsEntitiy>() {
    override fun areContentsTheSame(oldItem: SoundsEntitiy, newItem: SoundsEntitiy): Boolean =
        oldItem == newItem

    override fun areItemsTheSame(oldItem: SoundsEntitiy, newItem: SoundsEntitiy): Boolean =
        oldItem == newItem
}