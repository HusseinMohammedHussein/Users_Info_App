package com.e.users.ui.fragments.photos

import com.e.users.data.pojos.PhotosPojo
import com.e.users.data.remote.module.ApiModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Call
import javax.inject.Inject

// Created by Hussein_Mohammad on 4/25/2021.

@Module
@InstallIn(SingletonComponent::class)
class PhotosRepo @Inject constructor() {

    fun getAlbumPhotos(albumId: Int): Call<List<PhotosPojo>> {
        return ApiModule.createService().getAlbumPhotos(albumId)
    }
}