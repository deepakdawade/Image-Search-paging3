package com.devdd.framework.imagesearch_pagination.util.extenstion

import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

/**
 * Created by @author Deepak Dawade on 9/26/2020 at 12:07 AM.
 * Copyright (c) 2020 deepakdawade.dd@gmail.com All rights reserved.
 **/

internal fun <T> AppCompatImageView.setImage(url: T) {
    try {
        Glide.with(this.context)
            .load(url)
            .fitCenter()
            .into(this)
    } catch (e: IllegalArgumentException) {
        Log.e("BindingAdapter", "setImage: ",e )
    }
}

@BindingAdapter("app:setRoundImage", "app:placeholderForRoundImage")
fun AppCompatImageView.setRoundImage(url: String?, placeholder: Drawable) {
    Glide.with(this.context)
        .load(url)
        .placeholder(placeholder)
        .apply(RequestOptions().circleCrop())
        .into(this)
}

@BindingAdapter("loadImage", "errorPlaceholder", requireAll = false)
fun AppCompatImageView.glideLoadImage(
    imagePath: String?,
    errorPlaceHolder: Drawable? = null
) {
    val glideRequest = Glide.with(this)
    if (imagePath != null) {
        val loader =
            glideRequest.load(imagePath).centerCrop().transition(DrawableTransitionOptions.withCrossFade())
        errorPlaceHolder?.let { drawable ->
            loader.placeholder(drawable)
        }
        loader.into(this)
    } else {
        if (errorPlaceHolder != null) {
            val loader = glideRequest.load(errorPlaceHolder)
                .transition(DrawableTransitionOptions.withCrossFade(100))
            loader.into(this)
        }
    }
}
