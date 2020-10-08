package com.devdd.framework.imagesearch_pagination.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import com.devdd.framework.imagesearch_pagination.R
import com.devdd.framework.imagesearch_pagination.databinding.FragmentGalleryBinding
import com.devdd.framework.imagesearch_pagination.ui.gallery.adapter.UnsplashPhotoAdapter
import com.devdd.framework.imagesearch_pagination.ui.gallery.adapter.UnsplashPhotoLoadStateAdapter
import com.devdd.framework.imagesearch_pagination.util.extenstion.bindingWithLifecycleOwner
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by @author Deepak Dawade on 9/25/2020 at 12:00 AM.
 * Copyright (c) 2020 deepakdawade.dd@gmail.com All rights reserved.
 **/
@AndroidEntryPoint
class GalleryFragment : Fragment(), Toolbar.OnMenuItemClickListener {

    private var _binding: FragmentGalleryBinding? = null
    private val binding: FragmentGalleryBinding get() = requireNotNull(_binding)
    private val viewModel by viewModels<GalleryViewModel>()
    private val unsplashPhotoAdapter by lazy {
        UnsplashPhotoAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return bindingWithLifecycleOwner<FragmentGalleryBinding>(
            inflater,
            R.layout.fragment_gallery,
            container
        ).also {
            _binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObserver()
        setupListener()
    }

    private fun setupListener() {
        binding.galleryFragmentToolbar.setOnMenuItemClickListener(this)
        unsplashPhotoAdapter.addLoadStateListener { loadState ->
            binding.apply {
                galleryFragmentProgressBar.isVisible = loadState.source.refresh is LoadState.Loading
                galleryFragmentRecyclerView.isVisible =
                    loadState.source.refresh is LoadState.NotLoading
                galleryFragmentRetryButton.isVisible = loadState.source.refresh is LoadState.Error
                galleryFragmentResultText.isVisible = loadState.source.refresh is LoadState.Error

                //For empty view only
                if (loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    unsplashPhotoAdapter.itemCount < 1
                ) {
                    galleryFragmentRecyclerView.isVisible = false
                    galleryFragmentEmptyResultText.isVisible = true
                } else galleryFragmentEmptyResultText.isVisible = false
            }
        }
        binding.galleryFragmentRetryButton.setOnClickListener {
            unsplashPhotoAdapter.retry()
        }
    }

    private fun setupObserver() {
        viewModel.photos.observe(viewLifecycleOwner) {
            unsplashPhotoAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    private fun setupRecyclerView() {
        binding.galleryFragmentRecyclerView.apply {
            setHasFixedSize(true)
            adapter = unsplashPhotoAdapter.withLoadStateHeaderAndFooter(
                header = UnsplashPhotoLoadStateAdapter {
                    unsplashPhotoAdapter.retry()
                },
                footer = UnsplashPhotoLoadStateAdapter {
                    unsplashPhotoAdapter.retry()
                }
            )
        }
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.menu_gallery_search -> {
                val searchView = item.actionView as? SearchView
                searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        query?.let {
                            binding.galleryFragmentRecyclerView.scrollToPosition(0)
                            viewModel.searchPhotos(query)
                            searchView.clearFocus()
                        }
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean = true
                })
                true
            }
            else -> false

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
