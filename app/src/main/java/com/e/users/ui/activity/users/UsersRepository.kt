package com.e.users.ui.activity.users

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

/**
 * Created by Hussein on 4/15/2021
 */

@Module
@InstallIn(ActivityComponent::class)
class UsersRepository @Inject constructor() {
        private lateinit var usersService: ApiService

    @Provides
    @Singleton
    fun getUsers(): Call<List<UsersPojo>> {
        usersService = ApiModule.getRetrofit().create(ApiService::class.java)
        return usersService.getUsers()
    }

}