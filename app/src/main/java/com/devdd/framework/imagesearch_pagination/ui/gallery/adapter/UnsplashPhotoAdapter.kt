package com.devdd.framework.imagesearch_pagination.ui.gallery.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.devdd.framework.imagesearch_pagination.R
import com.devdd.framework.imagesearch_pagination.data.UnSplashPhoto
import com.devdd.framework.imagesearch_pagination.databinding.ItemViewUnsplashPhotoBinding
import com.devdd.framework.imagesearch_pagination.util.extenstion.bindWithLayout

class UnsplashPhotoAdapter :
    PagingDataAdapter<UnSplashPhoto, UnsplashPhotoAdapter.UnsplashViewHolder>(DiffUtilCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            UnsplashViewHolder =
        UnsplashViewHolder(bindWithLayout(R.layout.item_view_unsplash_photo, parent))

    override fun onBindViewHolder(holder: UnsplashViewHolder, position: Int): Unit =
        getItem(position)?.let {
            holder.bind(it)
        } ?: Unit

    inner class UnsplashViewHolder(private val binding: ItemViewUnsplashPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UnSplashPhoto) {
            binding.unsplashPhoto = item
            binding.executePendingBindings()
        }
    }
}

object DiffUtilCallback : DiffUtil.ItemCallback<UnSplashPhoto>() {
    override fun areItemsTheSame(oldItem: UnSplashPhoto, newItem: UnSplashPhoto): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UnSplashPhoto, newItem: UnSplashPhoto): Boolean {
        return oldItem == newItem
    }
}