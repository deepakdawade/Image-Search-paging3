package com.devdd.framework.imagesearch_pagination.data

import com.devdd.framework.imagesearch_pagination.api.UnsplashApi
import androidx.paging.PagingSource
import retrofit2.HttpException
import java.io.IOException

/**
 * Created by @author Deepak Dawade on 9/25/2020 at 10:22 PM.
 * Copyright (c) 2020 deepakdawade.dd@gmail.com All rights reserved.
 **/
private const val UNSPLASH_STARTING_PAGE_INDEX = 1
class UnsplashPagingSource constructor(
    private val unsplashApi: UnsplashApi,
    private val query:String
) :PagingSource<Int,UnSplashPhoto>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnSplashPhoto> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX
        return try {
            val response = unsplashApi.searchPhoto(query,position,params.loadSize)
            val photos = response.results
            LoadResult.Page(
                data = photos,
                prevKey = if (position == UNSPLASH_STARTING_PAGE_INDEX) null else position + 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )
        }catch (ex:IOException){
            LoadResult.Error(ex)
        }catch (ex:HttpException){
            LoadResult.Error(ex)
        }
    }
}
