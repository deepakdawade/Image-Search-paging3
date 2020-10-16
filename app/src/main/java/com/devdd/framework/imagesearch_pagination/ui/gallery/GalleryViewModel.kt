package com.devdd.framework.imagesearch_pagination.ui.gallery

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.devdd.framework.imagesearch_pagination.data.UnSplashPhoto
import com.devdd.framework.imagesearch_pagination.data.repository.UnsplashRepository

/**
 * Created by @author Deepak Dawade on 9/25/2020 at 10:15 PM.
 * Copyright (c) 2020 deepakdawade.dd@gmail.com All rights reserved.
 **/
class GalleryViewModel @ViewModelInject constructor(
    private val repository: UnsplashRepository,
    @Assisted state: SavedStateHandle
) : ViewModel() {
    companion object {
        private const val DEFAULT_QUERY = "cats"
        private const val CURRENT_QUERY = "current_query"
    }

    private val currentQuery: MutableLiveData<String> = state.getLiveData(
        CURRENT_QUERY,
        DEFAULT_QUERY
    )
    val photos: LiveData<PagingData<UnSplashPhoto>> = currentQuery.switchMap {
        repository.getSearchResults(it).cachedIn(viewModelScope)
    }

    fun searchPhotos(query: String) {
        currentQuery.value = query
    }
}
