package com.e.users.data.service

import com.e.users.data.pojos.PostsPojo
import com.e.users.data.pojos.UsersPojo
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Hussein on 4/10/2021
 */

interface ApiService {

    @GET("posts")
    fun getPosts(): Call<List<PostsPojo>>

    @GET("users")
    fun getUsers(): Call<List<UsersPojo>>
}