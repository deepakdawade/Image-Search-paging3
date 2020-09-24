package com.devdd.framework.imagesearch_pagination.api

import com.devdd.framework.imagesearch_pagination.data.UnSplashPhoto

/**
 * Created by @author Deepak Dawade on 9/25/2020 at 12:22 AM.
 * Copyright (c) 2020 deepakdawade.dd@gmail.com All rights reserved.
 **/
data class UnsplashResponse(
    val results: List<UnSplashPhoto>
)
