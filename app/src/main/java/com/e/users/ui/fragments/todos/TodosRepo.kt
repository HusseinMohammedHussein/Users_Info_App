package com.e.users.ui.fragments.todos

import com.e.users.data.pojos.TodoPojo
import com.e.users.data.remote.module.ApiModule
import com.e.users.data.service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class TodosRepo @Inject constructor(){

    @Provides
    @Singleton
    fun getTodos(userId: Int): Call<List<TodoPojo>> =
        ApiModule.getRetrofit().create(ApiService::class.java).getTodos(userId)
}