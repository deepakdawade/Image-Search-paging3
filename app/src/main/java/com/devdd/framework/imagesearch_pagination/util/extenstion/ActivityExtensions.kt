package com.devdd.framework.imagesearch_pagination.util.extenstion

import android.content.Intent
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Created by @author Deepak Dawade on 9/26/2020 at 12:35 AM.
 * Copyright (c) 2020 deepakdawade.dd@gmail.com All rights reserved.
 **/
inline fun <reified T : ViewDataBinding> AppCompatActivity.bindingWithLifecycleOwner(
    @LayoutRes layoutId: Int,
    bind: (T.() -> Unit) = {}
): T {

    val binding: T = DataBindingUtil.setContentView(this, layoutId)
    binding.lifecycleOwner = this
    binding.bind()
    return binding
}
inline fun <reified T : AppCompatActivity> AppCompatActivity.finishAndLaunch(
    noinline with: (Intent.() -> Intent) = { Intent() }
) {
    with(Intent()).setClass(this, T::class.java).also {
        startActivity(it)
    }
    finish()
}

