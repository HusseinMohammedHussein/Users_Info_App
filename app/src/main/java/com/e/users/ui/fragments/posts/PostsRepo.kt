package com.e.users.ui.fragments.posts

import com.e.users.data.pojos.PostsPojo
import com.e.users.data.remote.module.ApiModule
import com.e.users.data.service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Hussein on 4/10/2021
 */

@Module
@InstallIn(ActivityComponent::class)
class PostsRepo @Inject constructor() {
    private lateinit var apiService: ApiService

    @Provides
    @Singleton
    fun getPosts(): Call<List<PostsPojo>> {
        apiService = ApiModule.getRetrofit()
            .create(ApiService::class.java)
        return apiService.getPosts()
    }
}