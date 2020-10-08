package com.devdd.framework.imagesearch_pagination.ui.gallerydetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.devdd.framework.imagesearch_pagination.R
import com.devdd.framework.imagesearch_pagination.data.UnSplashPhoto
import com.devdd.framework.imagesearch_pagination.databinding.FragmentGalleryDetailsBinding
import com.devdd.framework.imagesearch_pagination.util.extenstion.bindingWithLifecycleOwner
import com.devdd.framework.imagesearch_pagination.util.extenstion.loadImage
import com.devdd.framework.imagesearch_pagination.util.extenstion.toDataClass

/**
 * Created by @author Deepak Dawade on 10/9/2020 at 12:33 AM.
 * Copyright (c) 2020 deepakdawade.dd@gmail.com All rights reserved.
 **/
class GalleryDetailsFragment : Fragment() {
    private var _binding: FragmentGalleryDetailsBinding? = null
    private val binding: FragmentGalleryDetailsBinding get() = requireNotNull(_binding)
    private val viewModel by viewModels<GalleryDetailsViewModel>()
    private val args by navArgs<GalleryDetailsFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return bindingWithLifecycleOwner<FragmentGalleryDetailsBinding>(
            inflater,
            R.layout.fragment_gallery_details,
            container
        ).also {
            _binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val photo = args.photo.toDataClass<UnSplashPhoto>()
        binding.galleryDetailsFragmentImage.loadImage(photo.urls?.regular)
        binding.galleryDetailsFragmentDescription.text = photo.description
        binding.galleryDetailsFragmentCreator.text = photo.user.name
        binding.galleryDetailsFragmentDescription.isVisible = !photo.description.isNullOrEmpty()
        binding.galleryDetailsFragmentProgressBar.isVisible = false
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}