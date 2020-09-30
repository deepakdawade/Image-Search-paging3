package com.devdd.framework.imagesearch_pagination.util.extenstion

import android.graphics.drawable.Drawable
import android.util.Log
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

/**
 * Created by @author Deepak Dawade on 9/26/2020 at 12:07 AM.
 * Copyright (c) 2020 deepakdawade.dd@gmail.com All rights reserved.
 **/

internal fun <T> AppCompatImageView.loadImage(url: T) {
    try {
        Glide.with(this.context)
            .load(url)
            .fitCenter()
            .into(this)
    } catch (e: IllegalArgumentException) {
        Log.e("BindingAdapter", "loadImage: ", e)
    }
}

@BindingAdapter("app:setRoundImage", "app:placeholderForRoundImage")
fun AppCompatImageView.roundImage(url: String?, placeholder: Drawable) {
    Glide.with(this.context)
        .load(url)
        .placeholder(placeholder)
        .apply(RequestOptions().circleCrop())
        .into(this)
}

@BindingAdapter("loadImage", "errorPlaceholder", requireAll = false)
fun AppCompatImageView.loadImage(
    imagePath: String?,
    errorPlaceHolder: Drawable? = null
) {
    val glideRequest = Glide.with(this)
    imagePath?.let { url ->
        val loader =
            glideRequest.load(url).centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade(100))
        errorPlaceHolder?.let { drawable ->
            loader.placeholder(drawable)
        }
        loader.into(this)
    } ?: run {
        if (errorPlaceHolder != null) {
            val loader = glideRequest.load(errorPlaceHolder)
                .transition(DrawableTransitionOptions.withCrossFade(100))
            loader.into(this)
        }
    }
}
