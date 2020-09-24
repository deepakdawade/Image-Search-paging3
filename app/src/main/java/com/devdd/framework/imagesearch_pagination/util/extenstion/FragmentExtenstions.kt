package com.devdd.framework.imagesearch_pagination.util.extenstion

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * Created by @author Deepak Dawade on 9/25/2020 at 12:01 AM.
 * Copyright (c) 2020 deepakdawade.dd@gmail.com All rights reserved.
 **/
inline fun <V : ViewDataBinding> Fragment.bindingWithLifecycleOwner(
    inflater: LayoutInflater,
    @LayoutRes layoutId: Int,
    container: ViewGroup?,
    bind: (V.() -> Unit) = {}
): V {

    val binding: V = DataBindingUtil.inflate(inflater, layoutId, container, false)
    binding.lifecycleOwner = viewLifecycleOwner
    binding.bind()
    return binding
}