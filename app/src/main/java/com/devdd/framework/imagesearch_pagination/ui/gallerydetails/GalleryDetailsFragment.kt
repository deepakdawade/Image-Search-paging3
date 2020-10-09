package com.devdd.framework.imagesearch_pagination.ui.gallerydetails

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
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
        binding.galleryDetailsFragmentImage.loadImage(photo.urls?.full,R.drawable.ic_launcher_foreground,object :RequestListener<Drawable>{
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                binding.galleryDetailsFragmentProgressBar.isVisible = false
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                binding.galleryDetailsFragmentDescription.isVisible = photo.description != null
                    binding.galleryDetailsFragmentCreator.isVisible = true
                binding.galleryDetailsFragmentProgressBar.isVisible = false
                return false
            }
        })
        binding.galleryDetailsFragmentDescription.text = photo.description
        val uri = Uri.parse(photo.user.attributionUrl)
        val intent = Intent(Intent.ACTION_VIEW,uri)
        binding.galleryDetailsFragmentCreator.apply {
            text = photo.user.createdBY()
            paint.isUnderlineText = true
            setOnClickListener {
                requireContext().startActivity(intent)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}