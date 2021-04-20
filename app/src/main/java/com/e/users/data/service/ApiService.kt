package com.e.users.data.service

import com.e.users.data.pojos.PostCommentsPojo
import com.e.users.data.pojos.PostsPojo
import com.e.users.data.pojos.TodoPojo
import com.e.users.data.pojos.UsersPojo
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
}