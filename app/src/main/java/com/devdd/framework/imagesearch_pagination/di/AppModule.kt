package com.devdd.framework.imagesearch_pagination.di

import com.devdd.framework.imagesearch_pagination.api.UnsplashApi
import com.devdd.framework.imagesearch_pagination.data.repository.UnsplashRepository
import com.devdd.framework.imagesearch_pagination.data.repository.UnsplashRepositoryImpl
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by @author Deepak Dawade on 9/25/2020 at 10:02 PM.
 * Copyright (c) 2020 deepakdawade.dd@gmail.com All rights reserved.
 **/
@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder().baseUrl(UnsplashApi.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke()).build()

    @Singleton
    @Provides
    fun provideUnsplashApi(retrofit: Retrofit): UnsplashApi =
        retrofit.create(UnsplashApi::class.java)

    @Singleton
    @Provides
    fun provideUnsplashRepository(unsplashRepositoryImpl: UnsplashRepositoryImpl): UnsplashRepository =
        unsplashRepositoryImpl

}