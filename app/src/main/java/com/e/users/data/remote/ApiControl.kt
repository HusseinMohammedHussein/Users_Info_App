package com.e.users.data.remote

import android.app.Application
import com.e.users.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.DebugTree

/**
 * Created by Hussein on 4/10/2021
 */
@HiltAndroidApp
class ApiControl : Application() {
    var debugTree = DebugTree()

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(debugTree)
        }
    }

//    companion object {

//        private lateinit var retrofit: Retrofit
//        private var okHttpClient: OkHttpClient = OkHttpClient()
//        private val BASE_URL: String = "https://jsonplaceholder.typicode.com/"
//        private val REQUEST_TIMEOUT: Int = 20

//        fun getRetrofit(): Retrofit {
//            if (okHttpClient.equals(null))
//                initOkHttp()
//
//            retrofit = Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(okHttpClient)
//                .build()
//            return retrofit
//        }

//        private fun initOkHttp() {
//            val httpLoggingInterceptor = HttpLoggingInterceptor { message -> Timber.i("PostsData:::%s", message) }
//            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
//            okHttpClient = OkHttpClient.Builder()
//                .addInterceptor(httpLoggingInterceptor)
//                .connectTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
//                .readTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
//                .writeTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
//                .build()
//        }
}
//}