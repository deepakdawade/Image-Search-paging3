package com.devdd.framework.imagesearch_pagination.util.extenstion

import android.util.Base64
import com.google.gson.Gson
import java.io.ByteArrayOutputStream
import java.nio.charset.StandardCharsets
import java.util.zip.GZIPOutputStream

/**
 * Created by @author Deepak Dawade on 9/26/2020 at 12:30 AM.
 * Copyright (c) 2020 deepakdawade.dd@gmail.com All rights reserved.
 **/

fun <T> T.toJsonString(): String {
    return Gson().toJson(this)
}

inline fun <reified T> String.toDataClass(): T {
    return Gson().fromJson(this, T::class.java)
}

fun String.compress(): String? {
    val encodedByteArray = gzip() ?: return null
    return Base64.encodeToString(encodedByteArray, Base64.DEFAULT)
}

fun String.gzip(): ByteArray? {
    val bos = ByteArrayOutputStream()
    GZIPOutputStream(bos).bufferedWriter(StandardCharsets.UTF_8).use { it.write(this) }
    return bos.toByteArray()
}
