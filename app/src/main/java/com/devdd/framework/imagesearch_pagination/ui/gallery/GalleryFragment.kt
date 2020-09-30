package com.devdd.framework.imagesearch_pagination.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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
class GalleryFragment : Fragment() {

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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
