package com.e.users.ui.fragments.postDetails

import com.e.users.data.pojos.PostCommentsPojo
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
class PostCommentsRepo @Inject constructor() {

    @Provides
    @Singleton
    fun getPostComments(postId: Int): Call<List<PostCommentsPojo>> {
        return ApiModule.getRetrofit().create(ApiService::class.java).getPostComments(postId)
    }
}