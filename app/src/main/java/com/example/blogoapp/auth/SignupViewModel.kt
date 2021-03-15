package com.example.blogoapp.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.ConduitClient
import com.example.api.model.entity.UserCred
import com.example.api.model.requests.UserRequests
import com.example.api.model.response.UserResponse
import kotlinx.coroutines.launch

class SignupViewModel : ViewModel() {
    private val _userResponse=MutableLiveData<String>()
    val userResponse:LiveData<String> =_userResponse
    val api=ConduitClient().api
    fun addUser(userRequests: UserRequests){
        viewModelScope.launch {
            val user: UserResponse? =api.addUser(userRequests).body()
            _userResponse.postValue(user?.user?.username)
        }
    }
}