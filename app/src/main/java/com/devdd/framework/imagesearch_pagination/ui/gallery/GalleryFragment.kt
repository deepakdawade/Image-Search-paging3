package com.devdd.framework.imagesearch_pagination.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.devdd.framework.imagesearch_pagination.R
import com.devdd.framework.imagesearch_pagination.databinding.FragmentGalleryBinding
import com.devdd.framework.imagesearch_pagination.util.extenstion.bindingWithLifecycleOwner

/**
 * Created by @author Deepak Dawade on 9/25/2020 at 12:00 AM.
 * Copyright (c) 2020 deepakdawade.dd@gmail.com All rights reserved.
 **/
class GalleryFragment :Fragment(){
    lateinit var binding: FragmentGalleryBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return bindingWithLifecycleOwner<FragmentGalleryBinding>(inflater, R.layout.fragment_gallery,container).also {
            binding = it
        }.root
    }
}
