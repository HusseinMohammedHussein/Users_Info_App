package com.e.users

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.DebugTree

/**
 * Created by Hussein on 4/10/2021
 */

@HiltAndroidApp
class UsersApplication : Application() {
    var debugTree = DebugTree()

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(debugTree)
        }
    }
}