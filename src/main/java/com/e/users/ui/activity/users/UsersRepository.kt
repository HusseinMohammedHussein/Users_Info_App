package com.e.users.ui.activity.users

import com.e.users.data.pojos.UsersPojo
import com.e.users.data.remote.module.ApiModule
import com.e.users.data.service.ApiService
import retrofit2.Call

/**
 * Created by Hussein on 4/15/2021
 */


class UsersRepository {

    fun getUsers(): Call<List<UsersPojo>> {
        return ApiModule.getRetrofit().create(ApiService::class.java).getUsers()
    }

}