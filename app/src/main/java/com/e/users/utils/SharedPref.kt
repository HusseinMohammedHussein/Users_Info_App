package com.e.users.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

@SuppressLint("CommitPrefEdits")
class SharedPref constructor(var context: Context) {
    private var preferences: SharedPreferences

    private val PREFS_NAME = "users_kotlin"
    private val MODE = Context.MODE_PRIVATE
    private var operation: SharedPreferences.Editor

    init {
        preferences = context.getSharedPreferences(PREFS_NAME, MODE)
        operation = preferences.edit()
    }

    fun setString(key: String, value: String?) {
        operation.apply {
            putString(key, value)
            apply()
        }
    }

    fun getString(key: String): String? {
        return preferences.getString(key, null)
    }

    fun setInt(key: String, value: Int) {
        operation.apply {
            putInt(key, value)
            apply()
        }
    }

    fun getInt(key: String): Int {
        return preferences.getInt(key, 0)
    }

    fun setBoolean(key: String, value: Boolean) {
        operation.putBoolean(key, value)
    }

    fun getBoolean(key: String): Boolean {
        return preferences.getBoolean(key, false)
    }
}