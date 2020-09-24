package com.devdd.framework.imagesearch_pagination.api

import com.devdd.framework.imagesearch_pagination.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * Created by @author Deepak Dawade on 9/25/2020 at 12:23 AM.
 * Copyright (c) 2020 deepakdawade.dd@gmail.com All rights reserved.
 **/
interface UnsplashApi {
    companion object{
        const val CLIENT_ID = BuildConfig.UNSPLASH_ACCESS_KEY
        const val BASE_URL = "https://api.unsplash.com/"
    }
    @Headers("Accept-Version: v1","Authorization: Client-ID $CLIENT_ID")
    @GET("search/photos")
    suspend fun searchPhoto(
        @Query("query") query:String,
        @Query("page") page:Int,
        @Query("per_page") perPage:Int,
    ):UnsplashResponse
}