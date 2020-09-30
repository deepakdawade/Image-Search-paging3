package com.devdd.framework.imagesearch_pagination.ui.gallery.adapter

import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devdd.framework.imagesearch_pagination.R
import com.devdd.framework.imagesearch_pagination.databinding.ItemViewUnsplashPhotoFooterBinding
import com.devdd.framework.imagesearch_pagination.util.extenstion.bindWithLayout

/**
 * Created by @author Deepak Dawade on 9/30/2020 at 11:46 PM.
 * Copyright (c) 2020 deepakdawade.dd@gmail.com All rights reserved.
 **/
class UnsplashLoadStateAdapter(private val retry:()->Unit) :
    LoadStateAdapter<UnsplashLoadStateAdapter.UnsplashLoadStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): UnsplashLoadStateViewHolder = UnsplashLoadStateViewHolder(
        bindWithLayout(
            R.layout.item_view_unsplash_photo_footer,
            parent
        )
    )

    override fun onBindViewHolder(holder: UnsplashLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }


    inner class UnsplashLoadStateViewHolder(private val binding: ItemViewUnsplashPhotoFooterBinding) :
        RecyclerView.ViewHolder(binding.root){
        init {
            binding.unsplashPhotoFooterItemViewRetryButton.setOnClickListener {
                retry.invoke()
            }
        }
        fun bind(loadState: LoadState){
             binding.apply {
                 unsplashPhotoFooterItemViewProgressBar.isVisible = loadState is LoadState.Loading
                 unsplashPhotoFooterItemViewRetryButton.isVisible = loadState is LoadState.NotLoading
                 unsplashPhotoFooterItemViewProgressBar.isVisible = loadState is LoadState.NotLoading
                 executePendingBindings()
             }
        }
    }
}