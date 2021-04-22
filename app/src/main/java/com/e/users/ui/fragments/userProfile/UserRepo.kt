package com.e.users.ui.fragments.userProfile

import com.e.users.data.pojos.PostsPojo
import com.e.users.data.pojos.UsersPojo
import com.e.users.data.remote.module.ApiModule
import com.e.users.data.service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
class UserRepo @Inject constructor() {

    @Provides
    @Singleton
    fun getUserPosts(userId: Int): Call<List<PostsPojo>> {
        return ApiModule.getRetrofit().create(ApiService::class.java).getUserPosts(userId)
    }

    @Provides
    @Singleton
    fun getUserInfo(userId: Int): Call<UsersPojo> {
        return ApiModule.getRetrofit().create(ApiService::class.java).getUserInfo(userId)
    }
}