package com.devdd.framework.imagesearch_pagination.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.devdd.framework.imagesearch_pagination.api.UnsplashApi
import com.devdd.framework.imagesearch_pagination.data.UnSplashPhoto
import com.devdd.framework.imagesearch_pagination.data.UnsplashPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by @author Deepak Dawade on 9/25/2020 at 10:11 PM.
 * Copyright (c) 2020 deepakdawade.dd@gmail.com All rights reserved.
 **/

interface UnsplashRepository{
    fun getSearchResults(query: String):LiveData<PagingData<UnSplashPhoto>>
}

class UnsplashRepositoryImpl @Inject constructor(
    private val unsplashApi: UnsplashApi
) : UnsplashRepository{
    override fun getSearchResults(query:String): LiveData<PagingData<UnSplashPhoto>> = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = 100,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {UnsplashPagingSource(unsplashApi,query)}
    ).liveData
}