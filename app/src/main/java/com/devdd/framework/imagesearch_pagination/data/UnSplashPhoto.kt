package com.devdd.framework.imagesearch_pagination.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by @author Deepak Dawade on 9/24/2020 at 11:48 PM.
 * Copyright (c) 2020 deepakdawade.dd@gmail.com All rights reserved.
 **/
@Parcelize
data class UnSplashPhoto(
    val id: String,
    val description: String?,
    val urls: UnsplashPhotoUrls?,
    val user: UnsplashPhotoUser
) : Parcelable {
    @Parcelize
    data class UnsplashPhotoUrls(
        val raw: String,
        val full: String,
        val regular: String,
        val small: String,
        val thumb: String,
    ) : Parcelable

    @Parcelize
    data class UnsplashPhotoUser(
        val name: String,
        val username: String
    ) : Parcelable {
        val attributionUrl get() = "https://unsplash.com/$username?utm-source=ImageSearch&utm_medium_referral"
        fun createdBY() = "Photo by $name on Unsplash"
    }
}
