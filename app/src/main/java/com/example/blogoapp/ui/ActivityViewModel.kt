package com.example.blogoapp.ui

import android.content.Context
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.model.response.UserResponse
import com.example.blogoapp.data.UserAuthRepo
import com.example.blogoapp.data.UserSharedpreferences
import kotlinx.coroutines.launch

class ActivityViewModel:ViewModel() {
    private val _userRes= MutableLiveData<UserResponse>()
    val userRes: LiveData<UserResponse> =_userRes

    fun logout(context: Context):Boolean{
        UserSharedpreferences(context).sharedPreferences.edit {
            remove("PREFS_KEY_TOKEN")
        }.let {
            return true
        }
    }

    fun getCurrentUserInfo(){
        viewModelScope.launch {
            UserAuthRepo.getCurrentUser().let {
                _userRes.postValue(it.body())
            }
        }
    }
}