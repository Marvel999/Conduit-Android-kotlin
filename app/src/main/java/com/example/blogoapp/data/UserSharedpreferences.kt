package com.example.blogoapp.data

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class UserSharedpreferences(context: Context) {
    val PREFERENCE_NAME = "USER_DETAIL_SHAREDPREFERANCE"
    val PREFS_KEY_TOKEN = "PREFS_KEY_TOKEN"
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
    var token: String? = null
        get() = sharedPreferences.getString(PREFS_KEY_TOKEN, null)
        set(value) {
            field = value
            sharedPreferences.edit {
                putString(PREFS_KEY_TOKEN, field)
            }
        }

}