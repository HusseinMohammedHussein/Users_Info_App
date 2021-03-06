package com.e.users.data.service

import com.e.users.data.pojos.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Hussein on 4/10/2021
 */

interface ApiService {

    @GET("posts")
    fun getPosts(): Call<List<PostsPojo>>

    @GET("users")
    fun getUsers(): Call<List<UsersPojo>>

    @GET("users/{id}/posts")
    fun getUserPosts(@Path("id") userId: Int): Call<List<PostsPojo>>

    @GET("posts/{id}/comments")
    fun getPostComments(@Path("id") postId: Int): Call<List<PostCommentsPojo>>

    @GET("user/{id}/todos")
    fun getTodos(@Path("id") userId: Int): Call<List<TodoPojo>>

    @GET("users/{id}")
    fun getUserInfo(@Path("id") userId: Int): Call<UsersPojo>

    @GET("users/{id}/albums")
    fun getUserAlbums(@Path("id") userId: Int): Call<List<AlbumsPojo>>

    @GET("albums/{id}/photos")
    fun getAlbumPhotos(@Path("id") albumId: Int): Call<List<PhotosPojo>>
}