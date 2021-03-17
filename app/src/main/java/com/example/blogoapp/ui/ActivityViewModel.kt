package com.example.blogoapp.ui

import android.content.Context
import androidx.core.content.edit
import androidx.lifecycle.ViewModel
import com.example.blogoapp.data.UserSharedpreferences

class ActivityViewModel:ViewModel() {
    fun logout(context: Context):Boolean{
        UserSharedpreferences(context).sharedPreferences.edit {
            remove("PREFS_KEY_TOKEN")
        }.let {
            return true
        }
    }
}