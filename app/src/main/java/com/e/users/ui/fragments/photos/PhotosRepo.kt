package com.e.users.ui.fragments.photos

import com.e.users.data.pojos.PhotosPojo
import com.e.users.data.remote.module.ApiModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton

// Created by Hussein_Mohammad on 4/25/2021.

@Module
@InstallIn(SingletonComponent::class)
class PhotosRepo @Inject constructor() {

    @Provides
    @Singleton
    fun getAlbumPhotos(albumId: Int): Call<List<PhotosPojo>> {
        return ApiModule.createService().getAlbumPhotos(albumId)
    }
}