package com.e.users

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
}