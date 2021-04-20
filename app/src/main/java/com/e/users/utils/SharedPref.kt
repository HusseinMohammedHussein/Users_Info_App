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


//    @SuppressLint("CommitPrefEdits")
//    private inline fun SharedPreferences.editor(operation: (SharedPreferences.Editor) -> Unit) {
//        val editor = this.edit()
//        operation(editor)
//        editor.apply()
//    }

//    private operator fun SharedPreferences.set(key: String, value: Any?) {
//        when (value) {
//            is String? -> edit { it.putString(key, value) }
//            is Int -> edit { it.putInt(key, value) }
//            is Boolean -> edit { it.putBoolean(key, value) }
//            is Float -> edit { it.putFloat(key, value) }
//            is Long -> edit { it.putLong(key, value) }
//            else -> throw UnsupportedOperationException("Not yet implemented")
//        }
//    }

//    private inline operator fun <reified T : Any> SharedPreferences.get(
//        key: String,
//        defaultValue: T? = null
//    ): T? {
//        return when (T::class) {
//            String::class -> getString(key, defaultValue as? String) as T?
//            Int::class -> getInt(key, defaultValue as? Int ?: -1) as T?
//            Boolean::class -> getBoolean(key, defaultValue as? Boolean ?: false) as T?
//            Float::class -> getFloat(key, defaultValue as? Float ?: -1f) as T?
//            Long::class -> getLong(key, defaultValue as? Long ?: -1) as T?
//            else -> throw UnsupportedOperationException("Not yet implemented")
//        }
//    }
}