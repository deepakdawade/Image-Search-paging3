package com.devdd.framework.imagesearch_pagination.ui.gallery

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
    private val repository: UnsplashRepository
):ViewModel() {
    companion object{
        private const val DEFAULT_QUERY = "cats"
    }
    private val currentQuery:MutableLiveData<String> = MutableLiveData(DEFAULT_QUERY)
    val photos: LiveData<PagingData<UnSplashPhoto>> = currentQuery.switchMap {
        repository.getSearchResults(it).cachedIn(viewModelScope)
    }
    fun searchPhotos(query:String){
        currentQuery.value = query
    }
}
