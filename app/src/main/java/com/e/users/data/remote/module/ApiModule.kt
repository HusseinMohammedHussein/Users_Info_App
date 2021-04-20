package com.e.users.data.remote.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Hussein on 4/13/2021
 */

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    private var okHttpClient: OkHttpClient = OkHttpClient()
    private const val BASE_URL: String = "https://jsonplaceholder.typicode.com/"
    private const val REQUEST_TIMEOUT: Int = 60

    @Provides
    @Singleton
    fun getRetrofit(): Retrofit {
        if (okHttpClient.equals(null))
            initOkHttp()

        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }

    private fun initOkHttp() {
        val httpLoggingInterceptor =
            HttpLoggingInterceptor { message -> Timber.i("PostsData:::%s", message) }
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .build()
    }
}